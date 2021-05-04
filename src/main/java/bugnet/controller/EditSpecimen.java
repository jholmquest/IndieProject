package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;
import bugnet.util.InputController;
import bugnet.util.MessageAttribute;
import bugnet.util.UserFeedback;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Controls the editing of insect records
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = {"/editBug"}
)
public class EditSpecimen extends HttpServlet implements InputController {

    /**
     * Gets the insect that's being edited and passes it to the jsp to give current values for editing
     * Checks to make sure the current user is actually allowed to edit the record
     *
     * @param req http request object
     * @param resp http response object
     * @throws ServletException issue with the servlet
     * @throws IOException issue with input/output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int editId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen toEdit = dao.getById(editId);

            if (toEdit == null) {
                specimenNotFound(req);
                resp.sendRedirect("bugs");

            } else if (isOwner(toEdit, req)) {

                req.setAttribute("specimen", toEdit);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/editSpecimen.jsp");
                dispatcher.forward(req, resp);

            } else {
                illegalAccess(req);
                resp.sendRedirect("bugs");
            }
        } else {

            resp.sendRedirect("bugs");
        }
    }

    /**
     * collects parameter from form and updates the associated specimen
     *
     * @param req the http request
     * @param resp the http response
     * @throws IOException issue with input/output
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LocalDate collectedDate = LocalDate.parse(req.getParameter("collectedDate"));
        int specimenId = Integer.parseInt(req.getParameter("id"));
        Double latitude = setCoordinate(req.getParameter("latitude"));
        Double longitude = setCoordinate(req.getParameter("longitude"));
        GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
        Specimen updateSpecimen = dao.getById(specimenId);

        updateSpecimen.setBugName(req.getParameter("determination"));
        updateSpecimen.setCollectedLocation(req.getParameter("location"));
        updateSpecimen.setCollectedDate(collectedDate);
        updateSpecimen.setBugNotes(req.getParameter("notes"));
        updateSpecimen.setLatitude(latitude);
        updateSpecimen.setLongitude(longitude);
        dao.saveOrUpdate(updateSpecimen);
        req.getSession().setAttribute(MessageAttribute.SPECIMEN.getAttribute(), UserFeedback.UPDATE_SUCCESS.getMessage());
        resp.sendRedirect("bugs");
    }



}
