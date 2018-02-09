package able.com.debug.logger;

public interface FormatStrategy {

    void log(int priority, String tag, String message);
}
