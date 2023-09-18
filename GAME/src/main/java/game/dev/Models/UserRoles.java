package game.dev.Models;

import lombok.*;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="user_roles", schema="public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "role_name")
    private String roleName;







}


