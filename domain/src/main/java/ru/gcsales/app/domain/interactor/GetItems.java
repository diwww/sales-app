package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.repository.ItemRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/18/18
 */
public class GetItems extends UseCase<List<Item>, GetItems.Params> {

    private ItemRepository mItemRepository;

    @Inject
    public GetItems(PostExecutionThread postExecutionThread, ItemRepository itemRepository) {
        super(postExecutionThread);
        mItemRepository = itemRepository;
    }

    @Override
    Observable<List<Item>> buildObservable(Params params) {
        return mItemRepository.getItems(params.mShopId, params.mCategory, params.mPage);
    }

    public static class Params {

        private long mShopId;
        private String mCategory;
        private int mPage;

        public Params(long shopId, String category, int page) {
            mShopId = shopId;
            mCategory = category;
            mPage = page;
        }

        public static Params get(long shopId, String category, int page) {
            return new Params(shopId, category, page);
        }
    }
}
