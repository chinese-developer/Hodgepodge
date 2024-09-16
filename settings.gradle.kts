@file:Suppress("UnstableApiUsage")

// 在没有 TYPESAFE_PROJECT_ACCESSORS 时，你引用模块的方式是：
// implementation(project(":library"))
// 启用 TYPESAFE_PROJECT_ACCESSORS 后，引用模块的方式变为：
// implementation(projects.library)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()

    // fetch dagger plugin only
    mavenCentral() {
      content {
        includeGroup("com.google.dagger")
        includeGroup("com.google.dagger.hilt.android")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch plugins from gradle plugin portal (https://plugins.gradle.org)
    gradlePluginPortal()

    // fetch snapshot plugins from sonatype
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
      mavenContent {
        snapshotsOnly()
      }
    }

    // JitPack Repository
    maven {
      url = uri("https://jitpack.io")
    }
  }
}

plugins {
  id("com.android.settings") version "8.6.0"
}

android {
  minSdk = 23
  compileSdk = 34
}

dependencyResolutionManagement {
  /**
   * 1、PREFER_PROJECT：项目中的仓库定义优先。
   * 2、FAIL_ON_PROJECT_REPOS：项目中定义仓库将导致构建失败。
   * 3、IGNORE_PROJECT：忽略项目中的仓库定义，只使用全局定义的仓库(Settings.gradle.kts)。
   */
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    // fetch libraries from google maven (https://maven.google.com)
    google() {
      content {
        // content{} 该块用于过滤要从这个仓库获取的库，使用正则表达式来获取仅包含某些特定的依赖项组。
        includeGroupByRegex("androidx\\..*") // 如: androidx.lifecycle
        includeGroupByRegex("com\\.android(\\..*|)") // 匹配 com.android 及其子包的库
        includeGroupByRegex("com\\.google\\.android\\..*") // 匹配以 com.google.android 开头的库
        includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
        includeGroupByRegex("com\\.google\\.gms(\\..*|)")
        includeGroupByRegex("com\\.google\\.mlkit")
        includeGroupByRegex("com\\.google\\.oboe")
        includeGroupByRegex("com\\.google\\.prefab")
        includeGroupByRegex("com\\.google\\.testing\\.platform")
      }
      mavenContent {
        // 仅从这个仓库中获取正式版本（releases），而不是快照（snapshots）
        releasesOnly()
      }
    }

    // fetch libraries from jcenter
    @Suppress("DEPRECATION")
    jcenter {
      content {
        // 只从 JCenter 获取 com.github.florent37 这个开发者发布的库
        includeGroupByRegex("com\\.github\\.florent37")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch libraries from maven central
    mavenCentral() {
      mavenContent {
        releasesOnly()
      }
    }

    // fetch snapshot libraries from sonatype
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
      mavenContent {
        snapshotsOnly()
      }
    }
  }
}

rootProject.name = "Hodgepodge"
include(":app")
include(":bindables")
include(":whatif")

