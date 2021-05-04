<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
<form action="newBug" method="POST" class="form">
    <div class="row">
        <div class="mb-3 col-4">
            <label for="determination" class="form-label">What is it?</label>
            <input type="text" id="determination" name="determination" class="form-control" maxlength="100" required>
        </div>
        <div class="mb-3 col-4">
            <label for="location" class="form-label">Where did you collect it?</label>
            <input type="text" id="location" name="location" class="form-control" maxlength="50" required>
        </div>
        <div class="mb-3 col-4">
            <label for="collectedDate" class="form-label">When did you collect it?</label>
            <input type="date" id="collectedDate" name="collectedDate" class="form-control" required>
        </div>
        <div class="mb-3 col-6">
            <label for="notes" class="form-label">Notes</label>
            <textarea id="notes" name="notes" class="form-control" rows="3">Enter additional notes...</textarea>
        </div>
    </div>
    <button type="submit" name="submitSpecimen" class="btn btn-primary">Submit</button>
</form>
    <c:if test="${not empty insertMessage}">
        <h3>${insertMessage}</h3>
        <c:remove var="insertMessage" />
    </c:if>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>