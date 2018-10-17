package com.oerlemans.strzelno.warehouse.domain.item;

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
    private String itemNumber;
}
