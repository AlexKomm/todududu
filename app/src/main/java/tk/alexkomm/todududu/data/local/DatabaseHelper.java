package tk.alexkomm.todududu.data.local;

import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import tk.alexkomm.todududu.data.entities.TodoItem;

public class DatabaseHelper {

    private final BriteDatabase db;

    public DatabaseHelper(@NonNull DbOpenHelper dbOpenHelper, @NonNull Scheduler scheduler) {
        db = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, scheduler);
    }

    public Observable<List<TodoItem>> getTodoItems() {
        return db.createQuery(Db.Item.TABLE_NAME, "SELECT * FROM " + Db.Item.TABLE_NAME)
                .mapToList(cursor -> null);
    }
}
