public class Main {

    public static void main(String[] args) {
        Controller c = new Controller();
        // below commented code has to be run just for the first time
//        c.createAuthorTable(); // creating author table first because Book table has reference of authorId from Author Table
//
//        c.createBookTable();

//        c.seedData();
// i have seeded the data data is stored in tables so need to call seeder file again
// if i uncomment it i need to avoid adding duplicate data otherwise it will lead to errors
        c.executeQueries();

    }
}
