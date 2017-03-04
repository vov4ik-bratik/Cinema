package pack;

/**
 * Created by vs on 14.10.2016.
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Cinema {

    private Map<Days, Schedule> dailySchedule;
    private Time startTime;
    private Time endTime;
    private Set<Movie>movieList;

    private Scanner sc = new Scanner(System.in);

    public Map<Days, Schedule> getDailySchedule() {
        return dailySchedule;
    }

    public void setDailySchedule(Map<Days, Schedule> dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Set<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(Set<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dailySchedule == null) ? 0 : dailySchedule.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((movieList == null) ? 0 : movieList.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
        Cinema other = (Cinema) obj;
        if (dailySchedule == null) {
            if (other.dailySchedule != null)
                return false;
        } else if (!dailySchedule.equals(other.dailySchedule))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (movieList == null) {
            if (other.movieList != null)
                return false;
        } else if (!movieList.equals(other.movieList))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }

    public Cinema() {
        super();
        this.dailySchedule = new HashMap<Days, Schedule>();
        this.movieList = new TreeSet<Movie>();

        for (Days weekDay : Days.values()) {
            dailySchedule.put(weekDay, new Schedule());
        }

        //Scanner sc = new Scanner(System.in);
        boolean flag = true;

        int sTimeH = 0;
        int sTimeM = 0;

        while(flag){
            System.out.println("enter cinema open time(in 24 hours format).\nhours: ");
            sTimeH = sc.nextInt();

            if(sTimeH < 0 || sTimeH > 23)
                System.out.println("\nerror. wrong hours value. please enter correct.\n");
            else
                flag = false;
        }

        flag = true;

        while(flag){
            System.out.println("enter minutes: ");
            sTimeM = sc.nextInt();
            if(sTimeM < 0 || sTimeM > 59)
                System.out.println("\nerror. wrong minutes value. please enter correct.\n");
            else
                flag = false;
        }

        flag = true;

        Time sTime = new Time(sTimeH, sTimeM);

        int eTimeH = 0;
        int eTimeM = 0;

        while(flag){
            System.out.println("Enter cinema close time(in 24 hours format).\nhours: ");
            eTimeH = sc.nextInt();
            if(eTimeH <= sTimeH){
                System.out.println("\nerror. end time can't be less than star time. enter correct cinema close hours value\n");
            }
            else
            if(eTimeH > 23)
                System.out.println("\nerror. wrong hours value. please enter correct.\n");
            else
                flag = false;
        }

        flag = true;

        while(flag){
            System.out.println("enter minutes: ");
            eTimeM = sc.nextInt();
            if(eTimeM < 0 || eTimeM > 59)
                System.out.println("\nerror. wrong minutes value. please enter correct.\n");
            else
                flag = false;
        }

        Time eTime = new Time(eTimeH, eTimeM);

        this.startTime = sTime;
        this.endTime = eTime;
    }

    public void printMoviesList(){
        System.out.println("\n");
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    //add movie to movie list only. No changes in seances and schedules
    public void addMovie(){
        //Scanner sc = new Scanner(System.in);

        System.out.println("\nenter movie title: ");
        String title = sc.next();

        System.out.println("\nenter duration in minute: ");
        int duration = sc.nextInt();

        movieList.add(new Movie(title, new Time(duration)));
    }

    public void addSeance(){
        //Scanner sc = new Scanner(System.in);

        //виводимо список фільмів. вводимо з клавіатури назву фільму
        System.out.println("\nEnter movie title from list below:");
        this.printMoviesList();
        String movieDescr = sc.next();

        //вводимо день тижня
        System.out.println("\nenter day of the week number (from 1 to 7), which you want to edit");
        System.out.print("1 - monday, 2 - tuesday ... 7 - sunday\n> ");
        int  day = sc.nextInt();

        Set<Entry<Days, Schedule>> scheduleEntrySet = dailySchedule.entrySet();

        boolean flag = false;
        boolean movieFlag = false;
        Time duration = null;
        int maxEndTime = 0;


        for (Entry<Days, Schedule> entry : scheduleEntrySet) {
            if (entry.getKey().getId() == day){

                //дістаю по назві тривалість фільму
                Iterator<Movie>movieIter = movieList.iterator();
                while (movieIter.hasNext()) {
                    Movie movie = (Movie) movieIter.next();
                    if(movie.getTitle().equalsIgnoreCase(movieDescr)){
                        duration = movie.getDuration();
                        movieFlag = true;
                        break;
                    }
                }

                //якщо розклад порожній - додаю сеанс на початок
                if (entry.getValue().getSchedule().isEmpty()&&movieFlag)
                    entry.getValue().addSeance(new Movie(movieDescr, duration), this.startTime);

                    //якщо розклад не пустий - визначаю сеанс, який закінчується найпізніше
                else
                if(!entry.getValue().getSchedule().isEmpty()&&movieFlag){

                    Iterator<Seance>seanceIter = entry.getValue().getSchedule().iterator();
                    while (seanceIter.hasNext()) {
                        maxEndTime = seanceIter.next().endTimeToInt();
                    }

                    //перевірка чи сеанс не закінчиться пізніше, ніж зачиниться кінотеатр
                    Time endTime = new Time(maxEndTime);

                    if((endTime.getHours()*60+endTime.getMinute() + duration.getHours()*60+duration.getMinute())
                            < (this.endTime.getHours()*60 + this.endTime.getMinute())){
                        entry.getValue().addSeance(new Movie(movieDescr, duration), endTime);
                    }
                    else
                        System.out.println("\nseance cant be added to schedule. it is end to late.");

                }
                else
                    System.out.println("\nincorrect film title.\n");
                flag = true;
                break;
            }
        }


        if(!flag)
            System.out.println("error... incorrect day number.");
    }

    public void printAllSchedules(){
        Set<Entry<Days, Schedule>>entrySet = dailySchedule.entrySet();

        for(int i = 1; i <= entrySet.size();i++)
            for (Entry<Days, Schedule> entry : entrySet)
                if(entry.getKey().getId() == i){
                    System.out.println("\n"+entry.getKey().name());
                    entry.getValue().printSchedule();
                    break;
                }

    }

    public void printSpecificDay(){

        //Scanner sc = new Scanner(System.in);
        System.out.println("\nenter day of the week number (from 1 to 7), which you want to view\n");
        System.out.print("1 - monday, 2 - tuesday ... 7 - sunday\n >");
        int  day = sc.nextInt();

        Set<Entry<Days, Schedule>>entrySet = dailySchedule.entrySet();
        boolean flag = false;

        for (Entry<Days, Schedule> entry : entrySet)
            if(entry.getKey().getId() == day){
                System.out.println("\n"+entry.getKey().name());
                entry.getValue().printSchedule();
                flag = true;
                break;
            }

        if(!flag)
            System.out.println("\nerror. incorrect day input\n");
    }

    public boolean printSpecificDay(int day){

        Set<Entry<Days, Schedule>>entrySet = dailySchedule.entrySet();
        boolean flag = false;

        for (Entry<Days, Schedule> entry : entrySet)
            if(entry.getKey().getId() == day){
                System.out.println("\n"+entry.getKey().name());
                entry.getValue().printSchedule();
                flag = true;
                break;
            }
        return flag;
    }



    public void removeMovie(){
        System.out.println("You choose remove movie.\n");
        this.printMoviesList();
        System.out.println("\nenter movie title, which you want to remove\n");
        //Scanner sc = new Scanner(System.in);
        boolean flag = false;

        String title = sc.next();

        Iterator<Movie>movieIter = movieList.iterator();

        while (movieIter.hasNext()) {
            Movie movie = (Movie) movieIter.next();
            if(movie.getTitle().equalsIgnoreCase(title)){

                movieIter.remove();
                System.out.println("\nmovie succsssfully removed from list.\n");
                flag = true;
                break;
            }
        }

        if(!flag)
            System.out.println("\nerror. incorrect input.\n");
        else{

            Set<Entry<Days, Schedule>>entrySet = dailySchedule.entrySet();
            //перебираю ітератором дні тижня
            for (Iterator iteratorDays = entrySet.iterator(); iteratorDays.hasNext();) {
                Entry<Days, Schedule> entry = (Entry<Days, Schedule>) iteratorDays.next();
                entry.getValue().removeAllMovieSeances(title, this.startTime);
            }

        }
    }

    public void removeSeance(){
        //Scanner sc = new Scanner(System.in);

        System.out.println("\nenter day of the week number (from 1 to 7), which you want to edit");
        System.out.print("1 - monday, 2 - tuesday ... 7 - sunday\n> ");
        int  day = sc.nextInt();


        Set<Entry<Days, Schedule>> scheduleEntrySet = dailySchedule.entrySet();

        for (Entry<Days, Schedule> entry : scheduleEntrySet) {

            if(entry.getKey().getId() == day){

                System.out.println("\n"+entry.getKey().name());
                entry.getValue().printSchedule();

                System.out.println("\nEnter movie title from schedule:");
                String movieDescr = sc.next();

                System.out.print("\nEnter movie start time. Hours:\n>");
                int hours = sc.nextInt();
                System.out.print("end minutes:\n>");
                int minutes = sc.nextInt();
                Time movieStartTime = new Time(hours, minutes);

                entry.getValue().removeSeance(movieDescr, this.startTime, movieStartTime);

            }

        }
    }

    public void scannerClose(){
        sc.close();
    }

    public void insertInSchedule(){

        boolean timeForSeance = false;
        boolean seanceCorrectTimeStart = false;

        //вибираю день тижня який редагується
        System.out.println("\nenter day of the week number (from 1 to 7), which you want to edit");
        System.out.print("1 - monday, 2 - tuesday ... 7 - sunday\n> ");
        int  day = sc.nextInt();

        //виводжу список фільмів і вводжу назву
        boolean flag = false;
        this.printMoviesList();
        System.out.println("\nenter movie title, which you want to insert\n");
        String title = sc.next();

        //пеервіряю чи правильно введена назва фільму
        Iterator<Movie>movieIter = movieList.iterator();
        Time duration = new Time(0);

        while (movieIter.hasNext()) {
            Movie movie = (Movie) movieIter.next();

            if(movie.getTitle().equalsIgnoreCase(title)){
                duration = movie.getDuration();
                flag = true;
                break;
            }
        }

        if(!flag)
            System.out.println("\nerror. incorrect movie title\n");
        else
            //якщо назва фільму введена правильно
            //виводжу розклад на цей день
            if(!this.printSpecificDay(day))
                System.out.println("\nwrong day number\n");
            else{
                //перевірити чи достатньо часу для сеансу щоб його вставляти
                Set<Entry<Days, Schedule>>schedulesSet = dailySchedule.entrySet();

                for (Entry<Days, Schedule> entry : schedulesSet) {
                    if(entry.getKey().getId() == day){
                        Iterator<Seance>seanceIterator = entry.getValue().getSchedule().iterator();

                        while (seanceIterator.hasNext()) {
                            Seance seance = (Seance) seanceIterator.next();
                            if(seance.getEndTime().toInt()+duration.toInt() > this.endTime.toInt()){
                                System.out.println("\nIt is not enought time for this seance\n");
                                timeForSeance = false;
                                break;
                            }
                            else timeForSeance = true;
                        }

                        if (timeForSeance){
                            //прошу вибрати години куди вставити сеанс
                            System.out.print("\nEnter movie start time. Hours:\n>");
                            int hours = sc.nextInt();
                            System.out.print("end minutes:\n>");
                            int minutes = sc.nextInt();
                            Time movieStartTime = new Time(hours, minutes);

                            //перевірити чи година введена коректно - чи співпрадає з початком сеансу якогось із фільмів

                            Iterator<Seance>seanceIter = entry.getValue().getSchedule().iterator();

                            while (seanceIter.hasNext()) {
                                Seance seance = (Seance) seanceIter.next();
                                if(seance.getStarTime().toInt() == movieStartTime.toInt()){
                                    seanceCorrectTimeStart = true;
                                    break;
                                }
                            }
                        }

                        break;
                    }
                    //якщо всі перевірки пройдені створити 2 нових сети
                    //в один додати сеанси до часу сеансу який вставляєть
                    //в інший додати сеанси після
                    //очитити вихідний сет і додати в нього преший + сенс який вставляється+ другий сет
                }

            }



        //end of the method
    }


    //end of the class
}

//додати вставку в розклад фільму - всі решта посуваються (перевірка чи вистачає часу до закриття)
//додати запис в файл



