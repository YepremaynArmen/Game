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
@Table(name="student_answers", schema="public")
@Getter
@Setter
@ToString
public class StudentAnswers {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "answer")
    private String answer;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "aproved_by")
    private UUID aprovedBy;

    @Column(name = "aproved_at")
    private Date aprovedAt;

    @Column(name = "result")
    private Boolean result;

    @Column(name = "progress")
    private Long progress;

    @Column(name = "status")
    private String status;

    @Column(name = "question_id")
    private Long questionId;


}



