package com.oerlemans.strzelno.warehouse.repository.exact;

import com.oerlemans.strzelno.warehouse.domain.exact.DmHead;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface DmPlHeadRepository extends CrudRepository<DmHead, Integer> {
    List<DmHead> findByProject(String project);
    List<DmHead> findByProjectAndDocumentType(String project, String documentType);
}
