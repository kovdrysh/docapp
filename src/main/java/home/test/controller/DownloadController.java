package home.test.controller;

import com.mongodb.gridfs.GridFSDBFile;
import home.test.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

@RestController
public class DownloadController {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(DocumentController.class);
    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    //@Produces(MediaType.ALL_VALUE)
    public void downloadDocument(@PathVariable("id") String id, HttpServletResponse response){
        LOGGER.debug("Start download document");
//        Response response = null;
        GridFSDBFile fileById = null;
        try {
            fileById = documentService.getFileById(id);
            LOGGER.debug("File is download. Name: " + fileById.getFilename());
            InputStream in = fileById.getInputStream();

//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            int data = in.read();
//            while (data >= 0) {
//                out.write((char) data);
//                data = in.read();
//            }
//            out.flush();
//            out.close();
            LOGGER.debug("File is ready");
            //Response.ResponseBuilder responseBuilder = Response.ok(out.toByteArray());
            //Response.ResponseBuilder responseBuilder = Response.ok(out.toByteArray(), fileById.getContentType());
//            Response.ResponseBuilder responseBuilder = Response.ok(fileById);

            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileById.getFilename(), "UTF-8").replace("+", " ") + "\"");

            response.setContentType(fileById.getContentType());
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int data = in.read();
            while (data >= 0) {
                out.write((char) data);
                data = in.read();
            }
            out.flush();
            out.close();
//            response = responseBuilder.build();

        } catch (IOException e) {
            LOGGER.debug(e.getMessage());
        }

//        return response;
    }
}
