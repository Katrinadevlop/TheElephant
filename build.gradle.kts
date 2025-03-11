// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    kotlin("android") version "1.9.24" apply false
    id("com.google.gms.google-services") version "4.3.15"
}