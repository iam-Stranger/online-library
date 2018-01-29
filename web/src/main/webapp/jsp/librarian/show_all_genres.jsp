<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="label.libr.show_all_genres"/></title>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <div class="card" style="width: 100%;">
        <div class="card-block">
            <h5 class="card-header"><fmt:message key="label.libr.show_all_genres"/></h5>
            <div class="card-text">

                <table class="table table-bordered table-hover table-responsive" width="100%">
                    <tr>
                        <th class="">#</th>
                        <th class="w-65"><fmt:message key="genre.type"/></th>
                        <th class="w-15" style="text-align: center"><fmt:message key="list.edit"/></th>
                        <th class="w-15" style="text-align: center"><fmt:message key="list.delete"/></th>
                    </tr>
                    <c:forEach var="author" items="${genre_list}" varStatus="loop">

                        <c:choose>
                            <c:when test="${author.isDeleted}">
                                <tr class="table-light text-muted">
                                <c:set var="color" value="btn-outline-light"/>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                <c:set var="color" value="btn-outline-primary"/>
                            </c:otherwise>
                        </c:choose>

                        <td>${loop.index+1}</td>
                        <td>${author.type}</td>

                        <td style="text-align: center"><a href="${abs_path}/controller?command=edit_genre_info&id=${author.id}"
                               class="${color} btn-sm"><i class="far fa-edit"></i></a>
                        </td>
                        <td style="text-align: center">
                            <c:if test="${!author.isDeleted}">
                                <a href="${abs_path}/controller?command=delete_genre&id=${author.id}"
                                   class="btn-outline-danger btn-sm"><i class="far fa-times-circle"></i></a>
                            </c:if>
                        </td >
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