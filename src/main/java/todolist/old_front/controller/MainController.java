package todolist.old_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class MainController {

    // Index.html
    @RequestMapping(method=RequestMethod.GET)
    public String main()
    {
        return "index";
    }
    
}
