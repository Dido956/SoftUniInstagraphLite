package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserSeedDto {

    @Expose
    @Length(min = 2, max = 18)
    @NotBlank
    private String username;

    @Expose
    @Length(min = 4)
    private String password;

    @Expose
    @NotBlank
    private String profilePicture;
}
