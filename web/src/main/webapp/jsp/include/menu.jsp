<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc"/>
<html>
<head>
</head>
<body>

<nav class="navbar navbar-expand-md bg-secondary navbar-dark sticky-top">
    <div class="container">
        <%--<a class="navbar-brand" href="#">ADMIN</a>--%>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="menu_content">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="${abs_path}/controller?command=go_to_page"><fmt:message key="menu.home_page" bundle="${loc}"/> </a>
                </li>
                <%--<span class="sr-only">(current)</span>--%>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="search" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <fmt:message key="menu.find.group" bundle="${loc}"/> </a>
                    <div class="dropdown-menu" aria-labelledby="search_menu">
                        <a class="dropdown-item" href="#"><fmt:message key="menu.find.all_books" bundle="${loc}"/></a>
                        <a class="dropdown-item" href="${abs_path}/controller?command=to_find_book_page"><fmt:message key="menu.find.by_title" bundle="${loc}"/></a>
                        <a class="dropdown-item" href="#"><fmt:message key="menu.find.by_author" bundle="${loc}"/></a>
                        <a class="dropdown-item" href="#"><fmt:message key="menu.find.by_genre" bundle="${loc}"/></a>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="menu.user.orders" bundle="${loc}"/></a>
                </li>

            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle" href="#" id="user" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${user.firstName} ${user.lastName}</a>
                    <div class="dropdown-menu" aria-labelledby="user_menu">
                        <a class="dropdown-item" href="#"><fmt:message key="menu.user.settings" bundle="${loc}"/></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${abs_path}/controller?command=sign_out"><fmt:message key="menu.user.exit" bundle="${loc}"/></a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>


</body>
</html>