package events;

import news.News;

public class NewsEvent {
	private final News content;
	private final EventFlag type;

	public NewsEvent(EventFlag tip, News continut) {
		content = continut;
		type = tip;
	}

	public News getContent() {
		return content;
	}

	public String getEventDescription() {
		switch (type) {
		case STIRI_APARUTE:
			return "Au aparut stiri noi";
		case STIRI_STERSE:
			return "Au fost sterse niste stiri";
		case STIRI_CITITE:
			return "Stirea a fost citita";
		default:
			return "Error";
		}
	}
	
	public EventFlag getType() {
		return type;
	}
}
