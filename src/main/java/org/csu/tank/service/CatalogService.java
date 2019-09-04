package org.csu.tank.service;

import org.csu.tank.domain.Item;

import java.util.List;

public interface CatalogService {

    List<Item> searchItemList(String keyword);
    List<Item> getItemListByProduct(int productId);
    List<Item> getItemListByCategory(int categoryId);

    Item getItem(int itemId);    //商品展示


}
