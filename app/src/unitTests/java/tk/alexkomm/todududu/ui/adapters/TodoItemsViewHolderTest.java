package tk.alexkomm.todududu.ui.adapters;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import tk.alexkomm.todududu.R;
import tk.alexkomm.todududu.TodududuRobolectricUnitTestRunner;
import tk.alexkomm.todududu.data.entities.TodoItem;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(TodududuRobolectricUnitTestRunner.class)
public class TodoItemsViewHolderTest {

    private TodoItemsViewHolder viewHolder;
    private TodoItem item;

    private TextView noteTextview;

    @Before
    public void beforeEachTest() {
        View itemView = mock(View.class);

        noteTextview = mock(TextView.class);

        when(itemView.findViewById(R.id.todo_list_item_note_text_view)).thenReturn(noteTextview);

        viewHolder = new TodoItemsViewHolder(itemView);

        item = TodoItem.builder().id(1).note("Note 1").color(Color.BLUE).date(new Date())
                .reminder(false).build();
    }

    @Test
    public void bind_shouldSetNote() {
        viewHolder.bind(item);
        verify(noteTextview).setText(item.note());
    }
}
