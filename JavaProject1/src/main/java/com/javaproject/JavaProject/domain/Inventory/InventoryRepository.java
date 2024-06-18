package com.javaproject.JavaProject.domain.Inventory;

import com.javaproject.JavaProject.domain.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
