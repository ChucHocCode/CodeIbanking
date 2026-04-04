package Controller;
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
                    1001,
                    1000000000.0,
                    new User(1,"admin", "123","admin")
            );
            getServletContext().setAttribute("adminAccount",adminAcc);//luu vao bo nho server
        }
        if(userAcc==null){
            userAcc=new Account(
                    1002,
                    10000000.0,
                    new User(2,"user","234","user")
            );
            getServletContext().setAttribute("userAccount",userAcc);
        }

        //gui du lieu sang jsp
        request.setAttribute("account", account);
        request.setAttribute("adminAccount",adminAcc);
        request.setAttribute("userAccount",userAcc);
        request.setAttribute("history", History);

        request.getRequestDispatcher("home.jsp").forward(request, response);

    }
}
