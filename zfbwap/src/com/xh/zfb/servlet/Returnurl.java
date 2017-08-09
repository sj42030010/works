package com.xh.zfb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.internal.util.AlipaySignature;
import com.xh.zfb.util.DbConn;
import com.xh.zfb.util.Key;

public class Returnurl extends HttpServlet {
//	private DbConn dbConn;
	/**
	 * Constructor of the object.
	 */
	public Returnurl() {
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
			
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
//				System.out.println("return name==="+name);
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
//							System.out.println("return valueStr==="+valueStr);
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
	
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	
			//支付宝交易号
	
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			String select = "SELECT return_url FROM lanuchpay WHERE sdorderno="+out_trade_no;
			ResultSet rs = dbConn.executeQuery(select, true);
			String return_url = "";
			if(rs.next())
				return_url = rs.getString("return_url");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, Key.ALIPAY_PUBLIC_KEY, Key.CHARSET, "RSA2");
			if(verify_result){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//该页面可做页面美工编辑
				System.out.println("同步验证成功<br />");
				//out.clear();
				out.println("验证成功<br />");
				response.sendRedirect(return_url);
				
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
	
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{
				//该页面可做页面美工编辑
				System.out.println("同步验证失败<br />!");
				//out.clear();
				out.println("验证失败");
				response.sendRedirect(return_url);
			}
			dbConn.closeConnection();
		}catch(Exception e){
			e.printStackTrace();
			dbConn.closeConnection();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(){
		// Put your code here
//		dbConn = new DbConn();
	}

}
