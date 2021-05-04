package bugnet.controller;


import bugnet.entity.User;
import bugnet.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logs user out by destroying the session
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = "/logout"
)
public class Logout extends HttpServlet {

    /**
     * destroys the session and brings user back to home
     * @param req http request
     * @param resp http response
     * @throws IOException issue with input/output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.getSession().invalidate();
        resp.sendRedirect(".");
    }

}