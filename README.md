# KTOR-FAILURE

Failed to find HttpClientEngineContainer. Consider adding [HttpClientEngine] implementation in dependencies.

https://youtrack.jetbrains.com/issue/KTOR-3517#focus=Comments-27-5583278.0-0

Run the iosSimulatorArm64Test and you see it fail.

When commenting out the kotlin.native.binary.memoryModel=experimental from gradle properties file, it works
