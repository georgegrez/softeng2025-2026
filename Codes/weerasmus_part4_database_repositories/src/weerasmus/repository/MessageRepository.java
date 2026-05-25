package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Message;

public class MessageRepository {

    public ArrayList<Message> findAll() {
        return new ArrayList<>(InMemoryDatabase.messages);
    }

    public Message findById(int messageId) {
        for (Message message : InMemoryDatabase.messages) {
            if (message.getMessageId() == messageId) {
                return message;
            }
        }
        return null;
    }

    public ArrayList<Message> findByUser(int userId) {
        ArrayList<Message> results = new ArrayList<>();

        for (Message message : InMemoryDatabase.messages) {
            if (message.getSenderId() == userId || message.getReceiverId() == userId) {
                results.add(message);
            }
        }

        return results;
    }

    public Message save(Message message) {
        if (message == null) {
            return null;
        }

        if (message.getMessageId() == 0) {
            message.setMessageId(InMemoryDatabase.generateMessageId());
            InMemoryDatabase.messages.add(message);
            return message;
        }

        Message existing = findById(message.getMessageId());
        if (existing == null) {
            InMemoryDatabase.messages.add(message);
        }

        return message;
    }
}
