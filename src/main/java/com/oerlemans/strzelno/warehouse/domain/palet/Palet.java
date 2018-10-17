package com.oerlemans.strzelno.warehouse.domain.palet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "palets")
@Getter
@Setter
@NoArgsConstructor


public class Palet {
    @Transient
    @Autowired
    private Environment env;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id")
    private String item_id;

    @Column(name="system_id")
    private String systemId; //

    @Column(name= "best_before")
    private String bestBefore;

    @Column(name = "weight")
    private double weight;

    @Column(name = "lot")
    private String lot;

    @Column(name ="palet_id")
    private int paletId;

    @Column(name ="count")
    private double count;

    @Column(name ="content")
    private String mfgItem;

    @Column(name ="description")
    private String description;

    @Column(name ="engDescription")
    private String engDescription;

    @Column(name ="eanCode")
    private String eanCode;

    @Column(name = "stamp")
    private String stamp;
    //TimeStamp - to be gereted by SQL triger onInsert

    @Column(name ="workOrder")
    private String workOrder;

}
