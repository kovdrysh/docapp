package home.test.controller;


import home.test.model.Folder;
import home.test.services.DocumentService;
import home.test.services.FolderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        //modelAndView.addObject("user", );
            if (id == null){
            id = Long.valueOf("0");
        }

        if (id.equals(0) || id == 0){
            modelAndView.addObject("folderName", "Top");
        }
        else{
            modelAndView.addObject("folderName", folderService.get(id).getName());
            modelAndView.addObject("stepback", folderService.getAllParentFolders(id));
        }

        modelAndView.addObject("folders", folderService.getCustom(id));
        modelAndView.addObject("parentId", id);
        modelAndView.addObject("documents", documentService.getAllByParentId(id));

        return modelAndView;
    }

    @RequestMapping(value = "/addFolder", method = RequestMethod.POST)
    public String addFolder(Folder folder){
        LOGGER.debug("Folder "+folder.getName());
        if (folder.getId() == null){
            folder.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            folderService.add(folder);
        }
        else{

            folderService.update(folder);
        }
        return "redirect:/document?id=" + folder.getParentId();
    }

    @RequestMapping(value = "/editFolder", method = RequestMethod.GET)
    public ModelAndView showEditFolderForm(@RequestParam(required = true) Long id){
        Folder folder = folderService.get(id);
//        if (folder.getCreatedBy().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            return new ModelAndView("addFolder", "folder", folder);
//        }
//        else
//            return new ModelAndView("");
    }

    @RequestMapping(value = "/deleteFolder", method = RequestMethod.GET)
    public String deleteFolder(@RequestParam(required = true) Long id){
        Folder folder = folderService.get(id);
        Long parentId = folder.getParentId();
//        if (folder.getCreatedBy().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            folderService.remove(id);
//        }
//        else{
//
//        }
        return "redirect:/document?id=" + parentId;

    }

    @RequestMapping(value = "/deleteDoc", method = RequestMethod.GET)
    public String deleteDocument(@RequestParam(required = true) Long id, @RequestParam(required = true) Long parentId){
        documentService.delete(id);
        return "redirect:/document?id=" + parentId;
    }
    //@GET
    //@Path("/download/file/{id}")

}
