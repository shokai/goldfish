all:
	ant debug
setup:
	android update project --path `pwd` --name 'goldfish'
install:
	adb install -r bin/goldfish-debug.apk
uninstall:
	adb uninstall org.ubif.goldfish
debug:
	adb logcat | grep goldfish
clean:
	/bin/rm -r -f bin/classes
update:
	git update
push:
	git push

