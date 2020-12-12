package server;

import events.*;
import news.News;

import java.util.LinkedList;

public class Server {
    private static Server serverInstance;
    private LinkedList<News> newsLinkedList;
    private Thread serverThread;
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    private final Object mutex = new Object();

    private Server(){
        newsLinkedList = new LinkedList<>();
        serverThread = new Thread(this::serverLoop);
        serverThread.start();
    }

    private void serverLoop(){
        while(!Thread.currentThread().isInterrupted()){
            try {

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static Server getServerInstance(){
         if(serverInstance == null) {
             serverInstance = new Server();
         }
         return serverInstance;
    }

    public void publishNews(News news, NewsEventListener newsEventListener){
        synchronized (mutex){
            newsLinkedList.addFirst(news);
        }

        ListenerData listenerData = new ListenerData(newsEventListener);
        listenerData.addFilter(item -> news.equals(item));



        eventDispatcher.register(EventFlag.STIRI_CITITE, listenerData);

        NewsEvent newsEvent = new NewsEvent(EventFlag.STIRI_APARUTE, news);
        eventDispatcher.publishEvent(newsEvent);
    }

    public void updateNews(News news){
        synchronized (mutex){
            newsLinkedList.stream().filter(news::equals).map(item ->news);

            NewsEvent newsEvent = new NewsEvent(EventFlag.STIRI_SCHIMBATE, news);
            eventDispatcher.publishEvent(newsEvent);
        }
    }

    public void deleteNews(News news){
        synchronized (mutex) {
            //newsLinkedList.removeIf(news::equals);
            for(News news1 : newsLinkedList){
                if(news.equals(news1)) {
                    newsLinkedList.remove(news1);
                }
            }
        }
        NewsEvent newsEvent = new NewsEvent(EventFlag.STIRI_STERSE, news);
        eventDispatcher.publishEvent(newsEvent);
    }

    public void subscribeToNewsByType(NewsEventListener newsEventListener, String newsType){
        ListenerData listenerData = new ListenerData(newsEventListener);
        listenerData.addFilter(event -> event.getCategory().equals(newsType));

        eventDispatcher.register(EventFlag.STIRI_SCHIMBATE, listenerData);
        eventDispatcher.register(EventFlag.STIRI_APARUTE, listenerData);
        eventDispatcher.register(EventFlag.STIRI_STERSE, listenerData);
    }
}
