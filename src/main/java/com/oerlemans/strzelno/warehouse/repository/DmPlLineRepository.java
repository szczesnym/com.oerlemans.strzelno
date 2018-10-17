package com.oerlemans.strzelno.warehouse.repository;

import com.oerlemans.strzelno.warehouse.domain.dmHead.DmHead;
import com.oerlemans.strzelno.warehouse.domain.dmLine.DmLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DmPlLineRepository extends CrudRepository<DmLine, Integer> {
    List<DmLine> findByheaderGuid(String headerGuid);
}
