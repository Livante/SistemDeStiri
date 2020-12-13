package main;

import server.Server;
import users.Reader;

public class Schnitzel implements Runnable {

    private Server server;

    public Schnitzel(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Reader reader = new Reader(server, "Schnitzel");
        reader.abonare("Politica");
        reader.abonare("Bucatarie");
    }
}
