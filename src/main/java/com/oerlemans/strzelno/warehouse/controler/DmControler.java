package com.oerlemans.strzelno.warehouse.controler;

import com.oerlemans.strzelno.warehouse.domain.exact.DmLine;
import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import com.oerlemans.strzelno.warehouse.domain.palet.PaletDto;
import com.oerlemans.strzelno.warehouse.mapper.PaletMapper;
import com.oerlemans.strzelno.warehouse.repository.exact.DmPlHeadRepository;
import com.oerlemans.strzelno.warehouse.repository.exact.DmPlLineRepository;
import com.oerlemans.strzelno.warehouse.repository.exact.ItemRepository;
import com.oerlemans.strzelno.warehouse.repository.warehouse.PaletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dm")
public class DmControler {
    @Autowired
    private DmPlLineRepository lineDmPLRepository;

    @Autowired
    private PaletRepository paletRepository;

    @Autowired
    private PaletMapper paletMapper;

    @Value("${warehouse.document.type}")
    private String DOCUMENT_TYPE;

    private static final Logger LOGGER = LoggerFactory.getLogger(DmControler.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<String> getDmLinesItemNumbers(@RequestParam("searchProdOrder") String prodOrder) {
        List<DmLine> listOfLines = lineDmPLRepository.findByDocumentTypeAndProject(DOCUMENT_TYPE, prodOrder);
        List<String> listOfItems = listOfLines.stream()
                .map(list -> list.getItemNumber())
                .collect(Collectors.toList());
        return listOfItems;
    }

   @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public int postPaletFromWorkOrder(@RequestBody PaletDto paletDto) {
        LOGGER.info("Order:" + paletDto.getWorkOrder() + ", item:" + paletDto.getItemId() + ", weight:" + paletDto.getWeight());
        try {
            Palet palet = paletMapper.mapPaletDtoToPalet(paletDto);
            LOGGER.info("Palet:"+palet);
            paletRepository.save(palet);
            palet.setPaletId(paletMapper.generatePaletNumberFromNumber(palet.getId()));
            paletRepository.save(palet);
            return palet.getPaletId();
        } catch (Exception ex) {
            return 0;
        }

   }

}
