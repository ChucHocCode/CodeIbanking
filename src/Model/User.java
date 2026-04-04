package Model;

public class User {
    private int Id;
    private String UserName;
    private String PassWord;
    private String Role;

    public User(int Id, String UserName, String PassWord, String Role){
        this.Id=Id;
        this.UserName=UserName;
        this.PassWord=PassWord;
        this.Role=Role;
    }

    public int getId(){
        return Id;
    }

    public String getUserName(){
        return UserName;
    }

    public String getPassWord(){
        return PassWord;
    }
    public String getRole(){
        return Role;
    }

    public void setId(int id) {
        Id = id;
    }
    public void setUserName(String username){
        UserName=username;
    }
    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
    public void setRole(String role){
        Role=role;
    }

    //Ham kiem tra mk
    public boolean checkPassword(String InputPass){
        return PassWord.equals(InputPass);
    }

}
