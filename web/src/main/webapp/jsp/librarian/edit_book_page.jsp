<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="label.libr.edit_book"/>: #${book.id}</title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <form class="form" action="${abs_path}/controller" method="get">
        <input type="hidden" name="command" value="update_book_info">
        <input type="hidden" name="id" value="${book.id}">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="label.libr.edit_book"/></h5>
                <div class="card-text m-2">
                    <fieldset>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="title"><fmt:message key="book.info.title"/></label>
                            <div class="col-9">
                                <input class="form-control " id="title" value="${book.title}" name="title"
                                       type="text" required="" >
                                <span class="form-text small text-muted"><fmt:message key="tips.book.title"/></span>
                                <span class="form-text small text-danger">${errors['title']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="publish_year"><fmt:message key="book.info.publish_year"/></label>
                            <div class="col-9">
                                <input class="form-control " id="publish_year" value="${book.publishYear}" name="publish_year"
                                       type="text" required="" >
                                <span class="form-text small text-muted"><fmt:message key="tips.book.publish_year"/></span>
                                <span class="form-text small text-danger">${errors['publish_year']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="total_amount"><fmt:message key="book.info.total_amount"/></label>
                            <div class="col-9">
                                <input class="form-control " id="total_amount" value="${book.totalAmount}" name="total_amount"
                                       type="text" required="" >
                                <span class="form-text small text-muted"><fmt:message key="tips.book.total_amount"/></span>
                                <span class="form-text small text-danger">${errors['total_amount']}</span>
                            </div>
                        </div>


                        <div class="form-group row">
                            <label class="col-form-label col-3" for="real_amount"><fmt:message key="book.info.real_amount"/></label>
                            <div class="col-9">
                                <input class="form-control " id="real_amount" value="${book.realAmount}" name="real_amount"
                                       type="text" required="" >
                                <span class="form-text small text-muted"><fmt:message key="tips.book.real_amount"/></span>
                                <span class="form-text small text-danger">${errors['real_amount']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="genres"><fmt:message key="book.info.genre"/></label>
                            <div class="col-9">
                                <select id="genres" name="genres" class="form-control" multiple size="6">
                                    <c:forEach var="genre" items="${genres}" >
                                        <c:set var="selected" value=""/>
                                        <c:forEach var="curr_genre" items="${book.genres}" >
                                                <c:if test="${genre.id == curr_genre.id}">
                                                    <c:set var="selected" value="selected"/>
                                                </c:if>
                                        </c:forEach>
                                        <option value="${genre.id}" ${selected}>${genre.type}</option>
                                    </c:forEach>
                                </select>
                                <span class="form-text small text-muted"><fmt:message key="tips.book.title"/></span>
                                <span class="form-text small text-danger">${errors['genres']}</span>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${book.isDeleted eq 'true'}">
                                <c:set var="bool" value="1"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="bool" value="0"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="status"><fmt:message key="user.status"/></label>
                            <div class="col-9">
                                <select id="status" name="status" class="form-control">
                                    <c:forEach var="j" begin="0" end="1">
                                        <c:choose>
                                            <c:when test="${j == bool}">
                                                <option value="${j}" selected><fmt:message key="user.status.${j}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${j}"><fmt:message key="user.status.${j}"/></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-success"><fmt:message key="button.dialog.apply"/></button>
                    <a href="${abs_path}/controller?command=show_all_books" id="cancel" name="cancel" class="btn btn-primary"><fmt:message key="button.dialog.cancel"/></a>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
