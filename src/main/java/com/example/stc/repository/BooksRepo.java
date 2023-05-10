package com.example.stc.repository;

import com.example.stc.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books,Long> {
    Books findAllById(Long fileId);

}
