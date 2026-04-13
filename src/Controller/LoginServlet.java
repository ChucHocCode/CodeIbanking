package Controller;
import Config.AppConstants;
import DAO.AccountDAO;
import DAO.UserDAO;
import Model.Account;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //lay du lieu
        String usernameParam=request.getParameter("username");
        String passwordParam=request.getParameter("password");
        if(usernameParam == null || passwordParam == null) {
            request.setAttribute("error", "Username hoặc Password không được gửi.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String username=usernameParam.trim();
        String password=passwordParam.trim();
        if(username==null || password==null|| username.trim().isEmpty()|| password.trim().isEmpty()){
            request.setAttribute("error", "UserName va PassWord khong dc trong");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        //ktra user
        UserDAO userDAO=new UserDAO();
        User user=userDAO.checkLogin(username, password);

        if(user ==null){//ktra xem user ton tai hay k
            request.setAttribute("error","Sai username hoac password");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        HttpSession session=request.getSession();
        //Role admin
        if(AppConstants.ROLE_ADMIN.equals(user.getRole())){
            Account adminAcc=new Account(AppConstants.ADMIN_ACCOUNT_NUMBER,
                                        AppConstants.ADMIN_INITIAL_BALANCE,
                                        user);
            session.setAttribute(AppConstants.SESSION_ACCOUNT, adminAcc);
            response.sendRedirect(AppConstants.HOME_PAGE);
            return;
        }
        //ROLE USER
        AccountDAO accountDAO=new AccountDAO();
        Account account=accountDAO.getAccountByUserId(user.getId());
        if(account==null){
            request.setAttribute("error","User chua co tai khoan");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        account.setUser(user);
        session.setAttribute(AppConstants.SESSION_ACCOUNT,account);
        response.sendRedirect(AppConstants.HOME_PAGE);


    }
}
