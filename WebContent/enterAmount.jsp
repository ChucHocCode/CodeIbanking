<%@ page language="java" contentType="text/html ;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>
<head>
    <title> EnterAmount</title>
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
    border-radius:10px;
    box-shadow:0 4px 12px rgba(0,0,0,0.15);
    text-align:center;
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

p{
    text-align:center;
    font-weight:bold;
}

</style>
<body>
<%
    if(session.getAttribute("account")==null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<form action="enterAmount" method="post">
    <h3>Nhap so tien can chuyen </h3>
    <p type="color:#004080 ; font-size:14px">
        Chuyen den tai khoan :<%= session.getAttribute("toAccount") %>
    </p>
    So tien:
    <input type="number" name="amount" required min="2000">
    <button type="submit">Tiep tuc</button>
    <c:if test="${not empty sessionScope.message}">
        <p class="success">${sessionScope.message}</p>
        <% session.removeAttribute("message") ;%>
    </c:if>
    <c:if test="${param.status=='success'}">
       <div style="color:green;">Giao dich hoan tat!</div>
    </c:if>
    <a href="logout">Logout</a>


</form>
</body>
</html>