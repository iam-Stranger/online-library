<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .my-header {
            color: #FFFFFF;
            text-shadow: 3px 3px 6px rgba(0, 0, 0, .5);
        }
    </style>
</head>
<body>

<jsp:include page="common_imports.jspf"/>

<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<fmt:message key="project.name_p1" var="proj_name_p1"/>
<fmt:message key="project.name_p2" var="proj_name_p2"/>
<fmt:message key="locale.lang" var="curr_lang"/>


<div class="gradient-overlay py-3" style="background-image: url(&quot;${abs_path}/images/background.png&quot;);">
    <div class="container py-0">
        <div class="row align-items-baseline">
            <div class="col-md-7 my-header">
                <h1 class="text-left"><b>${proj_name_p1}</b>${proj_name_p2}</h1>
            </div>
            <div class="col-md-5 text-white text-center">

                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="change_locale">
                    <input type="submit" class="btn btn-primary" value="${curr_lang}">
                </form>

            </div>
        </div>
    </div>
</div>

<c:import url="${abs_path}/jsp/include/menu.jsp"/>

</body>
</html>