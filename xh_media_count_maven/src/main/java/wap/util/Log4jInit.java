package wap.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        String prefix = config.getServletContext().getRealPath("/"); //鑾峰彇褰撳墠璺緞
        String file = config.getInitParameter("log4j");//浠巜eb.xml涓幏鍙栧弬鏁板�,鎵惧埌log4j杩欎釜鏂囦欢
        System.out.println("webRoot====="+prefix);
        System.setProperty("webRoot", prefix); //log4j.properties鏂囦欢涓殑鍙橀噺鏄湪杩欓噷璁剧疆鐨�
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
        }
    }
}
