<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <form action="editBug" method="POST">
        <label for="determination">What is it?</label>
        <input type="text" id="determination" name="determination" value="${specimen.bugName}">
        <label for="location">Where did you collect it?</label>
        <input type="text" id="location" name="location" value="${specimen.collectedLocation}">
        <label for="collectedDate">When did you collect it?</label>
        <input type="date" id="collectedDate" name="collectedDate" value="${specimen.collectedDate}">
        <label for="notes">Notes</label>
        <textarea id="notes" name="notes">${specimen.bugNotes}</textarea>
        <input type="hidden" value="${specimen.id}">
        <button type="submit" name="submitSpecimen">Submit</button>
    </form>
</main>
</body>
</html>