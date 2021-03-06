<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <table class="table table-hover">
    <tr>
        <th>Specimen Name</th>
        <th>Location</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Date</th>
        <th>Notes</th>
        <th>Edit</th>
        <th>Delete</th>
        <th>Magic</th>
    </tr>
    <c:forEach var="specimen" items="${specimens}">
        <tr>
            <td>${specimen.bugName}</td>
            <td>${specimen.collectedLocation}</td>
            <td>${specimen.latitude}</td>
            <td>${specimen.longitude}</td>
            <td>${specimen.collectedDate}</td>
            <td>${specimen.bugNotes}</td>
            <td><a href="editBug?id=${specimen.id}">edit</a></td>
            <td><a href="deleteBug?id=${specimen.id}">delete</a></td>
            <td><a href="coordinates?id=${specimen.id}">Generate Coordinates (Experimental)</a></td>
        </tr>
    </c:forEach>

    </table>

    <c:if test="${not empty specimenMessage}">
        <h3>${specimenMessage}</h3>
        <c:remove var="specimenMessage" />
    </c:if>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>