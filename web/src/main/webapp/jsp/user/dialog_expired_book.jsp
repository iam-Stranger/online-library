<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="dialog.expired_book1"/>: </title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="dialog.expired_book1"/></h5>

                <div class="card-text m-2">
                    <fieldset style="padding: 5px">
                        <h5><fmt:message key="dialog.expired_book2"/></h5>
                    </fieldset>
                </div>

                <br/>
                <div class="card-footer text-right">
                    <a href="${abs_path}/controller?command=to_main_page" name="to_main" class="btn btn-outline-secondary btn-sm">
                        <fmt:message key="button.go_to_main"/></a>
                    <a href="${abs_path}/controller?command=show_active_user_orders" name="to_expired" class="btn btn-outline-secondary btn-sm">
                        <fmt:message key="button.go_to_orders"/></a>
                </div>
            </div>
        </div>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
