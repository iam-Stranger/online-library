<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="dialog.error"/></title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="dialog.error"/></h5>
                <div class="card-text m-2" style="padding: 5px">
                    <fieldset>
                        <h6><i class="fas fa-exclamation-triangle" style="font-size: 2rem; color: #bd2130"></i>
                            <c:out value="${sessionScope.message}"/></h6>
                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <%--<a href="${abs_path}/controller?command=go_to_page" name="return" class="btn btn-primary">--%>
                        <%--<fmt:message key="button.dialog.back"/></a>--%>
                    <a href="javascript:history.back()" name="return" class="btn btn-primary">
                        <fmt:message key="button.dialog.back"/></a>
                </div>
            </div>
        </div>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
