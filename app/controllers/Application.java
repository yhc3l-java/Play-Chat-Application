package controllers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Message;
import models.User;
import play.*;
import play.mvc.*;
import repositories.FileSystemMessageRepository;
import repositories.MessageRepository;
import views.html.*;

public class Application extends Controller {
	public static Result index() {
		return ok(index.render());
	}

	public static Result getMessages() throws Exception{
		MessageRepository repo = new FileSystemMessageRepository();
		return ok(messages.render(repo.getMessagesSince(null)));
	}

	public static Result sendMessage() throws Exception{
		String message = request().body().asFormUrlEncoded().get("message")[0];
		
		SecureRandom random = new SecureRandom();
		
		PrintWriter out = new PrintWriter("public/messages/" + new BigInteger(130, random).toString(32) + ".txt");
		out.println(request().remoteAddress());
		out.println();
		out.println(message);
		out.close();

		return ok();
	}
}
