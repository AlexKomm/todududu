package tk.alexkomm.todududu.ui.views;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import tk.alexkomm.todududu.utils.rxtask.Result;

public interface TodoItemsView {

    Func1<Observable<Void>, Subscription> showLoadingUi();

    Func1<Observable<Result.Error>, Subscription> showErrorUi();

    Func1<Observable<Result.Success>, Subscription> showContentUi();
}
