package Controller;

import DAO.AccountDAO;
import DAO.UserDAO;
import Model.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/ConfirmTransferServlet")
public class ConfirmTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session=request.getSession();
        if(session ==null || session.getAttribute("account")==null){
            response.sendRedirect("login.jsp");
            return;
        }
        //lay du lieu
        Account account=(Account) session.getAttribute("account");
        if(account==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String toAccount =(String) session.getAttribute("toAccount");
        Double amount=(Double) session.getAttribute("amount");
        if(amount==null|| toAccount==null){
            request.getRequestDispatcher("transfer.jsp").forward(request, response);
            return;
        }
        //Lay du lieu PIN
        String pin=request.getParameter("pin");
        UserDAO userDAO=new UserDAO();
        String realPin=userDAO.getPinByUserId(account.getUser().getId());
        if(pin==null || pin.isEmpty()){
            request.setAttribute("error", "Vui long nhap ma pin");
            request.getRequestDispatcher("enterPIN.jsp").forward(request, response);
            return;
        }
        if(!pin.equals(realPin) || realPin==null){
            request.setAttribute("error","Ma pin k dung!");
            request.getRequestDispatcher("enterPIN.jsp").forward(request, response);
            return;
        }

        try{
            //tim account cua nguoi nhan
            AccountDAO accountDAO=new AccountDAO();

            int receiverAccountNumber=Integer.parseInt(toAccount);
            Account receiver=accountDAO.getAccountByNumber(receiverAccountNumber);
            if(receiver==null){
                session.setAttribute("error","Tai khoan nguoi nhan khong ton tai");
                response.sendRedirect("transfer.jsp");
                return;
            }
            //thuc hien giao dich trong databse
            boolean success= accountDAO.transfer(
                    account.getAccountNumber(),
                    receiver.getAccountNumber(),
                    amount
            );

            if(success){
                //cap nhap lai thong tin moi nhat
                Account updateAccount=accountDAO.getAccountByUserId(account.getUser().getId());
                //lay lich su
                List<String> history=accountDAO.getHistoryByAccountNumber(account.getAccountNumber());
                session.setAttribute("history",history);
                //luu session
                session.setAttribute("account", updateAccount);
                session.setAttribute("message", "Chuyen tien thanh cong");
                session.removeAttribute("toAccount");
                session.removeAttribute("amount");
                session.removeAttribute("error");
                response.sendRedirect("transfer.jsp");
            }else{
                request.setAttribute("error", "Giao dich that bai vui long thu lai!");
                request.getRequestDispatcher("enterPIN.jsp").forward(request, response);
            }
        }catch (NumberFormatException  e){
            response.sendRedirect("transfer");
        }
    }
}
