package wap.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

public class Util {
		// MD5加密
		public static String Md5(String password) {
			StringBuffer buf = new StringBuffer("");
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte b[] = md.digest();

				int i;

				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}

				// System.out.println("result: " + buf.toString());// 32浣嶇殑鍔犲瘑

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return buf.toString();
		}
		
		/**
	     * 向前端页面发送javascript脚本 
	     * 
	     * @param response
	     * @param text
	     * @throws IOException
	     */  

		public static void writeJsToFrontPage(HttpServletResponse response, String msg){
			try{
		        response.setContentType("text/html; charset=UTF-8"); 
		        response.setHeader("Cache-Control", "no-cache"); 
		        PrintWriter pw = response.getWriter(); 
		        pw.write("<SCRIPT TYPE='text/javascript'>" + msg + "</SCRIPT>"); 
		        pw.close(); 
			}catch(Exception e){
				e.printStackTrace();
			}
	    }
		
		/**
		 * 下载导出的excel
		 * @param path
		 * @param response
		 */
		public static void download(String path, HttpServletResponse response) {
			try {
				// path是指欲下载的文件的路径。
				File file = new File(path);
				if(!file.exists()){
			            return;//文件不存在就退出方法
			        }
//				// 取得文件名。
				 String filename = file.getName();
				 FileInputStream fileInputStream = new FileInputStream(file);
			        //设置Http响应头告诉浏览器下载这个附件
			        response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(filename, "UTF-8"));
			        OutputStream outputStream = response.getOutputStream();
			        byte[] bytes = new byte[2048];
			        int len = 0;
			        while ((len = fileInputStream.read(bytes))>0){
			            outputStream.write(bytes,0,len);
			        }
			        fileInputStream.close();
			        outputStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
}
