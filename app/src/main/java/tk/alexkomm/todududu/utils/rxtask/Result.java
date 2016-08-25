package tk.alexkomm.todududu.utils.rxtask;

public interface Result {
    class Success implements Result {
        private final Object data;

        public Success(Object data) {
            this.data = data;
        }

        @SuppressWarnings("unchecked")
        public <T> T getData() {
            return (T) data;
        }
    }

    class Error implements Result {
        private final Throwable throwable;

        public Error(Throwable throwable) {
            this.throwable = throwable;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }
}
