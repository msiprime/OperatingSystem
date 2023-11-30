package memory_management_technique;

import java.util.Scanner;

public class TestingL$ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of blocks:");
        int numBlocks = scanner.nextInt();
        int[] blockSize = new int[numBlocks];
        int[] blockSizeCopy = new int[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            System.out.println("Enter size of block " + (i + 1) + ":");
            blockSize[i] = scanner.nextInt();
            blockSizeCopy[i] = blockSize[i];
        }

        System.out.println("Enter the number of processes:");
        int numProcesses = scanner.nextInt();
        int[] processSize = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter memory required for process " + (i + 1) + ":");
            processSize[i] = scanner.nextInt();
        }

        // Initialize block allocation values
        int[] blockAllocation = new int[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            blockAllocation[i] = -1;
        }

        // Allocate blocks to processes
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numBlocks; j++) {
                if (blockSize[j] >= processSize[i]) {
                    blockAllocation[j] = i;
                    blockSize[j] -= processSize[i];
                    break;
                }
            }
        }

        // Print allocation details
        System.out.println("Processes\tProcesses size\tBlocks\tBlocks size\tAllocated\tInt. Frag.");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            int allocatedBlock = -1;
            for (int j = 0; j < numBlocks; j++) {
                if (blockAllocation[j] == i) {
                    allocatedBlock = j;
                    break;
                }
            }
            if (allocatedBlock != -1) {
                System.out.print((allocatedBlock + 1) + "\t\t" + blockSizeCopy[allocatedBlock] + "\t\tYES\t\t" + blockSize[allocatedBlock]);
            } else {
                System.out.print("-\t\t-\t\tNO\t\t-");
            }
            System.out.println();
        }
    }
}
