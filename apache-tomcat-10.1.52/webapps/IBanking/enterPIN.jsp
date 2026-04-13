<%@ page language="java" contentType="text/html ;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>PIN</title>
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

form{
    width:350px;
    background:white;
    padding:30px;
    border-radius:12px;
    box-shadow:0 4px 12px rgba(0,0,0,0.15);
    text-align:center;
}

h2{
    text-align:center;
    color:#004080;
    margin-bottom:20px;
}

input{
    width:100%;
    padding:10px;
    margin-top:10px;
    border-radius:6px;
    border:1px solid #ccc;
    box-sizing:border-box;
}

button{
    margin-top:15px;
    padding:10px 20px;
    border:none;
    background:#004080;
    color:white;
    border-radius:6px;
    cursor:pointer;
}

button:hover{
    background:#0066cc;
}

</style>

</head>
<form action="ConfirmTransferServlet" method="post">
    <c:if test="${not empty requestScope.error}">
        <p style="color:red; font-weight:bold;margin-bottom:15px;">${requestScope.error}</p>
    </c:if>
    Enter PIN: <input type="password" name="pin">
    <button type="submit">Confirm</button>

    <div style="margin-top:15px; ">
        <a href="transfer.jsp" style="text-decoration:none; color:#666; font-size:14px;">Huy giao dich</a>
    </div>
</form>



</html>