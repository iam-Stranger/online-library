<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="dialog.success"/></title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <form class="form" action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="${params['type']}">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="dialog.success"/></h5>
                <div class="card-text m-2" style="padding: 5px">
                    <fieldset>
                        <h5><i class="far fa-check-circle" style="font-size: 3rem; color: #28a745"></i></h5>
                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <a href="${abs_path}/${sessionScope.return_page}" name="return" class="btn btn-success">
                        <fmt:message key="button.dialog.ok"/></a>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
