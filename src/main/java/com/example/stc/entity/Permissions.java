package com.example.stc.entity;


import javax.persistence.*;
@Entity
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name= "user_email")
    private String email;
    @Column(name= "permision_level")
    private String permitionLevel;


    @ManyToOne
    @JoinColumn(name = "group_id")
    PermissionGroups permissions;

    public Permissions() {
    }

    public Permissions(Long id, String email, String permitionLevel, PermissionGroups permissions) {
        this.id = id;
        this.email = email;
        this.permitionLevel = permitionLevel;
        this.permissions = permissions;
    }

    public Permissions(String email, String permitionLevel, PermissionGroups permissions) {
        this.email = email;
        this.permitionLevel = permitionLevel;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPermitionLevel() {
        return permitionLevel;
    }
    public void setPermitionLevel(String permitionLevel) {
        this.permitionLevel = permitionLevel;
    }

    public PermissionGroups getPermissions() {
        return permissions;
    }

    public void setPermissions(PermissionGroups permissions) {
        this.permissions = permissions;
    }
}
