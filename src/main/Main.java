package main;

import server.Server;

public class Main {
    public static void main(String[] args) {
            Server server = Server.getServerInstance();

            George thread1 = new George(server);
            Ancuta thread2 = new Ancuta(server);
            Schnitzel thread3 = new Schnitzel(server);
            Vasile_Editor thread4 = new Vasile_Editor(server);
            Catalina_Editor thread5 = new Catalina_Editor(server);


            new Thread(thread1).start();
            new Thread(thread2).start();
            new Thread(thread3).start();
            new Thread(thread4).start();
            new Thread(thread5).start();


    }
}
