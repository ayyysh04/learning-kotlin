#!/bin/bash
#Check if device locked
if adb shell dumpsys window | grep  "screenState=SCREEN_STATE_OFF"; then 
    echo "Locked"
    adb shell input keyevent KEYCODE_WAKEUP # activate 
    sleep 0.5
    adb shell input touchscreen swipe 530 500 530 0 # swipe up
    adb shell input text 3132 # <Change to the device password> input password 
    #adb shell input keyevent 66 # press enter, if you keyguard requires it
else 
    echo "UnLocked"  
fi

# 2 = Stay awake on USB, 0 = reset
# adb shell settings put global stay_on_while_plugged_in 2