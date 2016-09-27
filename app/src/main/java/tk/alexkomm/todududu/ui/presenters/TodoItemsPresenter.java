package tk.alexkomm.todududu.ui.presenters;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;
import tk.alexkomm.todududu.data.entities.TodoItem;
import tk.alexkomm.todududu.models.TodoItemsModel;
import tk.alexkomm.todududu.ui.views.TodoItemsView;
import tk.alexkomm.todududu.utils.RxUi;
import tk.alexkomm.todududu.utils.rxtask.Task;

public class TodoItemsPresenter extends Presenter<TodoItemsView> {
    @NonNull
    private final TodoItemsModel itemsModel;

    @NonNull
    private final TodoItemsPresenterConfiguration itemsPresenterConfiguration;

    public TodoItemsPresenter(@NonNull TodoItemsModel itemsModel, @NonNull
            TodoItemsPresenterConfiguration itemsPresenterConfiguration) {
        this.itemsModel = itemsModel;
        this.itemsPresenterConfiguration = itemsPresenterConfiguration;
    }

    public void reloadData() {
        final CompositeSubscription subscription = new CompositeSubscription();

        Observable<List<TodoItem>> getItems = itemsModel.getAll().subscribeOn(itemsPresenterConfiguration.ioScheduler());

        final Task task = Task.Factory.fromObservable(getItems);

        final TodoItemsView view = getView();
        if (view != null) {
            subscription.add(RxUi.bind(task.loading(), view.showLoadingUi()));
            subscription.add(RxUi.bind(task.error(), view.showErrorUi()));
            subscription.add(RxUi.bind(task.success(), view.showContentUi()));
        }

        task.start();

        unsubscribeOnUnbindView(subscription);
    }
}
