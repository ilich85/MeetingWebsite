<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/set-user-info.css">
    <link rel="icon" href="../resources/images/heart.png">
    <title>Личная информация</title>
</head>
<body>
<h2>Для завершения регистрации пройдите два простых шага</h2>
<div class="user-info-form">
    <spring:form method="post" modelAttribute="userInfo" action="/set-user-info">
        Имя: <br/>
        <spring:input type="text" path="name" required="true"/><br/>
        Город: <br/>
        <spring:input type="text" path="city" required="true"/><br/>
        Возраст: <br/>
        <spring:input type="number" path="age" value="16" min="16" max="99"/><br/>
        Пол: <br/>
        <spring:select items="${genders}" type="text" path="gender"/>
        <br/><br/>
        <spring:button>Сохранить</spring:button>
    </spring:form><br/>
    <h2>Шаг 1: заполните свои данные</h2>
</div>
</body>
</html>