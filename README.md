#Appium Java Cross Platform Tests#

###Requirements###
+ [Appium 1.5.3](https://www.npmjs.com/package/appium) (`npm install -g appium@1.5.3`)
  + I recommend using appium via node module instead of appium.app. This allows the appium server to be started by simply running `appium`
  + However, feel free to use what you are comfortable with
+ Xcode 7: For help on installing side-by-side versions of Xcode, see section below
+ IntelliJ
+ Maven: `brew install maven` if you do not have it installed already

###Multiple Xcode Versions###
If you are trying to run iOS tests, a current limitation is that iOS 10 is not supported. Because of this, Xcode 8 is not supported with the compatible version of Appium. To get around this, you can install Xcode 7 along side Xcode 8.

+ Download Xcode 7 from [Apple](http://developer.apple.com/download/more) 
+ Copy Xcode.app to your desktop (just not /Applications/), and rename to Xcode7.app
+ Move Xcode7.app to /Applications/.
+ Open Xcode7 and accept the terms and conditions

You're all set to switch between Xcode versions! In order to select which Xcode command line tools are used (Appium needs to use Xcode 7 CLI), you will need to run the command:
``` bash
$ sudo xcode-select --switch /Applications/Xcode7.app
```
To see which verion of Xcode CLI are currently in use:
``` bash
$ xcode-select -p
```

###Running the tests locally###
+ Run `appium-doctor` or select the Button in Appium.app. Ensure that all checks pass
+ Open up Appium.app, select the appropriate platform settings
+ Set Appium's main settings to:
    + Server Address: 127.0.0.1:4723
    + Check For Updates: true
    + Prelaunch Application: true
    + Override Existing Sessions: true
    + New Command Timeout: 7,200s
+ Go to Appium's iOS settings under advanced and ensure Xcode Path is set to Xcode7.app
+ For testing on iOS, change the following UDID in AppInitializer value to your device:
``` java
capabilities.setCapability(MobileCapabilityType.UDID, "your-udid");
```
+ Tests can be run using the `mvn test` command from the project directory
+ To run iOS/Android tests, change the AppInitializer.executionOS field to your desired platform

###Running the tests in Xamarin Test Cloud###

If you have not done so already, install our command line interface by following [the installation instructions](UploaderInstall.md/#installation).

*Note: If you are an existing Test Cloud user currently using the command line tools for Calabash or UITest, you will need to install this new tool.*

If you do not have an existing device key ready, you can generate one by following the *new test run* dialog in [Test Cloud](https://testcloud.xamarin.com). On the final screen, extract only the device key from the generated command.

Steps to upload a test:

Pack your test classes and all dependencies into the `target/upload` folder:

```
mvn -DskipTests -P prepare-for-upload package
```

Perform upload:

```
xtc test /path/to/app <api-key> --devices <selection> --user <email> --workspace target/upload 
```
*Note: If you are having trouble targeting the `xtc` command, try executing with the fully qualified path to the package.*

*Note: For Android apps, ensure your app was not built with Instant Run enabled as this will cause failures in Test Cloud.*

###Limitations###
* No support for TestNG
* No support for iOS 10
* No support for Android 7.0 (Nougat)
* No support for Android 4.2 or prior
* Maven version must be atleast 3.3.9
* Support for Appium version 1.5 only 
* JUnit 4.9 or newer 
* Automating browsers (web testing) is not supported.
* Tests that launch multiple apps or no apps are not currently supported. The test must launch precisely one app.
* Performance data is not yet included in the test reports