package biz.paluch.clean.architecture.usecases;

import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.usecases.boundaries.UserRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 07:34
 */
public class CreateOrUpdateUser
{
    private UserRepository userRepository;


    public void createOrUpdateUser(String userName)
    {

        User user = userRepository.find(userName);
        if (user == null)
        {
            user = new User(userName);
            userRepository.store(user);
        }
    }

    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}
