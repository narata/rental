package com.narata.rental.service.impl;

import com.narata.rental.service.IImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

/**
 * @author narata
 * @since 2019/04/12
 */

@Service
@Slf4j
public class ImageServiceImpl implements IImageService {
    @Override
    public String uploadImage(MultipartFile file, String path) {
        String[] buf = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String suffix = buf[buf.length-1];
        String fileName = UUID.randomUUID().toString() + "." + suffix;
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return fileName;
        } catch (Exception e) {
            log.error("文件上传失败：{}", e.toString());
            return null;
        }
    }
}
