import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

	    // read
            int n = sc.nextInt();
            int maxWeight = sc.nextInt();
            List<Item> items = new ArrayList<Item>();
            for (int i = 0; i < n; i++)
                items.add(new Item(sc.nextInt(), sc.nextInt()));

	    // solve
            double profit = solve(items, maxWeight);

	    // print
	    System.out.println(profit);
        }
    }

    public static double solve(List<Item> items, int maxWeight) {

	Collections.sort(items, Collections.reverseOrder());
	
	// Use a custom comparator if you want something different
	// than the natural ordering, or there is no natural ordering
        // Collections.sort(items, new Comparator<Item> () {
        //         public int compare(Item it1, Item it2) {
        //             return Double.compare(it1.ratio, it2.ratio);
        //         }});

        // In JDK 1.8 you can use lambdas:
        // Collections.sort(items,
        //     (item1, item2) -> {return Double.compare(item2.ratio, item1.ratio);});

        int weightUsed = 0;
        double profit = 0;
        List<Item> used = new ArrayList<Item>();
        for (Item item : items) {
            int left = maxWeight - weightUsed;
            if (item.weight > left) {
                profit += item.useProportion(left);
                used.add(item);
                break;
            } else {
                profit += item.price;
                used.add(item);
                weightUsed += item.weight;
            }
        }

        System.out.println(used);
        return profit;
    }

}

// Implement comparable to provide a natural ordering for objects of
// type Item; this is used for example in Collections.sort()
class Item implements Comparable<Item> {
    double weight, price, ratio;
    double prop = 1.0;

    Item(int weight, int price) {
        this.weight = (double)weight;
        this.price = (double)price;
        this.ratio = this.price / this.weight;
    }

    double useProportion(double weight){
        this.prop = weight / this.weight;
	return this.prop * this.price;
    }

    // Use annotations to let the compiler know you want to override a
    // base class method; if there is no such method, the compiler
    // will generate an error - very useful to catch silly bugs
    @Override
    public String toString() {
        return String.format("[w=%.3f, p=%.3f, r=%.3f, p=%.3f]\n",
                             weight, price, ratio, prop);
    }

    @Override
    public int compareTo(Item item) {
        if (item == null)
            return 1;
        return Double.compare(ratio, item.ratio);
    }
}
