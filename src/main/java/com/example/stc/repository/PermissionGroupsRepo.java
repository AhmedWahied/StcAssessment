package com.example.stc.repository;

import com.example.stc.entity.PermissionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupsRepo extends JpaRepository<PermissionGroups,Long> {
}
