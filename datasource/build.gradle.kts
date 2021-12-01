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
    compileOptions {
        sourceCompatibility = '1.8'
        targetCompatibility = '1.8'
    }


}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Support
    implementation(Depends.Support.getSupportV7Version())
    implementation(Depends.Support.getSupportConstraintLayoutVersion())

    //Kotlin
    implementation(Depends.Kotlin.getKotlinStdlibVersion())
    implementation(Depends.Kotlin.getCoreKtxVersion())

    // Test
    implementation(Depends.Test.getJunitVersion())
    implementation(Depends.Test.getRunnerVersion())
    implementation(Depends.Test.getEspressoVersion())

    // Dagger
    implementation(Depends.Dagger.getDaggerCore())
    kapt(Depends.Dagger.getDaggerCompiler())
    // Dagger Android
    implementation(Depends.Dagger.getDaggerAndroid())
    implementation(Depends.Dagger.getDaggerSupport())
    kapt(Depends.Dagger.getDaggerProcessor())

    // Retrofit
    implementation(Depends.Retrofit.getRetrofitcoreVersion())
    implementation(Depends.Retrofit.getConverterGsonVersion())

    //OkHttp3
    implementation(Depends.OkHttp3.getCoreVersion())
    implementation(Depends.OkHttp3.getInterceptorVersion())

    //Room
    implementation(Depends.Room.getRoomVersion())
    kapt(Depends.Room.getRoomCompilerVersion())

    //Coroutines
    implementation(Depends.Coroutines.getCoreCoroutinesVersion())
}
//repositories {
//    mavenCentral()
//}
