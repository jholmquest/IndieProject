<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
<form action="newBug" method="POST">
    <label for="determination">What is it?</label>
    <input type="text" id="determination" name="determination">
    <label for="location">Where did you collect it?</label>
    <input type="text" id="location" name="location">
    <label for="collectedDate">When did you collect it?</label>
    <input type="date" id="collectedDate" name="collectedDate">
    <label for="notes">Notes</label>
    <textarea id="notes" name="notes">Enter additional notes...</textarea>
    <button type="submit" name="submitSpecimen">Submit</button>
</form>
</main>
</body>
</html>