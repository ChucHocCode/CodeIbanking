package Model;

public class User {
    private int Id;
    private String UserName;
    private String PassWord;
    private String PinCode;
    private String Role;


    public User(int Id, String UserName, String PassWord, String Role,String PinCode){
        this.Id=Id;
        this.UserName=UserName;
        this.PassWord=PassWord;
        this.PinCode=PinCode;
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
    public String getPin(){return PinCode;}
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
    public void setPin(String PinCode){
        PinCode=PinCode;
    }
    public void setRole(String role){
        Role=role;
    }


    //Ham kiem tra mk
    public boolean checkPassword(String InputPass){
        return PassWord.equals(InputPass);
    }

}
