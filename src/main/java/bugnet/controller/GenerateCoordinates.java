package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;
import bugnet.persistence.LocationBuilder;
import bugnet.util.MessageAttribute;
import bugnet.util.UserFeedback;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Generates coordinates based on location listed in the table
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = {"/coordinates"}
)
public class GenerateCoordinates extends HttpServlet {

    /**
     * Receives id of record getting the coordinates generated for
     * Gets the record from that id for generating
     *
     * @param req http request object
     * @param resp http response object
     * @throws IOException issue with input/output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int editId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen specimen = dao.getById(editId);

            String message = saveCoordinates(specimen, dao);
            req.getSession().setAttribute(MessageAttribute.SPECIMEN.getAttribute(), message);
        }
        resp.sendRedirect("bugs");

    }

    /**
     * Sends the location the insect got collected at to get back coordinates
     * Saves the coordinates to the insect record
     *
     * @param specimen Insect being checked
     * @param dao data access object so insect can be modified
     * @return message based on results of search
     */
    public String saveCoordinates(Specimen specimen, GenericDao<Specimen> dao) {
        LocationBuilder coordinateGenerator = new LocationBuilder();
        coordinateGenerator.findCoordinates(specimen.getCollectedLocation());

        if (coordinateGenerator.getResults().size() > 0) {

            specimen.setLatitude(coordinateGenerator.getLatitude());
            specimen.setLongitude(coordinateGenerator.getLongitude());
            dao.saveOrUpdate(specimen);

            return UserFeedback.COORDINATE_SUCCESS.getMessage();
        } else {

            return UserFeedback.COORDINATE_FAILURE.getMessage();
        }
    }
}
