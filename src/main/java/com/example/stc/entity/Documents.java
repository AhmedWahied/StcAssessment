package com.example.stc.entity;

import java.util.List;

public class Documents {
    private Long id ;
    private  String name ;
    List<Books> books;
    List<Documents> folders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public List<Documents> getFolders() {
        return folders;
    }

    public void setFolders(List<Documents> folders) {
        this.folders = folders;
    }
}
