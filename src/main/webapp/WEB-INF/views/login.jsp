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
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in</h1>
            <div class="account-wall">
                <img class="profile-img" src="../resources/images/main.png"
                     alt="">

                <form class="form-signin" action="/j_spring_security_check" method="post">
                    <%--<label for="j_username">Username: </label>--%>
                    <input class="form-control" placeholder="Login" id="j_username" name="j_username" size="20" maxlength="50" type="text" />
                        <%--<label for="j_password">Password: </label>--%>
                    <input class="form-control" placeholder="Password" required id="j_password" name="j_password" size="20" maxlength="50" type="password" />
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign In </button>
                </form>

            </div>
        </div>
    </div>




</div>






</body>



</html>