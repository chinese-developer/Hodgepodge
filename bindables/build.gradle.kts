@file:Suppress("SpellCheckingInspection")

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.dokka)
  alias(libs.plugins.kotlin.binary.compatibility)
}


android {
  namespace = "com.component.bindables"

  defaultConfig {
    consumerProguardFiles("proguard-rules.pro")
  }

  buildFeatures {
    dataBinding = true
  }
}

apiValidation {
  // com/component/bindables/databinding 包被忽略，表示这个包不会暴露给公共 API。
  ignoredPackages.add("com.component.bindables.databinding")
  // kotlin.PublishedApi 通常用于暴露 internal API，以便其他模块或包可以使用它，但你不希望这些 API 出现在公共 API 文档中
  nonPublicMarkers.add("kotlin.PublishedApi")
}

dependencies {
  implementation(libs.material)
  implementation(libs.kotlin.reflect)
  implementation(libs.androidx.appcompat)
  api(libs.androidx.lifecycle)
  api(libs.androidx.lifecycle.savedstate)
}