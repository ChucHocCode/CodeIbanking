<%@ page language="java" contentType="text/html ;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hỗ Trợ iBangking</title>
    <style>

    body{
        margin:0;
        font-family:Arial, Helvetica, sans-serif;
        background:#f4f6f9;
    }


    h2{
        text-align:center;
        background:#004080;
        color:white;
        padding:18px;
        margin:0;
    }


    form{
        width:450px;
        margin:40px auto;
        background:white;
        padding:25px;
        border-radius:12px;
        box-shadow:0 4px 12px rgba(0,0,0,0.15);
    }


    textarea{
        width:100%;
        padding:10px;
        border-radius:6px;
        border:1px solid #ccc;
        margin-top:10px;
    }


    input[type="submit"]{
        margin-top:15px;
        padding:10px 20px;
        border:none;
        background:#004080;
        color:white;
        border-radius:6px;
        cursor:pointer;
    }

    input[type="submit"]:hover{
        background:#0066cc;
    }


    p{
        text-align:center;
        font-size:16px;
    }

    </style>

</head>
<body>
<h2>Gửi yêu cầu hỗ trợ</h2>

<form action="support.jsp" method="post">
    <label>Nội dung yêu cầu: </label><br>
    <textarea name="message" rows="5" cols="40" required ></textarea><br><br>
    <input type="submit" value="Gửi">
</form>
<%
    String message=request.getParameter("message");
    if(message != null){
        session.setAttribute("lastSupportMessage",message);
        out.println("<p style='color:green'>Yêu cầu hỗ trợ được ghi nhận!</p>");
        out.println("<p><strong>Nội Dung: </strong>"+message+"</p>");
    }
%>
</body>
</html>