import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    jvm()

    android {
        namespace = "com.m4ykey.text_markdown"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_25
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.markdown.renderer.m3)
            implementation(libs.markdown.renderer.code)
            implementation(libs.markdown.renderer.coil3)
            implementation(libs.markdown.renderer)
            implementation(libs.ksoup)
            implementation(libs.ksoup.entities)

            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.runtime)
        }
    }
}