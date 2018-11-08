package com.oerlemans.strzelno.warehouse.controler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class FrontEndController {

    @RequestMapping("/")

    public String index(Map<String, Object> model) {
        return "index-item";
    }

    @RequestMapping("/index-item.html")
    public String itemIndex(Map<String, Object> model) {
        return "index-item";
    }

    @RequestMapping("/index-order.html")
    public String orderIndex(Map<String, Object> model) {
        return "index-order";
    }

    @RequestMapping("/index-reprint.html")
    public String rePrintIndex(Map<String, Object> model) {
        return "index-reprint";
    }


}
