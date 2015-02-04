package biz.paluch.clean.architecture.repository.jpa.entity;

import javax.persistence.*;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:24
 */
@Entity
@Table(name = "Users")
@NamedQueries({ @NamedQuery(name = UserEntity.QUERY_FIND_BY_USERNAME, query = "SELECT u from UserEntity u where u.userName = :userName") })
public class UserEntity {
    public static final String QUERY_FIND_BY_USERNAME = "UserEntity.finyByUserName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String userName;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(']');
        return sb.toString();
    }
}
