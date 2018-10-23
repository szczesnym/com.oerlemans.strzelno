package com.oerlemans.strzelno.warehouse.domain.palet;

import com.oerlemans.strzelno.warehouse.domain.exact.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
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

    public PaletDto(ItemDto itemDto) {
        this.itemId = itemDto.getItemCode();
        this.weight = itemDto.getWeight();
    }
}
