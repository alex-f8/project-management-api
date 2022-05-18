package af.projectmanagement.forms;

public class UserWorkDayUpdateForm {
    private long id;
    String day;

    public UserWorkDayUpdateForm(long id, String days) {
        this.id = id;
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
