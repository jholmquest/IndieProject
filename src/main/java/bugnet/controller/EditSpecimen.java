package bugnet.controller;

import bugnet.entity.Specimen;
import bugnet.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/editBug"}
)
public class EditSpecimen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null) {
            int editId = Integer.parseInt(idString);
            GenericDao<Specimen> dao = new GenericDao<>(Specimen.class);
            Specimen toEdit = dao.getById(editId);
            req.setAttribute("specimen", toEdit);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editSpecimen.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("bugs");
        }


    }
}
