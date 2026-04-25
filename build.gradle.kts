// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

    val detektPluginId = rootProject.libs.plugins.detekt.get().pluginId
    val detektConfigFile = rootProject.files("config/detekt/detekt.yml")

    plugins.withId("org.jetbrains.kotlin.android") {
        apply(plugin = detektPluginId)
        configure<dev.detekt.gradle.extensions.DetektExtension> {
            config.setFrom(detektConfigFile)
            buildUponDefaultConfig = true
        }
    }
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = detektPluginId)
        configure<dev.detekt.gradle.extensions.DetektExtension> {
            config.setFrom(detektConfigFile)
            buildUponDefaultConfig = true
        }
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")

            ktlint().editorConfigOverride(
                mapOf(
                    "ktlint_code_style" to "ktlint_official",
                    "max_line_length" to "160",
                    "ij_kotlin_allow_trailing_comma" to "true",
                    "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                    "ktlint_standard_argument-list-wrapping" to "enabled",
                    "ktlint_standard_trailing-comma-on-call-site" to "always",
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable,Test",
                ),
            )

            trimTrailingWhitespace()
            leadingSpacesToTabs(4)
            endWithNewline()
        }

        kotlinGradle {
            target("*.gradle.kts")
            endWithNewline()
            ktlint()
        }
    }
}
