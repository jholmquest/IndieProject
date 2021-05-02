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
        <th>Latitude</th>
        <th>Longitude</th>
    </tr>
    <c:forEach var="specimen" items="${specimens}">
        <tr>
            <td>${specimen.bugName}</td>
            <td>${specimen.collectedLocation}</td>
            <td>${specimen.collectedDate}</td>
            <td>${specimen.bugNotes}</td>
            <td>${specimen.latitude}</td>
            <td>${specimen.longitude}</td>
            <td><a href="editBug?id=${specimen.id}">edit</a></td>
            <td><a href="deleteBug?id=${specimen.id}">delete</a></td>
            <td><a href="coordinates?id=${specimen.id}">Generate Coordinates(Experimental)</a></td>
        </tr>
    </c:forEach>

    </table>

    <c:if test="${not empty coordinateMessage}">
        <h3>${coordinateMessage}</h3>
        <c:remove var="coordinateMessage" />
    </c:if>
</main>
</body>
</html>