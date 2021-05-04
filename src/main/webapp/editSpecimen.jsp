<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <form action="editBug" method="POST" class="form">
        <div class="row">
            <div class="mb-3 col-4">
                <label for="determination">What is it?</label>
                <input type="text" id="determination" name="determination" value="${specimen.bugName}" maxlength="100" required>
            </div>
            <div class="mb-3 col-4">
                <label for="location">Where did you collect it?</label>
                <input type="text" id="location" name="location" value="${specimen.collectedLocation}" maxlength="50" required>
            </div>
            <div class="mb-3 col-4">
                <label for="collectedDate">When did you collect it?</label>
                <input type="date" id="collectedDate" name="collectedDate" value="${specimen.collectedDate}" required>
            </div>
            <div class="mb-3 col-4">
                <label for="latitude">Latitude</label>
                <input type="text" id="latitude" name="latitude" value="${specimen.latitude}" pattern="^[\+\-]?[\d]+\.?[\d]*$" title="decimal format">
            </div>
            <div class="mb-3 col-4">
                <label for="longitude">Longitude</label>
                <input type="text" id="longitude" name="longitude" value="${specimen.longitude}" pattern="^[\+\-]?[\d]+\.?[\d]*$" title="decimal format">
            </div>
            <div class="mb-3 col-6">
                <label for="notes">Notes</label>
                <textarea id="notes" name="notes">${specimen.bugNotes}</textarea>
            </div>
        </div>
        <input type="hidden" value="${specimen.id}" name="id">
        <button type="submit" name="submitSpecimen" class="btn btn-primary">Submit</button>
    </form>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>