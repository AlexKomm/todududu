package tk.alexkomm.todududu;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import tk.alexkomm.todududu.models.ModelsModule;
import tk.alexkomm.todududu.ui.activities.MainActivity;
import tk.alexkomm.todududu.ui.fragments.TodoItemsFragment;

@Component(modules = {ApplicationModule.class, ModelsModule.class})
@Singleton
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    TodoItemsFragment.TodoItemsFragmentComponent plus(
            @NonNull TodoItemsFragment.TodoItemsFragmentModule todoItemsModule);
}
