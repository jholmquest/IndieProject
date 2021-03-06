package bugnet.controller;

import bugnet.entity.User;
import bugnet.persistence.AddUser;
import bugnet.util.MessageAttribute;
import bugnet.util.UserFeedback;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Facilitates user registration
 *
 * @author James Holmquest
 */
@WebServlet(
        urlPatterns = {"/signup"}
)
public class UserSignup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userSignup.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Checks if passwords match, if they do attempts to sign up user
     * @param req http request object
     * @param resp http response object
     * @throws IOException Issue with input/output
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        String signupMessage;
        if (password1.equals(password2)) {
            User newUser = new User(req.getParameter("username"), password1);
            AddUser signup = new AddUser();
            int id = signup.add(newUser);
            signupMessage = checkId(id);
        } else {
            signupMessage = UserFeedback.PASSWORDS_DIFFERENT.getMessage();
        }

        req.getSession().setAttribute(MessageAttribute.SIGNUP.getAttribute(), signupMessage);
        resp.sendRedirect("signup");
    }

    /**
     * checks the id passed in to generate feeback
     * @param id 0 if no record inserted, otherwise id of record
     * @return feedback message
     */
    public String checkId(int id) {
        if (id == 0) {
            return UserFeedback.GENERIC_FAILURE.getMessage();
        } else if (id == -1) {
            return UserFeedback.DUPLICATE_USER.getMessage();
        } else {
            return (UserFeedback.SIGNUP_SUCCESS.getMessage() + id);
        }
    }
}
