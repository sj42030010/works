package com.xh.zfb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xh.zfb.util.DbConn;

public class CheckOrderStatus extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckOrderStatus() {
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
		try{
			String out_trade_no = request.getParameter("out_trade_no");
			System.out.println("check order status:::"+out_trade_no);
			String select = "SELECT notify_status FROM lanuchpay WHERE sdorderno="+out_trade_no;
			ResultSet rs1 = dbConn.executeQuery(select, true);
			int status = 0;
			if(rs1.next()){
				status = rs1.getInt("notify_status");
			}
			PrintWriter out = response.getWriter();
			out.print(status);//0未支付或支付失败，1支付成功
			dbConn.closeResultSet();
			dbConn.closeConnection();
		}catch(Exception e){
			e.printStackTrace();
			dbConn.closeResultSet();
			dbConn.closeConnection();
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
