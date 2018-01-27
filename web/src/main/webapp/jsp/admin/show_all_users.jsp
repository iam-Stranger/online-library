<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="label.users.show_all_users"/></title>

</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>


<div class="container">

    <br/>

        <div class="card" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="label.users.show_all_users"/></h5>
                <div class="card-text">

                    <table class="table table-bordered table-hover table-responsive" width="100%">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="user.login" /></th>
                            <th><fmt:message key="user.first_name" /></th>
                            <th><fmt:message key="user.last_name" /></th>
                            <th><fmt:message key="user.email" /></th>
                            <th><fmt:message key="user.role" /></th>
                            <th></th>
                        </tr>
                        <c:forEach var="user" items="${user_list}" varStatus="loop">
                            <c:choose>
                                <c:when test="${user.deleted}">
                                    <tr class="table-light text-danger">
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                </c:otherwise>
                            </c:choose>
                                <td>${loop.index+1}</td>
                                <td>${user.login}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td><ftm:message key="user.role.${user.roleId}"/> </td>
                            <%--rel="modal:open"--%>
                                <td><a href="${abs_path}/controller?command=edit_user_info&id=${user.id}"
                                       class="btn-outline-primary btn-sm"><i class=""><fmt:message key="label.edit" /></i></a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>

            </div>
        </div>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>


</body>
</html>