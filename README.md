#Appium Java Cross Platform Tests#

**Note:** This serves as an in-progress example for writing cross-platform (Android/iOS) Appium Java tests, for use in Xamarin Test Cloud. As features become available, this sample will change.

###Requirements###
+ [Appium 1.6.3](https://www.npmjs.com/package/appium) (`npm install -g appium@1.6.3`)
  + I recommend using appium via node module instead of appium.app. This allows the appium server to be started by simply running `appium`
  + However, feel free to use what you are comfortable with
+ Xcode 8
+ IntelliJ
+ Maven: `brew install maven` if you do not have it installed already
+ Additional Setup to run on iOS Devices can be found [here] (http://appium.io/slate/en/master/?ruby#appium-on-real-ios-devices).

###Running the tests locally###

####1. Prepare the Appium test server####
+ Run `appium-doctor` or select the Button in Appium.app. Ensure that all checks pass
+ Open up Appium.app, select the appropriate platform settings
+ Set Appium's main settings to:
    + Server Address: 127.0.0.1:4723
    + Check For Updates: true
    + Prelaunch Application: true
    + Override Existing Sessions: true
    + New Command Timeout: 7,200s
+ Go to Appium's iOS settings under advanced and ensure Xcode Path is set to Xcode7.app
+ Launch the test server

####2a. Running tests from command line####
+ Run `mvn test -P [platform]` from the project directory
    + `[platform]` should be either `android`, `ios`, or `ios-simulator` depending on which platform you want to run your tests on

####2b. Running tests from IntelliJ####
+ Open the "Maven Projects" tool window
+ Under "Profiles" choose the platfrom you wish to tests on (making sure only one is checked)
    + Should be either `android`, `ios`, or `ios-simulator`
+ Right click on the test(s) that you wish to run and choose "Run"

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
* No support for Android 4.2 or prior
* Maven version must be atleast 3.3.9
* Support for Appium version 1.6 only 
* JUnit 4.9 or newer 
* Automating browsers (web testing) is not supported.
* Tests that launch multiple apps or no apps are not currently supported. The test must launch precisely one app.
