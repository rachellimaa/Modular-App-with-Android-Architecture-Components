import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation
import com.android.build.gradle.BaseExtension

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(Depends.Android.gradle)
        classpath(Depends.Android.kotlinGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

    }

    configureAndroid()
}

fun Project.configureAppAndroid() {
    apply(plugin = "com.android.application")
}

fun Project.configureAndroidLibrary() {
    apply(plugin = "com.android.library")
//    apply(from = "$rootDir/jacoco.gradle")

    if (projectDir.name != "app" && projectDir.name != "core") {
        tasks.register("cleanLibsModule") {
            dependsOn("clean")
        }
        tasks.register("buildLibsModule") {
            dependsOn("assembleRelease")
        }
        tasks.register("publishLibsModule") {
            dependsOn("publish")
        }
        tasks.register("publishLibsModuleLocal") {
            dependsOn("publishToMavenLocal")
        }
    }

//    if (projectDir.name == "core") {
//        apply(from = "${rootDir}/jacocomerge.gradle")
//    }
}

fun Project.configureAndroid() {
    val isAppModule = name == "app"

    when {
        isAppModule -> configureAppAndroid()
        ModuleDependencies.modules.contains(name) -> configureAndroidLibrary()
        else -> return
    }

    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-android-extensions")
    apply(plugin = "kotlin-kapt")

    configure<BaseExtension> {
        compileSdkVersion(Config.compileSdkVersion)
        buildToolsVersion(Config.buildToolsVersion)

        defaultConfig {
            minSdkVersion(Config.minSdkVersion)
            targetSdkVersion(Config.targetSdkVersion)
            versionCode = Config.versionCode
            versionName = Config.versionName
            testInstrumentationRunner = Config.androidTestInstrumentation
            vectorDrawables.useSupportLibrary = true
            multiDexEnabled = true
        }

        lintOptions {
            isCheckReleaseBuilds = false
            isCheckDependencies = true
            isCheckAllWarnings = true
            isWarningsAsErrors = true
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
            unitTests.isReturnDefaultValues = true
        }

        dataBinding.isEnabled = true

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
            getByName("test").java.srcDirs("src/test/kotlin")
            getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        }
    }

    configure<AndroidExtensionsExtension> {
        isExperimental = true
        defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
    }
}

tasks.register("clean").configure {
    delete("build")
}

fun BaseExtension.kotlinOptions(configure: org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)
}


