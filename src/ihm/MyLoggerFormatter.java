package ihm;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by kamil on 18.04.2016.
 */
class MyLoggerFormatter extends Formatter {
  //
  // Create a DateFormat to format the logger timestamp.
  //
  private final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");

  public String format(LogRecord record) {
    StringBuilder builder = new StringBuilder(1000);
    builder.append(df.format(new Date(record.getMillis()))).append(" - ");
    builder.append("[").append(record.getSourceClassName()).append(".");
    builder.append(record.getSourceMethodName()).append("] - ");
    builder.append("[").append(record.getLevel()).append("] - ");
    builder.append(formatMessage(record));
    builder.append("\n");
    return builder.toString();
  }

  public String getHead(Handler handler) {
    return super.getHead(handler);
  }

  public String getTail(Handler handler) {
    return super.getTail(handler);
  }
}
