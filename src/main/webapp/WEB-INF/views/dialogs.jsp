<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/dialogs.css">
    <link rel="icon" href="../resources/images/heart.png">
    <title>Диалоги</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="dialogs">
    <c:choose>
        <c:when test="${dialogs.size()==0}">
            <h2>У Вас пока нет сообщений</h2>
        </c:when>
        <c:otherwise>
            <c:forEach items="${dialogs}" var="dialog">
                <div class="dialog-info">
                    <form method="get" action="${pageContext.request.contextPath}/user_${dialog.idUser}">
                        <c:choose>
                            <c:when test="${dialog.photoId==null}">
                                <input type="image"
                                       src="${pageContext.request.contextPath}../../resources/images/no_image.jpg"
                                       class="user-photo"/>
                            </c:when>
                            <c:otherwise>
                                <input type="image" src="/photo/${dialog.photoId}" class="user-photo"/>
                            </c:otherwise>
                        </c:choose>
                    </form>
                    <div class="interlocutor">${dialog.interlocutorName}</div>
                    <div class="dialog" onclick="location.href='/messages-by-dialog/${dialog.dialogId}'">
                            ${dialog.lastMessage}
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>