package tk.alexkomm.todududu.api.entities;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class TodoItemTest {

    @Test
    public void hashCode_equals_shouldWorkCorrectly() {
        EqualsVerifier.forClass(AutoValue_TodoItem.class).suppress(Warning.NULL_FIELDS).verify();
    }
}
