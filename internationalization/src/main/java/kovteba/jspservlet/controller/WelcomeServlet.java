package kovteba.jspservlet.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String i18nValue = (String) req.getSession().getAttribute("i18n");

        if (i18nValue == null){
            req.getSession().setAttribute("i18n", "MessagesBundle_en_US");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/templates/index.jsp");
        dispatcher.forward(req, resp);

    }
}