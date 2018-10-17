package com.oerlemans.strzelno.warehouse.domain.exact;

//import DmLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pldmDocumentHeaders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DmHead {
    //@Transient
/*    @OneToMany( targetEntity = DmLine.class,
            mappedBy = "headerGuid",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
*/
    @Transient
    private List<DmLine> dmLines = new ArrayList<>();

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "DocumentType")
    private String documentType;

    @Column(name ="DocumentNumber")
    private int documentNumber;

    @Column(name = "Project")
    private String project;

    @Column(name = "HeaderGUID")
    private String headerGUID;

    @Override
    public DmHead clone() {
        return new DmHead(new ArrayList<>(), this.id, this.documentType, this.documentNumber, this.project, this.headerGUID);
    }
}
