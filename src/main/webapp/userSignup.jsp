<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <form action="signup" method="POST" class="form mb-3">
        <div class="row">
            <div class="col-4 mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" maxlength="25" required>
            </div>
        </div>
        <div class="row">
            <div class="col-4 mb-3">
                <label for="password1" class="form-label">Password</label>
                <input type="password" id="password1" name="password1" class="form-control" maxlength="40" required>
            </div>
        </div>
        <div class="row">
            <div class="col-4 mb-3">
                <label for="password2" class="form-label">Confirm Password</label>
                <input type="password" id="password2" name="password2" class="form-control" maxlength="40" required>
            </div>
        </div>

        <button type="submit" name="submitUser" class="btn btn-primary">Submit</button>
    </form>

    <c:if test="${not empty signupMessage}">
        <h3>${signupMessage}</h3>
        <c:remove var="signupMessage" />
    </c:if>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>
