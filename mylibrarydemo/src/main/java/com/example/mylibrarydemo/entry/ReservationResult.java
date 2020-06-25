package com.example.mylibrarydemo.entry;

public class ReservationResult<T> {
    boolean success;
    T data;
    String error;

    public ReservationResult() {

    }
    public ReservationResult(boolean success,T reservation) {
        this.success=success;
        this.data=reservation;
    }

    public ReservationResult(boolean fail, String error) {
        this.success=fail;
        this.error=error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
