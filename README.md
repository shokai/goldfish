GoldFish
========

Clone this Repository
---------------------

    % git clone git://github.com/shokai/goldfish2.git

System Requirements
-------------------

* Android 2.3.1+
* NFC


Build and Install Android
=========================

With ECLIPSE
------------

Import "android" directory into your eclipse workspace and build it.


With AndroidSDK
----------------

    % cd android
    % android update project --path `pwd`
    % ant debug
    % adb install -r bin/GoldFish.apk



run Mac client
--------------

    % ruby client_mac/goldfish_clipboard_client.rb --help
    % ruby client_mac/goldfish_clipboard_client.rb -tag 011a0005150dc715
