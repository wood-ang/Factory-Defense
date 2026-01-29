/*
 * 版权所有 2020 damios
 *
 * 根据 Apache 许可证 2.0 版本（"许可证"）授权；
 * 除非遵守许可证，否则不得使用此文件。
 * 您可以在以下网址获取许可证副本：
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * 除非适用法律要求或书面同意，否则按"原样"分发软件，
 * 没有任何明示或暗示的担保或条件。
 * 请参阅许可证了解特定语言下的权限和限制。
 */
//注意：上述许可证和版权声明仅适用于此文件。

package com.wood.FactoryDefense.lwjgl3;

import com.badlogic.gdx.Version;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import org.lwjgl.system.macosx.LibC;
import org.lwjgl.system.macosx.ObjCRuntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import static org.lwjgl.system.JNI.invokePPP;
import static org.lwjgl.system.JNI.invokePPZ;
import static org.lwjgl.system.macosx.ObjCRuntime.objc_getClass;
import static org.lwjgl.system.macosx.ObjCRuntime.sel_getUid;

/**
 * 提供一些实用工具，确保 JVM 是以 {@code -XstartOnFirstThread} 参数启动的，
 * 这在 macOS 上是 LWJGL 3 正常工作的必要条件。同时帮助解决 Windows 上
 * 用户名称包含非拉丁字母字符时的常见启动崩溃问题。
 * <br>
 * <a href="https://jvm-gaming.org/t/starting-jvm-on-mac-with-xstartonfirstthread-programmatically/57547">基于 kappa 在 java-gaming.org 上的这篇帖子</a>
 * @author damios
 */
public class StartupHelper {

    private static final String JVM_RESTARTED_ARG = "jvmIsRestarted";

    private StartupHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * 如果应用程序在 macOS 上启动时没有使用 {@code -XstartOnFirstThread} 参数，
     * 则启动一个新的 JVM。这还包括一些针对 Windows 的代码，用于处理用户主目录
     * 包含某些非拉丁字母字符的情况（没有此代码，大多数 LWJGL3 应用会为这些用户
     * 立即失败）。返回是否启动了新的 JVM，从而指示是否不应在此 JVM 中执行代码。
     * <p>
     * <u>用法：</u>
     *
     * <pre><code>
     * public static void main(String... args) {
     *     if (StartupHelper.startNewJvmIfRequired(true)) return; // 这处理 macOS 支持并帮助解决 Windows 问题。
     *     // 之后是实际的主方法代码
     * }
     * </code></pre>
     *
     * @param redirectOutput
     *            是否应将新 JVM 的输出重定向到旧 JVM，以便可以在同一位置访问；
     *            如果启用，则保持旧 JVM 运行
     * @return 是否启动了新的 JVM，从而指示是否不应在此 JVM 中执行代码
     */
    public static boolean startNewJvmIfRequired(boolean redirectOutput) {
        String osName = System.getProperty("os.name").toLowerCase(java.util.Locale.ROOT);
        if (!osName.contains("mac")) {
            if (osName.contains("windows")) {
// 这里我们尝试解决 LWJGL3 加载其提取的 .dll 文件时的问题。
// 默认情况下，LWJGL3 提取到由 "java.io.tmpdir" 指定的目录，通常是用户的主目录。
// 如果用户的名称包含非 ASCII（或某些非字母数字）字符，则会失败。
// 通过提取到相关的 "ProgramData" 文件夹（通常是 "C:\ProgramData"），我们避免了此问题。
// 我们还将 "user.name" 属性临时更改为一个不包含任何无效字符的名称。
// 在加载 LWJGL3 原生库后，我们立即恢复我们的更改。
                String programData = System.getenv("ProgramData");
                if(programData == null) programData = "C:\\Temp\\"; // 如果未设置 ProgramData，尝试一些后备方案。
                String prevTmpDir = System.getProperty("java.io.tmpdir", programData);
                String prevUser = System.getProperty("user.name", "libGDX_User");
                System.setProperty("java.io.tmpdir", programData + "/libGDX-temp");
                System.setProperty("user.name", ("User_" + prevUser.hashCode() + "_GDX" + Version.VERSION).replace('.', '_'));
                Lwjgl3NativesLoader.load();
                System.setProperty("java.io.tmpdir", prevTmpDir);
                System.setProperty("user.name", prevUser);
            }
            return false;
        }

        // 在 Graal 原生镜像上不需要 -XstartOnFirstThread
        if (!System.getProperty("org.graalvm.nativeimage.imagecode", "").isEmpty()) {
            return false;
        }

        // 检查我们是否已经在主线程上，例如通过 Construo 运行。
        long objc_msgSend = ObjCRuntime.getLibrary().getFunctionAddress("objc_msgSend");
        long NSThread      = objc_getClass("NSThread");
        long currentThread = invokePPP(NSThread, sel_getUid("currentThread"), objc_msgSend);
        boolean isMainThread = invokePPZ(currentThread, sel_getUid("isMainThread"), objc_msgSend);
        if(isMainThread) return false;

        long pid = LibC.getpid();

        // 检查是否启用了 -XstartOnFirstThread
        if ("1".equals(System.getenv("JAVA_STARTED_ON_FIRST_THREAD_" + pid))) {
            return false;
        }

        // 检查 JVM 是否之前已重新启动
        // 避免循环，但几乎肯定会导致崩溃
        if ("true".equals(System.getProperty(JVM_RESTARTED_ARG))) {
            System.err.println(
                "评估 JVM 是否以 -XstartOnFirstThread 参数启动时出现问题。");
            return false;
        }

        // 使用 -XstartOnFirstThread 重新启动 JVM
        ArrayList<String> jvmArgs = new ArrayList<>();
        String separator = System.getProperty("file.separator", "/");
        // 以下行假设您针对 Java 8（LWJGL3 的最低要求）。
        String javaExecPath = System.getProperty("java.home") + separator + "bin" + separator + "java";
        // 如果针对 Java 9 或更高版本，您可以使用以下行代替上面的行：
        //String javaExecPath = ProcessHandle.current().info().command().orElseThrow();

        if (!(new File(javaExecPath)).exists()) {
            System.err.println(
                "找不到 Java 安装。如果您使用捆绑的 JRE 分发此应用，请确保手动设置 -XstartOnFirstThread 参数！");
            return false;
        }

        jvmArgs.add(javaExecPath);
        jvmArgs.add("-XstartOnFirstThread");
        jvmArgs.add("-D" + JVM_RESTARTED_ARG + "=true");
        jvmArgs.addAll(ManagementFactory.getRuntimeMXBean().getInputArguments());
        jvmArgs.add("-cp");
        jvmArgs.add(System.getProperty("java.class.path"));
        String mainClass = System.getenv("JAVA_MAIN_CLASS_" + pid);
        if (mainClass == null) {
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            if (trace.length > 0) {
                mainClass = trace[trace.length - 1].getClassName();
            } else {
                System.err.println("无法确定主类。");
                return false;
            }
        }
        jvmArgs.add(mainClass);

        try {
            if (!redirectOutput) {
                ProcessBuilder processBuilder = new ProcessBuilder(jvmArgs);
                processBuilder.start();
            } else {
                Process process = (new ProcessBuilder(jvmArgs))
                    .redirectErrorStream(true).start();
                BufferedReader processOutput = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
                String line;

                while ((line = processOutput.readLine()) != null) {
                    System.out.println(line);
                }

                process.waitFor();
            }
        } catch (Exception e) {
            System.err.println("重新启动 JVM 时出现问题");
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 如果应用程序在 macOS 上启动时没有使用 {@code -XstartOnFirstThread} 参数，
     * 则启动一个新的 JVM。返回是否启动了新的 JVM，从而指示是否不应在此 JVM 中执行代码。
     * 将新 JVM 的输出重定向到旧 JVM。
     * <p>
     * <u>用法：</u>
     *
     * <pre>
     * public static void main(String... args) {
     *     if (StartupHelper.startNewJvmIfRequired()) return; // 这处理 macOS 支持并帮助解决 Windows 问题。
     *     // 实际的主方法代码
     * }
     * </pre>
     *
     * @return 是否启动了新的 JVM，从而指示是否不应在此 JVM 中执行代码
     */
    public static boolean startNewJvmIfRequired() {
        return startNewJvmIfRequired(true);
    }
}
