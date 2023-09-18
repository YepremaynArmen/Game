package game.dev.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="user_local", schema="public")
@Getter
@Setter
@ToString
public class UserLocal {
    @Id
    @Column(name = "user_id")
    private UUID userId;

/*    @Type(type = "org.hibernate.type.BinaryType")*/
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;







}


