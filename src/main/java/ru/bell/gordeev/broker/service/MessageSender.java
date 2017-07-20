package ru.bell.gordeev.broker.service;

/**
 * Created by EGordeev on 14.07.2017.
 */
public interface MessageSender<T> {
    void sendMessage(T message);
}
