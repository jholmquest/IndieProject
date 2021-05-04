<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <form action="editBug" method="POST" class="form mb-3">
        <div class="row">
            <div class="mb-3 col-4">
                <label for="determination" class="form-label">*What is it?</label>
                <input type="text" id="determination" name="determination" class="form-control" value="${specimen.bugName}" maxlength="100" required>
            </div>
            <div class="mb-3 col-4">
                <label for="location" class="form-label">*Where did you collect it?</label>
                <input type="text" id="location" name="location" class="form-control" value="${specimen.collectedLocation}" maxlength="50" required>
            </div>
            <div class="mb-3 col-4">
                <label for="collectedDate" class="form-label">*When did you collect it?</label>
                <input type="date" id="collectedDate" name="collectedDate" class="form-control" value="${specimen.collectedDate}" required>
            </div>
            <div class="mb-3 col-4">
                <label for="latitude" class="form-label">Latitude</label>
                <input type="text" id="latitude" name="latitude" class="form-control" value="${specimen.latitude}" pattern="^[\+\-]?[\d]+\.?[\d]*$" title="decimal format">
            </div>
            <div class="mb-3 col-4" class="form-label">
                <label for="longitude">Longitude</label>
                <input type="text" id="longitude" name="longitude" class="form-control" value="${specimen.longitude}" pattern="^[\+\-]?[\d]+\.?[\d]*$" title="decimal format">
            </div>
            <div class="mb-3 col-6">
                <label for="notes" class="form-label">Notes</label>
                <textarea id="notes" name="notes" class="form-control">${specimen.bugNotes}</textarea>
            </div>
        </div>
        <input type="hidden" value="${specimen.id}" name="id">
        <button type="submit" name="submitSpecimen" class="btn btn-primary">Submit</button>
    </form>
    <p class="text-muted">*required</p>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>