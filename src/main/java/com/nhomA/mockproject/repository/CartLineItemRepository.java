package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartLineItemRepository extends JpaRepository<CartLineItem, Long> {
    List<CartLineItem> findByCartIdAndIsDeleted (Long cartId, boolean isDeleted);
    List<CartLineItem> findByCartIdAndIsDeletedOrderById(Long cartId, boolean isDeleted);
    Optional<CartLineItem> findByCartIdAndProductIdAndIsDeletedAndColorAndStorage (Long cartId, Long productId, boolean isDeleted, String color, String storageCapacity);
    List<CartLineItem> findByCartIdAndProductId (Long cartId, Long id);
    Optional<CartLineItem> findByIdAndCartId (Long id, Long CartId);

}
