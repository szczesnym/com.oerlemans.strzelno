package com.oerlemans.strzelno.warehouse.repository.exact;

import com.oerlemans.strzelno.warehouse.domain.exact.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByItemCode(String itemId);
}
