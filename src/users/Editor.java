package users;

import Server.Server;
import Events.NewsEvent;
import News.NewsEventListener;
import News.News;

public class Editor implements NewsEventListener
{
    private String name;
    private Server server;

    public Editor(Server server, String name)
    {
        this.name=name;
        this.server=server;
    }
    public void subscribeToNews(String type)
    {
        server.subscribeToNewsByType(this,type);
    }
    @Override
    public void handleEvent(NewsEvent event)
    {
        News content = event.getContent();
        String eventDescription = event.getEventDescription();

        System.out.println("["+name+"]: "+eventDescription+"\n"+content);
    }
}