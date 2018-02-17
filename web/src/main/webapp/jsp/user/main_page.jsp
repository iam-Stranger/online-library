<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<fmt:message key="login.hellomessage" var="hello_message"/>

<div class="container">

    <div style="font-size: 3em; text-shadow: 1px 1px 1px #999999; color: #f7f7f7; text-align: center;">
        <c:out value="${hello_message}"/> ${user.firstName} ${user.lastName}
    </div>
    <div style="font-size: 1em; text-shadow: 1px 1px 1px #999999; color: #f7f7f7; text-align: center;">
        <fmt:message key="today"/> <ctg:date-tag/>
    </div>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>
</body>
</html>
