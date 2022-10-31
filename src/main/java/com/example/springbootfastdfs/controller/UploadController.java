package com.example.springbootfastdfs.controller;


import cn.hutool.core.date.DateUtil;
import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/upload")
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FastFileStorageClient storageClient;

    @Value("${file.prefix}")
    private String filePrefix;

    @PostMapping("/img")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream stream = file.getInputStream();
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        StorePath storePath = storageClient.uploadFile(stream, file.getSize(), suffix, null);
        System.out.println(storePath);

        return  filePrefix+storePath.getFullPath();

    }

    private Set<MataData> createMataData() {
        Set<MataData> metaDataSet = new HashSet<MataData>();
        metaDataSet.add(new MataData("Author", "cl"));
        metaDataSet.add(new MataData("CreateDate", DateUtil.now()));
        return metaDataSet;
    }




}
