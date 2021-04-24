<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<table>
    <tr>
        <th>Specimen Name</th>
    </tr>
    <c:forEach var="specimen" items="${specimens}">
        <tr>
            <td>${specimen.bugname}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>