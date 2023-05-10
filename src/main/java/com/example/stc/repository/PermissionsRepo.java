package com.example.stc.repository;

import com.example.stc.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepo extends JpaRepository<Permissions,Long> {

    Permissions findAllByEmail(String email);
}
