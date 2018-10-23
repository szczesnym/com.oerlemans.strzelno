package com.oerlemans.strzelno.warehouse.mapper;

import com.oerlemans.strzelno.warehouse.controler.DmControler;
import com.oerlemans.strzelno.warehouse.domain.exact.Item;
import com.oerlemans.strzelno.warehouse.domain.palet.Palet;
import com.oerlemans.strzelno.warehouse.domain.palet.PaletDto;
import com.oerlemans.strzelno.warehouse.repository.exact.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class PaletMapper {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(DmControler.class);

    public Palet mapPaletDtoToPalet(PaletDto paletDto) {
        Palet resultPalet = new Palet();
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        Item item= itemRepository.findByItemCode(paletDto.getItemId());

        resultPalet.setItem_id(paletDto.getItemId());
        resultPalet.setSystemId(env.getProperty("warehouse.exact.system"));

        LOGGER.info("DATA:"+now);
        resultPalet.setStamp(gregorianCalenadarToSqlDate(now));
        now.add(GregorianCalendar.MONTH, item.getBestBeforePeriod());

        resultPalet.setWeight(paletDto.getWeight());
        resultPalet.setBestBefore(gregorianCalenadarToSqlDate(now));
        resultPalet.setWeight(paletDto.getWeight());
        resultPalet.setPaletId(generatePaletNumberFromNumber(resultPalet.getId()));
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

    public int generatePaletNumberFromNumber(int seqNumber) {
        return Integer.valueOf('7' + intOfGivenLenWit(seqNumber, 6));
    }

    protected String intOfGivenLenWit(int number, int length) {
        StringBuilder resultString = new StringBuilder();
        String numberAsString = String.valueOf(number);
        while (resultString.length() + numberAsString.length() < length) {
            resultString.append('0');
        }
        resultString.append(number);
        return resultString.toString();
    }

    protected String gregorianCalenadarToSqlDate(GregorianCalendar calendar) {
        return new StringBuilder()
                .append(calendar.get(Calendar.YEAR))
                .append("-")
                .append(intOfGivenLenWit(calendar.get(Calendar.MONTH) + 1, 2))
                .append("-")
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append( " ")
                .append(calendar.get(Calendar.HOUR_OF_DAY))
                .append(":")
                .append(calendar.get(Calendar.MINUTE))
                .append(":")
                .append(calendar.get(Calendar.SECOND))
                .toString();
    }
}
