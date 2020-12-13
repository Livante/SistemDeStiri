package events;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Predicate;
import java.util.stream.Stream;

import news.News;

public class EventDispatcher {
	private final EventManager eventListener = new EventManager();
	private final BlockingQueue<NewsEvent> eventsQueue = new LinkedBlockingQueue<NewsEvent>();
	private final Thread dispatchThread;

	public EventDispatcher() {
		dispatchThread = new Thread(this::dispatchLoop);
		dispatchThread.start();
	}

	public void register(EventFlag eventType, ListenerData listenerData) {
		eventListener.register(eventType, listenerData);
	}

	public void unregister(EventFlag eventType, ListenerData listenerData) {
		eventListener.unregister(eventType, listenerData);
	}

	public void publicareStire(NewsEvent event) {
		if (event != null) {
			eventsQueue.add(event);
		}
	}

	private void dispatch(ListenerData listenerData, NewsEvent event) {
		NewsEventListener listener = listenerData.getListener();
		News eventContent = event.getContent();
		Stream<Predicate<News>> filters = listenerData.getFilters();
		if (filters.allMatch(filter -> filter.test(eventContent))) {
			listener.handleEvent(event);
		}
	}

	private void dispatchLoop() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				final NewsEvent event = eventsQueue.take();
				eventListener.getListenersForEvent(event.getType())
						.forEach(listenerData -> dispatch(listenerData, event));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}

		}
	}
}
