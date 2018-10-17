package com.oerlemans.strzelno.warehouse.domain.palet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaletDto {

    private int id;
    private String itemId;
    private double weight;
    private String workOrder;

    public PaletDto(String itemId, double weight, String workOrder) {
        this.itemId = itemId;
        this.weight = weight;
        this.workOrder = workOrder;
    }
}
