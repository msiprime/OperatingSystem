package memory_management_technique;
import java.util.Arrays;
import java.util.Scanner;
public class LabReport4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of blocks: ");
        int numBlocks = scanner.nextInt();
        int[] blockSize = new int[numBlocks];
        int[] blockSizeCopy = new int[numBlocks];
        int totalMemory = 0;
        int totalMemoryUsed = 0;

        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Enter size of block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
            blockSizeCopy[i] = blockSize[i];
            totalMemory += blockSize[i];
            totalMemoryUsed += blockSize[i];
        }

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        int[] processSize = new int[numProcesses];
        int totalProcessSizes = 0;
        int totalProcessSizesUsed = 0;

        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter memory required for process " + (i + 1) + ": ");
            processSize[i] = scanner.nextInt();
            totalProcessSizes += processSize[i];
        }

        int[] blockAllocation = new int[numBlocks];
        Arrays.fill(blockAllocation, -1);

        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numBlocks; j++) {
                if (blockSize[j] >= processSize[i]) {
                    blockAllocation[j] = i;
                    blockSize[j] -= processSize[i];
                    totalMemoryUsed -= processSize[i];
                    totalProcessSizesUsed += processSize[i];
                    break;
                }
            }
        }

        System.out.println("\nProcesses\tProcesses size\tBlocks\tBlocks size\tAllocated\tInt. Frag.");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("\t"+(i + 1) + "   \t\t\t" + processSize[i] +"\t\t\t");
            int allocatedBlock = -1;
            for (int j = 0; j < numBlocks; j++) {
                if (blockAllocation[j] == i) {
                    allocatedBlock = j;
                    break;
                }
            }
            if (allocatedBlock != -1) {
                System.out.print((allocatedBlock + 1) + "\t\t" + blockSizeCopy[allocatedBlock] +
                        "    \t\tYES\t\t\t " + blockSize[allocatedBlock]);
            } else {
                System.out.print("-\t\t-\t\tNO\t\t-");
            }
            System.out.println();
        }

        System.out.println("\nTotal Memory: " + totalMemory);
        System.out.println("Total Memory Used/Allocated: " + (totalMemory - totalMemoryUsed));
        System.out.println("Total Process Sizes: " + totalProcessSizes);
        System.out.println("Total Process Sizes Used/Allocated: " + totalProcessSizesUsed);
    }
}
