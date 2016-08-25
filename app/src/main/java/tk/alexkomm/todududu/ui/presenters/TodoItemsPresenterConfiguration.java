package tk.alexkomm.todududu.ui.presenters;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

import rx.Scheduler;

@AutoValue
public abstract class TodoItemsPresenterConfiguration {

    public static Builder builder() {
        return new AutoValue_TodoItemsPresenterConfiguration.Builder();
    }

    @NonNull
    public abstract Scheduler ioScheduler();

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder ioScheduler(@NonNull Scheduler ioScheduler);

        @NonNull
        public abstract TodoItemsPresenterConfiguration build();
    }
}
