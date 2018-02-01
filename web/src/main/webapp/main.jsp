<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Main page</title>
</head>
<body id="app-layout">

<c:import url="jsp/include/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-12 col-md-offset-0">
            <div class="panel panel-default">
                <div class="panel-heading">Main page</div>

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_all_users">
                    <input type="submit" value="Get all users list"/>
                </form>

                <br/>


                <form action="controller" method="get">
                    <input type="hidden" name="command" value="to_find_book_page">
                    <input type="submit" value="Find book"/>
                </form>

            </div>
        </div>
    </div>
</div>

<c:import url="jsp/include/footer.jsp"/>

</body>
</html>