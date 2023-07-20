package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PictureSeedDto {

    @Expose
    @NotBlank
    private String path;

    @Expose
    @Min(500)
    @Max(60000)
    private double size;
}
