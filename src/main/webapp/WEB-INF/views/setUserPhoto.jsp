<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/set-user-photo.css">
    <link rel="icon" href="../resources/images/heart.png">
    <title>Фотография</title>
</head>
<body>
<h2>Для завершения регистрации пройдите два простых шага</h2>
<div class="user-photo-form">
    ${checkPhoto}<br/>
    <form method="POST" action="${pageContext.request.contextPath}/set-user-photo" enctype="multipart/form-data">
        <h3>Выберите файл не более 3 МБ</h3>
        <input type="file" name="file"><br/>
        <br/>
        <button class="confirm">Загрузить</button>
    </form>
    <button class="cancel" onclick="document.location.href='/user-profile'">Пропустить</button>
    <div class="step"><h2>Шаг 2: загрузите фотографию</h2></div>
</div>
</body>
</html>