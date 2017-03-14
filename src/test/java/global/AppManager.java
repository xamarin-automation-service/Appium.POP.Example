package global;

import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import com.xamarin.testcloud.appium.EnhancedIOSDriver;
import com.xamarin.testcloud.appium.Factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by ethand on 1/10/17.
 */
public class AppManager {

    public final static int SHORT_TIMEOUT = 5;
    public final static int LONG_TIMEOUT = 30;

    private static Platform platform = null;
    private static EnhancedAndroidDriver<MobileElement> androidDriver;
    private static EnhancedIOSDriver<MobileElement> iOSDriver;
    private static WebDriverWait waitDriver;

    public static Platform getPlatform() {
        if (platform != null) {
            return platform;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using the platform");
    }

    public static AppiumDriver getAppiumDriver() {
        if (platform == Platform.ANDROID && androidDriver != null) {
            return androidDriver;
        }

        if (platform == Platform.IOS && iOSDriver != null) {
            return iOSDriver;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using the driver");
    }

    public static WebDriverWait getWaitDriver() {
        if ((platform == Platform.ANDROID && androidDriver != null)
                || (platform == Platform.IOS && iOSDriver != null)) {
            return waitDriver;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using the waitDriver");
    }

    public static void startApp() {
        stopApp();

        String envPlatform = System.getenv("XTC_PLATFORM");
        if (envPlatform == null) {
            throw new RuntimeException("The 'XTC_PLATFORM' environment variable is not set");
        } else if (envPlatform.equals("android")) {
            platform = Platform.ANDROID;
        } else if (envPlatform.equals("ios") || envPlatform.equals("ios-simulator")) {
            platform = Platform.IOS;
        } else {
            throw new RuntimeException("Platform not supported: " + envPlatform);
        }

        File app = null;
        File classpathRoot = new File(System.getProperty("user.dir"));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // The value of DEVICE_NAME is only used for running on the iOS simulator,
        // but must also have some (any) value for iOS and Android physical devices
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");

        URL driverUrl;
        try {
            driverUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }

        switch(platform){
            case ANDROID:
                app = new File(classpathRoot, "app/Android/com.sample.evolve.apk");

                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability("appActivity", "md5be5f5a522500d14b497fe968fc46ddc7.MainActivity");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

                androidDriver = Factory.createAndroidDriver(driverUrl, capabilities);

                androidDriver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT, TimeUnit.SECONDS);
                waitDriver = new WebDriverWait(androidDriver, LONG_TIMEOUT);

                break;
            case IOS:
                // Use .ipa for physical device
                if (envPlatform.equals("ios")){
                    app = new File(classpathRoot, "app/iOS/XamarinEvolveiOS.ipa");
                }

                // Use .app for simulator
                if (envPlatform.equals("ios-simulator")) {
                    app = new File(classpathRoot, "app/iOS/XamarinEvolveiOS.app");
                }

                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability("autoAcceptAlerts",true);

                // UDID only used for physical device
                if (envPlatform.equals("ios")) {
                    capabilities.setCapability(MobileCapabilityType.UDID, "auto");
                }

                iOSDriver = Factory.createIOSDriver(driverUrl, capabilities);

                iOSDriver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT, TimeUnit.SECONDS);
                waitDriver = new WebDriverWait(iOSDriver, LONG_TIMEOUT);

                break;
        }
    }

    public static void stopApp() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }

        if (iOSDriver != null) {
            iOSDriver.quit();
            iOSDriver = null;
        }

        waitDriver = null;
    }

    public static void label(String label) {
        if (platform == Platform.ANDROID && androidDriver != null) {
            androidDriver.label(label);
            return;
        }

        if (platform == Platform.IOS && iOSDriver != null) {
            iOSDriver.label(label);
            return;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using label");
    }
}
