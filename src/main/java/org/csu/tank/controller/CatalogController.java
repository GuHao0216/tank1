package org.csu.tank.controller;

import com.alibaba.fastjson.JSONObject;
import org.csu.tank.base.Response;
import org.csu.tank.domain.Item;
import org.csu.tank.service.CatalogService;
import org.csu.tank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.csu.tank.base.Response.success;

@RestController
@CrossOrigin(allowCredentials = "true")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/product/{productId}/items")
    public Response getItemListByProductId(@PathVariable("productId") int productId) {
        JSONObject object = new JSONObject();
        object.put("itemList", catalogService.getItemListByProduct(productId));
        return success(object);

    }

    @GetMapping("/catalog/item/{itemId}")
    public Response getItem(@PathVariable("itemId") int itemId) {
        JSONObject object = new JSONObject();
        String Detail = catalogService.getItem(itemId).getDetail();
        if(Detail == null)
            object.put("itemDetail",null);
        else {
            String[] detail = catalogService.getItem(itemId).getDetail().split(";");
            object.put("itemDetail", detail);
        }
        object.put("item", catalogService.getItem(itemId));
        return success(object);

    }

    @GetMapping("/catalog/category/{categoryId}/items")
    public Response getItemListByCategoryId(@PathVariable("categoryId") int categoryId){
        JSONObject object = new JSONObject();
        object.put("itemList", catalogService.getItemListByCategory(categoryId));
        return success(object);

    }

    @GetMapping("/catalog/searchItemList/{keyword}/items")
    public Response searchItemList(@PathVariable("keyword") String keyword){
        JSONObject object = new JSONObject();
        object.put("itemList", catalogService.searchItemList(keyword));
        return success(object);
    }
}
