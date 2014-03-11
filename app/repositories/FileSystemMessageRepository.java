package repositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Message;
import models.User;

public class FileSystemMessageRepository implements MessageRepository {
	@Override
	public List<Message> getMessagesSince(Date pointInTime) throws Exception {
		List<Message> messages = new ArrayList<Message>();  

		String path = "public/messages/"; 
		
		File[] listOfFiles = new File(path).listFiles(); 

		for (int i = 0; i < listOfFiles.length; i++) 
		{
			if (listOfFiles[i].isFile()) 
			{
				Message message = parseMessage(listOfFiles[i].getAbsolutePath());
				messages.add(message);
			}
		}
		
		return messages;
	}

	private Message parseMessage(String path) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			StringBuilder sb = new StringBuilder();
			String ip = br.readLine();
			String name = br.readLine();
			String message = br.readLine();
			
			return new Message(message, new User(ip, name));
		} finally {
			br.close();
		}
	}




}