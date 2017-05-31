/*
 */

package br.com.dbserver.configuration;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Rafael Coutinho <rafaelc85@gmail.com>
 */
public class Util {
    
//retorna true se as datas estiverem na mesma semana ou falso caso contrario
    public static boolean isSameWeek(final Date d1, final Date d2) {
        if ((d1 == null) || (d2 == null))
            throw new IllegalArgumentException("Valor da Data nao pode ser null");

        return isSameWeek(new DateTime(d1), new DateTime(d2));
    }
    public static boolean isSameWeek(final DateTime d1, final DateTime d2) {
        if ((d1 == null) || (d2 == null))
            throw new IllegalArgumentException("Valor da Data nao pode ser null");

        final int week1 = d1.getWeekOfWeekyear();
        final int week2 = d2.getWeekOfWeekyear();

        final int year1 = d1.getWeekyear();
        final int year2 = d2.getWeekyear();

        final int era1 = d1.getEra();
        final int era2 = d2.getEra();

        if ((week1 == week2) && (year1 == year2) && (era1 == era2))
            return true;

        return false;
    }

}
