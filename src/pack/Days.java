package pack;

/**
 * Created by vs on 14.10.2016.
 */

public enum Days {

    SUNDAY(7), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);

    private int id;

    private Days(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}

