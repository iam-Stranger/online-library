<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="label.admin.show_all_users"/></title>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container">

    <form action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="dialog_delete"/>
        <input type="hidden" name="type" value="delete_user"/>
        <input type="hidden" name="return" value="show_all_users"/>
        <div class="card shadow" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="label.admin.show_all_users"/></h5>
                <div class="card-text">

                    <table class="table table-bordered table-hover table-responsive" width="100%">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="user.login"/></th>
                            <th><fmt:message key="user.first_name"/></th>
                            <th><fmt:message key="user.last_name"/></th>
                            <th><fmt:message key="user.email"/></th>
                            <th><fmt:message key="user.role"/></th>
                            <th class="w-10" style="text-align: center"><fmt:message key="list.edit"/></th>
                            <th class="w-10" style="text-align: center"><fmt:message key="list.delete"/></th>
                        </tr>
                        <c:forEach var="user" items="${user_list}" varStatus="loop">

                            <c:choose>
                                <c:when test="${user.isDeleted}">
                                    <tr class="table-light text-muted">
                                    <c:set var="color" value="btn-outline-light"/>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                    <c:set var="color" value="btn-outline-primary"/>
                                </c:otherwise>
                            </c:choose>

                            <td>${loop.index+1}</td>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td><ftm:message key="user.role.${user.roleId}"/></td>

                            <td style="text-align: center"><a
                                    href="${abs_path}/controller?command=edit_user_info&id=${user.id}"
                                    class="${color} btn-sm btn"><i class="far fa-edit"></i></a>
                            </td>
                            <td style="text-align: center">
                                <c:if test="${!user.isDeleted}">
                                    <button type="submit" name="id" value="${user.id}" class="btn btn-outline-danger btn-sm"><i
                                            class="far fa-times-circle"></i></button>
                                </c:if>
                            </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="card-footer text-right">
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>
</body>
</html>