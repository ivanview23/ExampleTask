package org.example.part3;

import java.io.Serial;

public class WriteOrReadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2323L;

    public WriteOrReadException(String message) {
        super(message);
    }

    public WriteOrReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteOrReadException(Throwable cause) {
        super(cause);
    }
}