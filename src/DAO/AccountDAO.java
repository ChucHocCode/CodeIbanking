package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.User;
import Model.Account;

public class AccountDAO {
    //ket noi mysql
    private Connection conn;

    //Contructor nhan connection
    public AccountDAO(){
        conn=DBConnection.getConnect();
    }

    //1.Lay taikhoan theo user id
    public Account getAccountByUserId(int UserId){
        try{
            String sql="SELECT*FROM Account WHERE UserId=?";//tao cau lenh sql
            PreparedStatement ps=conn.prepareStatement(sql);//cbi gui cau lenh
            ps.setInt(1,UserId);//gan gtri cho ?
            ResultSet rs=ps.executeQuery();//chay sql va nhan kq
            if(rs.next()){//neu co kq(neu thay account
                User user=new User(
                        rs.getInt("UserId"),
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

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;// tra ve null neu nhu k tim thay
    }

    //Lay accout boi stk
    public Account getAccountByNumber(int AccountNumber){
        try{
            String sql="SELECT *FROM Account WHERE AccountNumber=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,AccountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                User user=new User(rs.getInt("UserId"),null, null,null);
                Account account=new Account(
                        rs.getInt("AccountNumber"),
                        rs.getDouble("Balance"),
                        user
                );
                return account;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //2.Nap tien
    public boolean deposit(int AccountNumber , double Amount){
        try {
            String sql = "UPDATE Account Set Balance= Balance +? WHERE AccountNumber=?";
            //nghia la update Account : balance=balance+amount tu so tai khoan ?
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, Amount);
            ps.setInt(2, AccountNumber);
            return ps.executeUpdate() > 0;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //3.RUT TIEN
    public boolean withdraw(int AccountNumber , double Amount){
        try{
            String sql="UPDATE Account SET Balance=Balance-? WHERE AccountNumber=? AND Balance>= ?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setDouble(1, Amount);
            ps.setInt(2,AccountNumber);
            ps.setDouble(3,Amount);

            return ps.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;//tra ve false neu nhu k thay
    }
    public boolean saveHistory(int sender , int receiver, double amount) throws SQLException{
        String sql ="INSERT INTO History(SenderAccount, ReceiverAccount,Amount) VALUES (?,?,?)";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, sender);
            ps.setInt(2,receiver);
            ps.setDouble(3,amount);
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //4.Chuyen tien
    public boolean transfer(int fromAccount, int toAccount, double amount){
        try{
            conn.setAutoCommit(false);//tat autocommit
            //Goi ham kiem tra ket qua tra ve
            boolean isWithdrawn=withdraw(fromAccount,amount);
            boolean isDeposited=deposit(toAccount,amount);
            //chi commit khi ca 2cung thanh cong
            if(isWithdrawn && isDeposited){
                saveHistory(fromAccount, toAccount, amount);
                conn.commit();
                return true;
            }else{
                conn.rollback();
                return false;
            }

        }catch (Exception e){
            try{
                conn.rollback();//quay lai trang thai ban dau : chua chuyen,
            }catch (Exception ex){}
            e.printStackTrace();

        }finally {
            try{
                if(conn!=null) conn.setAutoCommit(true);
            }catch (Exception e){}
        }
        return false;
    }
    //5.Ham lay lich su
    public List<String> getHistoryByAccountNumber(int acc){
        List<String> list=new ArrayList<>();
        String sql="SELECT *FROM History WHERE SenderAccount=? OR ReceiverAccount=? ORDER BY TransactionDate DESC";

        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,acc);
            ps.setInt(2,acc);
            ResultSet rs=ps.executeQuery();
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
