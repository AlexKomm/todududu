package tk.alexkomm.todududu.ui.views;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import tk.alexkomm.todududu.utils.rxtask.Result;

import static org.mockito.Mockito.mock;
import static tk.alexkomm.todududu.utils.RxUi.testUi;

@SuppressWarnings("unchecked")
public class TodoItemTestView implements TodoItemsView {

    public Action1<Result.Error> errorUiAction = mock(Action1.class);
    public Action1<Void> loadingUiAction = mock(Action1.class);
    public Action1<Result.Success> successUiAction = mock(Action1.class);

    @Override
    public Func1<Observable<Void>, Subscription> showLoadingUi() {
        return testUi(loadingUiAction);
    }

    @Override
    public Func1<Observable<Result.Error>, Subscription> showErrorUi() {
        return testUi(errorUiAction);
    }

    @Override
    public Func1<Observable<Result.Success>, Subscription> showContentUi() {
        return testUi(successUiAction);
    }
}
