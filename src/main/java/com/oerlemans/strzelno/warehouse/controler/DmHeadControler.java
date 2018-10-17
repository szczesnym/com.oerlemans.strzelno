package com.oerlemans.strzelno.warehouse.controler;

import com.oerlemans.strzelno.warehouse.domain.exact.DmHead;
import com.oerlemans.strzelno.warehouse.domain.exact.DmLine;
import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import com.oerlemans.strzelno.warehouse.domain.palet.PaletDto;
import com.oerlemans.strzelno.warehouse.mapper.PaletMapper;
import com.oerlemans.strzelno.warehouse.repository.exact.DmPlHeadRepository;
import com.oerlemans.strzelno.warehouse.repository.exact.DmPlLineRepository;
import com.oerlemans.strzelno.warehouse.repository.exact.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dm")
public class DmHeadControler {
    @Autowired
    private DmPlHeadRepository headDmPLHeadRepository;

    @Autowired
    private DmPlLineRepository lineDmPLRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PaletMapper paletMapper;

    @Value("${warehouse.document.type}")
    private String DOCUMENT_TYPE;

    private static final Logger LOGGER = LoggerFactory.getLogger(DmHeadControler.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<String> getDmLinesItemNumbers(@RequestParam("searchProdOrder") String prodOrder) {
        List<DmHead> listOfHead = headDmPLHeadRepository.findByProjectAndDocumentType(prodOrder, DOCUMENT_TYPE);
        List<List<DmLine>> resultListOfHead = new ArrayList<>();
        listOfHead.stream().forEach(head -> resultListOfHead.add(lineDmPLRepository.findByheaderGuid(head.getHeaderGUID())));
        List<String> listOfItems = resultListOfHead.stream()
                .flatMap(line -> line.stream())
                .map(list -> list.getItemNumber())
                .collect(Collectors.toList());
        return listOfItems;
    }
   @RequestMapping(method = RequestMethod.POST, value = "/")
    public void postPalet(@RequestParam("order_id") String orderId,
                          @RequestParam("item") String item,
                          @RequestParam("weight") String weightString ) {
       Double weight;
        LOGGER.info("Order:" + orderId + ", item:" + item + ", weight:" + weightString);

        try{
            weight = Double.valueOf(weightString);
        } catch (NumberFormatException ex) {
            LOGGER.error("Błąd konwersj wagi !!!");
            weight = 0.0;
        }

        PaletDto paletDto = new PaletDto(item, weight.doubleValue(), orderId);
        Palet palet = paletMapper.mapPaletDtoToPalet(paletDto);
        LOGGER.info("Palet:"+palet);
   }

}
