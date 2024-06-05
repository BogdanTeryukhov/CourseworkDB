package com.example.courseworkdatabases.util;


import java.util.List;

public class CrudMessagesUtil {

    public static String getCreateString(Object object){
        return object.getClass().getSimpleName() + " has been saved";
    }

    public static String getCreateString(List<Object> objects){
        return objects.get(0).getClass().getSimpleName() + "s has been saved";
    }

    public static String getUpdateString(Object object){
        return object.getClass().getSimpleName() + " has been updated";
    }

    public static String getDeleteString(Object object){
        return object.getClass().getSimpleName() + " has been deleted";
    }

    public static String getCreateErrorString(Object object){
        return object.getClass().getSimpleName() + " is already exists! You can`t make another " + object.getClass().getName().toLowerCase() +" with same name";
    }

    public static String getDeleteErrorString(Object object){
        return object.getClass().getSimpleName() + " is not exists!";
    }

}
