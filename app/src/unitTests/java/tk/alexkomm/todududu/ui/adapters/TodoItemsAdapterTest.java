package tk.alexkomm.todududu.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import tk.alexkomm.todududu.R;
import tk.alexkomm.todududu.TodududuRobolectricUnitTestRunner;
import tk.alexkomm.todududu.data.entities.TodoItem;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(TodududuRobolectricUnitTestRunner.class)
public class TodoItemsAdapterTest {

    private LayoutInflater layoutInflater;
    private TodoItemsAdapter todoItemsAdapter;

    @Before
    public void beforeEachTest() {
        layoutInflater = mock(LayoutInflater.class);
        todoItemsAdapter = new TodoItemsAdapter(layoutInflater);
    }

    @Test
    public void getItemCount_shouldReturnZeroByDefault() {
        assertThat(todoItemsAdapter.getItemCount()).isEqualTo(0);
    }

    @Test
    public void setData_shouldNotifyObserversAboutChange() {
        RecyclerView.AdapterDataObserver observer = mock(RecyclerView.AdapterDataObserver.class);

        todoItemsAdapter.registerAdapterDataObserver(observer);
        todoItemsAdapter.setData(emptyList());
        verify(observer).onChanged();
        verifyNoMoreInteractions(observer);
    }

    @Test
    public void onCreateViewHolder_shouldCreateViewHolder() {
        ViewGroup parent = mock(ViewGroup.class);
        View itemView = mock(View.class);

        TextView noteTextView = mock(TextView.class);
        when(itemView.findViewById(R.id.todo_list_item_note_text_view)).thenReturn(noteTextView);

        when(layoutInflater.inflate(R.layout.todo_list_item, parent, false)).thenReturn(itemView);

        TodoItemsViewHolder viewHolder = todoItemsAdapter.onCreateViewHolder(parent, 0);
        verify(layoutInflater).inflate(R.layout.todo_list_item, parent, false);

        assertThat(viewHolder.itemView).isSameAs(itemView);
    }

    @Test
    public void onBindViewHolder_shouldBindItemsToTheViewHolders() {
        List<TodoItem> items = asList(TodoItem.builder().id(1).note("Note 1").color(Color.BLUE)
                .date(new Date()).reminder(false).build(), TodoItem.builder().id(2).note("Note "
                + "2").color(Color.BLUE).date(new Date()).reminder(false).build());

        todoItemsAdapter.setData(items);

        for (int i = 0, size = items.size(); i < size; i++) {
            TodoItemsViewHolder viewHolder = mock(TodoItemsViewHolder.class);
            todoItemsAdapter.onBindViewHolder(viewHolder, i);

            verify(viewHolder).bind(items.get(i));
        }
    }
}
