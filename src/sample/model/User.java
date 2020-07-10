package sample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Setter
    @Getter
    private String firstName;
    @Setter
    @Getter
    private String surname;
    @Setter
    @Getter
    private String login;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String gender;
}
