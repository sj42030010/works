package com.xh.media.util;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrTest {
	public static void main(String[] args){
		try{
//			String url = "http://localhost:8080/solr/solrcore2";
//			HttpSolrClient client = new HttpSolrClient(url);
//			
//			SolrInputDocument doc = new SolrInputDocument();
//			doc.addField("id", "3054");
//			doc.addField("date", "2017-06-32");
//			doc.addField("origin_id", "10003");
//			doc.addField("origin_name", "solr测试渠道");
//			doc.addField("channel_id", "100002");
//			doc.addField("channel_name", "solr测试频道2");
//			doc.addField("global_id", "1000002");
//			doc.addField("chinese_name", "solr测试节目");
//			doc.addField("pub_date", "1492240246");
//			doc.addField("type", "1");
//			doc.addField("play_time", "1492");
//			doc.addField("time_length", "1492");
//			doc.addField("play_number", "1");
//			doc.addField("play_user_number", "1");
//			doc.addField("source", "0");
//			doc.addField("pv", "1");
//			doc.addField("uv", "1");
//			doc.addField("ip", "1");
//			doc.addField("site_code", "14");
//			doc.addField("create_time", "2017-06-20T18:00:40Z");
//			client.add(doc);  
//			client.commit(); 
//			client.close();
			
//			String url = "http://cms.dev.ctvcloud.com:888/tidesolr/core1";
//			HttpSolrClient client = new HttpSolrClient(url);
//			
//			SolrInputDocument doc = new SolrInputDocument();
//			doc.addField("id", 37);
//			doc.addField("globalid", 1306089);
//			doc.addField("site", "36");
//			doc.addField("content", "");
//			doc.addField("duration", "100");
//			doc.addField("publishdate", "1494836955");
//			doc.addField("title", "母亲打断儿子玩游戏遭暴打");
//			doc.addField("summary", "简介：5月12日消息，近日,温州永嘉一眼镜城,一名中学生甩手暴打母亲,起因疑似儿子嫌弃母亲为他配的眼镜价格便宜。");
//			doc.addField("number", 10);
//			doc.addField("photo", "");
//			doc.addField("photo2", "");
//			doc.addField("photo3", "");
//			doc.addField("sphoto", "http://image.ctv-cloud.com/webpic/2017/5/15/20175151494835927512_46.jpg");
//			doc.addField("dphoto", "");
//			doc.addField("smallphoto", "");
//			doc.addField("programurl", "http://mnews.ctv-cloud.com/content_center/news/play_37.html");
//			client.add(doc);  
//			client.commit(); 
//			client.close();
			
//			String xml = "";
//			xml += "<add><doc>";
//			xml += "<field name=\"id\">"+40+"</field>";
//			xml += "<field name=\"globalid\">"+1306092+"</field>";
//			xml += "<field name=\"site\">"+39+"</field>";
//			xml += "<field name=\"content\"></field>";
//			xml += "<field name=\"duration\">"+100+"</field>";
//			xml += "<field name=\"publishdate\">"+1494846955+"</field>";
//			xml += "<field name=\"title\">母亲打断儿子玩游戏遭暴打</field>";
//			xml += "<field name=\"summary\">简介：5月12日消息，近日,温州永嘉一眼镜城,一名中学生甩手暴打母亲,起因疑似儿子嫌弃母亲为他配的眼镜价格便宜。</field>";
//			xml += "<field name=\"number\">"+10+"</field>";
//			xml += "<field name=\"photo\"></field>";
//			xml += "<field name=\"photo2\"></field>";
//			xml += "<field name=\"photo3\"></field>";
//			xml += "<field name=\"sphoto\">http://image.ctv-cloud.com/webpic/2017/5/15/20175151494835927512_46.jpg</field>";
//			xml += "<field name=\"dphoto\"></field>";
//			xml += "<field name=\"smallphoto\"></field>";
//	        xml += "<field name=\"programurl\">http://mnews.ctv-cloud.com/content_center/news/play_37.html</field>";
//			xml += "</doc></add>";
//			
//			String httpurl = "http://cms.dev.ctvcloud.com:888/tidesolr/core1/update?commit=true";
////			String httpurl = "http://localhost:8080/solr/solrcore2/update?commit=true";
//			URL url = new URL(httpurl);
//			java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setUseCaches(false);
//			connection.setRequestProperty("Content-Type","text/xml;charset=utf-8");
//			connection.setRequestMethod("POST");
//			connection.connect();
//			OutputStreamWriter out1 = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
//
//			out1.write(xml);
//			out1.flush();
//			
//			String sCurrentLine = "";
//			String content = "";
//
//			java.io.InputStream l_urlStream = connection.getInputStream();
//			java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));
//			while ((sCurrentLine = l_reader.readLine()) != null)
//			{
//				content+=sCurrentLine;
//			}
//			out1.close();
//			out1 = null;
//			connection.disconnect();
//			connection = null;
			
			
//			SolrQuery query = new SolrQuery(); //定义查询内容 
//			query.set("q","chinese_name:共*");
////			query.addFilterQuery("id:[0 TO 9]");
////			query.addFilterQuery("channel_id:15823");
//	        query.setStart(0);//起始页  
//	        query.setRows(100);//每页显示数量  
//	        QueryResponse rsp = client.query( query );  
//	        SolrDocumentList results = rsp.getResults();  
//	        System.out.println(results);
//	        System.out.println(results.getNumFound());//查询总条数  
//	        for(SolrDocument doc:results){  
//	            System.out.println(doc);  
//	        } 
			
//			String str = "szlist.html";
//			String[] temp = str.split("\\.");
//			System.out.println(temp.length);
			
//			String str = "<p>你好测试者";
//			Pattern CRLF1 = Pattern.compile("<");  
//			Matcher m1 = CRLF1.matcher(str);  
//			str = m1.replaceAll("%3c");
//			Pattern CRLF2 = Pattern.compile(">");  
//			Matcher m2 = CRLF2.matcher(str);  
//			str = m2.replaceAll("%3e");
//			System.out.println(str);
//			str = URLDecoder.decode(str);
//			System.out.println(str);
			String str = "15800_15801_15802_15806_";
			String[] strs = str.split("_");
			System.out.println(strs.length);
			System.out.println(strs[strs.length-1]);
			System.out.println(strs[strs.length-2]);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
