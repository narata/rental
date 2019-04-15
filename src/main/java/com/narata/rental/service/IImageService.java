package com.narata.rental.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author narata
 * @since 2019/04/12
 */
public interface IImageService {
    /**
     * 上传图片
     * @param file 图片文件对象
     * @param path 路径
     * @return true or false
     */
    String uploadImage(MultipartFile file, String path);
}
