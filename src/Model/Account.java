package Model;

public class Account {
    private int AccountNumber;
    private double Balance;
    private User user;

    public Account(int AccountNumber, double Balance, User user){
        this.AccountNumber=AccountNumber;
        this.Balance=Balance;
        this.user=user;
    }

    public int getAccountNumber(){
        return AccountNumber;
    }
    public double getBalance(){
        return Balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setAccountNumber(int AccountNumber){
        this.AccountNumber=AccountNumber;
    }
    public void setBalance(double Balance){
        this.Balance=Balance;
    }

    //Nap/Gui tien
    public void deposit(double amount){
        if(amount>0){
            Balance+=amount;
        }
    }

    //Rut tien
    public boolean withdraw(double amount){
        if(amount >0 && amount <=Balance){
            Balance-=amount;
            return true;
        }
        return false;
    }
    //Chuyen tien tk khac
    public boolean transfer(Account target,double amount){
        if(withdraw(amount)){
            target.deposit(amount);
            return true;
        }
        return false;
    }
}
