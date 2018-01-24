<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/jsp/include/common_imports.jspf"/>

    <h1>
        Your error : <c:out value="${message}"/>
    </h1>

<br/>
<a href="${pageContext.request.contextPath}/controller?command=go_to_page">Назад на сайт</a>



</body>
</html>
