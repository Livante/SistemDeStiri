package news;

public class News
{
    private final String title;
    private final String category;
    private final String author;
    private String content;

    public News(String categorie,String autor,String titlu,String continut)
    {
        title=titlu;
        category=categorie;
        author=autor;
        content=continut;
    }

    public String getTitle()
    {
        return title;
    }
    
    public String getCategory()
    {
        return category;
    }

    public boolean Equals(Object Object)
    {
        if(Object instanceof News)
        {
            News newz = (News) Object;
            return (newz.author == author && newz.title == title);
        }
        return false;
    }
    
    public String toString()
    {
    	return category+": "+title+" by "+ author+"\n\t "+content+"\n";
    	
    }

}