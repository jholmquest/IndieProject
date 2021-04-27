package bugnet.controller;

import bugnet.entity.Specimen;
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
        urlPatterns = {"/bugs"}
)
public class SpecimenDisplay extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
        User user = (User)req.getSession().getAttribute("sessionUser");
        List<Specimen> specimens = dao.findByPropertyEqual(Column.USER, user);

        req.setAttribute("specimens", specimens);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/specimenDisplay.jsp");
        dispatcher.forward(req, resp);
    }
}
