@echo off
::设置要临时加入到path环境变量中的路径
set CLASS_PATH=%CLASS_PATH%;D:\Program Files\antlr-4.6-complete\antlr-4.6-complete.jar
java org.antlr.v4.gui.TestRig %*