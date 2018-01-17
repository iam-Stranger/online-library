<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>--%>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <%--<link rel="stylesheet" href="https://v40.pingendo.com/assets/bootstrap/bootstrap-4.0.0-beta.1.css" type="text/css">--%>
    <link href="bootstrap/css/bootstrap-4.0.0-beta.1.css" rel="stylesheet" type="text/css"/>

    <style>
        .my-header {
            color: #FFFFFF;
            text-shadow: 3px 3px 6px rgba(0, 0, 0, .5);
        }
    </style>

</head>
<body>

<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc"/>
<fmt:message bundle="${loc}" key="login.hellomessage" var="hello_message"/>
<fmt:message bundle="${loc}" key="login.exit" var="exit"/>
<fmt:message bundle="${loc}" key="locale.lang" var="curr_lang"/>


<div class="gradient-overlay py-4" style="background-image: url(&quot;${abs_path}/images/background.png&quot;);">
    <div class="container py-0">
        <div class="row align-items-baseline">
            <div class="col-md-5 my-header">
                <h1 class="text-left"><b>Online</b>Library </h1>
            </div>
            <div class="col-md-5 text-white my-header">
                <h5><c:out value="${hello_message}"/> ${user.firstName} ${user.lastName} [<a
                        href="${abs_path}/controller?command=sign_out">${exit}</a>]<br/>
                </h5>
            </div>
            <div class="col-md-2 text-white text-center">
                <%--<div class="btn-group">--%>
                <%--<a href="#" class="btn btn-primary">RUS</a>--%>
                <%--<a href="#" class="btn btn-primary">ENG</a>--%>
                <%--</div>--%>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale">
                    <input type="submit" class="btn btn-primary" value="${curr_lang}">
                </form>

            </div>
        </div>
    </div>
</div>

<c:import url="${abs_path}/import/menu.jsp"/>

</body>
</html>