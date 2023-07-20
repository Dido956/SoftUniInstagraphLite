package softuni.exam.instagraphlite.models.dto.XML;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class PicturePathDto {

    @XmlElement
    @NotBlank
    private String path;
}
