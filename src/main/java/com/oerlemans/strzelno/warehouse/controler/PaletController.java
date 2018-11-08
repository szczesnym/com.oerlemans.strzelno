package com.oerlemans.strzelno.warehouse.controler;


import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import com.oerlemans.strzelno.warehouse.repository.warehouse.PaletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palet")

public class PaletController {
    @Autowired PaletRepository paletRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemControler.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Palet getPalet(@RequestParam("palet_no") String stringPaletNo) {
        int paletNo;
        try{
            paletNo = Integer.valueOf(stringPaletNo);
        } catch (NumberFormatException nfEx) {
            nfEx.printStackTrace();
            LOGGER.error(nfEx.getMessage());
            LOGGER.error("Value provided:" + stringPaletNo);
            return new Palet();
        }
       return paletRepository.getByPaletId(paletNo);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/")
    public boolean rePrintPalet(@RequestParam("palet_no") String stringPaletNo) {
    Palet rePrint;
    int paletNo;
    try{
        paletNo = Integer.valueOf(stringPaletNo);
    } catch (NumberFormatException nfEx) {
        nfEx.printStackTrace();
        LOGGER.error(nfEx.getMessage());
        LOGGER.error("Cannot reprint palet -> number conversion error value provided:" + stringPaletNo);
        return false;
    }
    try {
        rePrint = paletRepository.getByPaletId(paletNo);
    } catch (Exception ex) {
        ex.printStackTrace();
        LOGGER.error(ex.getMessage());
        LOGGER.error("Cannot reprint palet -> cannot find pallet of given number:" + paletNo);
        return false;
    }
    rePrint.setPrint(1);
    paletRepository.save(rePrint);
    return true;
    }
}
