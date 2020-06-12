package Player;

public class Inventory {
    private Item weapon;
    private Item ability;

    public void setWeapon(Item w) { weapon = w; }
    public void setAbility(Item a) { ability = a; }
    public Item getWeapon() { return weapon; }
    public Item getAbility() {return ability; }
}