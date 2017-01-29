package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loadAdmin() {
        return "admin";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));
        return "done";
    }
    
    @RequestMapping(value = "/signUps", method = RequestMethod.GET)
    public String loadSignUps(Model model) {
        model.addAttribute("signups", signupRepository.findAll());
        return "signUps";
    }

    
    public String list(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("comments", commentRepository.findAll());
        
//        List<String> comments = new ArrayList<String>();
//        comments.add("Example");
//        model.addAttribute("comments", comments);
        return "items";
    }
}
