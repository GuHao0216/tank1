package org.csu.tank.persistence;

import org.csu.tank.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemDAO {
    List<Item> searchItemList(String keywords);

    void updateCartQuantity(int cartId,int itemId,int count);

    int getCartQuantity(int itemId);

    List<Item> getItemListByProduct(int productId);

    Item getItem(int itemId);
}
