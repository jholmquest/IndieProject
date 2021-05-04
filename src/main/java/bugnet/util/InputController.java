package bugnet.util;

import bugnet.entity.Specimen;
import javax.servlet.http.HttpServletRequest;

/**
 * Contains methods with basic logic for making form input usable
 *
 * @author James Holmquest
 */
public interface InputController {

    /**
     * Checks if any text was entered from the form, and if
     * any text was it converts it to a double
     *
     * @param coordinateText text form of coordinate being parsed
     * @return null if no text, otherwise the converted double
     */
    default Double setCoordinate(String coordinateText) {

        if (coordinateText.isEmpty()) {
            return null;
        } else {
            return Double.valueOf(coordinateText);
        }
    }

    /**
     * Checks if there was a successful insert based on returned id
     * @param id id of new specimen or 0 representing a failure
     * @return feedback message
     */
    default String checkId(int id) {
        if (id != 0) {
            return UserFeedback.INSERT_SUCCESS.getMessage() + id;
        } else {
            return UserFeedback.INSERT_FAILURE.getMessage();
        }
    }

    /**
     * Checks if user in session is also user associated with specimen
     * @param specimen specimen being checked
     * @param req http request
     * @return if the session user is also the owner of the specimen
     */
    default Boolean isOwner(Specimen specimen, HttpServletRequest req) {
        return specimen.getUser().equals(req.getSession().getAttribute("sessionUser"));
    }

    /**
     * sets message if user can't access that record
     * @param req http request
     */
    default void illegalAccess(HttpServletRequest req) {
        req.getSession().setAttribute(MessageAttribute.SPECIMEN.getAttribute(),
                UserFeedback.ILLEGAL_ACCESS.getMessage());
    }

    /**
     * sets message if record doesn't exist
     * @param req http request
     */
    default void specimenNotFound(HttpServletRequest req) {
        req.getSession().setAttribute(MessageAttribute.SPECIMEN.getAttribute(),
                UserFeedback.NOT_FOUND.getMessage());
    }
}
