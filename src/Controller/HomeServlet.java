package Controller;
import Config.AppConstants;
import DAO.AccountDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import Model.Account;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session =request.getSession(false);//lay session k tao moi
        if(session ==null || session.getAttribute("account")==null){
            response.sendRedirect("login");
            return ;
        }
        //lay account dang login
        Account account =(Account) session.getAttribute("account");

        //cap nhap so du hien tai
        AccountDAO accountDAO=new AccountDAO();
        account= accountDAO.getAccountByUserId(account.getUser().getId());

        List<String> History=accountDAO.getHistoryByAccountNumber(account.getAccountNumber());
        session.setAttribute("history",History);
        //demo account dung chung toan server
        Account adminAcc=(Account) getServletContext().getAttribute("adminAccount");
        Account userAcc=(Account) getServletContext().getAttribute("userAccount");
        //getServerContext: bo nho dung chung cua toan server
        if(adminAcc==null){//neu chua co ->tao tk demo
            adminAcc=new Account(
                    AppConstants.ADMIN_ACCOUNT_NUMBER,
                    AppConstants.ADMIN_INITIAL_BALANCE,
                    new User(1,"admin","123",AppConstants.PASS_ADMIN, AppConstants.ROLE_ADMIN)
            );
            getServletContext().setAttribute(AppConstants.CONTEXT_ADMIN_ACCOUNT,adminAcc);//luu vao bo nho server
        }
        if(userAcc==null){
            userAcc=new Account(
                    AppConstants.USER_ACCOUNT_NUMBER,
                    AppConstants.USER_INITIAL_BALANCE,
                    new User(2,"user","234",AppConstants.PASS_USER,AppConstants.ROLE_USER)
            );
            getServletContext().setAttribute(AppConstants.CONTEXT_USER_ACCOUNT,userAcc);
        }

        //gui du lieu sang jsp
        request.setAttribute(AppConstants.SESSION_ACCOUNT, account);
        request.setAttribute(AppConstants.CONTEXT_ADMIN_ACCOUNT,adminAcc);
        request.setAttribute(AppConstants.CONTEXT_USER_ACCOUNT,userAcc);
        request.setAttribute(AppConstants.SESSION_HISTORY, History);

        request.getRequestDispatcher("home.jsp").forward(request, response);

    }
}
