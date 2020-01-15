package part3.lesson15.servlet;

import part3.lesson15.dao.UserDao;
import part3.lesson15.pojo.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @autor Aleksey Danilchik
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Login Page");
        req.setAttribute("PageBody", "loginform.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String loginName = req.getParameter("loginId");
        User user = userDao.getUserByLoginId(loginName);

        HttpSession session = req.getSession();
        session.setAttribute("userLogin", user);

        if (user != null) {
            resp.sendRedirect(req.getContextPath() + "/mainpage.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/accessDenied.jsp");
        }
    }
}
