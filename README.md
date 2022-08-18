
## 技术交流群773699239
# 项目工程说明

clean架构

MVVM+jectpack+dagegr2+Rerotfit+gilde+rxjava2

ROOM和双向绑定还没封装，持续更新

后期更新MVVM+hit+协程，记得start关注一下

- app_test

  **引入新功能后、要在此处写测试！！！！！！！！！**

- just

  just项目

- libarch

  架构包，常用工具类等，上个版本遗留下来的
  
- libview

  UI库包
  1. 引入的三方，能用源码就用源码！
  2. 自己写的UI库

```

                             app项目
                                |                         
                            ---------
                            |        |
                     libview基类等  其他的
                            |        |
                            ---------
                                |
                         libarch架构封装

```

Android-CleanArchitecture 
=========================

## New version available written in Kotlin:
[Architecting Android… Reloaded](https://github.com/Primary-hacker1/JustSafe/tree/master/justsy)

Introduction
-----------------
This is a sample app that is part of a blog post I have written about how to architect android application using the Uncle Bob's clean architecture approach. 

[Architecting Android…The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

[Architecting Android…The evolution](http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/)

[Tasting Dagger 2 on Android](http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/)

[Clean Architecture…Dynamic Parameters in Use Cases](http://fernandocejas.com/2016/12/24/clean-architecture-dynamic-parameters-in-use-cases/)

[Demo video of this sample](http://youtu.be/XSjV4sG3ni0)

Clean architecture
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)

Architectural approach
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers.png)

Architectural reactive approach
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers_details.png)

Local Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check.
 * `./gradlew installDebug` - Install the debug apk on the current connected device.
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration).
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests.
 
Discussions
-----------------

Refer to the issues section: https://github.com/android10/Android-CleanArchitecture/issues
 

Code style
-----------

Here you can download and install the java codestyle.
https://github.com/android10/java-code-styles


License
--------

    Copyright 2018 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


![http://www.fernandocejas.com](https://github.com/android10/Sample-Data/blob/master/android10/android10_logo_big.png)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--CleanArchitecture-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/909)

<a href="https://www.buymeacoffee.com/android10" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>


  
# Git提交说明

当前分支有三只：master、main两个分支

默认推送分支：master

命令终端：

```
git push origin origin/main

git pull origin main
```

稳定版本分支：master


master稳定分支说明：

```
在当前dev分支发布版本时候，同步代码到master稳定分支：
在当前dev分支发布版本时候，同步代码到master稳定分支，并添加版本号说明、版本相关内容。
ster稳定分支，并添加版本号说明、版本相关内容。
```

**提交信息模板**

```
add　by　名字
commit message：　内容
```

提交方式：每次完成一个功能或者修复一个bug，就要commit（方便查看任务状态、修复信息和过程）
         当天下班未完成的功能或者bug暂不commit，但是要Push当天已完成的任务
