package home.test.controller;

import home.test.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("history", historyService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/deleteHistory", method = RequestMethod.GET)
    public String delete(@RequestParam(required = true) Long id){
        historyService.remove(id);
        return "redirect:/history";
    }
}
