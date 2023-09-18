package notification.sender.Models;

import jakarta.persistence.*;
import lombok.*;
import notification.sender.Utils.MailStatus;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="letters", schema="public")
@Getter
@Setter
@ToString
public class Letters {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long id;

    @Column(name = "transaction_id")
    //@NonNull
    private UUID transactionId;

    @Column(name = "body")
    private String body;

    @Column(name = "address_to")
    //@JdbcTypeCode(SqlTypes.JSON)
    //@JsonProperty
    private String addressTo;

    @Column(name = "sent_date")
    private Date sentDate;

    @Column(name = "status")
    private MailStatus status;

    @Column(name = "response_msg")
    private String responseMsg;

    public Letters() {

    }
}




