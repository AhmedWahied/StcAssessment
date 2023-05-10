package com.example.stc.service;

import com.example.stc.entity.*;
import com.example.stc.repository.BooksRepo;
import com.example.stc.repository.ItemRepo;
import com.example.stc.repository.PermissionGroupsRepo;
import com.example.stc.repository.PermissionsRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileService {
    BooksRepo booksRepo;
    ItemRepo itemRepo;
    PermissionGroupsRepo permissionGroupsRepo;
    PermissionsRepo permissionsRepo;

    public FileService(BooksRepo booksRepo,ItemRepo itemRepo,PermissionGroupsRepo permissionGroupsRepo,PermissionsRepo permissionsRepo){
        this.booksRepo=booksRepo;
        this.itemRepo=itemRepo;
        this.permissionGroupsRepo=permissionGroupsRepo;
        this.permissionsRepo=permissionsRepo;
    }
    public Item addSpace() {
        PermissionGroups permissionGroups = new PermissionGroups();
        permissionGroups.setGroupName("AdminGroup");
        permissionGroups = permissionGroupsRepo.save(permissionGroups);
        permissionsRepo.save(new Permissions("ahmed@gmail.com", "edit", permissionGroups));
        permissionsRepo.save(new Permissions("wahied@gmail.com", "view", permissionGroups));
        return itemRepo.save(new Item("Space", "stc-Assessments", permissionGroups));


    }

    public ResponseEntity<Item> addFolder(String email) {
        Permissions permission = permissionsRepo.findAllByEmail(email);
        Item item = itemRepo.findAllByName("stc-Assessments");
        if (permission != null)
            if (permission.getPermitionLevel().equalsIgnoreCase("Edit")) {
                if (permission.getPermissions().equals(item.getPermissionGroups())) {
                    return new ResponseEntity<>(itemRepo.save(new Item("Folder", "BackEnd", item.getPermissionGroups(), item)), HttpStatus.OK);

                }
            }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }

    public ResponseEntity<Books> addFile(String email, byte[] file, String bookName) {
        Permissions permission = permissionsRepo.findAllByEmail(email);
        Item item = itemRepo.findAllByName("BackEnd");
        if (permission != null)
            if (permission.getPermitionLevel().equalsIgnoreCase("Edit")) {
                if (permission.getPermissions().equals(item.getPermissionGroups())) {
                    return new ResponseEntity<>(booksRepo.save(new Books(file,  item)), HttpStatus.OK);

                }
            }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    public List<Documents> viewMeta(String email) {
        Map<Long, List<Books>> books = new HashMap<>();
        List<Books> allBooks = booksRepo.findAll();
        List<Item> allSpaces= itemRepo.findAllByType("Space");
        List<Documents> documents = new ArrayList<>();


        for (Books b : allBooks) {
            b.setBinary(null);
            List<Books> booksGrope = books.computeIfAbsent(b.getItem().getId(), k -> new ArrayList<Books>());
            booksGrope.add(b);
        }

        for(Item i :allSpaces ){
            Documents document = new Documents();
            document.setId(i.getId());
            document.setName(i.getName());
            if(!books.isEmpty()){
                document.setFolders(new ArrayList<>());

            }
            for(Map.Entry<Long, List<Books>> obj : books.entrySet()){
                Documents folder = new Documents();
                folder.setId(obj.getKey());
                folder.setName(obj.getValue().get(0).getItem().getName());
                folder.setBooks(obj.getValue());
                document.getFolders().add(folder);

            }
            documents.add(document);
        }
        return documents;

    }
    public ResponseEntity<byte[]> downloadFile(String email,Long fileId){
        Permissions permission = permissionsRepo.findAllByEmail(email);
        Books book = booksRepo.findAllById(fileId);

        Item item = itemRepo.findAllByName(book.getItem().getName());
        if (permission != null)
            if (permission.getPermitionLevel().equalsIgnoreCase("Edit")||permission.getPermitionLevel().equalsIgnoreCase("View")) {
                if (permission.getPermissions().equals(item.getPermissionGroups())) {

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("content-disposition", "attachment; filename = " + book.getBookName());
                    return  ResponseEntity.ok().headers(responseHeaders).body(book.getBinary());

                }
            }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }



}
