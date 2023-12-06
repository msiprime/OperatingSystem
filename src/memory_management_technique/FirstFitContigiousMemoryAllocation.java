package memory_management_technique;

public class FirstFitContigiousMemoryAllocation {
    public static void main(String[] args) {
        int blockSize[] = {5, 8, 10, 4};
        int fileSize[] = {7, 4, 3};
        int m = blockSize.length;
        int n = fileSize.length;

        firstFit(blockSize, m, fileSize, n);
    }

    static void firstFit(int blockSize[], int m, int fileSize[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (blockSize[j] >= fileSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= fileSize[i];
                    break;
                }
            }
        }

        System.out.println("File No.  File Size  Block No.  Block Size  Fragment");
        for (int i = 0; i < n; i++) {
            System.out.print("   " + (i+1) + "         " + fileSize[i] + "         ");
            if (allocation[i] != -1) {
                System.out.print((allocation[i] + 1) + "         " + blockSize[allocation[i]] + "         " + (blockSize[allocation[i]] - fileSize[i]));
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }
}
