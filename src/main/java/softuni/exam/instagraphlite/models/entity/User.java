package softuni.exam.instagraphlite.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Length(min = 2, max = 18)
    private String username;

    @Column(nullable = false)
    @Length(min = 4)
    private String password;


    @ManyToOne(optional = false)
    private Picture profilePicture;
}
