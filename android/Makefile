all:
	ant debug
setup:
	android update project --path `pwd` --name 'gynamic'
install:
	adb install -r bin/gynamic-debug.apk
uninstall:
	adb uninstall org.ubif.gynamic
debug:
	adb logcat | grep gynamic
clean:
	/bin/rm -r -f bin/classes
update:
	git update
push:
	git push

