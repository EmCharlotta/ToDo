package sample.entity;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Setter
    @Getter
    @NonNull
    private String firstName;
    @Setter
    @Getter
    @NonNull
    private String surname;
    @Setter
    @Getter
    @NonNull
    private String login;
    @Setter
    @Getter
    @NonNull
    private String password;
    @Setter
    @Getter
    @NonNull
    private String gender;
}
