package users;

import server.Server;
import events.NewsEvent;
import events.NewsEventListener;
import news.News;

public class Editor implements NewsEventListener {
    private String name;
    private Server server;

    public Editor(Server server, String nume) {
        this.server = server;
        name = nume;
    }

    public void publicareStiri(String category,String titlu, String content) {
        News news = new News(category, name, titlu, content);
        server.publishNews(news, this);
    }

    public void stergereStiri(String titlu) {
      
        for (News stireDeSters: server.getNewsLinkedList()) {
			if(stireDeSters.getTitle().equals(titlu)) {
				server.deleteNews(stireDeSters);
				return;
			}
		}
    }
    
    @Override
    public void handleEvent(NewsEvent event) {
        News content = event.getContent();
        String eventDescription = event.getEventDescription();

        System.out.println(name + ": " + eventDescription + "\n\t" + content);
    }
}