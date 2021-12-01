import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import kotlin.reflect.full.memberProperties

object Depends {

    object Module {
        const val account = ":account"
        const val albums = ":albums"
        const val datasource = ":datasource"
        const val photos = ":photos"

        val implementations = listOf(
            account,
            albums,
            datasource,
            photos
        )
    }

    val modules: List<String> by lazy {
        Module::class.memberProperties.map { it.name }
    }

    object Android {

        const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradleVersion}"
        const val runnerPackage = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Kotlin {
        fun getKotlinStdlibVersion() =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

        fun getCoreKtxVersion() = "androidx.core:core-ktx:${Versions.coreKTXVersion}"
    }

    object Support {
        fun getSupportV7Version() =
            "androidx.appcompat:appcompat:${Versions.supportVersion}"

        fun getSupportConstraintLayoutVersion() =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    }

    object Navigation {
        fun getNavigationFragmentVersion() =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"

        fun getNavigationUiVersion() =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    }

    object Test {
        fun getJunitVersion() = "junit:junit:${Versions.junitVersion}"
        fun getRunnerVersion() = "androidx.test:runner:${Versions.runnerVersion}"
        fun getEspressoVersion() = "androidx.test.espresso:espresso-core${Versions.espressoVersion}"
    }

    object Dagger {
        fun getDaggerCore() =
            "com.google.dagger:dagger:${Versions.daggerVersion}"

        fun getDaggerCompiler() =
            "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"

        fun getDaggerAndroid() =
            "com.google.dagger:dagger-android:${Versions.daggerVersion}"

        fun getDaggerSupport() =
            "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"

        fun getDaggerProcessor() =
            "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    }

    object LifeCycle {
        fun getRuntimeVersion() =
            "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"

        fun getExtensionsVersion() =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"

        fun getCommonJavaVersion() =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"

        fun getViewModelVersion() = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_viewModel_version}"

        fun getViewModelKtxVersion() = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtxVersion}"
    }

    object Retrofit {
        fun getRetrofitcoreVersion() = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        fun getConverterGsonVersion() =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    }

    object OkHttp3 {
        fun getCoreVersion() = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        fun getInterceptorVersion() = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    }

    object Room {
        fun getRoomVersion() =  "androidx.room:room-runtime:${Versions.okhttpVersion}"
        fun getRoomCompilerVersion() =  "androidx.room:room-compiler:${Versions.okhttpVersion}"
    }

    object Coroutines {
        fun getCoreCoroutinesVersion() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    }
}