<%@ page language="java" contentType="text/html charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
    <title>History</title>
    <style>

    body{
        margin:0;
        font-family:Arial, Helvetica, sans-serif;
        background:#f4f6f9;
    }


    h1{
        text-align:center;
        background:#004080;
        color:white;
        padding:20px;
        margin:0;
    }


    ul{
        width:600px;
        margin:40px auto;
        padding:20px;
        background:white;
        border-radius:10px;
        box-shadow:0 4px 12px rgba(0,0,0,0.15);
        list-style:none;
    }


    li{
        padding:12px;
        border-bottom:1px solid #eee;
        font-size:16px;
    }

    li:last-child{
        border-bottom:none;
    }


    p{
        text-align:center;
        margin-top:40px;
        font-size:18px;
        color:gray;
    }

    </style>

</head>


<body>
    <h1>Lịch sử hoạt động</h1>
    <%
        List<String> history=(List<String>) session.getAttribute("history");
        if(history != null && !history.isEmpty()){
            out.println("<ul>");
            for(String item : history){
                out.println("<li>" +item + "</li>");
            }
            out.println("</ul>");
        }
        else{
            out.println("<p>Không có dữ liệu.</p>");
        }
    %>
</body>
</html>


