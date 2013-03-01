import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static List<Item> items = null;

    public static void main(String[] args) {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        printItemList(0);
        updateQuality();
    }

    public static void printItemList(int days) {
        System.out.println("Items in stock after " + days + " days:");
        for (Item item : items) {
            System.out.println(String.format("%45s %5s %5s", item.getName(), item.getSellIn(), item.getQuality()));
        }
    }

    public static void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
        }
    }

    private static void updateQuality(Item item) {
        if (("Aged Brie".equals(item.getName())) || "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
            increaseQuality(item);
            if (item.getQuality() < 50) {
                if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                    if (item.getSellIn() < 11) {
                        increaseQuality(item);
                    }

                    if (item.getSellIn() < 6) {
                        increaseQuality(item);
                    }
                }
            }
        } else {
            if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                decreaseQuality(item);
            }
        }

        if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
            item.setSellIn(item.getSellIn() - 1);
        }

        if (item.getSellIn() < 0) {
            if ("Aged Brie".equals(item.getName())) {
                increaseQuality(item);
            } else {
                if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                    item.setQuality(0);
                } else {
                    if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                        decreaseQuality(item);
                    }
                }
            }
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private static void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

}