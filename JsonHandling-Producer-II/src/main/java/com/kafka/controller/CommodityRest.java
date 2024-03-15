package com.kafka.controller;

import com.kafka.entity.Commodity;
import com.kafka.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commodity/v1")
public class CommodityRest {

    @Autowired
    private CommodityService commodityService;

    @GetMapping()
    public List<Commodity> addCommodity(){
        return commodityService.helperCommodity();
    }
}
