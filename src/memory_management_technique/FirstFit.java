package memory_management_technique;


import java.util.ArrayList;
import java.util.Scanner;

public class FirstFit {

    private static int block_size, file_size, total_fragment, total_file = 0;

    private static ArrayList<Integer> used = new ArrayList<>();
    private static ArrayList<Integer> files = new ArrayList<>();
    private static ArrayList<Integer> blocks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of blocks: ");
        block_size = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of files: ");
        file_size = Integer.parseInt(scanner.nextLine());

        for (int x = 0; x < block_size; x++) {
            System.out.print("Block " + (x+1) + " : ");
            blocks.add(Integer.parseInt(scanner.nextLine()));
        }

        for (int x = 0; x < file_size; x++) {
            System.out.print("File " + (x+1) + " : ");
            files.add(Integer.parseInt(scanner.nextLine()));
        }

        System.out.println("File_no:\tFile_size:\tBlock_no:\tBlock_size:\tFragment");
        for (int f = 0; f < file_size; f++) {
            for (int b = 0; b < block_size; b++) {
                if (!used.contains(b) && blocks.get(b) >= files.get(f)) {
                    System.out.println(f+1+"\t\t\t"+files.get(f)+"\t\t\t"+(b+1)+"\t\t\t"+blocks.get(b)+"\t\t\t"+(blocks.get(b)-files.get(f)));
                    used.add(b);
                    total_file++;
                    total_fragment += (blocks.get(b)-files.get(f));
                    b = block_size;
                }
            }
        }

        System.out.println("\nTotal Allocated Files : " + total_file);
        System.out.print("Total Fragmentation : " + total_fragment);
    }
}