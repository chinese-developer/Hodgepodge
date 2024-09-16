@file:Suppress("SpellCheckingInspection")

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  // alias：通过libs.plugins引用外部定义的插件（一般在libs.versions.toml中定义），以简化插件的引入
  alias(libs.plugins.android.application) apply false // apply false：表示此插件在当前项目中未被应用，但可以在子项目中使用。
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.test) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.kapt) apply false
  alias(libs.plugins.kotlin.parcelize) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.hilt.plugin) apply false
  alias(libs.plugins.spotless) // 应用代码格式化工具Spotless，用于自动化代码样式检查和修复。
}

// typealias：为CommonExtension定义了一个简化的别名AndroidExtension，该类通常用于Android构建配置
private typealias AndroidExtension = com.android.build.api.dsl.CommonExtension<*, *, *, *, *, *>

// androidExtension: 通过Project扩展属性获取CommonExtension，为后续简化Android项目配置提供方便。
private val Project.androidExtension: AndroidExtension
  get() = extensions.getByType(com.android.build.api.dsl.CommonExtension::class.java)

// android：这是一个扩展函数，允许项目以DSL方式配置Android相关的构建选项。它在确认应用了Android插件后，执行对androidExtension的配置
private fun Project.android(block: AndroidExtension.() -> Unit) {
  plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
    androidExtension.block()
  }
}

// targetSdkVersion：通过libs.versions文件中的配置获取目标SDK版本，用于后续Android配置。
private val targetSdkVersion = libs.versions.targetSdk.get().toInt()
// bytecodeVersion：指定Kotlin编译时目标的字节码版本，确保应用程序使用的Java字节码版本一致。
private val bytecodeVersion = JavaVersion.toVersion(libs.versions.jvmBytecode.get())

// subprojects：此块应用于所有子项目，确保每个子项目都应用Spotless插件，用于统一代码格式。
subprojects {
  apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

  // Common Android configurations
  android {
    defaultConfig {
      // 设置vectorDrawables.useSupportLibrary为true，强制在低版本设备上使用向量图支持库。
      vectorDrawables.useSupportLibrary = true
    }

    // 设置Java源码和目标兼容性，确保与指定字节码版本一致。
    compileOptions {
      sourceCompatibility = bytecodeVersion
      targetCompatibility = bytecodeVersion
    }

    // 配置Lint工具，当发现错误时不终止构建（abortOnError = false）。
    lint {
      abortOnError = false
    }
  }

  // AppPlugin: 为应用插件(com.android.application)配置ApplicationExtension，并设置目标SDK版本。
  plugins.withType<com.android.build.gradle.AppPlugin>().configureEach {
    extensions.configure<com.android.build.api.dsl.ApplicationExtension> {
      defaultConfig {
        targetSdk = targetSdkVersion
      }
    }
  }

  // TestPlugin: Configurations for `com.android.test` plugin
  plugins.withType<com.android.build.gradle.TestPlugin>().configureEach {
    extensions.configure<com.android.build.api.dsl.TestExtension> {
      defaultConfig {
        targetSdk = targetSdkVersion
      }
    }
  }

  // 配置非app模块启用一些实验性功能（如ExperimentalContracts），避免代码中会有警告提示。
  if (name != "app") {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions.jvmTarget = bytecodeVersion.toString()
      kotlinOptions.freeCompilerArgs += listOf(
        "-Xexplicit-api=strict",
        "-Xopt-in=kotlin.contracts.ExperimentalContracts"
      )
    }
  }

  // 为Kotlin编译任务设置JVM目标版本，并启用一些实验性功能（如Coroutines和Time API）
  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = bytecodeVersion.toString()
    kotlinOptions.freeCompilerArgs += listOf(
      "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
      "-opt-in=kotlin.time.ExperimentalTime",
    )
  }

  /**
   * 1、Spotless配置：设置代码格式化规则，包括Kotlin、KTS脚本和XML文件的格式化规则。
   * 2、ktlint：用于检查Kotlin代码的风格和格式，覆盖默认的缩进大小
   * 3、licenseHeaderFile：为代码文件添加许可证头文件。
   */
  extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    val buildDirectory = layout.buildDirectory.asFileTree
    kotlin {
      target("**/*.kt")
      targetExclude(buildDirectory)
      ktlint().editorConfigOverride(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2"
        )
      )
      licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"))
      trimTrailingWhitespace()
      endWithNewline()
    }
    format("kts") {
      target("**/*.kts")
      targetExclude(buildDirectory)
      licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"), "(^(?![\\/ ]\\*).*$)")
    }
    format("xml") {
      target("**/*.xml")
      targetExclude(buildDirectory)
      licenseHeaderFile(rootProject.file("spotless/spotless.license.xml"), "(<[^!?])")
    }
  }
}


