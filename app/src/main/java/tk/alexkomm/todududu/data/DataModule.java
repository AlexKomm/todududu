package tk.alexkomm.todududu.data;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tk.alexkomm.todududu.data.local.DbOpenHelper;

@Module
public class DataModule {

    @Provides
    @Singleton
    DbOpenHelper provideDbOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}
