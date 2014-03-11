package repositories;

import java.util.Date;
import java.util.List;

import models.Message;

public interface MessageRepository {
	public List<Message> getMessagesSince(Date pointInTime) throws Exception;
}
