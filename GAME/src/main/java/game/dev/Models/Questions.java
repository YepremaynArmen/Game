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
@Table(name="questions", schema="public")
@Getter
@Setter
@ToString
public class Questions {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name = "description")
    private String description;

    @Column(name = "groups")
    private String groups;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "complexity")
    private Long complexity;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "aproved_by")
    private UUID aprovedBy;

    @Column(name = "aproved_at")
    private Date aprovedAt;

    @Column(name = "change_at")
    private Date changeAt;

    @Column(name = "status")
    private String status;

    @Column(name = "task")
    private String task;

    @Column(name = "change_by")
    private UUID changeBy;


    /*@Type(type = "io.r2dbc.postgresql.codec")*/

}



