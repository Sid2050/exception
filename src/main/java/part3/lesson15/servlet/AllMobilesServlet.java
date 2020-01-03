package part3.lesson15.servlet;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import part3.lesson15.dao.MobileDao;
import part3.lesson15.pojo.Mobile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/allmobiles", name = "Mobiles")
public class AllMobilesServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;
    private Logger logger = LogManager.getLogger(AppContextListener.class);

//    @Override
//    public void init() throws ServletException {
//        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
//        super.init();
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Mobile> mobiles = mobileDao.getAllMobile();
        req.setAttribute("mobiles", mobiles);
        req.setAttribute("PageTitle", "Mobiles");
        req.setAttribute("PageBody", "allmobiles.jsp");
        req.getRequestDispatcher("/layout.jsp")
            .forward(req, resp);
    }
}
