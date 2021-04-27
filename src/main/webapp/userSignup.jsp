<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body>
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <form action="#" method="POST">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" maxlength="25" required>
        <label for="password1">Password</label>
        <input type="password" id="password1" name="password1" maxlength="40" required>
        <label for="password2">Confirm Password</label>
        <input type="password" id="password2" name="password2" maxlength="40" required>
        <button type="submit" name="submitUser">Submit</button>
    </form>
</main>
</body>
</html>
