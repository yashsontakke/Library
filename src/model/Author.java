package model;

public class Author {
    String name ;
    int exp ;
    int authorId ;

    public Author(String name, int exp ,int authorId) {
        this.name = name;
        this.exp = exp;
        this.authorId= authorId;
    }

    public String getName() {
        return name;
    }

    public int getExp() {
        return exp;
    }

    public int getAuthorId() {
        return authorId;
    }
}
