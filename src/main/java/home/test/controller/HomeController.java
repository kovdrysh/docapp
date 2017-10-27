package home.test.controller;

import home.test.model.Contact;
import home.test.services.ContactService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {
        return "redirect:/login";
    }


    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }


    @RequestMapping(value = "/secure/", method = RequestMethod.GET)
    public String secure() {
        return "secure";


    }




}
