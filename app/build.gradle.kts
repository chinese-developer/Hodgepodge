@file:Suppress("UnstableApiUsage")

import com.mvvm.core.Configuration
import com.android.build.gradle.internal.tasks.databinding.DataBindingGenBaseClassesTask
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.hilt.plugin)
}


android {
  // namespace：用于定义项目的命名空间。在 Android 项目中，namespace 影响生成的 R 类的包名，用于解决资源冲突和定位类。
  namespace = "com.aiden.hodgepodge"

  defaultConfig {
    // 指定应用的包名，它在整个项目中是唯一的。应用发布时，Google Play 商店将根据这个包名识别你的应用。
    applicationId = "com.aiden.hodgepodge"
    // 应用的版本代码，通常用于跟踪应用的更新。每次发布新的版本时，versionCode 都应该递增。
    versionCode = Configuration.versionCode
    // 应用的版本名称，通常是一个可读的版本号（如 "1.0.0"），用户可以在应用商店中看到这个值。
    versionName = Configuration.versionName
    // 指定用于运行仪器化测试的自定义测试运行器，AppTestRunner 类是自定义的测试环境配置。
    testInstrumentationRunner = "com.aiden.hodgepodge.AppTestRunner"
  }

  buildFeatures {
    // 启用 Android 的数据绑定功能，可以在 XML 布局中直接绑定数据到视图，从而减少手动绑定代码。
    dataBinding = true
    // 启用 BuildConfig 类生成，BuildConfig 类包含编译时的一些常量，如 DEBUG 标志等。
    buildConfig = true
  }

  // 配置 Dagger Hilt 依赖注入框架，需配置(dependencies hilt 依赖项)
  hilt {
    // 启用聚合任务，这可以提高编译性能，特别是在 Hilt 生成代码时。
    enableAggregatingTask = true
  }

  kotlin {
    // 为每个源代码集添加新的 Kotlin 源目录，KSP (Kotlin Symbol Processing) 的生成代码将被放置在该目录下。
    sourceSets.configureEach {
      kotlin.srcDir(layout.buildDirectory.files("generated/ksp/$name/kotlin/"))
    }
    // 设置 Kotlin 的语言版本，这里指定为 2.0
    sourceSets.all {
      languageSettings {
        languageVersion = "2.0"
      }
    }
  }

  testOptions {
    // 配置单元测试的选项。
    unitTests {
      // 使单元测试能够访问 Android 资源文件。
      isIncludeAndroidResources = true
      // 在单元测试中，当某些依赖缺失时返回默认值（例如未实现的接口）。
      isReturnDefaultValues = true
    }
  }

  buildTypes {
    // 创建一个新的构建类型 "mvvm"。它用于性能测试，通常启用调试功能。
    create("mvvm") {
      // 允许调试此构建类型。
      isDebuggable = true
      // 使用 debug 构建类型的签名配置
      signingConfig = getByName("debug").signingConfig
      // 在没有 "mvvm" 构建类型的情况下，回退到 release 类型的构建配置
      matchingFallbacks += listOf("release")
    }
  }
}

// 这是 Android Gradle 插件 7.0 引入的新 API，用于更灵活地管理构建过程中的变体（variants）。
androidComponents {
  // 为所有构建变体执行自定义逻辑。
  onVariants(selector().all()) { variant ->
    // 在项目评估完成后执行逻辑。
    afterEvaluate {
      // dataBindingTask: 查找生成数据绑定基类的任务，并将其输出目录添加到 Kotlin 编译任务的源目录中。
      val dataBindingTask =
        project.tasks.findByName("dataBindingGenBaseClasses" + variant.name.capitalized()) as? DataBindingGenBaseClassesTask
      if (dataBindingTask != null) {
        project.tasks.getByName("ksp" + variant.name.capitalized() + "Kotlin") {
          (this as AbstractKotlinCompileTool<*>).setSource(dataBindingTask.sourceOutFolder)
        }
      }
    }
  }
}

dependencies {

  // modules
  implementation(projects.bindables)
  implementation(projects.whatif)

  // androidx
  implementation(libs.material)
  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle)
  implementation(libs.androidx.startup) // https://developer.android.com/topic/libraries/app-startup

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  androidTestImplementation(libs.hilt.testing)
  kspAndroidTest(libs.hilt.compiler)

  // coroutines
  implementation(libs.coroutines)
}