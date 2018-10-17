package com.oerlemans.strzelno.warehouse.domain.palet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "palets")
@Getter
@Setter
@NoArgsConstructor

public class Palet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id")
    private String item_id;

    @Column(name = "weight")
    private double weight;

}
