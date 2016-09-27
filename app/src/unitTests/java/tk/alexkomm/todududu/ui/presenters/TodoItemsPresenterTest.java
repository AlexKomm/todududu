package tk.alexkomm.todududu.ui.presenters;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import tk.alexkomm.todududu.data.entities.TodoItem;
import tk.alexkomm.todududu.models.TodoItemsModel;
import tk.alexkomm.todududu.ui.views.TodoItemTestView;
import tk.alexkomm.todududu.utils.rxtask.Result;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoItemsPresenterTest {

    private TodoItemsPresenter presenter;
    private TodoItemTestView todoItemsView;
    private TodoItemsModel itemsModel;

    @Before
    public void beforeEachTest() {

        TodoItemsPresenterConfiguration configuration = TodoItemsPresenterConfiguration.builder()
                .ioScheduler(Schedulers.immediate()).build();

        itemsModel = mock(TodoItemsModel.class);
        todoItemsView = new TodoItemTestView();

        presenter = new TodoItemsPresenter(itemsModel, configuration);
        presenter.bindView(todoItemsView);
    }

    @Test
    public void reloadData_shouldMoveViewToTheLoadingState() {
        when(itemsModel.getAll()).thenReturn(Observable.defer(() -> Observable.just(emptyList())));

        presenter.reloadData();

        verify(todoItemsView.loadingUiAction).call(null);
    }

    @Test
    public void reloadData_shouldSendDataToTheView() {
        List<TodoItem> items = asList(TodoItem.builder().id(1).note("Note 1").color(Color.BLUE)
                .date(new Date()).reminder(false).build(), TodoItem.builder().id(2).note("Note "
                + "2").color(Color.BLUE).date(new Date()).reminder(false).build());

        when(itemsModel.getAll()).thenReturn(Observable.defer(() -> Observable.just(items)));

        presenter.reloadData();
        verify(todoItemsView.successUiAction).call(any(Result.Success.class));
    }

    @Test
    public void reloadData_shouldSendErrorToTheView() {
        Throwable error = new RuntimeException();
        when(itemsModel.getAll()).thenReturn(Observable.defer(() -> Observable.error(error)));

        presenter.reloadData();
        verify(todoItemsView.errorUiAction).call(any(Result.Error.class));
    }
}
