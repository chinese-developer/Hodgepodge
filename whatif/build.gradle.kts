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