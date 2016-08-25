package tk.alexkomm.todududu.utils.rxtask;

import rx.Observable;

public interface Task {
    void start();

    Observable<Void> loading();

    Observable<Result.Success> success();

    Observable<Result.Error> error();

    class Factory {
        private Factory() {

        }

        public static Task fromObservable(Observable<?> observable) {
            return new TaskImpl(observable);
        }
    }
}
