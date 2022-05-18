package af.projectmanagement.workday;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * In class WorkDay shown relation between Project class and User class.
 */
@Entity
@Table(name = "project_usr")
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "project_id")
    private long pid;

    @Column(name = "usr_id")
    private long uid;

    @Column(name = "work_day")
    private LocalDate workDay;

    public WorkDay() {
    }
    public WorkDay(long pid, long uid, LocalDate workDay) {
        this.id = id;
        this.pid = pid;
        this.uid = uid;
        this.workDay = workDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getWorkDay() {
        return workDay;
    }

    public void setWorkDay(LocalDate workDay) {
        this.workDay = workDay;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", pid=" + pid +
                ", uid=" + uid +
                ", workDay=" + workDay +
                '}';
    }
}
