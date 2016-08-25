package tk.alexkomm.todududu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import tk.alexkomm.todududu.R;
import tk.alexkomm.todududu.api.entities.TodoItem;

public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemsViewHolder> {

    @NonNull
    private final LayoutInflater layoutInflater;
    private List<TodoItem> items = Collections.emptyList();

    public TodoItemsAdapter(@NonNull LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public void setData(List<TodoItem> items) {
        this.items = Collections.unmodifiableList(items);
        notifyDataSetChanged();
    }

    @Override
    public TodoItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoItemsViewHolder(layoutInflater.inflate(R.layout.todo_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TodoItemsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
