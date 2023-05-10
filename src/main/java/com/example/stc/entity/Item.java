package com.example.stc.entity;


import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String name;

    @ManyToOne
    @JoinColumn(name = "permition_Group_Id")
   private PermissionGroups permissionGroups;
    @ManyToOne
    private Item item;
    public Item() {
    }

    public Item( String type, String name, PermissionGroups permissionGroups) {
        this.type = type;
        this.name = name;
        this.permissionGroups = permissionGroups;
    }

    public Item(Long id, String type, String name, PermissionGroups permissionGroups) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.permissionGroups = permissionGroups;
    }

    public Item(String type, String name, PermissionGroups permissionGroups, Item item) {
        this.type = type;
        this.name = name;
        this.permissionGroups = permissionGroups;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PermissionGroups getPermissionGroups() {
        return permissionGroups;
    }

    public void setPermissionGroups(PermissionGroups permissionGroups) {
        this.permissionGroups = permissionGroups;
    }
}
