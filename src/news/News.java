package news;

public class News
{
    private final String title;
    private final String category;
    private final String author;
    private String content;

    public News(String category,String author,String title,String content)
    {
        this.title=title;
        this.category=category;
        this.author=author;
        this.content=content;
    }

    public String getCategory()
    {
        return category;
    }

    public String toString()
    {
        StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("[" + this.category + "]: ").append(this.title);
		messageBuilder.append("\n");
		messageBuilder.append("Written by: " + this.author);
		messageBuilder.append("\n");
		messageBuilder.append(this.content);

		return messageBuilder.toString();
    }

    public boolean Equals(Object Object)
    {
        if(Object instanceof News)
        {
            News newz = (News) Object;
            return (newz.author == this.author && newz.title == title);
        }
        return false;
    }

}