package biz.paluch.clean.architecture.backend.persistence;

import org.h2.Driver;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 13.06.13 21:28
 */
public abstract class AbstractJpaTest
{

    private static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() throws Exception
    {
        Map<String, String> properties = getH2Properties();
        entityManagerFactory = Persistence.createEntityManagerFactory("primary", properties);

    }


    @AfterClass
    public static void afterClass() throws Exception
    {
        entityManagerFactory.close();

    }


    @Before
    public void before() throws Exception
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

    }

    @After
    public void after() throws Exception
    {
        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public static Map<String, String> getH2Properties()
    {
        Map<String, String> properties = new HashMap<>();
        properties.put(AvailableSettings.DIALECT, H2Dialect.class.getName());
        properties.put(AvailableSettings.URL, "jdbc:h2:mem:primary");
        properties.put(AvailableSettings.USER, "SA");
        properties.put(AvailableSettings.DRIVER, Driver.class.getName());
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create");
        return properties;
    }
}
