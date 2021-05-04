<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container">
<c:import url="template/header.jsp" />
<main>
    <c:import url="template/nav.jsp" />
    <FORM ACTION="j_security_check" METHOD="POST" class="form mb-3">
        <div class="row">
            <div class="col-4 mb-3">
                <label for="j_username" class="form-label">User name:</label>
                <INPUT TYPE="TEXT" NAME="j_username" id="j_username" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="col-4 mb-3">
                <label for="j_password" class="form-label">Password: </label>
                <INPUT TYPE="PASSWORD" NAME="j_password" id="j_password" class="form-control">
            </div>
        </div>
        <INPUT TYPE="SUBMIT" VALUE="Log In" class="btn btn-primary">
    </FORM>
</main>
<c:import url="template/scripts.jsp"/>
</body>
</html>
