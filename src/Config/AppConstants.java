package Config;

public class AppConstants {
    //Role
     public static final String ROLE_ADMIN ="admin";
     public static final String ROLE_USER="user";

     public static final String PASS_ADMIN="1111";
     public static final String PASS_USER="2222";
     //Tai khoan admin
    public static final int ADMIN_ACCOUNT_NUMBER=1001;
    public static final double ADMIN_INITIAL_BALANCE=100_000_000.0;

    //Tai khoan user
    public static final int USER_ACCOUNT_NUMBER=1002;
    public static final double USER_INITIAL_BALANCE=10_000_000.0;

    // Context attribute (toàn server)
    public static final String CONTEXT_ADMIN_ACCOUNT = "adminAccount";
    public static final String CONTEXT_USER_ACCOUNT  = "userAccount";
    //session attribute
    public static final String SESSION_ACCOUNT="account";
    public static final String SESSION_HISTORY="history";
    //URL
    public static final String LOGIN_PAGE="login.jsp";
    public static final String HOME_PAGE="home";
}
