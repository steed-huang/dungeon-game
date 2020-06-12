package Player;

public class Inventory {
    private Item weapon;
    private Item ability;

    public void setWeapon(Item w) { weapon = w; }
    public void setAbility(Item a) { ability = a; }
    public Item getWeapon() { return weapon; }
    public Item getAbility() {return ability; }

    public void draw(java.awt.Graphics2D g){
        if (weapon != null) weapon.draw(g);
        if (ability != null) ability.draw(g);
    }
}