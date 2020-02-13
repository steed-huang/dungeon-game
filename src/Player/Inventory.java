package Player;

public class Inventory {
    private static final int SIZE = 3;
    private static Item slots[] = new Item[SIZE];

    public Item getItem(int slot){ return slots[slot];};

    public void addItem(){};
    public void useItem(){};
    public void deleteItem(){};
}