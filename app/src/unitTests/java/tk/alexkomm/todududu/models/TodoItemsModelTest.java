package tk.alexkomm.todududu.models;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoItemsModelTest {

    private TodoItemsModel todoItemsModel;

    @Before
    public void beforeEachTest() {
        todoItemsModel = new TodoItemsModel();
    }

    @Test
    public void getAll_shouldReturnItemsFromApi() {
        assertThat(todoItemsModel.getAll().toBlocking().single()).isNotEmpty();
    }

    @Test
    public void getAll_shouldReturnErrorsFromApi() {
        assertThat(todoItemsModel.getAll().toBlocking().single()).isNotEmpty();
    }
}
