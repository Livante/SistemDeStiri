package main;

import server.Server;
import users.Reader;

public class Ancuta implements Runnable {

    private Server server;

    public Ancuta(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Reader reader = new Reader(server, "Ancuta");
        reader.subscribeToNews("Politica");
    }
}
