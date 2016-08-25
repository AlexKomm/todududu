package tk.alexkomm.todududu.ui.presenters;

import org.junit.Before;
import org.junit.Test;

import rx.Subscription;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class PresenterTest {

    private Object mView;
    private Presenter<Object> mPresenter;

    @Before
    public void beforeEachTest() {
        mView = new Object();
        mPresenter = new Presenter<>();
    }

    @Test
    public void getView_shouldReturnNullByDefault() {
        assertThat(mPresenter.getView()).isNull();
    }

    @Test
    public void bindView_shouldAttachViewToThePresenter() {
        mPresenter.bindView(mView);

        assertThat(mPresenter.getView()).isSameAs(mView);
    }

    @Test
    public void bindView_shouldThrowIfPreviousViewIsNotUnbinded() {
        mPresenter.bindView(mView);

        boolean thrown = false;

        try {
            mPresenter.bindView(mView);
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException expected) {
            thrown = true;
        }

        assertThat(thrown).isTrue();
    }

    @Test
    public void unsubscribeOnUnbindView_shouldWorkAccordingToItsContract() {
        mPresenter.bindView(mView);

        Subscription subscription1 = mock(Subscription.class);
        Subscription subscription2 = mock(Subscription.class);
        Subscription subscription3 = mock(Subscription.class);

        mPresenter.unsubscribeOnUnbindView(subscription1, subscription2, subscription3);
        verify(subscription1, never()).unsubscribe();
        verify(subscription2, never()).unsubscribe();
        verify(subscription3, never()).unsubscribe();

        mPresenter.unbindView(mView);
        verify(subscription1).unsubscribe();
        verify(subscription2).unsubscribe();
        verify(subscription3).unsubscribe();
    }

    @Test
    public void unbindView_shouldDetachViewFromThePresenter() {
        mPresenter.bindView(mView);
        assertThat(mPresenter.getView()).isSameAs(mView);

        mPresenter.unbindView(mView);
        assertThat(mPresenter.getView()).isNull();
    }

    @Test
    public void unbindView_shouldThrowIfUnexpectedViewIsPassed() {
        mPresenter.bindView(mView);
        assertThat(mPresenter.getView()).isSameAs(mView);

        Object unexpected = new Object();

        boolean thrown = false;

        try {
            mPresenter.unbindView(unexpected);
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException expected) {
            thrown = true;
        }

        assertThat(thrown).isTrue();
    }
}
