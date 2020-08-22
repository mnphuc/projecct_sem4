package com.project.sem4.service;

import com.project.sem4.model.view.FileInfo;
import com.project.sem4.model.view.FolderInfo;
import com.project.sem4.vendor.MnpFileCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    public static final String DIR_NAME = "src/main/resources/static/uploads/";
    public static final String SAFE_DIR_NAME = "safe";
    public static final String[] fs = { DIR_NAME, SAFE_DIR_NAME };
    private static String ROOT_URL = "src/main/resources/static/uploads/";
    @Autowired
    private ServletContext context;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    ResourcePatternResolver resourcePatternResolver;
    @Autowired
    HttpServletRequest request;

    public List<FileInfo> findAll(String rootDirName, String dirPath, FileFilter fileFilter) throws IOException {
        List<FileInfo> fileInfos = new ArrayList<>();
        File directory = new File(ROOT_URL);
        //List<FolderInfo> listFolder = getAllFolder(DIR_NAME);
        File[] files = (directory.exists()) ? directory.listFiles(fileFilter) : new File[0];
        for (File file : files) {
            if (file.isDirectory()){
                File file1 = new File(ROOT_URL+"/"+file.getName());
                File[] files1 = (file1.exists()) ? file1.listFiles(fileFilter) : new File[0];
                for (File file11 : files1){
                    FileInfo info = MnpFileCommon.getFileInfo(file11);
                    fileInfos.add(info);
                }
            }

            if (file.length() == 0 || file.isDirectory())
                continue;
            FileInfo fileInfo = MnpFileCommon.getFileInfo(file);
            fileInfos.add(fileInfo);

        }
        return fileInfos;
    }
    public List<FolderInfo> getAllV2(String dir) throws IOException {
        List<FolderInfo> list = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                    FolderInfo info = new FolderInfo();
                    info.setNameFolder(path.getFileName().toString());
                    list.add(info);
            }
        }
        return list;
    }
    public List<FolderInfo> getAllFolder(String dir){
        List<FolderInfo>list = new ArrayList<>();
        File file = new File(dir);
        File[] files = file.listFiles();
        for(File f: files){
            FolderInfo info = new FolderInfo();
            if (f.isDirectory()){
                info.setNameFolder(f.getName());
                list.add(info);
            }
        }
        return list;
    }
    public Boolean saveFile(String rootDirName, MultipartFile file){
        Boolean bl =false;
        Path path;
        file.getResource();
        if (!file.isEmpty()){
            if (rootDirName == null){
               path =  Paths.get(ROOT_URL);
            }else {
               path = Paths.get(ROOT_URL+rootDirName);
            }
            try {
               // BufferedImage image = null;
                InputStream inputStream = file.getInputStream();
                Date date = new Date();
                Long mills = date.getTime();
                //image = ImageIO.read((File) file);
                //ImageIO.write(image, "jpg",new File(ROOT_URL));
               // ImageIO.write((RenderedImage) file, "jpg", (File) file);
                Files.copy(inputStream, path.resolve(file.getOriginalFilename()));
                bl = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return bl;
    }
    public Boolean deleteFile(String fileName){
        Path path = Paths.get("ROOT_URL"+fileName);
        Boolean bl = false;
        try {
            bl = Files.deleteIfExists(path);
            return bl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bl;
    }

}
