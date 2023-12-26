package memory_management_technique.kotlin

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter the number of blocks: ")
    val m = scanner.nextInt()
    val blockSize = IntArray(m)
    val blockSizeCopy = IntArray(m)
    println("Enter the size of the blocks:-")
    for (i in 0 until m) {
        print("Block ${i + 1}: ")
        blockSize[i] = scanner.nextInt()
        blockSizeCopy[i] = blockSize[i]
    }

    print("Enter the number of files: ")
    val n = scanner.nextInt()
    val fileSize = IntArray(n)
    println("Enter the size of the files:-")
    for (i in 0 until n) {
        print("File ${i + 1}: ")
        fileSize[i] = scanner.nextInt()
    }

    firstFit(blockSize, blockSizeCopy, m, fileSize, n)
}

fun firstFit(blockSize: IntArray, blockSizeCopy: IntArray, m: Int, fileSize: IntArray, n: Int) {
    val allocation = IntArray(n) { -1 }
    val fragment = IntArray(n)
    var totalMemoryAllocated = 0
    var totalFileSizeAllocated = 0

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (blockSize[j] >= fileSize[i]) {
                allocation[i] = j
                fragment[i] = blockSize[j] - fileSize[i]
                totalMemoryAllocated += blockSizeCopy[allocation[i]]
                totalFileSizeAllocated += fileSize[i]
                blockSize[j] = 0
                break
            }
        }
    }

    println("File no.  File size  Block no.  Block size  Fragment")
    for (i in 0 until n) {
        print("   ${i + 1}         ${fileSize[i]}         ")
        if (allocation[i] != -1) {
            print("${allocation[i] + 1}         ${blockSizeCopy[allocation[i]]}         ${fragment[i]}")
        } else {
            print("Not Allocated")
        }
        println()
    }

    println("Total Memory Allocated: $totalMemoryAllocated")
    println("Total File Size Allocated: $totalFileSizeAllocated")
}
