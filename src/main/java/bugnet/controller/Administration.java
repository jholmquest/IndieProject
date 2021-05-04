package bugnet.controller;

import bugnet.entity.User;
import bugnet.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Displays users for easy viewing
 * Based on user display exercise
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = {"/admin"}
)
public class Administration extends HttpServlet {

    /**
     * Gets all the users as well as the current user logged in
     * Passes the values to the display page
     * @param req http request
     * @param resp http response
     * @throws ServletException issue with the servlet
     * @throws IOException issue with input/output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<User> dao = new GenericDao<>(User.class);
        List<User> users = dao.getAll();

        req.setAttribute("loggedInAs", req.getRemoteUser());
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(req, resp);
    }
}
