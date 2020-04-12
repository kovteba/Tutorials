package kovteba.jspservlet.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "index",
        urlPatterns = "/"
)
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("INDEX PAGE");


        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/index.jsp");
        dispatcher.forward(req, resp);



        System.out.println("!!!!!!!!!!!!");
    }
}