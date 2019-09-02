package org.csu.tank.persistence;

import org.csu.tank.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemDAO {
    List<Item> searchItemList(String keywords);

    void updateInventoryQuantity(Map<String, Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
}
