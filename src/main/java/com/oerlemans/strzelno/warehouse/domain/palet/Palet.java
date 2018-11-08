package com.oerlemans.strzelno.warehouse.domain.palet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "palets")
@Getter
@Setter
@NoArgsConstructor


public class Palet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id")
    private String item_id;

    @Column(name = "system_id")
    private String systemId; //

    @Column(name = "best_before")
    private String bestBefore;

    @Column(name = "weight")
    private double weight;

    @Column(name = "lot")
    private String lot;

    @Column(name = "palet_id")
    private int paletId;

    @Column(name = "count")
    private double count;

    @Column(name = "content_id")
    private String mfgItem;

    @Column(name = "description")
    private String description;

    @Column(name = "engDescription")
    private String engDescription;

    @Column(name = "eanCode")
    private String eanCode;

    @Column(name = "stamp")
    private String stamp;
    //TimeStamp - to be gereted by SQL triger onInsert

    @Column(name = "workOrder")
    private String workOrder;

    @Column(name = "_active")
    private final int active = 1;

    @Column(name = "toBePrinter")
    private final int print = 1;

    @Override
    public String toString() {
        return "Palet{" +
                "id=" + id +
                ", item_id='" + item_id + '\'' +
                ", systemId='" + systemId + '\'' +
                ", bestBefore='" + bestBefore + '\'' +
                ", weight=" + weight +
                ", lot='" + lot + '\'' +
                ", paletId=" + paletId +
                ", count=" + count +
                ", mfgItem='" + mfgItem + '\'' +
                ", description='" + description + '\'' +
                ", engDescription='" + engDescription + '\'' +
                ", eanCode='" + eanCode + '\'' +
                ", stamp='" + stamp + '\'' +
                ", workOrder='" + workOrder + '\'' +
                ", active=" + active +
                ", print=" + print +
                '}';
    }

}
