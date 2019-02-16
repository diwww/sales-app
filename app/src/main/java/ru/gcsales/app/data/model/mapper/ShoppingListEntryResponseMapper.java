package ru.gcsales.app.data.model.mapper;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import ru.gcsales.app.data.model.remote.ShoppingListEntryResponse;
import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Shopping list entry response mapper
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public class ShoppingListEntryResponseMapper extends AbstractMapper<ShoppingListEntryResponse, ShoppingListEntry, Void> {

    @Override
    public ShoppingListEntry transform(ShoppingListEntryResponse input, Void params) {
        return new ShoppingListEntry()
                .setImageUrl(input.getImageUrl())
                .setItemId(input.getItemId())
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setShop(input.getShop())
                .setTitle(input.getTitle())
                .setCreated(new Date(TimeUnit.SECONDS.toMillis(input.getCreated())))
                .setShowShop(input.isShowShop());
    }
}
