package pl.karas.cyclingmanagementsystem.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.ActivationLink;
import pl.karas.cyclingmanagementsystem.model.User;
import pl.karas.cyclingmanagementsystem.repository.ActivationLinkRepository;
import pl.karas.cyclingmanagementsystem.repository.UserRepository;
import pl.karas.cyclingmanagementsystem.service.ActivationLinkService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class ActivationLinkServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivationLinkService activationLinkService;

    @Test
    public void oneUserSavedWithActivationLink_getActivationLinkByUserId_onUserWithId1() {
        //given
        User user = User.builder()
                .id(1L)
                .username("TestUsername")
                .firstName("TestFirstname")
                .email("test@o2.pl")
                .lastName("TestLatName")
                .password("TestPassword")
                .accountStatus("NON_ACTIVE")
                .build();

        User savedUser = userRepository.save(user);

        ActivationLink activationLink = ActivationLink.builder()
                .id(1L)
                .activationToken("TestActivationToken")
                .user(savedUser)
                .build();

        activationLinkService.save(activationLink);

        //when
        ActivationLink foundActivationLink = activationLinkService.getActivationLinkByUserId(savedUser.getId()).get();

        //then
        assertThat(foundActivationLink.getUser().getId(), is(1L) );

    }

}
