package pack;

/**
 * Created by vs on 14.10.2016.
 */
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Schedule implements Comparable<Schedule>{

    private Set<Seance>schedule;

    public Schedule() {
        super();
        this.schedule = new TreeSet<Seance>();
    }

    public Set<Seance> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Seance> seanceSet) {
        this.schedule = seanceSet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
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
        Schedule other = (Schedule) obj;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        return true;
    }

    @Override
    public int compareTo(Schedule o) {
        return 0;
    }

    //without time coflict check
    public void addSeance(Movie mov, Time timeStat){
        schedule.add(new Seance(mov, timeStat));
    }

    //without time coflict check
    public void addSeance(Seance seance){
        schedule.add(seance);
    }

    public void removeSeance(Movie mov, Time timeStat){

        Iterator<Seance>seanceIter = schedule.iterator();

        while (seanceIter.hasNext()) {
            Seance seance = (Seance) seanceIter.next();

            if(seance.equals(new Seance(mov, timeStat))){
                seanceIter.remove();
                break;
            }
        }
    }

    public void removeSeance(Seance seance){

        Iterator<Seance>seanceIter = schedule.iterator();

        while (seanceIter.hasNext()) {
            Seance seanceTmp = (Seance) seanceIter.next();

            if(seanceTmp.equals(seance)){
                seanceIter.remove();
                break;
            }
        }
    }

    public void removeSeance(String title, Time cinemaStat, Time movieStartTime){

        Iterator<Seance>seanceIter = schedule.iterator();
        boolean flag = false;

        //видаляю сеанс з розкладу
        while (seanceIter.hasNext()) {
            Seance seance = (Seance) seanceIter.next();

            if(seance.getMovie().getTitle().equals(title) && seance.getStarTime().equals(movieStartTime)){
                seanceIter.remove();
                flag = true;
                break;
            }
        }

        Time timeSpaceChecker = new Time(1439);

        if(flag){
            Iterator<Seance>seanceStartIter = schedule.iterator();
            Seance seanceFirst = (Seance) seanceStartIter.next();
            if(seanceFirst.getStarTime().toInt() > movieStartTime.toInt()){
                seanceFirst.setStarTime(movieStartTime);
            }

            Iterator<Seance>seanceIter2 = schedule.iterator();
            while (seanceIter2.hasNext()) {
                Seance seance = (Seance) seanceIter2.next();
                //якщо час початку наступного сеансу не співпадає з часом закінчення попереднього
                if(seance.startTimeToInt() > timeSpaceChecker.toInt()){
                    //тоді перевизначаємо час початку сеансу
                    seance.setStarTime(timeSpaceChecker);
                }
                timeSpaceChecker = seance.getEndTime();
            }

        }
        else
            System.out.println("error in film description or seance time start.\n");

    }

    public void removeAllMovieSeances(Movie mov){

        Iterator<Seance>seanceIter = schedule.iterator();

        while (seanceIter.hasNext()) {
            Seance seance = (Seance) seanceIter.next();
            if(seance.getMovie().equals(mov))
                seanceIter.remove();
        }
    }

    public void removeAllMovieSeances(String title, Time movieStartTime){

        Iterator<Seance>seanceIter = schedule.iterator();
        boolean flag = false;

        while (seanceIter.hasNext()) {
            Seance seance = (Seance) seanceIter.next();
            if(seance.getMovie().getTitle().equals(title)){
                seanceIter.remove();
                flag = true;
            }
        }

        //присвоїв значення часу кінця доби
        Time timeSpaceChecker = new Time(1439);

        //якщо введений фільм існує і був видалений з сеансів,
        //тоді потрібно відредагувати сеанси щоб не було прогалин в часі

        if(flag){
            //перевіряю чи перший сеанс починається з відкриття (чи не був видалений перший сеанс)
            //якщо так - тоді посуваю перший сеанс на час відкриття кінотеатру

            Iterator<Seance>seanceStartIter = schedule.iterator();
            Seance seanceFirst = (Seance) seanceStartIter.next();
            if(seanceFirst.getStarTime().toInt() > movieStartTime.toInt()){
                seanceFirst.setStarTime(movieStartTime);
            }
            //забираю всі прогалини - щоб сеанси йшли підряд
            Iterator<Seance>seanceIter2 = schedule.iterator();
            while (seanceIter2.hasNext()) {
                Seance seance = (Seance) seanceIter2.next();
                //якщо час початку наступного сеансу не співпадає з часом закінчення попереднього
                if(seance.startTimeToInt() > timeSpaceChecker.toInt()){
                    //тоді перевизначаємо час початку сеансу
                    seance.setStarTime(timeSpaceChecker);
                }
                timeSpaceChecker = seance.getEndTime();
            }
        }
        else
            System.out.println("error in film description.\n");
    }

    public void clearSchedule(){
        schedule.clear();
    }

    public void printSchedule(){
        for (Seance seance : schedule)
            System.out.println(seance);
    }

    //change start time (without time conflict check)
    public void editSeance(Seance seance, Time newTimeStart){
        Iterator<Seance>seanceIter = schedule.iterator();

        while (seanceIter.hasNext()) {
            if(seanceIter.next().equals(seance)){
                seance.setStarTime(newTimeStart);
                break;
            }
        }
    }




}

