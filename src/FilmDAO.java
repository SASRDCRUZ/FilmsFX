public class FilmDAO {
    public Film createFilm(String name, int lengthMins) {
        Film newFilm = new Film(name, lengthMins);
        // Then write this film to the database...
        return newFilm;
    }

    public Film[] loadData() {
        // load data from database...
        String[] data = {"Castaway", "Arrival", "Cloud Atlas", "Schindler's List", "Bohemian Rhapsody"};
        Film[] films = new Film[5];
        for (int i = 0; i < 5; i++) {
            films[i] = new Film(data[i], 120+(i*10));
        }
        films[0].setRating(3);
        return films;
    }

    public void update(Film theFilm) {
        // Persist change to database
        System.out.println("Film saved");
    }

}
