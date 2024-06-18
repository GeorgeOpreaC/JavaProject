package com.javaproject.JavaProject.domain.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query("""
            SELECT e FROM Item e WHERE e.userId = :userId
            """)
    List<Item> findAllByUserId(int userId);
}
