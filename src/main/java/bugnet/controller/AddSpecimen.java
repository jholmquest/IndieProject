package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.entity.User;
import bugnet.persistence.GenericDao;
import bugnet.util.InputController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Facilitates adding of a specimen to the database
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = {"/newBug"}
)
public class AddSpecimen extends HttpServlet implements InputController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addSpecimen.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Adds specimen with data that has been filled in
     * @param req the http request
     * @param resp the http response
     * @throws ServletException issue with the servlet
     * @throws IOException issue with I/O
     */
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
        int id = dao.insert(newSpecimen);

        if (id != 0) {
            user.addSpecimen(newSpecimen);
        }

        String insertMessage = checkId(id);

        req.getSession().setAttribute("insertMessage", insertMessage);
        resp.sendRedirect("newBug");
    }

}
