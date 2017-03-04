package pack;

/**
 * Created by vs on 14.10.2016.
 */

public class Seance implements Comparable<Seance>{

    private Movie movie;
    private Time starTime;
    private Time endTime;

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
        Time end = new Time();

        end.setHours(this.starTime.getHours()+movie.getDuration().getHours());

        if(starTime.getMinute()+movie.getDuration().getMinute() > 59){
            end.setHours(end.getHours()+
                    (starTime.getMinute()+ movie.getDuration().getMinute())/60);

            end.setMinute((starTime.getMinute()+movie.getDuration().getMinute())%60);
        }
        else
            end.setMinute(starTime.getMinute()+movie.getDuration().getMinute());

        if(end.getHours()>23)
            end.setHours(end.getHours()%24);

        this.endTime = end;
    }
    public Time getStarTime() {
        return starTime;
    }
    public void setStarTime(Time starTime) {
        this.starTime = starTime;
        Time end = new Time();

        end.setHours(this.starTime.getHours()+movie.getDuration().getHours());

        if(starTime.getMinute()+movie.getDuration().getMinute() > 59){
            end.setHours(end.getHours()+
                    (starTime.getMinute()+ movie.getDuration().getMinute())/60);

            end.setMinute((starTime.getMinute()+movie.getDuration().getMinute())%60);
        }
        else
            end.setMinute(starTime.getMinute()+movie.getDuration().getMinute());

        this.endTime = end;
    }
    public Time getEndTime() {
        return endTime;
    }

    public int startTimeToInt(){
        return this.starTime.getHours()*60+this.starTime.getMinute();
    }

    public int endTimeToInt(){
        return this.endTime.getHours()*60+this.endTime.getMinute();
    }

    public Seance(Movie movie, Time starTime) {
        super();
        this.movie = movie;
        this.starTime = starTime;

        Time end = new Time();

        end.setHours(this.starTime.getHours()+movie.getDuration().getHours());

        if(starTime.getMinute()+movie.getDuration().getMinute() > 59){
            end.setHours(end.getHours()+
                    (starTime.getMinute()+ movie.getDuration().getMinute())/60);

            end.setMinute((starTime.getMinute()+movie.getDuration().getMinute())%60);
        }
        else
            end.setMinute(starTime.getMinute()+movie.getDuration().getMinute());

        this.endTime = end;
    }
    @Override
    public String toString() {
        return movie + "\nStart time: " + starTime + "\nEnd time: " + endTime;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
        result = prime * result + ((starTime == null) ? 0 : starTime.hashCode());
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
        Seance other = (Seance) obj;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (movie == null) {
            if (other.movie != null)
                return false;
        } else if (!movie.equals(other.movie))
            return false;
        if (starTime == null) {
            if (other.starTime != null)
                return false;
        } else if (!starTime.equals(other.starTime))
            return false;
        return true;
    }

    @Override
    public int compareTo(Seance arg0) {
        if((this.getStarTime().getHours()*60+this.getStarTime().getMinute()) >
                (arg0.getStarTime().getHours()*60 + arg0.getStarTime().getMinute()))
            return 1;
        else
        if((this.getStarTime().getHours()*60+this.getStarTime().getMinute()) <
                (arg0.getStarTime().getHours()*60 + arg0.getStarTime().getMinute()))
            return -1;
        else return 0;
    }





}

