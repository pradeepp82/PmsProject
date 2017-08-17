package Com.Aartek.Controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Com.Aartek.Model.User;
import Com.Aartek.Service.LoginService;



@Controller
public class LoginController {
	
	@Autowired
	LoginService LoginService;
	
	
	

	@RequestMapping("/login")
	public String showlogin(Map<String, Object> map){
		
		System.out.println("inside controller showlogin");
		map.put("user", new User());
		
		return "login";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute("user") User user,HttpServletRequest request) {
		
		System.out.println("inside controller checklogin");
		 
		
		user = LoginService.userlogin(user);
		  
		  	//HttpSession session = request.getSession();
			//session.setAttribute("login", user);
			return "redirect:/project.do";
		
		
			
			
			
					
		
		
	}
}
