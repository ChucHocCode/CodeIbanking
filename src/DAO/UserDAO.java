package DAO;

import Model.User;
import java.sql.*;
public class UserDAO {
    private Connection conn;
    public UserDAO (){
        conn=DBConnection.getConnect();
    }

    //1.login
    public User checkLogin(String UserName, String PassWord){
        try{

            String sql="SELECT*FROM User WHERE UserName=? AND PassWord=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,UserName.trim());
            ps.setString(2,PassWord.trim());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("Id"),
                        rs.getString("UserName"),
                        rs.getString("PassWord"),
                        rs.getString("Role")
                );
            }else{
                System.out.println("No user found for username='" + UserName + "'");
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
        try{
            String sql="SELECT *FROM User WHERE Id=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,Id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("Id"),
                        rs.getString("UserName"),
                        rs.getString("PassWord"),
                        rs.getString("Role")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Tao User moi
    //Nhu la dang ki tai khoan moi vay
    public boolean createUser(User user){
        try{
            String sql="INSERT INTO User(UserName,PassWord, Role) VALUES(?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getRole());

            return ps.executeUpdate() >0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}