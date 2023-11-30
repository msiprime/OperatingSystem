package memory_management_technique.kotlin

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    fun inputArray(size: Int, prompt: String) = IntArray(size) {
        print("$prompt ${it + 1}: ")
        scanner.nextInt()
    }

    print("Enter the number of Blocks– ")
    val numBlocks = scanner.nextInt()
    val blockSize = inputArray(numBlocks, "Block")
    val blockSizeCopy = blockSize.copyOf()

    print("Enter the number of processes– ")
    val numProcesses = scanner.nextInt()
    val processSize = inputArray(numProcesses, "Enter memory required for process")

    val blockAllocation = IntArray(numBlocks) { -1 }

    for (i in 0 until numProcesses) {
        val j = blockSize.indexOfFirst { it >= processSize[i] }
        if (j != -1) {
            blockAllocation[j] = i
            blockSize[j] -= processSize[i]
        }
    }

    println("Processes\tProcesses size\tBlocks\tBlocks size\tAllocated\tInt. Frag.")

    for (i in 0 until numProcesses) {
        val allocatedBlock = blockAllocation.indexOfFirst { it == i }

        with(processSize[i]) {
            print("\t${i + 1}\t\t\t$this\t\t\t")
            if (allocatedBlock != -1) {
                print("${allocatedBlock + 1}\t\t\t${blockSizeCopy[allocatedBlock]}\t\t\tYES\t\t\t${blockSize[allocatedBlock]}")
            } else {
                print("-\t\t\t-\t\t\tNO\t\t\t-")
            }
            println()
        }
    }
}
