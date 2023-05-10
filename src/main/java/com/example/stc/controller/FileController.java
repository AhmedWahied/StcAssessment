package com.example.stc.controller;

import com.example.stc.entity.Item;
import com.example.stc.service.FileService;
import com.example.stc.entity.Books;
import com.example.stc.entity.Documents;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stc")
@Slf4j
public class FileController {
    FileService fileService;

    public FileController(FileService fileService){
        this.fileService=fileService;
    }

    @ApiOperation(value = "add Space")
    @RequestMapping(value = "/addSpace", method = RequestMethod.GET)
    public Item addSpace() {
        return  fileService.addSpace();
    }

    @GetMapping("/addFolder/{email}")
    public ResponseEntity<Item> addFolder(@PathVariable String email) {
        return  fileService.addFolder(email);
    }

    @PostMapping("/addFile/{email}")
    public ResponseEntity<Books> addFile(@PathVariable String email , @RequestPart("file") MultipartFile file) {
        try {
            return  fileService.addFile(email,file.getBytes(),file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/downloadFile/{email}/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String email,@PathVariable Long fileId) {

        return  fileService.downloadFile(email,fileId);
    }

    @GetMapping("/View/{email}")
    public List<Documents> view(@PathVariable String email) {

        return  fileService.viewMeta(email);
    }



}
