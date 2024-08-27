import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val command = listOf("kubectl", "port-forward", "-n", "din-global", "svc/coa-service", "9090:9024")
    // Run the command
    runCommand(command)
}

private fun runCommand(command: List<String>) {
    try {
        // Start the process
        val processBuilder = ProcessBuilder(command)
        processBuilder.redirectErrorStream(true)
        val process = processBuilder.start()
        BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                println(line)
            }
        }

    } catch (exception: Exception) {
        println(exception.message)
        throw exception
    }
}