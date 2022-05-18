package af.projectmanagement.forms;

public class ProjectUserForm {
    public long pid;
    public long uid;
    String[] days;

    public ProjectUserForm() {
    }

    public ProjectUserForm(long pid, long uid, String[] days) {
        this.pid = pid;
        this.uid = uid;
        this.days = days;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

}
