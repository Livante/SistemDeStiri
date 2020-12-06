package events;

import java.util.*;
import java.util.stream.Stream;

public class EventListenerManager {

    private final Object mutex = new Object();
    private Map<EventFlag, List<ListenerData>> listenerMap = new HashMap<>();

    public void register(EventFlag eventType, ListenerData listenerData){
        synchronized(mutex){
            listenerMap.computeIfAbsent(eventType, elem -> new ArrayList<>()).add(listenerData);
        }

    }

    public void unregister(EventFlag eventType, ListenerData listenerData){
        synchronized (mutex){
            if(mapContainsListener(eventType, listenerData)){
                listenerMap.computeIfPresent(eventType, (type,list) -> {
                    list.remove(listenerData);
                    return list.isEmpty()?null:list;
                });
            }
        }
    }

    public Stream<ListenerData> getListenersForEvent(EventFlag type){
        return listenerMap.getOrDefault(type, Collections.emptyList()).stream();
    }

    private boolean mapContainsListener(EventFlag type, ListenerData listener){
        return listenerMap.getOrDefault(type, Collections.emptyList()).stream().anyMatch(listener::equals);
    }

}
