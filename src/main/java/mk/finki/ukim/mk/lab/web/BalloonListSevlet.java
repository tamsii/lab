package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-list-servlet", urlPatterns = "/")
class BalloonListServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final SpringTemplateEngine templateEngine;

    BalloonListServlet(BalloonService balloonService, SpringTemplateEngine templateEngine) {
        this.balloonService = balloonService;
        this.templateEngine = templateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloons", balloonService.listAll());
        resp.setContentType("text/html;charset=UTF-8");
        templateEngine.process("listBalloons.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String desc = req.getParameter("color");

        if (desc == null) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("balloons", balloonService.listAll());
            context.setVariable("hasError", true);
            context.setVariable("error", "Please select a balloon.");
            resp.setContentType("text/html;charset=UTF-8");
            templateEngine.process("listBalloons.html", context, resp.getWriter());
            resp.sendRedirect("/");
        }
        String name = req.getParameter("color").split(" ")[0];

        Balloon b = new Balloon(name, desc);
        req.getSession().setAttribute("balloon", b);
        resp.sendRedirect("/selectBalloon");
    }
}
