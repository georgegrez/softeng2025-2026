package weerasmus.model;

import java.time.LocalDateTime;

public class Message {
    private int messageId;
    private int senderId;
    private int receiverId;
    private String content;
    private LocalDateTime sentAt;
    private boolean read;

    public Message() {
        this.sentAt = LocalDateTime.now();
        this.read = false;
    }

    public Message(int messageId, int senderId, int receiverId, String content) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.sentAt = LocalDateTime.now();
        this.read = false;
    }

    public static Message create(int messageId, int senderId, int receiverId, String content) {
        return new Message(messageId, senderId, receiverId, content);
    }

    public void send() {
        this.sentAt = LocalDateTime.now();
        this.read = false;
    }

    public void markAsRead() {
        this.read = true;
    }

    public boolean isBetweenUsers(int userA, int userB) {
        return (senderId == userA && receiverId == userB)
                || (senderId == userB && receiverId == userA);
    }

    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}
