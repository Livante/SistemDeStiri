package events;

import news.News;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ListenerData {
    
	private NewsEventListener listener;
    private List<Predicate<News>> filters;

    public boolean equals(Object object){
        if(object instanceof ListenerData){
            NewsEventListener eventListener = (NewsEventListener) object;
            return eventListener == listener;
        }
        return false;
    }

    public NewsEventListener getListener(){
        return listener;
    }

    public Stream<Predicate<News>> getFilters(){
        return filters.stream();
    }
    
    public ListenerData(NewsEventListener listen){
        listener = listen;
        filters = new ArrayList<Predicate<News>>();
    }

    public void addFilter(Predicate<News> filter){
        filters.add(filter);
    }



}
