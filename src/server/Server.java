package server;

import events.*;
import news.News;

import java.util.LinkedList;

public class Server {
    private static Server serverInstance;
    private LinkedList<News> newsLinkedList;
    public LinkedList<News> getNewsLinkedList() {
		return newsLinkedList;
	}

	public void setNewsLinkedList(LinkedList<News> newsLinkedList) {
		this.newsLinkedList = newsLinkedList;
	}

	private Thread serverThread;
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    private final Object mutex = new Object();

    private Server() {
        newsLinkedList = new LinkedList<>();
        serverThread = new Thread(this::serverLoop);
        serverThread.start();
    }

    private void serverLoop() {
        while(!Thread.currentThread().isInterrupted()){
            try {

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static Server getServerInstance() {
         if(serverInstance == null) {
             serverInstance = new Server();
         }
         return serverInstance;
    }

    public void publishNews(News news, NewsEventListener newsEventListener) {
        synchronized (mutex) {
            newsLinkedList.addFirst(news);
        }

        ListenerData listenerData = new ListenerData(newsEventListener);
        listenerData.addFilter(item -> news.equals(item));
        eventDispatcher.register(EventFlag.STIRI_CITITE, listenerData);

        NewsEvent newsEvent = new NewsEvent(EventFlag.STIRI_APARUTE, news);
        eventDispatcher.publicareStire(newsEvent);
    }

    public void deleteNews(News news){
        synchronized(mutex){
            newsLinkedList.removeIf(news::equals);
        }
        NewsEvent event = new NewsEvent(EventFlag.STIRI_STERSE, news);
        eventDispatcher.publicareStire(event);
    }

    public void abonareDupaTip(NewsEventListener newsEventListener, String newsType) {
        ListenerData listenerData = new ListenerData(newsEventListener);
        listenerData.addFilter(event -> event.getCategory().equals(newsType));

        eventDispatcher.register(EventFlag.STIRI_APARUTE, listenerData);
        eventDispatcher.register(EventFlag.STIRI_STERSE, listenerData);
    }
}
