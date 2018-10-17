package com.oerlemans.strzelno.warehouse.controler;

import com.oerlemans.strzelno.warehouse.domain.dmHead.DmHead;
import com.oerlemans.strzelno.warehouse.domain.dmLine.DmLine;
import com.oerlemans.strzelno.warehouse.repository.DmPlHeadRepository;
import com.oerlemans.strzelno.warehouse.repository.DmPlLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dm")
public class DmHeadControler {
    @Autowired
    private DmPlHeadRepository headDmPLHeadRepository;

    @Autowired
    private DmPlLineRepository lineDmPLRepository;

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
}
