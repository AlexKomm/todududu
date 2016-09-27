package tk.alexkomm.todududu.ui.presenters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base presenter
 *
 * @param <V> ViewInterface
 */
public class Presenter<V> {

    @NonNull
    private final CompositeSubscription subscriptionsToUnsubscribeOnUnbindView = new
            CompositeSubscription();

    @Nullable
    private volatile V view;

    @CallSuper
    public void bindView(@NonNull V view) {
        if (this.view != null) {
            throw new IllegalStateException("Previous view is not unbounded! previous view = " +
                    this.view);
        }

        this.view = view;
    }

    final void unsubscribeOnUnbindView(@NonNull Subscription subscription, @NonNull
            Subscription... subscriptions) {
        subscriptionsToUnsubscribeOnUnbindView.add(subscription);

        for (Subscription s : subscriptions) {
            subscriptionsToUnsubscribeOnUnbindView.add(s);
        }
    }

    @CallSuper
    void unbindView(@NonNull V view) {
        final V previousView = this.view;

        if (previousView != null && previousView.equals(view)) {
            this.view = null;
        } else {
            throw new IllegalStateException("Unexpected view! previous view = " + this.view + ", " +
                    "view to unbind = " + view);
        }

        subscriptionsToUnsubscribeOnUnbindView.clear();
    }

    @Nullable
    protected V getView() {
        return view;
    }
}
