package com.xh.zfb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xh.zfb.util.DbConn;

public class RecordVisitLog extends HttpServlet {
	private DbConn dbConn;
	/**
	 * Constructor of the object.
	 */
	public RecordVisitLog() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		DbConn dbConn = new DbConn();
		String time = "";
		String url = "";
		String ip = "";
		String ua = "";
		String refer = "";
		String globalid = "";
		try{
			PrintWriter out = response.getWriter();
			Enumeration<String> headers = request.getHeaderNames();
			while(headers.hasMoreElements()){
				String header = headers.nextElement();
//				System.out.println(header.toLowerCase()+"===="+request.getHeader(header).toString());
				if("user-agent".equals(header.toLowerCase())){
					ua = request.getHeader(header).toString();
				}else if("referer".equals(header.toLowerCase())){
					refer = request.getHeader(header).toString();
				}
//				else if("host".equals(header.toLowerCase())){
//					ip = request.getHeader(header).toString();
//				}
			}
			ip = request.getRemoteAddr();
			if(request.getParameter("time") != null)
				time = request.getParameter("time");
			if(request.getParameter("url") != null)
				url = request.getParameter("url");
			if(request.getParameter("globalid") != null)
				globalid = request.getParameter("globalid");
			Timestamp ts;
			if(!"".equals(time))
				ts = new Timestamp(Long.parseLong(time));
			else
				ts = new Timestamp(System.currentTimeMillis());
			StringBuffer insert = new StringBuffer("INSERT INTO visit_log (url, ip, user_agent, visit_time, refer, global_id) VALUES ('");
			insert.append(url).append("', '").append(ip).append("', '").append(ua).append("', '").append(ts).append("', '").append(refer).append("', '").append(globalid).append("')");
			boolean result = dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
