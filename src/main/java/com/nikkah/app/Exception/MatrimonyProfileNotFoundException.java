package com.nikkah.app.Exception;
public class MatrimonyProfileNotFoundException extends RuntimeException {

    public MatrimonyProfileNotFoundException() {
        super();
    }

    public MatrimonyProfileNotFoundException(String message) {
        super(message);
    }

    public MatrimonyProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
