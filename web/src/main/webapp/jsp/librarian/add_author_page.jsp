<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="label.libr.add_author"/></title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <form class="form" action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="add_new_author">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="label.libr.add_author"/></h5>
                <div class="card-text m-2">
                    <fieldset>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="name"><fmt:message key="author.name"/></label>
                            <div class="col-9">
                                <input class="form-control" id="name" value="${params['name']}" name="name"
                                       type="text" required="" pattern="[A-ZА-ЯЁ]{1}[A-ZА-ЯЁa-zа-яё\s]{2,48}">
                                <span class="form-text small text-muted"><fmt:message key="tips.genre_author"/></span>
                                <span class="form-text small text-danger">${errors['name']}</span>
                            </div>
                        </div>

                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-success"><fmt:message key="button.dialog.add"/></button>
                    <a href="${abs_path}/controller?command=show_all_authors" id="cancel" name="cancel"
                       class="btn btn-primary"><fmt:message key="button.dialog.cancel"/></a>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
