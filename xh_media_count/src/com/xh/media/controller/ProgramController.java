package com.xh.media.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.gson.Gson;
import com.xh.media.model.IndexXhMediaProgram;
import com.xh.media.service.IndexXhMediaProgramService;
@Controller
public class ProgramController extends AbstractController {
	private ApplicationContext context;
	private Gson gson;
	private IndexXhMediaProgramService indexXhMediaProgramService;
	public ProgramController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gson = new Gson();
		indexXhMediaProgramService=(IndexXhMediaProgramService) context.getBean("indexXhMediaProgramService");
	}

	@RequestMapping("/program")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		String channelId="";
		String siteCode="";
		int action=0;
		if (arg0.getParameter("channelId")!=null) {
			channelId = arg0.getParameter("channelId");
		}
		if (arg0.getParameter("originId")!=null) {
			siteCode = arg0.getParameter("originId");
		}
		if (arg0.getParameter("action")!=null) {
			action=Integer.parseInt(arg0.getParameter("action"));
		}  
		switch (action) {
		case 1:
			IndexXhMediaProgram record=new IndexXhMediaProgram();
			record.setChannelId(channelId);
			record.setSiteCode(siteCode);
			List<IndexXhMediaProgram> programByChannel = indexXhMediaProgramService.getProgramByChannel(record);
		    String resultStr = gson.toJson(programByChannel);
		    arg1.setCharacterEncoding("UTF-8");
			arg1.getWriter().write(resultStr);
			arg1.getWriter().flush();
			arg1.getWriter().close();
			break;

		default:
			break;
		}
		
		
		return null;
	}
	
	
	@RequestMapping("/getProgram")
	public void getProgram(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String channelId=request.getParameter("channelId");
		String siteCode=request.getParameter("siteCode");
		System.out.println("channelId=="+channelId+";;siteCode=="+siteCode);
		IndexXhMediaProgram record=new IndexXhMediaProgram();
		record.setChannelId(channelId);
		record.setSiteCode(siteCode);
		List<IndexXhMediaProgram> programByChannel = indexXhMediaProgramService.getProgramByChannel(record);
	    String resultStr = gson.toJson(programByChannel);
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(resultStr);
	    response.getWriter().flush();
	    response.getWriter().close();
		
	}

}
