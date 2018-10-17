package com.oerlemans.strzelno.warehouse.domain.exact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "pldmDocumentLines")

@Getter
@Setter
@NoArgsConstructor

public class DmLine {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "DocumentNumber")
    private int headDocumentNumber;

    @Column(name = "ItemCode")
    private String itemNumber;

    @Column(name = "HeaderGUID", columnDefinition="uniqueidentifier")
    private String headerGuid;
}
