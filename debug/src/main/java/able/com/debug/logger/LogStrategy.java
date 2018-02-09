package able.com.debug.logger;

public interface LogStrategy {

    void log(int priority, String tag, String message);
}
