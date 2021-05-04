<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <form action="editBug" method="POST">
        <label for="determination">What is it?</label>
        <input type="text" id="determination" name="determination" value="${specimen.bugName}" maxlength="100" required>
        <label for="location">Where did you collect it?</label>
        <input type="text" id="location" name="location" value="${specimen.collectedLocation}" maxlength="50" required>
        <label for="latitude">Latitude</label>
        <input type="text" id="latitude" name="latitude" value="${specimen.latitude}" pattern="^[\+\-]?[\d]+\.?[\d]*$" title="decimal format">
        <label for="longitude">Longitude</label>
        <input type="text" id="longitude" name="longitude" value="${specimen.longitude}" pattern="^[\+\-]?[\d]+\.?[\d]*$" title="decimal format">
        <label for="collectedDate">When did you collect it?</label>
        <input type="date" id="collectedDate" name="collectedDate" value="${specimen.collectedDate}" required>
        <label for="notes">Notes</label>
        <textarea id="notes" name="notes">${specimen.bugNotes}</textarea>
        <input type="hidden" value="${specimen.id}" name="id">
        <button type="submit" name="submitSpecimen">Submit</button>
    </form>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>