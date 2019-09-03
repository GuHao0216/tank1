package org.csu.tank.controller;

import org.csu.tank.domain.Item;
import org.csu.tank.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/product/{productId}")
    public List<Item> getItemList(@PathVariable("productId")int productId){
        return catalogService.getItemListByProduct(productId);
    }
}