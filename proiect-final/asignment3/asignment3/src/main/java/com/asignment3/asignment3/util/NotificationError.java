package com.asignment3.asignment3.util;

import com.asignment3.asignment3.model.enumeration.TypeError;

import java.util.ArrayList;
import java.util.List;

public class NotificationError {
    private static NotificationError singleton;
    private final List<TypeError> errors;

    private NotificationError(List<TypeError> errors) {
       this.errors = errors;
    }

    public static NotificationError getInstance(){
        if(singleton==null)
            return singleton = new NotificationError(new ArrayList<>());
        else
            return singleton;
    }

    public  List<TypeError> getErrors() {
        return errors;
    }

}
