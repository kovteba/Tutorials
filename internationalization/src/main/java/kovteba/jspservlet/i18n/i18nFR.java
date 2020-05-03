package kovteba.jspservlet.i18n;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "i18nFR",
        urlPatterns = "/fr",
        description = "Change language on fr"
)
public class i18nFR extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("i18n", "MessagesBundle_fr_FR");

        resp.sendRedirect("");

    }
}
