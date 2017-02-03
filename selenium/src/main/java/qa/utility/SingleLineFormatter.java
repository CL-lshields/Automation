package qa.utility;

import java.util.logging.*;

import qa.SeleniumTest;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SingleLineFormatter extends Formatter
{
    //SimpleDateFormat dateFormatter = new SimpleDateFormat ("MM/dd/YYYY HH:mm:ss");

    public String format(LogRecord record)
    {
        StringBuffer buffer = new StringBuffer();
        String datetimeString = SeleniumTest.dateFormat.format(new Date(record.getMillis()));
        buffer.append(datetimeString + " [" + record.getSourceClassName() + " " + record.getSourceMethodName() + "] " + record.getLevel().toString() + ": " + record.getMessage() + "\n");
        return buffer.toString();
    }

}
