package todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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
