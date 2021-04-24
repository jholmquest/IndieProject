<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<h2>Add a Specimen</h2>
<form action="#" method="POST">
    <label for="determination">What is it?</label>
    <input type="text" id="determination">
    <label for="location">Where did you collect it?</label>
    <input type="text" id="location">
    <label for="collectedDate">When did you collect it?</label>
    <input type="date" id="collectedDate">
    <label for="notes">Notes</label>
    <textarea id="notes">Enter additional notes...</textarea>
</form>
</body>
</html>