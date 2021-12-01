plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Depends.Android.runnerPackage

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

    // Module
    implementation(project(Depends.Module.datasource))

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

    // Dagger
    implementation(Depends.Dagger.getDaggerCore())
    kapt(Depends.Dagger.getDaggerCompiler())
    // Dagger Android
    implementation(Depends.Dagger.getDaggerAndroid())
    implementation(Depends.Dagger.getDaggerSupport())
    kapt(Depends.Dagger.getDaggerProcessor())

    // ViewModel and LiveData
    implementation(Depends.LifeCycle.getExtensionsVersion())
    implementation(Depends.LifeCycle.getRuntimeVersion())

    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation(Depends.LifeCycle.getCommonJavaVersion())
    implementation(Depends.LifeCycle.getViewModelVersion())
}
//repositories {
//    mavenCentral()
//}
