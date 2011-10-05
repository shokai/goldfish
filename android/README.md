Gynamic Android App
===================

System Requirements
-------------------

* Android 2.3.1+
* NFC


Build and Install Android App
=============================

With Eclipse
------------

Import "android" directory into your eclipse workspace and build it.


With AndroidSDK
----------------

    % cd android
    % android update project --path `pwd` --name 'gynamic'
    % ant debug
    % adb install -r bin/gynamic-debug.apk

