<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать</title>
    <link rel="stylesheet" href="../resources/css/login-reg.css">
    <link rel="icon" href="../resources/images/heart.png">
</head>
<body>
<script src="../resources/js/validate.js"></script>
<h2>Бесплатный сайт знакомств</h2>
<div class="form">
    ${checkResult}
    <br/>
    <br/>
    <form method="get" action="${pageContext.request.contextPath}/check-user">
        Имя пользователя: <br/>
        <input type="text" name="username" required/>
        <br/>
        Пароль: <br/>
        <input type="password" name="password"/>
        <br/>
        <br/>
        <input type="submit" value="Войти"/>
        <script>inputValidation()</script>
    </form>
    <form method="get" action="${pageContext.request.contextPath}/registration">
        <input type="submit" value="Регистрация"/>
    </form>
</div>
</body>
</html>