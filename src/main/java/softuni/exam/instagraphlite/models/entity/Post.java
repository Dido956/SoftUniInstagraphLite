package softuni.exam.instagraphlite.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Column(nullable = false)
    @Length(min = 21)
    private String caption;


    @ManyToOne(optional = false)
    private User user;


    @ManyToOne(optional = false)
    private Picture picture;
}
