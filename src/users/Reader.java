package users;

import server.Server;
import events.NewsEvent;
import events.NewsEventListener;
import news.News;

public class Reader implements NewsEventListener {
    private final String name;
    private final Server server;

    public Reader(Server server, String name) {
        this.name = name;
        this.server = server;
    }

    public void subscribeToNews(String type) {
        server.subscribeToNewsByType(this,type);
    }

    @Override
    public void handleEvent(NewsEvent event) {
        News content = event.getContent();
        String eventDescription = event.getEventDescription();

        System.out.println("[" + name + "]: " + eventDescription + "\n" + content);
    }
}