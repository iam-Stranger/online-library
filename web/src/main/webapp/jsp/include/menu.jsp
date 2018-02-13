<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
</head>
<body>

<nav class="navbar navbar-expand-md bg-secondary navbar-dark sticky-top">
    <div class="container">
        <ctg:is-user><a class="navbar-brand" href="#"><i class="fab fa-audible"></i></a></ctg:is-user>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav mr-auto align-baseline">

                <ctg:is-user>
                    <li class="nav-item">
                        <a class="nav-link text-light" href="${abs_path}/controller?command=go_to_page"><fmt:message key="menu.home_page"/> </a>
                    </li>
                    <%--<i class="fas fa-search"></i>--%>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" href="#" id="search" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <fmt:message key="menu.find.group"/> </a>
                        <div class="dropdown-menu" aria-labelledby="search_menu">
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_available_books"> <fmt:message key="menu.find.all_books"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=to_find_book_page"> <fmt:message key="menu.find.by_title"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_available_authors"> <fmt:message key="menu.find.by_author"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_available_genres"> <fmt:message key="menu.find.by_genre"/></a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link text-white" href="${abs_path}/controller?command=show_active_user_orders"><fmt:message key="menu.orders"/></a>
                    </li>
                </ctg:is-user>

                <ctg:is-librarian>
                    <li class="divider-vertical"></li>
                    <li class="nav-item dropdown" >
                        <a class="nav-link dropdown-toggle text-white" href="#" id="librarian" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="menu.libr.group"/></a>
                        <div class="dropdown-menu" aria-labelledby="user_menu">
                            <a class="dropdown-item" href="${abs_path}/controller?command=manage_orders"><i class="far fa-list-alt"></i> <fmt:message key="menu.libr.manage_orders"/></a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_all_genres"><i class="fas fa-edit"></i> <fmt:message key="menu.libr.edit_genres"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_all_authors"><i class="fas fa-edit"></i> <fmt:message key="menu.libr.edit_authors"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_all_books"><i class="fas fa-edit"></i> <fmt:message key="menu.libr.edit_books"/></a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${abs_path}/controller?command=to_add_genre_page"><i class="far fa-file-alt"></i> <fmt:message key="menu.libr.add_new_genre"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=to_add_author_page"><i class="far fa-file-alt"></i> <fmt:message key="menu.libr.add_new_author"/></a>
                            <a class="dropdown-item" href="${abs_path}/controller?command=to_add_book_page"><i class="far fa-file-alt"></i> <fmt:message key="menu.libr.add_new_book"/></a>
                        </div>
                    </li>
                </ctg:is-librarian>

                <ctg:is-admin>
                    <li class="divider-vertical"></li>
                    <li class="nav-item dropdown" >
                        <a class="nav-link dropdown-toggle text-white" href="#" id="admin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="menu.admin.group"/></a>
                        <div class="dropdown-menu" aria-labelledby="user_menu">
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_all_users"><i class="fas fa-edit"></i> <fmt:message key="menu.admin.find_all_users"/></a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${abs_path}/controller?command=show_history_orders"><i class="fas fa-history"></i> <fmt:message key="menu.admin.show_history_orders"/></a>
                        </div>
                    </li>
                </ctg:is-admin>
            </ul>

            <ctg:is-user>
                <ul class="navbar-nav navbar-right">
                    <li class="nav-item dropdown" >
                        <a class="nav-link dropdown-toggle text-white" href="#" id="user" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                        <div class="dropdown-menu" aria-labelledby="user_menu">
                            <a class="dropdown-item"><b><fmt:message key="your_role"/> <fmt:message key="user.role.${user.roleId}"/></b></a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${abs_path}/controller?command=to_change_password"><i class="fas fa-key"></i> <fmt:message key="user.change_password"/></a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${abs_path}/controller?command=sign_out"><i class="fas fa-sign-out-alt"></i> <fmt:message key="menu.user.exit"/></a>
                        </div>
                    </li>
                </ul>
            </ctg:is-user>

            <ctg:is-guest>
                <ul class="navbar-nav navbar-right">
                    <a href="${abs_path}/controller?command=to_sign_in_page" class="btn navbar-btn ml-2 btn-secondary" style="color: #9fcdff">
                        <i class="fas fa-sign-in-alt"></i> Sign in</a>
                    <a href="${abs_path}/controller?command=to_sign_up_page" class="btn navbar-btn ml-2 btn-secondary" style="color: #9fcdff">
                        <i class="fas fa-user-plus"></i> Sign up</a>
                </ul>
            </ctg:is-guest>


        </div>
    </div>
</nav>


<br/>


</body>
</html>