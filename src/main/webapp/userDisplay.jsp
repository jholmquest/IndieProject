<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<h2>Logged in as ${loggedInAs}</h2>
<table>
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