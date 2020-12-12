package main;

import server.Server;
import users.Editor;

public class Vasile_Editor implements Runnable {

	private Server server;

	public Vasile_Editor(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		Editor editor = new Editor(server, "Vasile Editor");
		editor.publishNews("Politica", "Kim Jung-un",
				"Kim Jung un a arestat toti cetatenii care nu se tund ca si el");
		editor.publishNews("Pandemie", "Vaccine",
				"Avem deja multe vaccine care sunt bune, dar nu le folosim sa nu ne prajeasca creierii americanii si rusii cu chip-ul din el");
		editor.publishNews("Bucatarie", "Supa crema",
				"Americanii au facut un nou tip de supa crema, dar in loc de legume au folosit mentos si bacon.");
		editor.publishNews("Politica", "Robu",
				"Robu nu suporta ca nu mai e primar, daca nu posteaza frustrarile lui si nu cauta greseli la noul primar, atunci intra in sevraj");
	}
}