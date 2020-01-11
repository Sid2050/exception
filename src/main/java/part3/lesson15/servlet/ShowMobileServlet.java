package part3.lesson15.servlet;


import part3.lesson15.dao.MobileDao;
import part3.lesson15.pojo.Mobile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showmobile")
public class ShowMobileServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = mobileDao.getMobileById(Integer.valueOf(mobileId));
        if (mobile == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("/notfound.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("mobile", mobile);
        req.setAttribute("PageTitle", "Mobile");
        req.setAttribute("PageBody", "showmobile.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);

//        req.getRequestDispatcher("/showmobile.jsp").forward(req, resp);
    }
}
