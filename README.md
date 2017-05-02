# Appium Java Cross-Platform Tests

**Note:** This is an in-progress example of cross-platform (Android/iOS) Appium Java tests and how to run them in Xamarin Test Cloud. As features become available, this sample will change.

## Requirements

+ Appium server
    + [Appium 1.6](https://www.npmjs.com/package/appium)
    + and/or
    + [Appium desktop app](http://appium.io/downloads.html)
+ Xcode 8
+ IntelliJ
+ [Maven](https://maven.apache.org/index.html) (on macOS you can also easily install it with `brew install maven`)
+ Additional Setup to run on iOS Devices can be found [here](http://appium.io/slate/en/master/?ruby#appium-on-real-ios-devices).

## Setting up the test server

### From command line

+ Run `appium-doctor` on command line. Ensure that all checks pass.
+ Run `appium` to start the server

### From Appium desktop app

+ Open app
+ Click start server button

## Running the tests locally

### From command line

+ Run `mvn test -P [platform]` from the project directory
    + `[platform]` should be either `android`, `ios`, or `ios-simulator` depending on which platform you want to run your tests on

### From IntelliJ
+ Open the "Maven Projects" tool window
+ Under "Profiles" choose the platform you wish to test on (making sure only one is checked)
    + Should be either `android`, `ios`, or `ios-simulator`
+ Right click on the test(s) that you wish to run and choose "Run"

## Uploading the tests to Xamarin Test Cloud

+ Follow [these instructions](https://docs.microsoft.com/en-us/mobile-center/test-cloud/preparing-for-upload/appium) to prepare your tests for Test Cloud.
+ Go to [Test Cloud](https://testcloud.xamarin.com/) and start a new test run
+ Go through the wizard and then select Appium on the last step
+ Follow the provided instructions to install the CLI and uplaod your tests
