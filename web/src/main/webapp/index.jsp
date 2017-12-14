<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Index</title>
</head>
<body>

<fmt:setLocale value="ru-RU"/>
<fmt:setBundle basename="localization" var="local"/>

<fmt:message bundle="${local}" key="login.hellomessage" var="hello_message"/>

<c:out value="${hello_message}"/>


</body>
</html>
