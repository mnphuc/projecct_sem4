package com.project.sem4.controller;


import com.project.sem4.model.Attribute;
import com.project.sem4.model.AttributeSet;
import com.project.sem4.model.Categories;
import com.project.sem4.model.view.FileInfo;
import com.project.sem4.model.view.FolderInfo;
import com.project.sem4.model.view.ImageInfo;
import com.project.sem4.repository.interfaces.AttributeRepository;
import com.project.sem4.repository.interfaces.CategoriesRepository;
import com.project.sem4.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("quan-tri/api")
public class AdminServiceController {
    @Autowired
    private FileService fileService;
    @Autowired
    AttributeRepository repository;

    @RequestMapping(value = "getAttribute/{id}", method = RequestMethod.GET)
    public List<Attribute> findAttributeByAttributeSetId(@PathVariable(value = "id") Integer id){
        return repository.findAttributeByAttributeSetId(id);
    }
    static class SortByDate implements Comparator<FileInfo> {
        @Override
        public int compare(FileInfo f, FileInfo ff) {
            return ff.getCreateTime().compareTo(f.getCreateTime());
        }
    }
    @RequestMapping(value = "getAllFile", method = RequestMethod.GET)
    public ResponseEntity<List<FileInfo>> getAllFile() throws IOException {
        List<FileInfo> fileInfos = fileService.findAll(FileService.DIR_NAME, "", null);
        Collections.sort(fileInfos, new SortByDate());
        return new ResponseEntity<>(fileInfos, HttpStatus.OK);
    }
    @RequestMapping(value = "getFileByFolder", method = RequestMethod.GET)
    public ResponseEntity<List<FileInfo>> getFileByFolder(@RequestParam(value = "nameFolder", required = false)String nameFolder) throws IOException {
        List<FileInfo> fileInfos = fileService.getFileByFolder(nameFolder, null);
        Collections.sort(fileInfos, new SortByDate());
        return new ResponseEntity<>(fileInfos, HttpStatus.OK);
    }
    @RequestMapping(value = "getAllFolder", method = RequestMethod.GET)
    public ResponseEntity<List<FolderInfo>> getAllFolder() throws IOException {
        List<FolderInfo> list = fileService.getAllFolder(FileService.DIR_NAME);
        return new  ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "saveImage", method = RequestMethod.POST)
    public ResponseEntity<String> saveImage(@RequestParam(value = "rootDir", required = false)String rootDir, @RequestParam("file") MultipartFile[] file) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile f : file) {
            fileService.saveFile(rootDir, f);
        }
            String txt = "đã lưu";
            return new ResponseEntity<>(txt, HttpStatus.OK);
    }

    @RequestMapping(value = "saveFolder", method = RequestMethod.POST)
    public ResponseEntity<String> saveFolder(@RequestParam(value = "dir", required = false)String dir){
        Boolean bl = fileService.saveFolder(dir);
        String txt;
        if (bl){
            txt = "tạo thư mục thành công";
            return new ResponseEntity<>(txt, HttpStatus.OK);
        }else {
            txt = "Tạo Không Thành Công";
            return new ResponseEntity<>(txt, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "deleteFolder", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFolder(@RequestParam(value = "dir", required = false)String dir){
        if (dir.equals("all") || dir.isEmpty()){
            return new ResponseEntity<>("Xóa không Thành Công", HttpStatus.NOT_FOUND);
        }else {
            fileService.deleteFolder(dir);
            return new ResponseEntity<>("thành công", HttpStatus.OK);
        }
    }
    @RequestMapping(value = "deleteImage/{photo}", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteImage(@PathVariable("photo")String photo){
        Boolean bl = fileService.deleteFile(photo);
        if (bl){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }

}
