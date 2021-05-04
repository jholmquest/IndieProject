<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<h2>Logged in as ${loggedInAs}</h2>
<h2>If you can see this page you are an admin</h2>
<table class="table">
    <tr>
        <th>Username</th>
        <th>ID</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.id}</td>
        </tr>
    </c:forEach>

</table>
<c:import url="template/scripts.jsp"/>
</body>
</html>