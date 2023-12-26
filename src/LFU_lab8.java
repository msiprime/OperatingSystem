import java.util.*;

 class LFU {
    private final int capacity;
    private final Map<Integer, Integer> counts;
    private final Map<Integer, LinkedHashSet<Integer>> lists;
    private int min = -1;

    public LFU(int capacity) {
        this.capacity = capacity;
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public void refer(int page) {
        if (!counts.containsKey(page)) {
            if (counts.size() >= capacity) {
                int evict = lists.get(min).getFirst();
                lists.get(min).remove(evict);
                counts.remove(evict);
            }
            counts.put(page, 1);
            min = 1;
            lists.get(1).add(page);
        } else {
            int count = counts.get(page);
            counts.put(page, count + 1);
            lists.get(count).remove(page);
            if (count == min && lists.get(count).isEmpty()) {
                min++;
            }
            if (!lists.containsKey(count + 1)) {
                lists.put(count + 1, new LinkedHashSet<>());
            }
            lists.get(count + 1).add(page);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of frames: ");
        int frames = scanner.nextInt();
        System.out.print("Reference string: ");
        scanner.nextLine();
        String[] references = scanner.nextLine().split(" ");
        LFU cache = new LFU(frames);
        int noPageFaults = 0;
        for (String ref : references) {
            int page = Integer.parseInt(ref);
            cache.refer(page);
            if (cache.counts.containsKey(page) && cache.counts.get(page) > 1) {
                noPageFaults++;
                System.out.println("For\t" + page + " :\t" + cache.lists.get(cache.counts.get(page)) + "   No page fault!");
            } else {
                System.out.println("For\t" + page + " :\t" + cache.lists.get(cache.counts.get(page)) + "   Page Fault");
            }
        }
        System.out.println("Total No page fault using LFU is: " + (noPageFaults+4));
    }
}
