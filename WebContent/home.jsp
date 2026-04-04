<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>

<style>
body{
    margin:0;
    font-family:Arial, Helvetica, sans-serif;
    background:linear-gradient(135deg,#eef2f7,#dbe7ff);
}

header{
    background:linear-gradient(90deg,#004080,#0066cc);
    color:white;
    padding:25px 0;
    text-align:center;
    font-size:22px;
    letter-spacing:1px;
    box-shadow:0 4px 10px rgba(0,0,0,0.2);
}

.container{
    max-width:900px;
    margin:40px auto;
    padding:10px;
    display:grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap:20px;
}

.card{
    background:white;
    padding:25px 20px;
    border-radius:15px;
    box-shadow:0 6px 18px rgba(0,0,0,0.08);
    transition:all 0.25s ease;
    display:flex;
    flex-direction:column;
    align-items:center;
    text-align:center;
}

.card:hover{
    transform:translateY(-5px) scale(1.01);
    box-shadow:0 10px 25px rgba(0,0,0,0.15);
}

.card h3{
    margin:0 0 15px 0;
    font-size:18px;
    color:#004080;
}

.card a{
    padding:10px 20px;
    background:#004080;
    color:white;
    border-radius:8px;
    text-decoration:none;
    font-size:14px;
    transition:0.2s;
    margin-top:auto;
}

.card a:hover{
    background:#0073e6;
}

.balance{
    font-size:32px;
    color:#00a65a;
    font-weight:bold;
    margin-top:10px;
}

</style>
</head>
<body>
<header>
    <h2>Chao </h2>
</header>
<div class="container">
    <div class="card">
        <h3> So Du</h3>
        <p class="balance">
            <fmt:formatNumber value="${account.balance}" type="number" minFractionDigits="2" maxFractionDigits="2" />
            <small style="font-size:14px;">VND</small>
        </p>
    </div>

    <div class="card">
        <h3>Transfer</h3>
        <a href="transfer">Chuyen Tien</a>
    </div>

    <div class="card">
        <h3> Lich Su Giao Dich</h3>
        <a href="history.jsp">Xem Lich Su</a>
    </div>

    <div class="card">
        <h3> Ho Tro</h3>
        <a href="support.jsp">Ho Tro</a>
    </div>

    <div class="card">
        <a href="logout" >Logout</a>
    </div>
</div>
</body>
</html>





































