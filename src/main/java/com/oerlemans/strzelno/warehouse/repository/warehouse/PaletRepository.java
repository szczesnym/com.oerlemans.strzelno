package com.oerlemans.strzelno.warehouse.repository.warehouse;

import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaletRepository extends JpaRepository<Palet, Integer> {
}
