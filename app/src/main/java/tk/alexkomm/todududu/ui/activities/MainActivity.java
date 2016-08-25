package tk.alexkomm.todududu.ui.activities;

import android.os.Bundle;

import tk.alexkomm.todududu.R;
import tk.alexkomm.todududu.TodududuApplication;
import tk.alexkomm.todududu.ui.fragments.TodoItemsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TodududuApplication.get(this).getApplicationComponent().inject(this);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, new TodoItemsFragment())
                    .commit();
        }
    }
}
