<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>
<head>
    <title>Transfer</title>
<style>
body{
    margin:0;
    font-family:Arial, Helvetica, sans-serif;
    background:#f4f6f9;
    display:flex;
    justify-content:center;
    align-items:center;
    height:100vh;
}

.container{
    width:400px;
    background:white;
    padding:30px;
    border-radius:12px;
    box-shadow:0 4px 12px rgba(0,0,0,0.15);
    text-align:center;
}

h2{
    color:#004080;
    margin-bottom:25px;
    font-size:26px;
}

input{
    width:90%;
    padding:10px;
    margin:10px 0;
    border-radius:6px;
    border:1px solid #ccc;
    box-sizing:border-box;
}

button{
    width:95%;
    padding:12px;
    background:#004080;
    color:white;
    border:none;
    border-radius:6px;
    margin-top:15px;
    cursor:pointer;
    transition:0.3s;
}

button:hover{
    background:#0066cc;
}

a.back{
    display:inline-block;
    margin-top:20px;
    padding:8px 16px;
    background:#004080;
    color:white;
    text-decoration:none;
    border-radius:6px;
    transition:0.3s;
}

a.back:hover{
    background:#0066cc;
    text-decoration:none;
}

.msg{
    color:green;
    margin-top:15px;
    font-weight:bold;
}
</style>
</head>
<body>
<%
    if(session.getAttribute("account")==null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<div class="container">
    <h2>Transfer</h2>
    <!-- chon nguoi nhan-->

    <div class="card">
        <form action="transfer" method="post">
            <h3>Chuyen Tien</h3>
            <label>Nhap so tai khoan nguoi nhan</label>
            <input type="text" name="toAccount" placeholder="Nhap stk: " required>
            <button type="submit">Kiem tra & Tiep Tuc</button>
        </form>
    </div>
<a href ="home">Back Home</a>
<c:if test="${not empty error}">
    <p style="color:red ; font-weight:bold; margin-top:15px;"> ${error}</p>
    <c:if test="${not empty sessionScope.error}">
        <% session.removeAttribute("error"); %>
    </c:if>
</c:if>

<c:if test="${not empty sessionScope.message}">
    <p style="color:green ; font-weight: bold ; margin-top:15px ;">${sessionScope.message}</p>
    <% session.removeAttribute("message") ; %>
</c:if>
</div>
</body>
</html>