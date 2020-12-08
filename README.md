# Advent of Code 2020

The Advent of code for 2020, done in Kotlin. Included is some gradle structure to simplify the downloading and loading of the input files.

## Running

### Environment

Can be done manually, or through some tool like `direnv` (recommended and already excluded from git tracking).

- `JAVA_HOME` to a java 11 compatible JDK location
- `AOC_SESSION` to a valid session token for `https://adventofcode.com/`
    - The easiest way to get this value is by logging into the site and copying out the session cookie from your browser.

### Gradle tasks

running with gradle wrapper us recommended (`./gradlew <task>`).Included in the gradle config are tasks for:

- downloading input files (`download-<day 1-25>`, stores as `build/resources/main/day<day 1-25>.txt`)
- running exercises (`run-<day 1-25>-<part 1-2>`, automatically downloads the input file)
