package org.csu.tank.service.impl;

import org.csu.tank.domain.Item;
import org.csu.tank.persistence.ItemDAO;
import org.csu.tank.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public List<Item> searchItemList(String keyword) {
        return itemDAO.searchItemList("%" + keyword + "%");
    }

    @Override
    public List<Item> getItemListByProduct(int productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    @Override
    public List<Item> getItemListByCategory(int categoryId) {
        return itemDAO.getItemListByCategory(categoryId);
    }

    @Override
    public Item getItem(int itemId) {
        return itemDAO.getItem(itemId);
    }
}
