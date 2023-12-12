public class Main {

    public static void main(String[] args) {
        Controller c = new Controller();
        c.createAuthorTable(); // creating author table first because Book table has reference of authorId from Author Table

        c.createBookTable();

        c.seedData();

    }
}
