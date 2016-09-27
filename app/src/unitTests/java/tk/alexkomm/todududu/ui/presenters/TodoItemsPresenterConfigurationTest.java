package tk.alexkomm.todududu.ui.presenters;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class TodoItemsPresenterConfigurationTest {

    @Test
    public void hashCode_equals_shouldWorkCorrectly() {
        EqualsVerifier.forClass(AutoValue_TodoItemsPresenterConfiguration.class).suppress(Warning
                .NULL_FIELDS).verify();
    }
}
