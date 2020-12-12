package main;

import server.Server;
import users.Editor;

public class Editor1Thread implements Runnable {

	private Server server;

	public Editor1Thread(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		Editor editor = new Editor(server, "Vasile Editor");
		editor.publishNews("Science", "Monkey-pig hybrid",
				"Created by Chinese scientists, this just proves that humans are weird.");
		editor.publishNews("Crime", "How to spot a psychopath",
				"A series of tests to discover if someone is a psychopath. Maybe the Chinese scientists.");
	}
}