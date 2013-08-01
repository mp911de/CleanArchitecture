package biz.paluch.clean.architecture.backend.persistence;

import biz.paluch.clean.architecture.applicationmodel.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 09:21
 */
public class JpaUserRepository
{
    @Inject
    private EntityManager entityManager;

    public void persist(User user)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        entityManager.persist(userEntity);
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
