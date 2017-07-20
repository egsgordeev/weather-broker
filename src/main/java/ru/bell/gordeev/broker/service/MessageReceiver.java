package ru.bell.gordeev.broker.service;

import java.io.IOException;

/**
 * Created by Sovereign on 14.07.2017.
 */
public interface MessageReceiver<T> {
    void receiveMessage(T message) throws IOException;
}
