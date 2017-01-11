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
public class AppInitializer {
    public static OS executionOS = OS.IOS;

    public enum OS {
        ANDROID,
        IOS
    }

    public static AppInitializer instance = new AppInitializer();

    private EnhancedAndroidDriver<MobileElement> androidDriver;
    private EnhancedIOSDriver<MobileElement> iOSDriver;


    public void startApp() throws MalformedURLException {
        if ((executionOS == OS.ANDROID && androidDriver != null) || (executionOS == OS.IOS && iOSDriver != null)) {
            return;
        }

        switch(executionOS){
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
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
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

    public AppiumDriver getDriver() throws Exception {
        if ((executionOS == OS.ANDROID && androidDriver == null) || (executionOS == OS.IOS && iOSDriver == null)) {
            throw new Exception("You must call AppInitializer.instance.startApp() before using the driver");
        }
        return executionOS == OS.ANDROID ? androidDriver : iOSDriver;
    }

    public void stopApp() {
        if ((executionOS == OS.ANDROID && androidDriver != null) || (executionOS == OS.IOS && iOSDriver != null)) {
            switch (executionOS) {
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

    public void screenshot(String label) {
        if (executionOS == OS.ANDROID) {
            androidDriver.label(label);
        } else {
            iOSDriver.label(label);
        }
    }
}
