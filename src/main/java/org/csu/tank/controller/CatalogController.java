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

    @GetMapping("/catalog/product/{productId}/items/{page}")
    public Response getItemListByProductId(@PathVariable("productId") int productId,@PathVariable("page") int page) {
        JSONObject object = new JSONObject();
        List<Item> itemList = catalogService.getItemListByProduct(productId);
        List<Item> newItemList = itemList.subList((page-1)*10,page*10);
        object.put("itemList", newItemList);
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

    @GetMapping("/catalog/category/{categoryId}/items/{page}")
    public Response getItemListByCategoryId(@PathVariable("categoryId") int categoryId,@PathVariable("page") int page){
        JSONObject object = new JSONObject();
        List<Item> itemList = catalogService.getItemListByCategory(categoryId);
        List<Item> newItemList = itemList.subList((page-1)*10,page*10);
        object.put("itemList", newItemList);
        return success(object);

    }

    @GetMapping("/catalog/searchItemList/{keyword}/items/{page}")
    public Response searchItemList(@PathVariable("keyword") String keyword,@PathVariable("page") int page){
        JSONObject object = new JSONObject();
        List<Item> itemList = catalogService.searchItemList(keyword);
        List<Item> newItemList = itemList.subList((page-1)*10,page*10);
        object.put("itemList", newItemList);
        return success(object);
    }
}
