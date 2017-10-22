<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Documents</title>
</head>
<style>
    <%@include file="../resources/css/style.css"%>
</style>
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
    <button><a href="/addFolder?parentId=${parentId}">Create folder</a></button>
    <button><a href="/upload?parentId=${parentId}">Add document</a></button>
</body>
</html>
