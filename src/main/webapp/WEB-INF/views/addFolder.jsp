<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new folder</title>
</head>
<body>
<form:form method="POST" action="/addFolder" modelAttribute="folder">
    <form:hidden path="id" />
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" maxlength="30" /></td>
        </tr>
        <tr style="display:none;">
            <td >Date:</td>
            <td ><form:input path="date"/></td>
        </tr>
        <tr style="display:none">
            <td>ParentId:</td>
            <td><form:input path="parentId"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" />
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
