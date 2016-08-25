package tk.alexkomm.todududu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tk.alexkomm.todududu.R;
import tk.alexkomm.todududu.TodududuApplication;
import tk.alexkomm.todududu.models.TodoItemsModel;
import tk.alexkomm.todududu.ui.adapters.TodoItemsAdapter;
import tk.alexkomm.todududu.ui.presenters.TodoItemsPresenter;
import tk.alexkomm.todududu.ui.presenters.TodoItemsPresenterConfiguration;
import tk.alexkomm.todududu.ui.views.TodoItemsView;
import tk.alexkomm.todududu.utils.RxUi;
import tk.alexkomm.todududu.utils.rxtask.Result;

public class TodoItemsFragment extends BaseFragment implements TodoItemsView {
    @BindView(R.id.todo_items_recyclerview)
    RecyclerView todoItemsRecyclerView;
    @Inject
    TodoItemsPresenter todoItemsPresenter;

    private Unbinder unbinder;
    private TodoItemsAdapter todoItemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TodududuApplication.get(getContext())
                .getApplicationComponent()
                .plus(new TodoItemsFragmentModule())
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_items, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        todoItemsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        todoItemsAdapter = new TodoItemsAdapter(getActivity().getLayoutInflater());
        todoItemsRecyclerView.setAdapter(todoItemsAdapter);

        todoItemsPresenter.bindView(this);
        todoItemsPresenter.reloadData();
    }

    @Override
    public void onDestroyView() {

        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroyView();
    }

    @Override
    public Func1<Observable<Result.Error>, Subscription> showErrorUi() {
        return RxUi.ui(error -> {
            String message = error.getThrowable().getMessage();

            Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public Func1<Observable<Result.Success>, Subscription> showContentUi() {
        return RxUi.ui(result -> {
            if (todoItemsAdapter != null) {
                todoItemsAdapter.setData(result.getData());
            }
        });
    }

    @Override
    public Func1<Observable<Void>, Subscription> showLoadingUi() {
        return RxUi.ui(
                aVoid -> Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.loading,
                        Snackbar.LENGTH_LONG).show());
    }

    @Subcomponent(modules = TodoItemsFragmentModule.class)
    public interface TodoItemsFragmentComponent {
        void inject(@NonNull TodoItemsFragment todoItemsFragment);
    }

    @Module
    public static class TodoItemsFragmentModule {

        @Provides
        @NonNull
        public TodoItemsPresenter providesTodoItemsPresenter(@NonNull TodoItemsModel itemsModel) {
            return new TodoItemsPresenter(itemsModel,
                    TodoItemsPresenterConfiguration.builder().ioScheduler(Schedulers.io()).build());
        }
    }
}
