package main;

import server.Server;
import users.Editor;

public class Catalina_Editor implements Runnable {

	private Server server;

	public Catalina_Editor(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		Editor editor = new Editor(server, "Catalina Editor");
		editor.publicareStiri("Pandemie", "AUR",
				"Partidul aur a facut proteste anti-mask in romania.");
		editor.publicareStiri("Politica", "Birocratie",
				"Nu se stia cum sa se faca cu statul la coada si acum si la platile online se simuleaza statul la coada.");
		editor.publicareStiri("Politica", "Neg***",
				"De astazi culoarea neag** nu se mai foloseste, IDE-urile sa o fie neg** deschise si tricourile se revopsesc.");
		editor.stergereStiri("AUR");
		editor.stergereStiri("Neg***");
	}
}