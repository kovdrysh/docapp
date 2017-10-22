package home.test.controller;

import home.test.model.Contact;
import home.test.model.Document;
import home.test.services.DocumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UploadController {

    private static final Logger logger = Logger.getLogger(UploadController.class);
    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView showUploadForm(@RequestParam(required = false) Long parentId){
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("parentId", parentId);
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("parentId") Long parentId){
        logger.debug("ParentId: " + parentId);
        documentService.save(parentId, file);

        return "redirect:/document?id=" + parentId;
    }
}
