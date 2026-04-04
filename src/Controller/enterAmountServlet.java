package Controller;

import Model.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/enterAmount")
public class enterAmountServlet extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
        HttpSession session =request.getSession();
        if(session ==null || session.getAttribute("account")==null){
            response.sendRedirect("login");
            return;

        }
        //2.Lay account dang login
        Account acc=(Account) session.getAttribute("account");
        if(acc==null){
            response.sendRedirect("login.jsp");
        }
        //3.Nguoi nhan tu transfer
        String toAccount=(String) session.getAttribute("toAccount");
        if(toAccount==null){
            response.sendRedirect("transfer.jsp");
            return ;
        }
        //4.Lay amount
        String amountStr=request.getParameter("amount");
        double amount;
        try{
            amount=Double.parseDouble(amountStr);
            if(amount <=0){
                request.setAttribute("error","So du phai > 0");
                request.getRequestDispatcher("enterAmount.jsp").forward(request,response);
                return ;
            }
            //5.Ktra so du;
            if(amount > acc.getBalance()){
                request.setAttribute("error", "so du khong du");
                request.getRequestDispatcher("enterAmount.jsp").forward(request,response);
                return ;
            }
            //luu du lieu
            session.setAttribute("amount", amount);
            response.sendRedirect("enterPIN.jsp");
        }catch (Exception e){
            request.setAttribute("error","Amount khong hop le  ");
            request.getRequestDispatcher("enterAmount.jsp").forward(request,response);
        }

    }
}