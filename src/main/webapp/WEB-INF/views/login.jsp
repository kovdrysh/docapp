<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <script type="text/javascript" src="../resources/js/script.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/popper.js"></script>
    <script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/upload.js"></script>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.2.css">
</head>
<style>
    <%@include file="../resources/css/style.css"%>
</style>


<body>
<h1>Spring Security - Sign In</h1>

<div style="color: red">${message}</div>
<form class="login-form" action="/j_spring_security_check" method="post">
    <label for="j_username">Username: </label>
    <input id="j_username" name="j_username" size="20" maxlength="50" type="text" />

    <label for="j_password">Password: </label>
    <input id="j_password" name="j_password" size="20" maxlength="50" type="password" />

    <input type="submit" value="Login" />
</form>

</body>



</html>