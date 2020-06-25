package com.example.mylibrarydemo.entry;

public class ProcessResult<T> {
    boolean status;
    int total;
    T data;
    String message;

    public ProcessResult() {
    }

    public ProcessResult(boolean status, int total, T data, String message) {
        this.status = status;
        this.total = total;
        this.data = data;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
