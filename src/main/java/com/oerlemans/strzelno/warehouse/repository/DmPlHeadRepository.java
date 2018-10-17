package com.oerlemans.strzelno.warehouse.repository;

import com.oerlemans.strzelno.warehouse.domain.dmHead.DmHead;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface DmPlHeadRepository extends CrudRepository<DmHead, Integer> {
    List<DmHead> findByProject(String project);
    List<DmHead> findByProjectAndDocumentType(String project, String documentType);
}
