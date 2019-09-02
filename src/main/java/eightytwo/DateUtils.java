package eightytwo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

  private static final DateFormat format = new SimpleDateFormat("hh:mm:ss.SSS");

  public static String format(final Date date) {
    return format.format(date);
  }
}
