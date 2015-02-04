package biz.paluch.clean.architecture.external.jpa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.contracts.UserRepository;
import biz.paluch.clean.architecture.external.jpa.entity.UserEntity;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 09:21
 */
public class JpaUserRepository implements UserRepository {
    @Inject
    private EntityManager entityManager;

    @Override
    public User find(String userName) {
        List<UserEntity> list = entityManager.createNamedQuery(UserEntity.QUERY_FIND_BY_USERNAME, UserEntity.class)
                .setParameter("userName", userName).getResultList();

        if (!list.isEmpty()) {
            UserEntity userEntity = list.get(0);
            User user = new User();
            user.setUserName(userEntity.getUserName());
            return user;
        }

        return null;
    }

    @Override
    public void store(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        entityManager.persist(userEntity);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
