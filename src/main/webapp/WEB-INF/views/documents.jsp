<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Documents</title>
</head>
<style>
    <%@include file="../resources/css/style.css"%>
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<script src="../resources/js/upload.js"></script>
<body>
    <table width="600px">
        <tr>
            <td><b>Name</b></td>
            <td><b>Created Date</b></td>
            <td><b>Action</b></td>
        </tr>
        <tr>
            <td><img src="../resources/images/nav.gif"> <a href="/document?id=${stepback}">...</a> </td>
        </tr>
    <c:forEach var="folder" items="${folders}">
        <tr>
            <td><img src="../resources/images/nav.gif"> <a href="/document?id=${folder.id}">${folder.name}</a></td>
            <td>${folder.date}</td>
            <td><a href="/editFolder?id=${folder.id}"><img src="../resources/images/edit.gif"></a> | <a href="/deleteFolder?id=${folder.id}"><img src="../resources/images/cross-icon.png"></a></td>
        </tr>
    </c:forEach>
    <c:forEach var="document" items="${documents}">
        <tr>
            <td><img src="../resources/images/${document.type}.png">${document.name}</td>
            <td>${document.date}</td>
            <td><img src="../resources/images/edit.gif">|<a href="/deleteDoc?id=${document.id}&parentId=${document.parentId}"><img src="../resources/images/cross-icon.png"></a></td>
        </tr>
    </c:forEach>
    </table>

    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createModal">Create folder</button>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal">Add document</button>


    <jsp:include page="createModal.jsp"/>
    <jsp:include page="uploadModal.jsp"/>


</body>
</html>
