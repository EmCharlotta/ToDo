package sample.entity;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Calendar;

@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @Getter
    @Setter
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp datecreated;

    @Getter
    @Setter
    @NonNull
    private String description;

    @Getter
    @Setter
    @NonNull
    private String task;

    @Getter
    @Setter
    private int userId;
}
