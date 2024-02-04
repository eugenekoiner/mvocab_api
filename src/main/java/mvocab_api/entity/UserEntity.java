package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Column(unique = true)
    private String phone;
    private String favorites;
}
