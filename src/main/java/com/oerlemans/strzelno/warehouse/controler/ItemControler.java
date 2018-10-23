package com.oerlemans.strzelno.warehouse.controler;


import com.oerlemans.strzelno.warehouse.domain.exact.ItemDto;
import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import com.oerlemans.strzelno.warehouse.domain.palet.PaletDto;
import com.oerlemans.strzelno.warehouse.mapper.PaletMapper;
import com.oerlemans.strzelno.warehouse.repository.exact.ItemRepository;
import com.oerlemans.strzelno.warehouse.repository.warehouse.PaletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/item")
public class ItemControler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemControler.class);

    @Autowired
    PaletMapper paletMapper;

    @Autowired
    PaletRepository paletRepository;

    @Autowired
    ItemRepository itemRepository;


    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public int postpaletFromItem(@RequestBody ItemDto itemDto) {
        LOGGER.info("*********Palet from item, item:" + itemDto.getItemCode() + ", weight:" + itemDto.getWeight());
        try {
            Palet palet = paletMapper.mapPaletDtoToPalet(new PaletDto(itemDto));
            LOGGER.info("*********PALET:" + palet);
            paletRepository.save(palet);
            palet.setPaletId(paletMapper.generatePaletNumberFromNumber(palet.getId()));
            paletRepository.save(palet);
            return palet.getPaletId();
        } catch (Exception ex) {
            return 0;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public String getItemDescription(@RequestParam("itemNumber") String itemCode) {
        String itemDescription;
        try {
            itemDescription = itemRepository.findByItemCode(itemCode).getDescription();
            LOGGER.info("Item description:" + itemDescription);
            return "{\"itemDesc\": \"" + itemDescription + "\"}";
        } catch (Exception ex) {
            return new String("{\"itemDesc\": \"\"}");
        }
    }
}
