package af.projectmanagement.useractivity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "usr_activity")
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "usr_id")
    private long userId;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    /**
     * in start_stop row,
     * "true" means start working and
     * "false" means finish the work
     */
    @Column(name = "start_stop")
    private boolean startStop;

    public UserActivity() {
    }

    public UserActivity(long userId, LocalDateTime dateTime, boolean startStop) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.startStop = startStop;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isStartStop() {
        return startStop;
    }

    public void setStartStop(boolean startStop) {
        this.startStop = startStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivity that = (UserActivity) o;
        return id == that.id &&
                userId == that.userId &&
                startStop == that.startStop &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, dateTime, startStop);
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", startStop=" + startStop +
                '}';
    }
}
