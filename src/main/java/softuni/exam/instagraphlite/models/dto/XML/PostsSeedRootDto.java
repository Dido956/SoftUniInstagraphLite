package softuni.exam.instagraphlite.models.dto.XML;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsSeedRootDto {

    @XmlElement(name = "post")
    private List<PostSeedDto> posts;

}
