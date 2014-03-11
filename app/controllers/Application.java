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
import views.html.*;

public class Application extends Controller {
	public static Result index() {
		return ok(index.render());
	}

	public static Result getMessages() throws Exception{
		FileSystemMessageRepository repo = new FileSystemMessageRepository();
		StringBuilder out = new StringBuilder();
		
		boolean first = true;
		
		out.append("[");
		for(String message: repo.getMessageIds()){
			if(first != true){
				out.append(",");
			}
			out.append("\"" + message + "\"");
			first = false;
		}
		out.append("]");
		
		return ok(out.toString());
	}

	public static Result sendMessage() throws Exception{
		String message = request().body().asFormUrlEncoded().get("message")[0];
		
		PrintWriter out = new PrintWriter("public/messages/" + System.currentTimeMillis() + ".txt");
		out.println(request().remoteAddress());
		out.println();
		out.println(message);
		out.close();

		return ok();
	}
}
