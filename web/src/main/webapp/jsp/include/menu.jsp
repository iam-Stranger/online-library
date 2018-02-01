<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
</head>
<body>

<nav class="navbar navbar-expand-md bg-secondary navbar-dark sticky-top">
    <div class="container">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarS" aria-controls="navbarS" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto align-baseline">

                <li class="nav-item">
                    <a class="nav-link" href="${abs_path}/controller?command=go_to_page"><fmt:message key="menu.home_page"/> </a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="search" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <fmt:message key="menu.find.group"/> </a>
                    <div class="dropdown-menu" aria-labelledby="search_menu">
                        <a class="dropdown-item" href="#"><fmt:message key="menu.find.all_books"/></a>
                        <a class="dropdown-item" href="${abs_path}/controller?command=to_find_book_page"><fmt:message key="menu.find.by_title"/></a>
                        <a class="dropdown-item" href="#"><fmt:message key="menu.find.by_author"/></a>
                        <a class="dropdown-item" href="#"><fmt:message key="menu.find.by_genre"/></a>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="menu.orders"/></a>
                </li>

                <li class="divider-vertical"></li>

                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle" href="#" id="librarian" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="menu.libr.group"/></a>
                    <div class="dropdown-menu" aria-labelledby="user_menu">
                        <a class="dropdown-item" href="${abs_path}/controller?command=show_all_genres"><fmt:message key="menu.libr.edit_genres"/></a>
                        <a class="dropdown-item" href="${abs_path}/controller?command=show_all_authors"><fmt:message key="menu.libr.edit_authors"/></a>
                        <a class="dropdown-item" href="${abs_path}/controller?command=show_all_books"><fmt:message key="menu.libr.edit_books"/></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${abs_path}/controller?command=to_add_genre_page"><fmt:message key="menu.libr.add_new_genre"/></a>
                        <a class="dropdown-item" href="${abs_path}/controller?command=to_add_author_page"><fmt:message key="menu.libr.add_new_author"/></a>
                        <a class="dropdown-item" href="${abs_path}/controller?command=to_add_book_page"><fmt:message key="menu.libr.add_new_book"/></a>
                    </div>
                </li>

                <li class="divider-vertical"></li>

                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle" href="#" id="admin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="menu.admin.group"/></a>
                    <div class="dropdown-menu" aria-labelledby="user_menu">
                        <a class="dropdown-item" href="${abs_path}/controller?command=show_all_users"><fmt:message key="menu.admin.find_all_users"/></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${abs_path}/controller?command=show_history_orders"><fmt:message key="menu.admin.show_history_orders"/></a>
                    </div>
                </li>
            </ul>

            <ul class="navbar-nav navbar-right">
                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle" href="#" id="user" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                    <div class="dropdown-menu" aria-labelledby="user_menu">
                        <a class="dropdown-item" href="#"><fmt:message key="menu.user.settings"/></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${abs_path}/controller?command=sign_out"><fmt:message key="menu.user.exit"/></a>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</nav>
<br/>


</body>
</html>