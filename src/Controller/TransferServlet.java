package Controller;

import DAO.AccountDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Account;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        if(session==null || session.getAttribute("account")==null){
            response.sendRedirect("login.jsp");
            return;
        }
        request.getRequestDispatcher("transfer.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session=request.getSession(false);
        if(session ==null || session.getAttribute("account")==null){
            response.sendRedirect("login.jsp");
            return ;
        }
        //lay account login
        Account fromAccount=(Account) session.getAttribute("account");
        String myId=String.valueOf(fromAccount.getAccountNumber());

        String toAccount =request.getParameter("toAccount");
        if(toAccount.equals(myId)){
            request.setAttribute("error","Khong the chuyen tien cho tai khoan chinh chu !");
            request.getRequestDispatcher("transfer.jsp").forward(request, response);
            return;
        }
        try {
            AccountDAO accountDAO=new AccountDAO();
            Account acc=accountDAO.getAccountByNumber(Integer.parseInt(toAccount));
            if(acc==null ) {
                request.setAttribute("error", "So tai khoan khong ton tai!");
                request.getRequestDispatcher("transfer.jsp").forward(request, response);
                return;
            }
            session.setAttribute("toAccount", toAccount);
            response.sendRedirect("enterAmount.jsp");
        }catch (NumberFormatException e){
            request.setAttribute("error","So tai khoan khong hop le!");
            request.getRequestDispatcher("transfer.jsp").forward(request, response);
        }

    }
}
