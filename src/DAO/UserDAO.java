package DAO;

import Model.User;
import java.sql.*;
public class UserDAO {

    //1.login
    public User checkLogin(String UserName, String PassWord){
        String sql="SELECT*FROM User WHERE UserName=? AND PassWord=?";

        try(Connection conn=DBConnection.getConnect()){

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,UserName.trim());
            ps.setString(2,PassWord.trim());

            try(ResultSet rs=ps.executeQuery();){
                if(rs.next()){
                    return new User(
                            rs.getInt("Id"),
                            rs.getString("UserName"),
                            rs.getString("PassWord"),
                            rs.getString("PinCode"),
                            rs.getString("Role")
                    );
                }else{
                    System.out.println("No user found for username='" + UserName + "'");
                }
            }
        }
        catch (Exception e){
            System.out.println("LỖI SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    //Lay user theo Id
    public User getUserById(int Id){
        String sql="SELECT *FROM User WHERE Id=?";

        try(Connection conn=DBConnection.getConnect()){
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,Id);

            try(ResultSet rs=ps.executeQuery();){
                if(rs.next()){
                    return new User(
                            rs.getInt("Id"),
                            rs.getString("UserName"),
                            rs.getString("PassWord"),
                            rs.getString("PinCode"),
                            rs.getString("Role")

                    );
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Tao User moi
    //Nhu la dang ki tai khoan moi vay
    public boolean createUser(User user){
        String sql="INSERT INTO User(UserName,PassWord,PinCode, Role) VALUES(?,?,?,?)";
        try(Connection conn=DBConnection.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3,user.getPin());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() >0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //lay ma pin
    public String getPinByUserId(int userId){
        String sql="SELECT PinCode FROM User WHERE Id=?";
        try(Connection conn=DBConnection.getConnect();
        PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,userId);
            try(ResultSet rs= ps.executeQuery()){
                if(rs.next()){
                    return rs.getString("PinCode");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}