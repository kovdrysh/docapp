package home.test.controller;

import home.test.dao.FolderDao;
import home.test.model.Folder;
import home.test.services.DocumentService;
import home.test.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DocumentController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private DocumentService documentService;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(DocumentController.class);

    @RequestMapping(value="/document", method = RequestMethod.GET)
    public ModelAndView showAll(@RequestParam(required = false) Long id){
        ModelAndView modelAndView = new ModelAndView("documents");
        if (id == null){
            id = Long.valueOf("0");
        }
        if (id.equals(0) || id == 0){
            modelAndView.addObject("stepback", 0);
        }
        else {
            modelAndView.addObject("stepback", folderService.get(id).getParentId());
        }
        modelAndView.addObject("folders", folderService.getAllByParentId(id));
        modelAndView.addObject("parentId", id);
        modelAndView.addObject("documents", documentService.getAllByParentId(id));

        return modelAndView;
    }

    @RequestMapping(value = "/addFolder", method = RequestMethod.POST)
    public String addFolder(Folder folder){
        LOGGER.debug("Folder "+folder.getName());
        if (folder.getId() == null){
            folderService.add(folder);
        }
        else{
            folderService.update(folder);
        }
        return "redirect:/document?id=" + folder.getParentId();
    }

    @RequestMapping(value = "/editFolder", method = RequestMethod.GET)
    public ModelAndView showEditFolderForm(@RequestParam(required = true) Long id){
        return new ModelAndView("addFolder", "folder", folderService.get(id));
    }

    @RequestMapping(value = "/deleteFolder", method = RequestMethod.GET)
    public String deleteFolder(@RequestParam(required = true) Long id){
        Long parentId = folderService.get(id).getParentId();
        folderService.remove(id);
        return "redirect:/document?id=" + parentId;
    }

    @RequestMapping(value = "/deleteDoc", method = RequestMethod.GET)
    public String deleteDocument(@RequestParam(required = true) Long id, @RequestParam(required = true) Long parentId){
        documentService.delete(id);
        return "redirect:/document?id=" + parentId;
    }
}
