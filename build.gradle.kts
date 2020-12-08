import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "6.7.1"
}

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    idea
    kotlin("jvm") version "1.4.20"
    application
    id("com.diffplug.spotless") version "5.8.2"
    id("de.undercouch.download") version "4.1.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

spotless {
    kotlin {
        ktlint("0.40.0")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

val session: String by extra {
    System.getenv("AOC_SESSION")
}

for (day in 1..25) {
    tasks.register<Download>("download-$day") {
        group = "build"
        description = "Download input file for day $day"

        src("https://adventofcode.com/2020/day/$day/input")
        dest("$buildDir/resources/main/day$day.txt")
        headers(mapOf("Cookie" to "session=$session"))
        overwrite(false)
    }

    for (part in 1..2) {
        tasks.register("run-$day-$part") {
            dependsOn("download-$day")

            group = "application"
            description = "Run exercise for day $day part $part"

            application {
                mainClass.set("com.jonesetc.aoc2020.day$day.Part$part")
            }

            finalizedBy("run")
        }
    }
}

// hide the :run task that is not configured when called as top level task
tasks.getByPath("run").group = null
