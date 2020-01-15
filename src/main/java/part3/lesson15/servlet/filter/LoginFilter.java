package part3.lesson15.servlet.filter;

import part3.lesson15.pojo.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @autor Aleksey Danilchik
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter {
    private final Logger LOGGER = Logger.getLogger(LoginFilter.class.getName());
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userLogin");

        String servletPath = req.getServletPath();

        LOGGER.info(servletPath);
        if ("/accessDenied.jsp".equals(servletPath) || "/login".equals(servletPath)) {
            chain.doFilter(req, res);
            return;
        }

        if ("/".equals(servletPath)) {
            chain.doFilter(req, res);
            return;
        }

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/accessDenied.jsp");
            return;
        } else {
            chain.doFilter(req, res);
        }
    }
}
