package game.dev.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="rewards", schema="public")
@Getter
@Setter
@ToString
public class Rewards {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "value")
    private Double value;

    @Column(name = "status")
    private String status;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "change_at")
    private Date changeAt;

    @Column(name = "change_by")
    private UUID changeBy;



}



