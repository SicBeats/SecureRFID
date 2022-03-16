# SecureRFID

An Android App meant to use NFC technology to communicate with and emulate RFID tags.
Built by Niklaas Cotta & Jordan Whiteley, for CIS 433 WI22.

A newer Android Phone with NFC is required for this to run.

Here are the steps to install:
1) Make sure that the NFC option on your phone is on. You can do so by swiping down from the top of the phone twice. If you do not see the option there, swipe right once or twice and NFC should appear.
2) Make sure that your Android is on developer mode.
   a) Go to "Settings"
   b) Go to "About Device"/"About Phone"
   c) Scroll down and tap "Build Number"
      If you do not see build number on this screen, select "Software Information" and follow step (c)
   d) Enter you password to enable Developer options menu
2) From the Developer options menu (in "Settings"), scroll down until you see "USB Debugging" and toggle it on
3) Now connect your phone to your computer via USB or similar
4) Download and install Android Studio
5) Once downloaded, open up Android Studio
6) Select "File", then "Open...". Then select the SecureRFID folder wherever you downloaded it
7) Once the project is open and your phone plugged in, make sure that your device is selected in the top toolbar, next to the "Run app" and "Make project"
8) Click on "Run app"
9) The program will take a minute to build and install, then the application should open automatically on your phone
10) Tap on the "RFID Scan (OFF)" button to turn on RFID scanning
11) Tap your credit card to the back of your phone. It may take two or three taps to show all information
12) The app will output some information about your card. This information is not saved to the app in any way :)
