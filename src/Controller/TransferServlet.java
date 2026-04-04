package Controller;

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
        //lay du lieu tu form
        String toAccount=request.getParameter("toAccount");
        if(toAccount.equals(myId)){
            request.setAttribute("error","Khong te chuyen cho tai khoan chinh chu");
            request.getRequestDispatcher("transfer.jsp").forward(request, response);
        }
        else if("1001".equals(toAccount) ||"1002".equals(toAccount)){
            session.setAttribute("toAccount", toAccount);
            response.sendRedirect("enterAmount.jsp");
        }else{
            request.setAttribute("error","So tai khoan khong ton tai!");
            request.getRequestDispatcher("transfer.jsp").forward(request,response);
        }
    }
}
