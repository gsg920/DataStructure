package projects;
import java.time.LocalDate;

public class Movie {
    private String movieId;
    private String title;
    private String director;
    private String genre;
    private String releaseDate;

    public Movie(String movieId, String title, String director, String genre, String releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    
}
