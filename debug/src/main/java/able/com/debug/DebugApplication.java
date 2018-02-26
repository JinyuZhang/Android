package able.com.debug;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class DebugApplication {
    public static void init(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return;
        }
        LeakCanary.install(application);
    }
}
