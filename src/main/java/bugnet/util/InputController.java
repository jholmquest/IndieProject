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

    default String checkId(int id) {
        if (id != 0) {
            return UserFeedback.INSERT_SUCCESS.getMessage() + id;
        } else {
            return UserFeedback.INSERT_FAILURE.getMessage();
        }
    }

    default Boolean isOwner(Specimen specimen, HttpServletRequest req) {
        return specimen.getUser().equals(req.getSession().getAttribute("sessionUser"));
    }

    default void illegalAccess(HttpServletRequest req) {
        req.getSession().setAttribute(MessageAttribute.SPECIMEN.getAttribute(),
                UserFeedback.ILLEGAL_ACCESS.getMessage());
    }

    default void specimenNotFound(HttpServletRequest req) {
        req.getSession().setAttribute(MessageAttribute.SPECIMEN.getAttribute(),
                UserFeedback.NOT_FOUND.getMessage());
    }
}
