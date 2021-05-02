package bugnet.controller;

import bugnet.api.ResultsItem;
import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;
import bugnet.persistence.LocationBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Generates coordinates based on location listed in the table
 */
@WebServlet(
        urlPatterns = {"/coordinates"}
)
public class GenerateCoordinates extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int editId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen specimen = dao.getById(editId);

            LocationBuilder coordinateGenerator = new LocationBuilder();
            coordinateGenerator.findCoordinates(specimen.getCollectedLocation());
            if (coordinateGenerator.getResults().size() > 0) {
                specimen.setLatitude(coordinateGenerator.getLatitude());
                specimen.setLongitude(coordinateGenerator.getLongitude());
                dao.saveOrUpdate(specimen);
            }
        }
        resp.sendRedirect("bugs");

    }
}
