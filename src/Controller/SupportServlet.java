package Controller;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


@WebServlet("/support")
public class SupportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        //Lay session hien tai
        HttpSession session=request.getSession(false);

        if(session!= null || session.getAttribute("account")!=null){
            //lay doi tuong account ra truoc
            Model.Account acc=(Model.Account)session.getAttribute("account");
            Model.User user=acc.getUser();
            request.setAttribute("username",user.getUserName());
            request.getRequestDispatcher("support.jsp").forward(request,response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost( HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //xu li logic khi nguoi dung gui form ho tro
        String message=request.getParameter("message");

        if(message != null && !message.trim().isEmpty()){
            request.setAttribute("seccess","Yêu cầu hỗ trợ đã được ");
        }else {
            request.setAttribute("error","Vui lòng nhập nội dung hỗ trợ");
        }
        request.getRequestDispatcher("support.jsp").forward(request,response);
    }
}
