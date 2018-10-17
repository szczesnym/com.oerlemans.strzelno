package com.oerlemans.strzelno.warehouse.repository.exact;

import com.oerlemans.strzelno.warehouse.domain.exact.DmLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DmPlLineRepository extends CrudRepository<DmLine, Integer> {
    List<DmLine> findByheaderGuid(String headerGuid);
}
