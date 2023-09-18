package user.remote.Models;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.util.UUID;


@Entity
@Data
@Table(name="user_personals", schema="public")
@Getter
@Setter
@ToString
public class UserRemote {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private UUID userId;
    @Column(name = "first_name")
    @Value("invite")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @Value("invite")
    @NonNull
    private String lastName;
    @Column(name = "patronymic_name")
    private String patronymicName;
    @Column(name = "birth_day")
    private Date birthDay;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "change_date")
    private Date changeDate;
    @Column(name = "register_date")
    private Date registerDate;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail")
    private String mail;
    @Column(name = "status")
    private Integer status;
    public UserRemote() {
    }

    public void setDefault() {
        firstName = "invite";
        lastName = "invite";
        createDate = new Date(System.currentTimeMillis());
        status = 0;
    }
}


