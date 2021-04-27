package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/deleteBug"}
)
/**
 * facilitates the deletion of insects
 *
 * @author James Holmquest
 */
public class DeleteSpecimen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int deleteId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen toDelete = dao.getById(deleteId);
            dao.delete(toDelete);
            resp.sendRedirect("bugs");
        }


    }
}
