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
        <th>Location</th>
        <th>Date</th>
        <th>Notes</th>
    </tr>
    <c:forEach var="specimen" items="${specimens}">
        <tr>
            <td>${specimen.bugName}</td>
            <td>${specimen.collectedLocation}</td>
            <td>${specimen.collectedDate}</td>
            <td>${specimen.bugNotes}</td>
        </tr>
    </c:forEach>

    </table>
</main>
</body>
</html>