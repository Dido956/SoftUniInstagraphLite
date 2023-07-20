package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private static final String USER_FILES_PATH = "src/main/resources/files/users.json";
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(USER_FILES_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readFromFileContent(), UserSeedDto[].class))
                .filter(userSeedDto -> {
                    boolean isValid = validationUtil.isValid(userSeedDto)
                            && !userRepository.existsByUsername(userSeedDto.getUsername())
                            && pictureRepository.existsByPath(userSeedDto.getProfilePicture());

                    sb.append(isValid
                                    ? String.format("Successfully imported User: %s",
                                    userSeedDto.getUsername())
                                    : "Invalid User")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(userSeedDto -> {
                    User user = modelMapper.map(userSeedDto, User.class);
                    user.setProfilePicture(pictureService.findByPath(userSeedDto.getProfilePicture()));

                    return user;
                })
                .forEach(userRepository::save);

        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();

        userRepository
                .findAllByPostsCountDescThenByUserId()
                .forEach(user -> {
                    sb
                            .append(String.format("""
                                            User: %s
                                            Post count: %d
                                            """,
                                    user.getUsername(), user.getPosts().size()));

                    user.getPosts()
                            .forEach(post -> {
                                sb
                                        .append(String.format("""
                                                        Post Details: 
                                                        Caption: %s
                                                        Picture Size: %.2f
                                                        """,
                                                post.getCaption(),
                                                post.getPicture().getSize()));
                            });


                });


        return sb.toString();
    }

    @Override
    public boolean isEntityExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElse(null);
    }
}
