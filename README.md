# FactoryDefense

开源流水线游戏 使用 [gdx-liftoff](https://github.com/libgdx/gdx-liftoff) 生成的 [libGDX](https://libgdx.com/) 项目。

## 平台

- `core`: 主模块，包含所有平台共享的应用程序逻辑。
- `lwjgl3`: 主要的桌面平台，使用LWJGL3；在旧文档中称为'desktop'。
- `android`: Android移动平台。需要Android SDK。

## Gradle

此项目使用 [Gradle](https://gradle.org/) 来管理依赖项。
包含了Gradle包装器，因此您可以使用`gradlew.bat`或`./gradlew`命令运行Gradle任务。
有用的Gradle任务和标志：

- `--continue`: 使用此标志时，错误不会阻止任务运行。
- `--daemon`: 多亏此标志，Gradle守护进程将用于运行选定的任务。
- `--offline`: 使用此标志时，将使用缓存的依赖项存档。
- `--refresh-dependencies`: 此标志强制验证所有依赖项。对于快照版本很有用。
- `android:lint`: 执行Android项目验证。
- `build`: 构建每个项目的源代码和存档。
- `cleanEclipse`: 移除Eclipse项目数据。
- `cleanIdea`: 移除IntelliJ项目数据。
- `clean`: 移除`build`文件夹，该文件夹存储编译的类和构建的存档。
- `eclipse`: 生成Eclipse项目数据。
- `idea`: 生成IntelliJ项目数据。
- `lwjgl3:jar`: 构建应用程序的可运行jar，可在`lwjgl3/build/libs`找到。
- `lwjgl3:run`: 启动应用程序。
- `test`: 运行单元测试（如果有）。

请注意，大多数不特定于单个项目的任务都可以使用`name:`前缀运行，其中`name`应替换为特定项目的ID。
例如，`core:clean`仅从`core`项目中移除`build`文件夹。

## HOW TO RUN
- Windows 终端输入
```shell
  .\gradlew.bat lwjgl3:run
```

- liunx 终端输入
```shell
  ./gradlew lwjgl3:run 
```
