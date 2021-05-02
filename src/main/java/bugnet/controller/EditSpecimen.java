package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;
import bugnet.util.InputController;
import bugnet.util.UserFeedback;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(
        urlPatterns = {"/editBug"}
)
public class EditSpecimen extends HttpServlet implements InputController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int editId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen toEdit = dao.getById(editId);

            if (isOwner(toEdit, req)) {

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resp.sendRedirect("bugs");
    }



}
