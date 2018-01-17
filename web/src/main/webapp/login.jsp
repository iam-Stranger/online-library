<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="ctg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="sign_in"/>
    <caption>login</caption><br/>
    <input type="text" name="login" value=""/><br/>
    <caption>password</caption><br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit" value="login">
</form>

</body>
</html>
