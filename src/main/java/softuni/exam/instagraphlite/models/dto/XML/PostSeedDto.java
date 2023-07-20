package softuni.exam.instagraphlite.models.dto.XML;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class PostSeedDto {

    @XmlElement
    @Size(min = 21)
    private String caption;

    @XmlElement(name = "user")
    @NotNull
    private UserUsernameDto user;

    @XmlElement(name = "picture")
    @NotNull
    private PicturePathDto picture;
}
