package Controller;
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
        if("admin".equals(user.getRole())){
            Account adminAcc=new Account(1001,100000000.0, user);
            session.setAttribute("account",adminAcc);//dung chung vs homeservlet
            response.sendRedirect("home");
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
        session.setAttribute("account", account);
        response.sendRedirect("home");


    }
}
