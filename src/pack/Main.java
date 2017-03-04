package pack;

public class Main {

    public static void main(String[] args) {

        Cinema forum = new Cinema();

        forum.addMovie();
        forum.addMovie();
        forum.addMovie();
        forum.addMovie();

        forum.printMoviesList();


        forum.addSeance();
        forum.addSeance();
        forum.addSeance();
        forum.addSeance();
        forum.addSeance();

        forum.printSpecificDay();
        forum.printAllSchedules();
    }
}
