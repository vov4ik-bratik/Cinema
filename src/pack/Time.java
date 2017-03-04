package pack;

/**
 * Created by vs on 14.10.2016.
 */

public class Time {

    private int hours;
    private int minute;

    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        if(minute < 10)
            return hours + ":0" + minute;
        else
            return hours + ":" + minute;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hours;
        result = prime * result + minute;
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
        Time other = (Time) obj;
        if (hours != other.hours)
            return false;
        if (minute != other.minute)
            return false;
        return true;
    }

    public Time(int hours, int minute) {
        super();
        this.hours = hours;
        this.minute = minute;
    }

    public Time (int min){
        this.hours = (int)min/60;
        this.minute = min%60;
    }

    public Time (){
    }

    public int toInt(){
        return (this.hours*60 + this.minute);
    }

}
