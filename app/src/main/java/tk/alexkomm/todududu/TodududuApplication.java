package tk.alexkomm.todududu;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

import timber.log.Timber;

public class TodududuApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @NonNull
    public static TodududuApplication get(Context context) {
        return (TodududuApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = prepareApplicationComponent().build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            AndroidDevMetrics.initWith(this);
        }
    }

    @SuppressWarnings("deprecation")
    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this));
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
