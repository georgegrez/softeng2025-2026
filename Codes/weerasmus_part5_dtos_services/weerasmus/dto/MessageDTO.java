package weerasmus.dto;

public class MessageDTO {
    private int messageId;
    private String content;
    private boolean sent;

    public MessageDTO(int messageId, String content, boolean sent) {
        this.messageId = messageId;
        this.content = content;
        this.sent = sent;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public boolean isSent() {
        return sent;
    }
}
