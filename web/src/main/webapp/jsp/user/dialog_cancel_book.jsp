<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="dialog.cancel_book_u"/>: #${params['id']}</title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <form class="form" action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="cancel_book_to_user">
        <input type="hidden" name="id" value="${id}">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="dialog.cancel_book_u"/></h5>

                <div class="card-text m-2">
                    <fieldset>
                        <h5>&nbsp;<fmt:message key="dialog.are_you_sure"/></h5>
                    </fieldset>
                </div>

                <br/>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-danger"><fmt:message key="button.dialog.apply"/></button>
                    <a href="${abs_path}/controller?command=show_active_user_orders" name="cancel" class="btn btn-primary">
                        <fmt:message key="button.dialog.cancel"/></a>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
