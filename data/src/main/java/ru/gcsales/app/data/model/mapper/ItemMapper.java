package ru.gcsales.app.data.model.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.gcsales.app.data.model.local.ItemEntity;
import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.Shop;

/**
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
public class ItemMapper {

    public ItemEntity transformResponse(ItemResponse response) {
        ItemEntity entity = null;

        if (response != null) {
            entity = new ItemEntity();
            entity.setId(response.getId());
            entity.setName(response.getName());
            entity.setCategory(response.getCategory());
            entity.setImageUrl(response.getImageUrl());
            entity.setCondition(response.getCondition());
            entity.setCrawlDate(response.getCrawlDate());
            entity.setDateIn(response.getDateIn());
            entity.setDateOut(response.getDateOut());
            entity.setDiscount(response.getDiscount());
            entity.setOldPrice(response.getOldPrice());
            entity.setNewPrice(response.getNewPrice());
            entity.setShopId(response.getShopResponse().getId());
        }

        return entity;
    }

    public List<ItemEntity> transformResponse(List<ItemResponse> responseList) {
        List<ItemEntity> entityList;

        if (responseList != null && !responseList.isEmpty()) {
            entityList = new ArrayList<>();
            for (ItemResponse response : responseList) {
                entityList.add(transformResponse(response));
            }
        } else {
            return Collections.emptyList();
        }

        return entityList;
    }

    public Item transformEntity(ItemWithShop itemWithShop) {
        Item item = null;

        if (itemWithShop != null) {
            item = new Item();
            item.setId(itemWithShop.getItemEntity().getId());
            item.setName(itemWithShop.getItemEntity().getName());
            item.setCategory(itemWithShop.getItemEntity().getCategory());
            item.setImageUrl(itemWithShop.getItemEntity().getImageUrl());
            item.setCondition(itemWithShop.getItemEntity().getCondition());
            item.setCrawlDate(itemWithShop.getItemEntity().getCrawlDate());
            item.setDiscount(itemWithShop.getItemEntity().getDiscount());
            item.setOldPrice(itemWithShop.getItemEntity().getOldPrice());
            item.setNewPrice(itemWithShop.getItemEntity().getNewPrice());

            // Cast string to date using date format
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date dateIn = format.parse(itemWithShop.getItemEntity().getDateIn());
                Date dateOut = format.parse(itemWithShop.getItemEntity().getDateOut());
                item.setDateIn(dateIn);
                item.setDateOut(dateOut);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Set shop
            Shop shop = new Shop();
            shop.setId(itemWithShop.getItemEntity().getShopId());
            shop.setName(itemWithShop.getShopName());
            shop.setAlias(itemWithShop.getShopAlias());
            shop.setImageUrl(itemWithShop.getShopImageUrl());

            item.setShop(shop);
        }

        return item;
    }

    public List<Item> transformEntity(List<ItemWithShop> itemWithShopList) {
        List<Item> entityList;

        if (itemWithShopList != null && !itemWithShopList.isEmpty()) {
            entityList = new ArrayList<>();
            for (ItemWithShop itemWithShop : itemWithShopList) {
                entityList.add(transformEntity(itemWithShop));
            }
        } else {
            return Collections.emptyList();
        }

        return entityList;
    }
}
