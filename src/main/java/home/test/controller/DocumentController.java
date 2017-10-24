package home.test.controller;

import com.mongodb.gridfs.GridFSDBFile;
import home.test.model.Folder;
import home.test.services.DocumentService;
import home.test.services.FolderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @RequestMapping(value = "/addFolder", method = RequestMethod.GET)
    public ModelAndView showAddFolderForm(@RequestParam(required = false) Long parentId){
        if (parentId == null){
            parentId = Long.valueOf("0");
        }
        return new ModelAndView("addFolder", "folder", new Folder(parentId));
    }

    @RequestMapping(value = "/addFolder", method = RequestMethod.POST)
    public String addFolder(@ModelAttribute("folder") Folder folder){
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
    //@GET
    //@Path("/download/file/{id}")

}
