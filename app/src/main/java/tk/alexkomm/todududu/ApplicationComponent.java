package tk.alexkomm.todududu;

import javax.inject.Singleton;

import dagger.Component;
import tk.alexkomm.todududu.data.DataModule;
import tk.alexkomm.todududu.models.ModelsModule;
import tk.alexkomm.todududu.ui.activities.MainActivity;
import tk.alexkomm.todududu.ui.fragments.TodoItemsFragment;

@SuppressWarnings("WeakerAccess")
@Component(modules = {ApplicationModule.class, ModelsModule.class, DataModule.class})
@Singleton
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    TodoItemsFragment.TodoItemsFragmentComponent plus(TodoItemsFragment
            .TodoItemsFragmentModule todoItemsModule);
}
