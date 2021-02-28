<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<html>
<body>
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
</body>
</html>