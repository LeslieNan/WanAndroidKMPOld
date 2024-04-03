import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.animation)
            implementation(libs.precompose)
            implementation(libs.precompose.viewModel)
            implementation(libs.precompose.koin)
            implementation(libs.precompose.molecule)
            implementation(libs.koin.core)
//            implementation(libs.koin.test)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.kotlinx.coroutines.core)
//            implementation("androidx.paging:paging-runtime:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common-ktx:3.3.0-alpha02")
//            implementation("androidx.paging:paging-runtime-ktx:3.3.0-alpha02")
            implementation("app.cash.paging:paging-compose-common:3.3.0-alpha02-0.4.0")
        }
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.koin.android)
//            implementation("androidx.paging:paging-common-android:3.3.0-alpha02")
//            implementation("androidx.paging:paging-compose-android:3.3.0-alpha02")
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
//            implementation("androidx.paging:paging-common-iosarm64:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common-iossimulatorarm64:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common-iosx64:3.3.0-alpha02")
        }
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.cio)
            implementation(libs.kotlinx.coroutines.core.jvm)
//            implementation(libs.androidx.paging.common.jvm)
            implementation("ch.qos.logback:logback-classic:1.5.3")
//            implementation("androidx.paging:paging-common-jvm:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common-linuxx64:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common-macosarm64:3.3.0-alpha02")
//            implementation("androidx.paging:paging-common-macosx64:3.3.0-alpha02")
        }
    }
}

//dependencies {
//    add("kspCommonMainMetadata", project(":test-processor"))
//    add("kspJvm", project(":test-processor"))
//    add("kspJvmTest", project(":test-processor")) // Not doing anything because there's no test source set for JVM
//    // There is no processing for the Linux x64 main source set, because kspLinuxX64 isn't specified
//    add("kspLinuxX64Test", project(":test-processor"))
//}

android {
    namespace = "org.leslie.wanandroidkmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.leslie.wanandroidkmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.leslie.wanandroidkmp"
            packageVersion = "1.0.0"
        }
    }
}
