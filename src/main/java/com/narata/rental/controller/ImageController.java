package com.narata.rental.controller;

import com.narata.rental.common.ReturnMsg;
import com.narata.rental.service.IImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author narata
 * @since 2019/04/12
 */

@RestController
@RequestMapping("/image")
public class ImageController {
    private final IImageService imageService;

    @Value("${upload.image.path}")
    private String uploadDir;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity uploadImage(
            @RequestParam("image") MultipartFile file,
            Principal principal
    ) {
        if (file == null) {
            return new ResponseEntity<>(ReturnMsg.ERROR_FILE, HttpStatus.BAD_REQUEST);
        }
        String path = uploadDir + principal.getName() + "/";
        String fileName = imageService.uploadImage(file, path);
        if (fileName != null) {
            Map<String, String> result = new HashMap<>(2);
            result.put("image", principal.getName() + "/" + fileName);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
