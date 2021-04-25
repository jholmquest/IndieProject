<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul>
        <c:choose>
            <c:when test="${REMOTE_USER}">

                <li><a href="bugs">My Bugs</a></li>
                <li><a href="newBug">Add a Bug</a></li>
                <li><a href="admin">Admin</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="signup">Sign Up</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
