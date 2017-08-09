package com.xh.zfb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.xh.zfb.bean.NotifyMsgBean;
import com.xh.zfb.util.DbConn;
import com.xh.zfb.util.Key;

public class WappayApp extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WappayApp() {
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
		try{
			//获取支付宝POST过来反馈信息
			
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				//System.out.println("notify name==="+name);
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				//System.out.println("notify valueStr==="+valueStr);
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
	
			NotifyMsgBean nmb = initNotifyBean();
			nmb = setNotifyBean(params,nmb);
		
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, Key.ALIPAY_PUBLIC_KEY, Key.CHARSET, "RSA2");
			StringBuffer insert_sql = new StringBuffer("INSERT INTO xhpay.zfbwap_notify(notify_type,notify_id,app_id,charset,version,sign_type,sign,trade_no,out_trade_no,out_biz_no,buyer_id,buyer_logon_id,seller_id,seller_email,trade_status,total_amount,receipt_amount,invoice_amount,buyer_pay_amount,point_amount,refund_fee,subject,body,gmt_create,gmt_payment,gmt_refund,gmt_close,fund_bill_list,passback_params,voucher_detail_list,notify_time,verify_result)VALUES ('");
			insert_sql.append(nmb.getNotify_type()).append("','").append(nmb.getNotify_id()).append("','").append(nmb.getApp_id()).append("','").append(nmb.getCharset()).append("','").append(nmb.getVersion()).append("','").append(nmb.getSign_type()).append("','")
					  .append(nmb.getSign()).append("','").append(nmb.getTrade_no()).append("','").append(nmb.getOut_trade_no()).append("','").append(nmb.getOut_biz_no()).append("','").append(nmb.getBuyer_id()).append("','").append(nmb.getBuyer_logon_id()).append("','")
					  .append(nmb.getSeller_id()).append("','").append(nmb.getSeller_email()).append("','").append(nmb.getTrade_status()).append("',").append(nmb.getTotal_amount()).append(",").append(nmb.getReceipt_amount()).append(",").append(nmb.getInvoice_amount()).append(",")
					  .append(nmb.getBuyer_pay_amount()).append(",").append(nmb.getPoint_amount()).append(",").append(nmb.getRefund_fee()).append(",'").append(nmb.getSubject()).append("','").append(nmb.getBody()).append("','").append(nmb.getGmt_create()).append("','")
					  .append(nmb.getGmt_payment()).append("','").append(nmb.getGmt_refund()).append("','").append(nmb.getGmt_close()).append("','").append(nmb.getFund_bill_list()).append("','").append(nmb.getPassback_params()).append("','").append(nmb.getVoucher_detail_list()).append("','")
					  .append(nmb.getNotify_time()).append("','");
			if(verify_result){//验证成功
				System.out.println("异步验证成功<br />");
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				insert_sql.append("1')");
				dbConn.execute(insert_sql.toString());
				System.out.println(insert_sql);
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						//如果有做过处理，不执行商户的业务程序
//					String update  = "UPDATE lanuchpay SET notify_status=1 WHERE sdorderno="+nmb.getOut_trade_no();
//					System.out.println(update);
//					dbConn.execute(update);
					//注意：
					//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						//如果有做过处理，不执行商户的业务程序
//					String update  = "UPDATE lanuchpay SET notify_status=1 WHERE sdorderno="+nmb.getOut_trade_no();
//					System.out.println(update);
//					dbConn.execute(update);
					//注意：
					//如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
				}

				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//				out.flush();
				
				out.println("success");	//请不要修改或删除
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				System.out.println("异步验证失败<br />");
				insert_sql.append("0')");
				dbConn.execute(insert_sql.toString());
//				System.out.println(insert_sql);
//				if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
//					String update  = "UPDATE lanuchpay SET notify_status=1 WHERE sdorderno="+nmb.getOut_trade_no();
//					System.out.println(update);
//					dbConn.execute(update);
//				}
				out.println("success");
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
	public void init() throws ServletException {
		// Put your code here
//		dbConn = new DbConn();
	}
	
	private NotifyMsgBean initNotifyBean(){
		Timestamp st = new Timestamp(System.currentTimeMillis());
		NotifyMsgBean nmb = new NotifyMsgBean();
		nmb.setApp_id("");
		nmb.setBody("");
		nmb.setBuyer_id("");
		nmb.setBuyer_logon_id("");
		nmb.setBuyer_pay_amount(0.00);
		nmb.setCharset("");
		nmb.setFund_bill_list("");
		nmb.setGmt_close(st+"");
		nmb.setGmt_create(st+"");
		nmb.setGmt_payment(st+"");
		nmb.setGmt_refund(st+"");
		nmb.setInvoice_amount(0.00);
		nmb.setNotify_id("");
		nmb.setNotify_time(st+"");
		nmb.setNotify_type("");
		nmb.setOut_biz_no("");
		nmb.setOut_trade_no("");
		nmb.setPassback_params("");
		nmb.setPoint_amount(0.00);
		nmb.setReceipt_amount(0.00);
		nmb.setRefund_fee(0.00);
		nmb.setSeller_email("");
		nmb.setSeller_id("");
		nmb.setSign("");
		nmb.setSign_type("");
		nmb.setSubject("");
		nmb.setTotal_amount(0.00);
		nmb.setTrade_no("");
		nmb.setTrade_status("");
		nmb.setVersion("");
		nmb.setVoucher_detail_list("");
		return nmb;
	}
	
	private NotifyMsgBean setNotifyBean(Map map, NotifyMsgBean nmb){
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
		    String key = iter.next();
		    if("notify_time".equals(key.toLowerCase().trim()))
		    	nmb.setNotify_time(map.get(key).toString());
		    else if("notify_type".equals(key.toLowerCase().trim()))
		    	nmb.setNotify_type(map.get(key).toString());
		    else if("notify_id".equals(key.toLowerCase().trim()))
		    	nmb.setNotify_id(map.get(key).toString());
		    else if("app_id".equals(key.toLowerCase().trim()))
		    	nmb.setApp_id(map.get(key).toString());
		    else if("charset".equals(key.toLowerCase().trim()))
		    	nmb.setCharset(map.get(key).toString());
		    else if("version".equals(key.toLowerCase().trim()))
		    	nmb.setVersion(map.get(key).toString());
		    else if("sign_type".equals(key.toLowerCase().trim()))
		    	nmb.setSign_type(map.get(key).toString());
		    else if("sign".equals(key.toLowerCase().trim()))
		    	nmb.setSign(map.get(key).toString());
		    else if("trade_no".equals(key.toLowerCase().trim()))
		    	nmb.setTrade_no(map.get(key).toString());
		    else if("out_trade_no".equals(key.toLowerCase().trim()))
		    	nmb.setOut_trade_no(map.get(key).toString());
		    else if("out_biz_no".equals(key.toLowerCase().trim()))
		    	nmb.setOut_biz_no(map.get(key).toString());
		    else if("buyer_id".equals(key.toLowerCase().trim()))
		    	nmb.setBuyer_id(map.get(key).toString());
		    else if("buyer_logon_id".equals(key.toLowerCase().trim()))
		    	nmb.setBuyer_logon_id(map.get(key).toString());
		    else if("seller_id".equals(key.toLowerCase().trim()))
		    	nmb.setSeller_id(map.get(key).toString());
		    else if("seller_email".equals(key.toLowerCase().trim()))
		    	nmb.setSeller_email(map.get(key).toString());
		    else if("trade_status".equals(key.toLowerCase().trim()))
		    	nmb.setTrade_status(map.get(key).toString());
		    else if("total_amount".equals(key.toLowerCase().trim()))
		    	nmb.setTotal_amount(Double.parseDouble(map.get(key).toString()));
		    else if("receipt_amount".equals(key.toLowerCase().trim()))
		    	nmb.setReceipt_amount(Double.parseDouble(map.get(key).toString()));
		    else if("invoice_amount".equals(key.toLowerCase().trim()))
		    	nmb.setInvoice_amount(Double.parseDouble(map.get(key).toString()));
		    else if("buyer_pay_amount".equals(key.toLowerCase().trim()))
		    	nmb.setBuyer_pay_amount(Double.parseDouble(map.get(key).toString()));
		    else if("point_amount".equals(key.toLowerCase().trim()))
		    	nmb.setPoint_amount(Double.parseDouble(map.get(key).toString()));
		    else if("refund_fee".equals(key.toLowerCase().trim()))
		    	nmb.setRefund_fee(Double.parseDouble(map.get(key).toString()));
		    else if("subject".equals(key.toLowerCase().trim()))
		    	nmb.setSubject(map.get(key).toString());
		    else if("body".equals(key.toLowerCase().trim()))
		    	nmb.setBody(map.get(key).toString());
		    else if("gmt_create".equals(key.toLowerCase().trim()))
		    	nmb.setGmt_create(map.get(key).toString());
		    else if("gmt_create".equals(key.toLowerCase().trim()))
		    	nmb.setGmt_create(map.get(key).toString());
		    else if("gmt_payment".equals(key.toLowerCase().trim()))
		    	nmb.setGmt_payment(map.get(key).toString());
		    else if("gmt_refund".equals(key.toLowerCase().trim()))
		    	nmb.setGmt_refund(map.get(key).toString());
		    else if("gmt_close".equals(key.toLowerCase().trim()))
		    	nmb.setGmt_close(map.get(key).toString());
		    else if("fund_bill_list".equals(key.toLowerCase().trim()))
		    	nmb.setFund_bill_list(map.get(key).toString());
		    else if("passback_params".equals(key.toLowerCase().trim()))
		    	nmb.setPassback_params(map.get(key).toString());
		    else if("voucher_detail_list".equals(key.toLowerCase().trim()))
		    	nmb.setVoucher_detail_list(map.get(key).toString());
		}
		return nmb;
	}

}
