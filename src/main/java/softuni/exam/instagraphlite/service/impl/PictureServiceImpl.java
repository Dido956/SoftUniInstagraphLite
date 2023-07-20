package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import javax.validation.Validation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/pictures.json";

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readFromFileContent(), PictureSeedDto[].class))
                .filter(pictureSeedDto -> {
                    boolean isValid = validationUtil.isValid(pictureSeedDto)
                            && !isEntityExists(pictureSeedDto.getPath());

                    sb.append(isValid
                                    ? String.format("Successfully imported Picture, with size %.2f", pictureSeedDto
                                    .getSize())
                                    : "Invalid Picture")
                            .append(System.lineSeparator());


                    return isValid;
                })
                .map(pictureSeedDto -> modelMapper.map(pictureSeedDto, Picture.class))
                .forEach(pictureRepository::save);

        return sb.toString();
    }

    public boolean isEntityExists(String path) {
        return pictureRepository.existsByPath(path);
    }

    @Override
    public String exportPictures() {
        return null;
    }

    @Override
    public Picture findByPath(String profilePicture) {
        return
                pictureRepository
                        .findByPath(profilePicture)
                        .orElse(null);
    }
}
