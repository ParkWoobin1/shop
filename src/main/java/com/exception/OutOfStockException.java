package com.exception;

public class OutOfStockException extends RuntimeException{

    /**
     *
     * @param message
     */
    public OutOfStockException(String message) {
        super(message);
    }

}
