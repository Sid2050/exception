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

@WebServlet("/addmobile")
public class AddMobileServlet extends HttpServlet {
    private  Logger logger = LogManager.getLogger(AddMobileServlet.class);

    @Inject
    private MobileDao mobileDao;

//    @Override
//    public void init() throws ServletException {
//        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
//        super.init();
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New Mobiles");
        req.setAttribute("PageBody", "addform.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(null, model, Integer.valueOf(price), manufacturer);
        mobileDao.addMobile(mobile);

        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }
}
