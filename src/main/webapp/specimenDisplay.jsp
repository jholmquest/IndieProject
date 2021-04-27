<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<c:import url="template/header.jsp" />
<main>
    <table>
    <c:import url="template/nav.jsp" />
    <tr>
        <th>Specimen Name</th>
    </tr>
    <c:forEach var="specimen" items="${specimens}">
        <tr>
            <td>${specimen.bugName}</td>
        </tr>
    </c:forEach>

    </table>
</main>
</body>
</html>