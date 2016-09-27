package tk.alexkomm.todududu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tk.alexkomm.everytime.casters.Views;
import tk.alexkomm.todududu.R;
import tk.alexkomm.todududu.data.entities.TodoItem;

class TodoItemsViewHolder extends RecyclerView.ViewHolder {
    private final TextView noteTextView;

    TodoItemsViewHolder(View itemView) {
        super(itemView);

        noteTextView = Views.findById(itemView, R.id.todo_list_item_note_text_view);
    }

    void bind(@NonNull TodoItem item) {
        noteTextView.setText(item.note());
    }
}
