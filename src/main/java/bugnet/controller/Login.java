package bugnet.controller;

import bugnet.entity.User;
import bugnet.persistence.Column;
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
        urlPatterns = "/login"
)
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<User> dao = new GenericDao<>(User.class);
        List<User> user = dao.findByPropertyEqual(Column.USERNAME, req.getRemoteUser());
        req.getSession().setAttribute("sessionUser", user.get(0));
        resp.sendRedirect(".");
    }

}
