package ru.gcsales.app.data.model.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Item entity mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ItemEntityMapper extends AbstractMapper<ItemWithShop, Item, Void> {
    @Override
    public Item transform(ItemWithShop input, Void params) {
        Item item = null;

        if (input != null) {
            item = new Item();
            item.setId(input.getItemEntity().getId());
            item.setName(input.getItemEntity().getName());
            item.setCategory(input.getItemEntity().getCategory());
            item.setImageUrl(input.getItemEntity().getImageUrl());
            item.setCondition(input.getItemEntity().getCondition());
            item.setCrawlDate(input.getItemEntity().getCrawlDate());
            item.setDiscount(input.getItemEntity().getDiscount());
            item.setOldPrice(input.getItemEntity().getOldPrice());
            item.setNewPrice(input.getItemEntity().getNewPrice());

            // Cast string to date using date format
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date dateIn = format.parse(input.getItemEntity().getDateIn());
                Date dateOut = format.parse(input.getItemEntity().getDateOut());
                item.setDateIn(dateIn);
                item.setDateOut(dateOut);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Set shop
            Shop shop = new Shop();
            shop.setId(input.getItemEntity().getShopId());
            shop.setName(input.getShopName());
            shop.setAlias(input.getShopAlias());
            shop.setImageUrl(input.getShopImageUrl());

            item.setShop(shop);
        }

        return item;
    }
}
