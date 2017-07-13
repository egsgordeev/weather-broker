package ru.bell.gordeev.broker.service;

import java.io.IOException;

/**
 * Created by EGordeev on 13.07.2017.
 */
public interface CustomParser<T> {
    T parseToInstance(String values) throws IOException;
    String parseAnInstance(T object);
}
