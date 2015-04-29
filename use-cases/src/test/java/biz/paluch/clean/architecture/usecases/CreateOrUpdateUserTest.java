package biz.paluch.clean.architecture.usecases;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import biz.paluch.clean.architecture.usecases.simple.CreateOrUpdateUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.contracts.repositories.UserRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 07:36
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateOrUpdateUserTest {

    public static final String USER_NAME = "mark";
    private CreateOrUpdateUser sut = new CreateOrUpdateUser();

    @Mock
    private UserRepository userRepository;

    @Before
    public void before() throws Exception {
        sut.setUserRepository(userRepository);
    }

    @Test
    public void testCreateOrUpdateUser() throws Exception {
        sut.createOrUpdateUser(USER_NAME);

        verify(userRepository).store(any(User.class));
    }
}
