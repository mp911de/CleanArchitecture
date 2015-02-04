package biz.paluch.clean.architecture.commons;

import java.util.Date;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:01
 */
public class DateProvider {
    private static DateProvider dateProvider = new DateProvider();

    public static Date get() {
        return dateProvider.getCurrentDate();
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public static void setDateProvider(DateProvider dateProvider) {
        DateProvider.dateProvider = dateProvider;
    }
}
