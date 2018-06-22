<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="../resources/css/login-reg.css">
    <link rel="icon" href="../resources/images/heart.png">
</head>
<body>
<script src="../resources/js/validate.js"></script>
<h2>Бесплатный сайт знакомств</h2>
<div class="form">
    <div class="check">${check}</div>
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/register">
        Имя пользователя: <br/>
        <input type="text" name="username" id="username" min="6" max="25" required/>
        <br/>
        Пароль: <br/>
        <input type="password" name="password" id="password" min="6" max="25" required/>
        <br/>
        <br/>
        <input type="submit" value="Зарегистрироваться"/>
        <script>inputValidation()</script>
    </form>
    <form method="get" action="${pageContext.request.contextPath}/index">
        <input type="submit" value="Войти"/>
    </form>
</div>
</body>
</html>