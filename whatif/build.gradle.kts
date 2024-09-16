plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.component.whatif"
}

dependencies {
  implementation(libs.androidx.appcompat)
  testImplementation(libs.junit)
  testImplementation(libs.robolectric)
}

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("mavenAndroid") {
        from(components["release"])
        groupId = "com.component.whatif"
        artifactId = "whatif"
        version = "1.0.0"
      }
    }
  }
}

