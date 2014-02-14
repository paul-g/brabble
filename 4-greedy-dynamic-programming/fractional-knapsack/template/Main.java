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
	// TODO Implement me!
        return 0;
    }

}

class Item  {
    double weight, price, ratio;
    double prop = 1.0;

    Item(int weight, int price) {
        this.weight = (double)weight;
        this.price = (double)price;
        this.ratio = this.price / this.weight;
    }
}
