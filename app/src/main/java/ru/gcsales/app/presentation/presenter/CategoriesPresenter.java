package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.domain.interactor.GetCategories;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.presentation.model.mapper.CategoryViewModelMapper;
import ru.gcsales.app.presentation.view.CategoriesView;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
@InjectViewState
public class CategoriesPresenter extends MvpPresenter<CategoriesView> {

    @Inject
    GetCategories mGetCategories;

    private long mShopId;
    private CategoryViewModelMapper mMapper = new CategoryViewModelMapper();

    public CategoriesPresenter(long shopId) {
        AppApplication.getApplicationComponent().inject(this);
        mShopId = shopId;
    }

    @Override
    protected void onFirstViewAttach() {
        loadCategories();
    }

    @Override
    public void onDestroy() {
        mGetCategories.dispose();
    }

    public void loadCategories() {
        getViewState().showProgress();
        mGetCategories.execute(new GetCategoriesObserver(), GetCategories.Params.get(mShopId));
    }

    private final class GetCategoriesObserver extends DisposableObserver<List<Category>> {

        @Override
        public void onNext(List<Category> categories) {
            getViewState().setData(mMapper.transform(categories, null));
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
        }
    }
}
