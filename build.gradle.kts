plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.devtools.ksp") version "2.3.4" apply false
    id("com.google.dagger.hilt.android") version "2.59.2" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}