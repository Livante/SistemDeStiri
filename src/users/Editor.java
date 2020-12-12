package users;

import server.Server;
import events.NewsEvent;
import events.NewsEventListener;
import news.News;

public class Editor implements NewsEventListener {
    private String name;
    private Server server;

    public Editor(Server server, String name) {
        this.name = name;
        this.server = server;
    }

    public void publishNews(String category, String type, String content) {
        News news = new News(category, this.name, type, content);
        server.publishNews(news, this);
    }

    @Override
    public void handleEvent(NewsEvent event) {
        News content = event.getContent();
        String eventDescription = event.getEventDescription();

        System.out.println("[" + name + "]: " + eventDescription + "\n" + content);
    }
}