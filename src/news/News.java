package news;

import java.util.Date;

public class News {
    private final String title;
    private final Date publishdate;
    private final String category;
    private final String author;
    private String content;
    private Date lastChangeDate;

    public News(String category,String author,String title,String content) {
        this.category=category;
        this.author=author;
        this.content=content;
        this.title=title;

        publishdate = new Date();
        lastChangeDate = new Date();
    }

    public String getCategory() {
        return category;
    }

    private void updateLastChangeDate() {
        lastChangeDate=new Date();
    }

    public void changeContent(String content) {
        this.content = content;
        updateLastChangeDate();
    }

    public String toString() {
        StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("[" + this.category + "]: ").append(this.title);
		messageBuilder.append("\n");
		messageBuilder.append("Written by: " + this.author);
		messageBuilder.append("\n");
		messageBuilder.append(this.content);

		return messageBuilder.toString();
    }

    public boolean Equals(Object object) {
        if(object instanceof News) {
            News newz = (News) object;
            boolean rez = newz.author == this.author && newz.title == title;
            return rez;
        }
        return false;
    }
}