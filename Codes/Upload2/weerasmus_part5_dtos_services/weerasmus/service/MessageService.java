package weerasmus.service;

import weerasmus.dto.MessageDTO;
import weerasmus.model.Message;
import weerasmus.repository.MessageRepository;

public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDTO createMessage(int senderId, int receiverId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.send();

        Message saved = messageRepository.save(message);
        return new MessageDTO(saved.getMessageId(), saved.getContent(), true);
    }
}
