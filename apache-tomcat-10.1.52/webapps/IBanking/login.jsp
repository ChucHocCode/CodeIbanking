<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body{
        font-family:Arial;
        background-color:#f2f2f2;
        display:flex;
        justify-content:center;
        height:100vh;
        margin:0;
        color:white;
        }

        .login-box{
        width: 300px;
        padding: 20px;
        background:#2e4f9b;
        border-radius:8px;
        box-shadow:0px 0px 10px #ccc;
        margin-top:50px;
        }

        h2{
        text-align:center;
        margin-bottom:20px;
        }

        label{
        display:block;
        margin-top: 10px;
        font-weight:bold;
        }

        input{
        width:100%;
        padding:8px;
        margin-top:5px;
        }

        button{
        width:100%;
        padding :10px;
        margin-top:15px;
        background:white;
        color: black;
        border:none;
        cursor:pointer;

        }

        button:hover{
        background:gray;
        }

        .error{
        color:red;
        text-align:center;
        }
        </style>
</head>
<body>

<div class="login-box">
    <h2>iBanking Login</h2>

    <form action="login" method="post">
        <p>UserName</p>
        <input type="text" name="username" required>

        <p>PassWord</p>
        <input type="password" name="password" required>

        <button type="submit">Login</button>

    </form>
    <%
        String error=(String) request.getAttribute("error");
        if(error!=null){
    %>
    <p style="color:red"><%= error %></p>
    <%
        }
    %>
</div>

</body>
</html>
