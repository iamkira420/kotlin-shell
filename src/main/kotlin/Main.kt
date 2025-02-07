fun main() {
    // Uncomment this block to pass the first stage

    while (true) {
        print("$ ")
        val command = readln() // Wait for user command
        if (command == "exit 0") {
               break
        } else {
            println("$command: command not found")
        }
    }
}

