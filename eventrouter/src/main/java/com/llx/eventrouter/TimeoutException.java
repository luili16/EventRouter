package com.llx.eventrouter;

public class TimeoutException extends Exception {

    TimeoutException() {
    }

    TimeoutException(String message) {
        super(message);
    }

    TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    TimeoutException(Throwable cause) {
        super(cause);
    }
}
