plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion(Config.buildToolsVersion)

    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Depends.Android.runnerPackage

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Modules
    Depends.Module.implementations.forEach { implementation(project(it)) }

    //Kotlin
    implementation(Depends.Kotlin.getKotlinStdlibVersion())
    implementation(Depends.Kotlin.getCoreKtxVersion())

    //Support
    implementation(Depends.Support.getSupportV7Version())
    implementation(Depends.Support.getSupportConstraintLayoutVersion())

    // Test
    implementation(Depends.Test.getJunitVersion())
    implementation(Depends.Test.getRunnerVersion())
    implementation(Depends.Test.getEspressoVersion())

    // Navigation
    implementation(Depends.Navigation.getNavigationFragmentVersion())
    implementation(Depends.Navigation.getNavigationUiVersion())

//    implementation 'org.koin:koin-android:2.0.0-GA4'

    // Dagger
    implementation(Depends.Dagger.getDaggerCore())
    kapt(Depends.Dagger.getDaggerCompiler())
    // Dagger Android
    implementation(Depends.Dagger.getDaggerAndroid())
    implementation(Depends.Dagger.getDaggerSupport())
    kapt(Depends.Dagger.getDaggerProcessor())

}
