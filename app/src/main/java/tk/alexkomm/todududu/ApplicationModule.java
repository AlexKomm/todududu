package tk.alexkomm.todududu;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {

    private final Application mApplication;

    ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
