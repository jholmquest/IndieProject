package bugnet.controller;

import bugnet.entity.User;
import bugnet.persistence.UserDao;

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

        UserDao dao = new UserDao();
        List<User> users = dao.getAllUsers();

        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userDisplay.jsp");
        dispatcher.forward(req, resp);
    }
}
