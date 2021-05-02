package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;
import bugnet.util.InputController;
import bugnet.util.UserFeedback;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * facilitates the deletion of insects
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = {"/deleteBug"}
)
public class DeleteSpecimen extends HttpServlet implements InputController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int deleteId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen toDelete = dao.getById(deleteId);
            if (toDelete == null) {
                specimenNotFound(req);
            } else if (isOwner(toDelete, req)) {
                dao.delete(toDelete);
                req.getSession().setAttribute("specimenMessage", UserFeedback.DELETE_SUCCESS.getMessage());
            } else {
                illegalAccess(req);
            }
            resp.sendRedirect("bugs");
        }


    }
}
