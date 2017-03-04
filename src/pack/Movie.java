package pack;

public class Movie implements Comparable<Movie> {

    private String title;
    private Time duration;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Time getDuration() {
        return duration;
    }
    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Movie(String title, Time duration) {
        super();
        this.title = title;
        this.duration = duration;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return title + " " + duration +"("+ (duration.getHours()*60+duration.getMinute())+"min"+")";
    }
    @Override
    public int compareTo(Movie o) {
        if(this.title.equalsIgnoreCase(o.getTitle()))
            return 0;
        else
            return title.compareToIgnoreCase(o.getTitle());

    }

}
