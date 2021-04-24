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

@WebServlet(
        urlPatterns = {"/test"}
)
public class UserDisplay extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<User> dao = new GenericDao<>(User.class);
        List<User> users = dao.getAll();

        req.setAttribute("loggedInAs", req.getRemoteUser());
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userDisplay.jsp");
        dispatcher.forward(req, resp);
    }
}
