package tk.alexkomm.todududu.utils.rxtask;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

class TaskImpl implements Task {

    private final PublishSubject<Void> start;
    private final Observable<Void> loading;
    private final Observable<Result.Success> success;
    private final Observable<Result.Error> error;

    TaskImpl(Observable<?> source) {
        start = PublishSubject.create();

        loading = start.switchMap(aVoid -> Observable.just(null));

        final Observable<Result> load = mapToErrorIfEmpty(source)
                .map(new ResultSuccessMapper())
                .onErrorReturn(new ResultErrorMapper());

        final Observable<Result> result = load.switchMap(aVoid -> load).publish().refCount();

        success = result.filter(result1 -> result1 instanceof Result.Success).cast(Result.Success
                .class);

        error = result.filter(result1 -> result1 instanceof Result.Error).cast(Result.Error.class);
    }

    @NonNull
    private <T> Observable<T> mapToErrorIfEmpty(Observable<T> source) {
        return source
                .filter(data -> data != null && !(data instanceof List && ((List) data).size() == 0))
                .switchIfEmpty(Observable.error(new NoSuchElementException()));
    }

    @Override
    public void start() {
        start.onNext(null);
    }

    @Override
    public Observable<Void> loading() {
        return loading;
    }

    @Override
    public Observable<Result.Success> success() {
        return success;
    }

    @Override
    public Observable<Result.Error> error() {
        return error;
    }

    private static class ResultSuccessMapper implements Func1<Object, Result> {
        @Override
        public Result call(Object o) {
            return new Result.Success(o);
        }
    }

    private static class ResultErrorMapper implements Func1<Throwable, Result> {
        @Override
        public Result call(Throwable throwable) {
            return new Result.Error(throwable);
        }
    }
}
