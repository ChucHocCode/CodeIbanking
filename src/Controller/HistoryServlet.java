package Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        HttpSession session=request.getSession();
        if(session==null ||session.getAttribute("account")==null){
            response.sendRedirect("login.jsp");
            return;
        }

        List<String> history=(List<String>) session.getAttribute("history");
        if(history==null){
            history=new ArrayList<>();
            session.setAttribute("history", history);
        }
        //Gui sang jsp
        request.setAttribute("history",history);//tao 1 bien history de jsp co the lay ra dung
        //chuyen trang
        request.getRequestDispatcher("history.jsp").forward(request,response);
    }
}
