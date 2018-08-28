package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.CategoryEntity;

/**
 * Category response mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class CategoryResponseMapper extends AbstractMapper<String, CategoryEntity, CategoryResponseMapper.Params> {

    @Override
    public CategoryEntity transform(String input, Params params) {
        CategoryEntity entity = null;

        if (input != null) {
            entity = new CategoryEntity();
            entity.setName(input);
            entity.setShopId(params.mShopId);
        }

        return entity;

    }

    /**
     * Params for transforming category
     */
    public static class Params {
        long mShopId;

        public Params(long shopId) {
            mShopId = shopId;
        }

        /**
         * Gets new params instance.
         *
         * @param shopId shop id
         * @return new params instance
         */
        public static Params get(long shopId) {
            return new Params(shopId);
        }
    }
}
