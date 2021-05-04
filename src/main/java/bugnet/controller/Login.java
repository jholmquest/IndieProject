package bugnet.controller;

import bugnet.entity.User;
import bugnet.util.Column;
import bugnet.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Sets up environment after successful login
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = "/login"
)
public class Login extends HttpServlet {

    /**
     * Adds current user to the session
     * @param req http request
     * @param resp http response
     * @throws IOException issue with input/output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        GenericDao<User> dao = new GenericDao<>(User.class);
        List<User> user = dao.findByPropertyEqual(Column.USERNAME, req.getRemoteUser());
        req.getSession().setAttribute("sessionUser", user.get(0));
        resp.sendRedirect(".");
    }

}
