package com.main.payload;

import java.util.Date;

public class ErrorDto {
    private String msg;
    private Date date;
    private String description;

    public ErrorDto(String msg, Date date, String description) {
        this.msg = msg;
        this.date = date;
        this.description = description;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
