package com.upadvisor.main.util;

import com.google.gson.JsonObject;

/**
 * Created with IntelliJ IDEA.
 * User: Jaden
 * Date: 6/18/14
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface JsonHandler<T>
{
    public JsonObject toJson(T obj);
    public T fromJson(JsonObject json);
}