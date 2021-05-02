package bugnet.util;

import bugnet.entity.Specimen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    default Boolean isOwner(Specimen specimen, HttpServletRequest req) {
        return specimen.getUser().equals(req.getSession().getAttribute("sessionUser"));
    }

    default void illegalAccess(HttpServletRequest req) {
        req.getSession().setAttribute("specimenMessage", UserFeedback.ILLEGAL_ACCESS.getMessage());
    }
}
