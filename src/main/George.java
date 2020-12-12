package main;

import server.Server;
import users.Reader;

public class George implements Runnable {

    private Server server;

    public George(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Reader reader = new Reader(server, "George");
        reader.subscribeToNews("Politica");
        reader.subscribeToNews("Pandemie");
    }
}
