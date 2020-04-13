package kovteba.jspservlet.i18n;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "i18nDE",
        urlPatterns = "/de",
        description = "Change language on de"
)
public class i18nDE extends HttpServlet {


    @Override
    public void init() throws ServletException {
        System.out.println("DE");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("i18n", "MessagesBundle_de_DE");

        System.out.println("DE");

        resp.sendRedirect("");

    }

}
