package net.minecraft.server;

public class ItemFood extends Item {

    private int a;

    public ItemFood(int i, int j) {
        super(i);
        this.a = j;
        this.aT = 1;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.aj.bNew(this.aS)) {
        	entityhuman.restoreHealth(this.a);
        }

        return itemstack;
    }
}
