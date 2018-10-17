package com.oerlemans.strzelno.warehouse.domain.exact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Items")
@Getter
@Setter
@NoArgsConstructor


public class Item {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "Description")
    private String description;

    @Column(name = "ItemCode")
    private String itemCode;

    @Column(name = "UserField_04")
    private String engDescription;

    @Column(name = "UserField_02")
    private String mfgItem;

    @Column( name ="UserNumber_04")
    private int bestBeforePeriod;

    @Column(name = "UserNumber_08")
    private int itemCount;

    @Column(name = "UserField_03")
    private String itemEan;

}
