package tk.alexkomm.todududu.models;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import rx.Observable;
import tk.alexkomm.todududu.data.entities.TodoItem;

public class TodoItemsModel {

    private final List<TodoItem> items;

    public TodoItemsModel() {
        TodoItem todoItem1 = TodoItem.builder().id(1).date(new Date()).note("Todo item 1").color(Color.BLUE).reminder(true).build();

        TodoItem todoItem2 = TodoItem.builder().id(2).date(new Date()).note("Todo item 2").color(Color.RED).reminder(false).build();

        TodoItem todoItem3 = TodoItem.builder().id(3).date(new Date()).note("Todo item 3").color(Color.YELLOW).reminder(false).build();

        items = new ArrayList<>(Arrays.asList(todoItem1, todoItem2, todoItem3));
    }

    public Observable<List<TodoItem>> getAll() {
        return Observable.defer(() -> Observable.just(items));
    }
}
