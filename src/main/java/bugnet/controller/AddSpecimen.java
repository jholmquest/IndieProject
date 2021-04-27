package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.entity.User;
import bugnet.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(
        urlPatterns = {"/newBug"}
)
public class AddSpecimen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addSpecimen.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate collectedDate = LocalDate.parse(req.getParameter("collectedDate"));
        User user = (User)req.getSession().getAttribute("sessionUser");
        Specimen newSpecimen = new Specimen(
                req.getParameter("determination"),
                req.getParameter("location"),
                collectedDate,
                req.getParameter("notes"),
                user);

        GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
        dao.insert(newSpecimen);
        user.addSpecimen(newSpecimen);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/.");
        dispatcher.forward(req, resp);
    }
}
