package softuni.exam.instagraphlite.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity(name = "pictures")
@Getter
@Setter
@NoArgsConstructor
public class Picture extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String path;

    @Column(nullable = false)
    private double size;
}
