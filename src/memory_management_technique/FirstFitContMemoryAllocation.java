package memory_management_technique;

import java.util.Arrays;
import java.util.Scanner;


public class FirstFitContMemoryAllocation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of blocks: ");
        int m = scanner.nextInt();
        int[] blockSize = new int[m];
        int[] blockSizeCopy = new int[m];
        System.out.println("Enter the size of the blocks:-");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
            blockSizeCopy[i] = blockSize[i];
        }

        System.out.print("Enter the number of files: ");
        int n = scanner.nextInt();
        int[] fileSize = new int[n];
        System.out.println("Enter the size of the files:-");
        for (int i = 0; i < n; i++) {
            System.out.print("File " + (i + 1) + ": ");
            fileSize[i] = scanner.nextInt();
        }

        firstFit(blockSize, blockSizeCopy, m, fileSize, n);
    }

    static void firstFit(int[] blockSize, int[] blockSizeCopy, int m, int[] fileSize, int n) {
        int[] allocation = new int[n];
        int[] fragment = new int[n];
        Arrays.fill(allocation, -1);

        int totalMemoryAllocated = 0;
        int totalFileSizeAllocated = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= fileSize[i]) {
                    allocation[i] = j;
                    fragment[i] = blockSize[j] - fileSize[i];
                    totalMemoryAllocated += blockSizeCopy[allocation[i]];
                    totalFileSizeAllocated += fileSize[i];
                    blockSize[j] = 0;
                    break;
                }
            }
        }

        System.out.println("File no.  File size  Block no.  Block size  Fragment");
        for (int i = 0; i < n; i++) {
            System.out.print("   " + (i + 1) + "         " + fileSize[i] + "         ");
            if (allocation[i] != -1) {
                System.out.print((allocation[i] + 1) + "         " + blockSizeCopy[allocation[i]] + "         " + fragment[i]);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }

        System.out.println("Total Memory Allocated: " + totalMemoryAllocated);
        System.out.println("Total File Size Allocated: " + totalFileSizeAllocated);
    }
}
