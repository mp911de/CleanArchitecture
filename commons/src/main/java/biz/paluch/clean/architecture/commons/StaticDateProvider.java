package biz.paluch.clean.architecture.commons;

import java.util.Date;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:03
 */
public class StaticDateProvider extends DateProvider {

    private Date currentDate;

    public static void initialize(Date currentDate) {
        StaticDateProvider instance = new StaticDateProvider();
        instance.setCurrentDate(currentDate);
        DateProvider.setDateProvider(instance);
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
