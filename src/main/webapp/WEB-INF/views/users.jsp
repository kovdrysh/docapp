<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>All Users</title>
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
        <div class="logout">
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/logout"/>"><button type="button" class="btn btn-success btn-md text-right">Logout</button></a>
            </sec:authorize>
        </div>
    </div>
    <div class="tablediv" >
        <h1><b>All Users</b></h1>
        <table class="table table-hover table-condensed col-md-4 col-sm-12 col-xs-12 col-lg-4 main-table" >
            <thread>
                <tr>
                    <td><b>Name</b></td>
                    <td><b>Surname</b></td>
                    <td><b>Email</b></td>
                    <td><b>Login Name</b></td>
                    <td><b>User Role</b></td>
                    <td><b>Action</b></td>
                </tr>
            </thread>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><p>${user.name}</p></td>
                    <td><p>${user.surname}</p></td>
                    <td><p>${user.email}</p></td>
                    <td><p>${user.nickname}</p></td>
                    <td><p>${user.userRole}</p></td>
                    <td><a style="color: black" href="#" id="edit-user" data-href="/editUser" data-toggle="modal" data-target="#editUser" data-name="${user.name}" data-id="${user.id}"
                           data-surname="${user.surname}" data-email="${user.email}" data-nickname="${user.nickname}" data-password="${user.password}" data-userRole="${user.userRole}" data-action="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a> | <a style="color: black" href="#" data-href="/deleteUser?id=${user.id}" data-toggle="modal" data-target="#confirm-delete"><i class="fa fa-trash" aria-hidden="true"></i></a> </td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#addUser">Add new User</button>

</div>

<jsp:include page="addUser.jsp"/>
<jsp:include page="uploadModal.jsp"/>
<jsp:include page="confirmDeleteModal.jsp"/>
<jsp:include page="editUser.jsp"/>

<script>
    $('#confirm-delete').on('show.bs.modal', function(e) {
        $('.debug-url').html('Are you sure to delete this user?');
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });
    $('#editUser').on('show.bs.modal', function(e) {
        if ($(e.relatedTarget).data('action') === "edit")
            $(this).find('#exampleModalLabel').html("Edit User");
            $(this).find('#user-name').val($(e.relatedTarget).data('name'));
            $(this).find('#user-id').val($(e.relatedTarget).data('id'));
            $(this).find('#user-surname').val($(e.relatedTarget).data('surname'));
            $(this).find('#user-email').val($(e.relatedTarget).data('email'));
            $(this).find('#user-nickname').val($(e.relatedTarget).data('nickname'));
            $(this).find('#user-password').val($(e.relatedTarget).data('password'));
            var text = $(e.relatedTarget).data('userRole');
            $(this).find("#user-userRole option").filter(function() {
                return $(this).text() === text;
                console.log(text);
            }).prop('selected', true);
            $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
            $(this).find('#user-name').focus();
    });
</script>
</body>
</html>
