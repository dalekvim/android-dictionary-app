# ==========================================
# ProGuard / R8 rules for DictionaryApp
# ==========================================
#
# This config preserves classes and metadata
# needed for:
# - AndroidX Compose
# - AndroidX lifecycle / activity libraries
# - Retrofit and Kotlin Serialization
# - Reflection-based operations
#
# For ProGuard documentation:
#   http://developer.android.com/guide/developing/tools/proguard.html
#

# ------------------------------------------
# Optional: keep line numbers for stack traces
# ------------------------------------------
-keepattributes SourceFile,LineNumberTable

# Optional: rename original source file attribute to hide file names
-renamesourcefileattribute SourceFile

# ------------------------------------------
# AndroidX - Compose and UI Libraries
# ------------------------------------------
-keep class androidx.compose.** { *; }
-keep class androidx.activity.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.savedstate.** { *; }
-keep class androidx.customview.** { *; }
#-keep class androidx.viewpager2.** { *; }
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.material.** { *; }

# ------------------------------------------
# Your App Classes
# ------------------------------------------

# Keep your own data models to avoid serialization issues.
-keep class com.example.dictionaryapp.ui.network.** { *; }

# ------------------------------------------
# Retrofit and Networking
# ------------------------------------------

# Keep Retrofit runtime classes
-keep class retrofit2.** { *; }
-keepclassmembers class retrofit2.** { *; }

# Keep Retrofit Call and Response generic signatures
# so Retrofit can parse API method return types correctly.
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# Keep Retrofit interfaces and their methods (annotations, etc.)
-keep interface com.example.dictionaryapp.network.DictionaryApiService
-keepclassmembers interface com.example.dictionaryapp.network.DictionaryApiService {
    *;
}

# Retrofit-Kotlinx Serialization Converter
-keep class com.jakewharton.retrofit2.converter.kotlinx.serialization.** { *; }

# ------------------------------------------
# Kotlin Serialization
# ------------------------------------------

# Keep all kotlinx.serialization classes and generated serializers
-keep class kotlinx.serialization.** { *; }

# Keep static serializer fields/methods in your classes
-keepclassmembers class ** {
    @kotlinx.serialization.Serializable <fields>;
}

# Keep generated serializers (e.g. Xxx$$serializer classes)
-keep class **$$serializer { *; }

# ------------------------------------------
# Preserve important metadata attributes
# ------------------------------------------

# Keep annotations used by serialization and libraries
-keepattributes *Annotation*

# Keep generic type info (needed for reflection, Retrofit, serialization)
-keepattributes Signature

# Keep inner class information (sometimes needed for serialization or UI binding)
-keepattributes InnerClasses, EnclosingMethod, MethodParameters

# Keep exception declarations in method signatures (generally harmless and helps debugging)
-keepattributes Exceptions

# ------------------------------------------
# Kotlin Coroutines
# ------------------------------------------

# R8 strips Continuation unless kept explicitly
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# ------------------------------------------
# R8 Optimization for Retrofit generic signatures
# ------------------------------------------

# R8 full mode strips generic signatures unless classes are kept.
# This rule helps preserve method return types for Retrofit interfaces
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# ------------------------------------------
# Suppress warnings for common harmless missing classes
# ------------------------------------------

-dontwarn kotlin.coroutines.**
-dontwarn okhttp3.**

# ------------------------------------------
# Optional: WebView JavaScript interface
# ------------------------------------------
# Uncomment and adjust if your app uses WebView JS interface:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
