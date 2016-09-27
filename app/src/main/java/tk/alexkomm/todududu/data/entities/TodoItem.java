package tk.alexkomm.todududu.data.entities;

import com.google.auto.value.AutoValue;

import java.util.Date;

@AutoValue
public abstract class TodoItem {
    public static Builder builder() {
        return new AutoValue_TodoItem.Builder();
    }

    public abstract int id();

    public abstract Date date();

    public abstract int color();

    public abstract String note();

    public abstract boolean reminder();

    @SuppressWarnings("WeakerAccess")
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder date(Date date);

        public abstract Builder color(int color);

        public abstract Builder note(String note);

        public abstract Builder reminder(boolean reminder);

        public abstract TodoItem build();
    }
}
