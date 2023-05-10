package com.example.stc.entity;


import javax.persistence.*;

@Entity
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private byte[] binary;
    private String bookName;



    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Books() {
    }

    public Books(byte[] binary, Item item) {
        this.binary = binary;
        this.item = item;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public byte[] getBinary() {
        return binary;
    }
    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
