package com.xh.zfb.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xh.zfb.util.DbConn;
import com.xh.zfb.util.Key;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.AlipayTradeCreateModel;

public class Wappay extends HttpServlet {
//	private DbConn dbConn;
	/**
	 * Constructor of the object.
	 */
	public Wappay() {
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

		doPost(request,response);
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
		// 商户订单号，商户网站订单系统中唯一订单号，必填
//	    String out_trade_no = System.currentTimeMillis()+"";
	    String out_trade_no = request.getParameter("out_trade_no");
		// 订单名称，必填
	    String subject = "视频收费";
	    // 付款金额，必填
	    String total_amount=request.getParameter("order_amt");
	    // 商品描述，可空
	    String body = "视频收费";
	    // 超时时间 可空
	    String timeout_express="2m";
	    // 销售产品码 必填
	    String product_code="QUICK_WAP_PAY";
	    //返回地址
	    String return_url=request.getParameter("return_url");
	    //商品全局唯一ID
	    String globalid = request.getParameter("globalid");
	    
	    String globalName = request.getParameter("name");
	    
	    System.out.println("return url is::::"+return_url+";;;;;out_trade_no is:::::::"+out_trade_no);
//	    //打印头信息
//	    Enumeration<String> headers = request.getHeaderNames();
//		while(headers.hasMoreElements()){
//			String header = headers.nextElement();
//			System.out.println(header.toLowerCase()+"===="+request.getHeader(header).toString());
//			if("user-agent".equals(header.toLowerCase())){
//				ua = request.getHeader(header).toString();
//			}else if("referer".equals(header.toLowerCase())){
//				refer = request.getHeader(header).toString();
//			}else if("referer".equals(header.toLowerCase())){
//				refer = request.getHeader(header).toString();
//			}
//		}
	    /**********************/
	    // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	    //调用RSA签名方式
	    AlipayClient client = new DefaultAlipayClient(Key.URL, Key.APPID, Key.RSA_PRIVATE_KEY, Key.FORMAT, Key.CHARSET, Key.ALIPAY_PUBLIC_KEY,Key.SIGNTYPE);
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject(subject);
	    model.setTotalAmount(total_amount);
	    model.setBody(body);
	    model.setTimeoutExpress(timeout_express);
	    model.setProductCode(product_code);
	    alipay_request.setBizModel(model);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(Key.notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(Key.return_url);
	    
	    Timestamp ts = new Timestamp(System.currentTimeMillis());
	    String serverTime = ts+"";
	    
	    // form表单生产
	    String form = "";
		try {
			StringBuffer insert_sql = new StringBuffer("INSERT INTO lanuchpay (version, customerid, sdorderno, total_fee, paytype, notifyurl, remark, sign, status, notify_status, return_url, global_id, chinese_name) VALUES ('1.0',");
		    insert_sql.append(Key.APPID).append(", ").append(out_trade_no).append(", ").append(total_amount).append(", 'zfbwappay', '").append(Key.notify_url).append("', '").append(serverTime).append("', '', 0, 0, '").append(return_url).append("', '").append(globalid).append("','").append(globalName).append("') ON DUPLICATE KEY UPDATE customerid=VALUES(customerid)");
//		    System.out.println(insert_sql);
		    dbConn.execute(insert_sql.toString());
		    dbConn.closeConnection();
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			//form = "<meta http-equiv=\"Access-Control-Allow-Origin\" content=\"*.ctv-cloud.com\">"+form;
//			System.out.println(form);
			response.setContentType("text/html;charset=" + Key.CHARSET); 
			response.setHeader("Access-Control-Allow-Origin", "*");
//			Pattern pattern = Pattern.compile(">\r\n<input type=\"hidden\"");
//			Matcher matcher = pattern.matcher(form);
//			String form1 = matcher.replaceFirst(">\r\n资费：0.01元/次\r\n<input type=\"hidden\"");
//			System.out.println("form1 after replace is::::::"+form1);
		    response.getWriter().write(form);//直接将完整的表单html输出到页面 
		    response.getWriter().flush(); 
		    response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
//		dbConn = new DbConn();
	}

}
