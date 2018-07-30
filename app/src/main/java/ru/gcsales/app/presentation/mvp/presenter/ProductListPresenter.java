package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.presentation.mvp.mapper.ProductModelDataMapper;
import ru.gcsales.app.presentation.mvp.view.ProductListView;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
@InjectViewState
public class ProductListPresenter extends MvpPresenter<ProductListView> {

    @Inject
    GetProducts mGetProducts;
    @Inject
    ProductModelDataMapper mProductModelDataMapper;

    public ProductListPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    public void loadProducts(long shopId) {
        getViewState().showProgress();
        mGetProducts.execute(new DisposableObserver<List<Product>>() {
            @Override
            public void onNext(List<Product> products) {
                getViewState().setProducts(mProductModelDataMapper.transform(products));
            }

            @Override
            public void onError(Throwable e) {
                // TODO
            }

            @Override
            public void onComplete() {
                getViewState().hideProgress();
            }
        }, GetProducts.Params.forShop(shopId, 1));
    }
}
