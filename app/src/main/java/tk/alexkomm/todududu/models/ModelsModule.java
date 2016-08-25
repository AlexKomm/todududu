package tk.alexkomm.todududu.models;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelsModule {

    @Provides
    @NonNull
    public TodoItemsModel providesTodoItemsModel() {
        return new TodoItemsModel();
    }
}
