package Com.Aartek.Controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 

import Com.Aartek.Model.Project;
import Com.Aartek.Service.Projectservice;
import Com.Artek.Validator.ProjectValidator;


@Controller
public class ProjectController {
	
	@Autowired
	Projectservice proservice;
	
	@Autowired
	ProjectValidator provalidator;
	
	@RequestMapping("/project")
	public String Addproject(Map<String, Object> map,Model model) {
		
		
		System.out.println("inside controller Addproject ");
 
		 
 
	
		map.put("project", new Project());
		
		List<Project> projectList = proservice.getProjects();
			
		model.addAttribute("projects", projectList);
		
		return "project";
	
	}
	
	@RequestMapping(value = "/Registerd", method = RequestMethod.POST)
	public String saveproject(@ModelAttribute("project") Project project,BindingResult result,Model model) {		
		
		provalidator.validate(project, result);
         if (result.hasErrors()) {
			
        	 List<Project> projectList = proservice.getProjects();
 			
     		model.addAttribute("projects", projectList);
			  return "project";
			
		 }else {
			
			 proservice.projservice(project);
		  }
     	return "redirect:/project.do";
	
	}
 
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("singlerow") Project project,BindingResult result,Model model) {		
		     System.out.println(project.getStartDate());
		proservice.update(project);
		 	return "redirect:/project.do";
	
	}	
	
	
	
    	/*for edit operation*/
	   @RequestMapping("/edit/{projectid}")
	   public String edit(Project projectid, Map<String, Object> map,Model model) {
		   System.out.println("inside edit ");
		   
	
		   System.out.println(projectid.getProjectid());
		   map.put("singlerow", new Project());
		   
		  List<Project> singlerow = (List<Project>) proservice.getsinglerow(projectid);
		 		model.addAttribute("edit",singlerow);
				return "edit";  
		     		 	     	

	   }

	   
	  

	   
	   
	   

	/*for delete operation*/
	@RequestMapping("/delete")
	public void delete(HttpServletRequest res,HttpServletResponse resp) {
		
		 
		res.getParameter("id");
		PrintWriter pw=null;
		
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int p = Integer.parseInt(res.getParameter("id"));
		
     if(p>0) {
    	 pw.print("deleted");
    	 
     }else
     {
    	 pw.print("no");
    	 
     }
		  
		proservice.delete(p);
		 
		
		
		//return "redirect:/project.do";
		
	}
	
	 

}
