<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul class="nav">
        <li class="nav-item"><a href="." class="nav-link">Home</a></li>
        <c:choose>
            <c:when test="${not empty sessionUser}">

                <li class="nav-item"><a href="bugs" class="nav-link">My Bugs</a></li>
                <li class="nav-item"><a href="newBug" class="nav-link">Add a Bug</a></li>
                <li class="nav-item"><a href="logout" class="nav-link">Logout(${sessionUser.username})</a></li>
            </c:when>
            <c:otherwise>
                <li class="nav-item"><a href="signup" class="nav-link">Sign Up</a></li>
                <li class="nav-item"><a href="login" class="nav-link">Log In</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
