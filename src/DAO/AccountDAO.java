package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.User;
import Model.Account;

public class AccountDAO {
    //ket noi mysql

    //1.Lay taikhoan theo user id
    public Account getAccountByUserId(int UserId){
        String sql="SELECT*FROM Account WHERE UserId=?";//tao cau lenh sql

        try(Connection  conn=DBConnection.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            //cbi gui cau lenh
            ps.setInt(1,UserId);//gan gtri cho ?
            //chay sql va nhan kq
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){//neu co kq(neu thay account
                    User user=new User(
                            rs.getInt("UserId"),
                            null,
                            null,
                            null,
                            null
                    );
                    //tao Object User: chi lay user k can lay username va pass
                    Account account= new Account(
                            rs.getInt("AccountNumber"),
                            rs.getDouble("Balance"),
                            user
                    );
                    return account;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;// tra ve null neu nhu k tim thay
    }

    //Lay accout boi stk
    public Account getAccountByNumber(int AccountNumber){
        String sql="SELECT *FROM Account WHERE AccountNumber=?";

        try(Connection conn=DBConnection.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,AccountNumber);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    User user=new User(rs.getInt("UserId"),null, null,null,null);
                    Account account=new Account(
                            rs.getInt("AccountNumber"),
                            rs.getDouble("Balance"),
                            user
                    );
                    return account;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //2.Nap tien
    public boolean deposit(Connection conn,int AccountNumber , double Amount) throws Exception{
        String sql = "UPDATE Account Set Balance= Balance +? WHERE AccountNumber=?";

        try (
             PreparedStatement ps = conn.prepareStatement(sql)){
            //nghia la update Account : balance=balance+amount tu so tai khoan ?

            ps.setDouble(1, Amount);
            ps.setInt(2, AccountNumber);
            return ps.executeUpdate() > 0;

        }
    }

    //3.RUT TIEN
    public boolean withdraw(Connection conn,int AccountNumber , double Amount)throws Exception{
        String sql="UPDATE Account SET Balance=Balance-? WHERE AccountNumber=? AND Balance>= ?";

        try(
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setDouble(1, Amount);
            ps.setInt(2,AccountNumber);
            ps.setDouble(3,Amount);

            return ps.executeUpdate()>0;
        }
    }
    public boolean saveHistory(Connection conn,int sender , int receiver, double amount)throws Exception{
        String sql ="INSERT INTO History(SenderAccount, ReceiverAccount,Amount) VALUES (?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1, sender);
            ps.setInt(2,receiver);
            ps.setDouble(3,amount);
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }

    //4.Chuyen tien
    public boolean transfer(int fromAccount, int toAccount, double amount){
        Connection conn=null;
        try{
            conn=DBConnection.getConnect();
            conn.setAutoCommit(false);//tat autocommit
            //Goi ham kiem tra ket qua tra ve
            boolean isWithdrawn=withdraw(conn,fromAccount,amount);
            boolean isDeposited=deposit(conn,toAccount,amount);
            //chi commit khi ca 2cung thanh cong
            if(isWithdrawn && isDeposited){
                saveHistory(conn,fromAccount, toAccount, amount);
                conn.commit();
                return true;
            }else{
                conn.rollback();
                return false;
            }

        }catch (Exception e){
            try{
                if(conn!=null) conn.rollback();
            }catch (Exception ex){
                e.printStackTrace();
            }
        }finally {
            try{
                if(conn!=null) conn.setAutoCommit(true);conn.close();
            }catch (Exception e){}
        }
        return false;
    }
    //5.Ham lay lich su
    public List<String> getHistoryByAccountNumber(int acc){
        List<String> list=new ArrayList<>();
        String sql="SELECT *FROM History WHERE SenderAccount=? OR ReceiverAccount=? ORDER BY TransactionDate DESC";

        try(Connection conn=DBConnection.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,acc);
            ps.setInt(2,acc);
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    int sender=rs.getInt("SenderAccount");
                    int receiver=rs.getInt("ReceiverAccount");
                    double amount=rs.getDouble("Amount");
                    Timestamp date=rs.getTimestamp("TransactionDate");
                    if(sender==acc){
                        list.add("[-] Chuyen "+String.format("%.0f", amount)+"VND den tai khoan" +receiver+" vao ngay "+date);

                    }else{
                        list.add("[+] Nhan "+String.format("%.0f",amount)+"VND tu STK "+sender+" vao"+date);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
