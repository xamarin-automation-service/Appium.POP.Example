package global;

import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import com.xamarin.testcloud.appium.EnhancedIOSDriver;
import com.xamarin.testcloud.appium.Factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by ethand on 1/10/17.
 */
public class AppManager {
    public static Platform platform = Platform.IOS;

    public enum Platform {
        ANDROID,
        IOS
    }

    private static EnhancedAndroidDriver<MobileElement> androidDriver;
    private static EnhancedIOSDriver<MobileElement> iOSDriver;

    public static void startApp() throws MalformedURLException {
        if ((platform == Platform.ANDROID && androidDriver != null) || (platform == Platform.IOS && iOSDriver != null)) {
            return;
        }

        switch(platform){
            case ANDROID:
                File classpathRoot = new File(System.getProperty("user.dir"));
                File appDir = new File(classpathRoot, "/app/Android");
                File app = new File (appDir, "com.sample.evolve.apk");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "NotUsed");
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                androidDriver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case IOS:
                classpathRoot = new File(System.getProperty("user.dir"));
                appDir = new File(classpathRoot, "/app/iOS");
                app = new File(appDir, "XamarinEvolveiOS.ipa");
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                capabilities.setCapability(MobileCapabilityType.UDID, "eacaf802667db5ec7b7db84002382c2761e9186d");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "NotUsed");
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability("autoAcceptAlerts",true);
                iOSDriver = Factory.createIOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                iOSDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
        }
    }

    public static AppiumDriver getDriver() throws Exception {
        if ((platform == Platform.ANDROID && androidDriver == null) || (platform == Platform.IOS && iOSDriver == null)) {
            throw new Exception("You must call AppManager.instance.startApp() before using the driver");
        }
        return platform == Platform.ANDROID ? androidDriver : iOSDriver;
    }

    public static void stopApp() throws Exception {
        if (getDriver() != null) {
            switch (platform) {
                case ANDROID:
                    androidDriver.quit();
                    androidDriver = null;
                    break;
                case IOS:
                    iOSDriver.quit();
                    iOSDriver = null;
                    break;
            }
        }
    }

    public static void screenshot(String label) {
        if (platform == Platform.ANDROID) {
            androidDriver.label(label);
        } else {
            iOSDriver.label(label);
        }
    }
}
