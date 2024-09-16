
# Bindables proguard rules #

# noinspection ShrinkerUnresolvedReference

# data binding
-dontwarn androidx.databinding.**
-keep class androidx.databinding.** { *; }
-keep class * extends androidx.databinding.DataBinderMapper
-keep class ** { @androidx.databinding.Bindable <methods>; }
-keep class ** { @androidx.databinding.Bindable <fields>; }

# bindables
-dontwarn com.component.bindables.**
-dontwarn androidx.databinding.Bindable
-keep class com.component.bindables.** { *; }
-keep class ** extends com.component.bindables.**
-keep @androidx.databinding.Bindable interface *