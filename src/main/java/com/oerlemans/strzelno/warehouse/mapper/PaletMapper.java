package com.oerlemans.strzelno.warehouse.mapper;

import com.oerlemans.strzelno.warehouse.controler.DmHeadControler;
import com.oerlemans.strzelno.warehouse.domain.exact.Item;
import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import com.oerlemans.strzelno.warehouse.domain.palet.PaletDto;
import com.oerlemans.strzelno.warehouse.repository.exact.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Component
public class PaletMapper {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(DmHeadControler.class);

    public Palet mapPaletDtoToPalet(PaletDto paletDto) {
        Palet resultPalet = new Palet();
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Item item= itemRepository.findByItemCode(paletDto.getItemId());

        resultPalet.setItem_id(paletDto.getItemId());
        resultPalet.setSystemId(env.getProperty("warehouse.exact.system"));

        LOGGER.info("DATA:"+now);
        resultPalet.setStamp(dateFormater.format(now));
        now.add(GregorianCalendar.MONTH, item.getBestBeforePeriod());

        resultPalet.setWeight(paletDto.getWeight());
        resultPalet.setBestBefore(dateFormater.format(now));
        resultPalet.setWeight(paletDto.getWeight());
        resultPalet.setPaletId(Integer.valueOf('7' + intOfGivenLenWit(resultPalet.getId(), 6)));
        //try-catch
        resultPalet.setCount(item.getItemCount());
        resultPalet.setMfgItem(item.getMfgItem());
        resultPalet.setDescription(item.getDescription());
        resultPalet.setEngDescription(item.getEngDescription());
        resultPalet.setEanCode(item.getItemEan());
        resultPalet.setWorkOrder(paletDto.getWorkOrder());

        return resultPalet;
    }

    public PaletDto mapPaletToPaletDto(Palet palet) {
        return new PaletDto(palet.getId(), palet.getItem_id(), palet.getWeight(), palet.getWorkOrder());
    }

    protected String intOfGivenLenWit(int number, int length) {
        StringBuilder resultString = new StringBuilder();
        String numberAsString = String.valueOf(number);
        while (resultString.length() + numberAsString.length() < length) {
            resultString.append('0');
        }
        return resultString.toString();
    }
}
