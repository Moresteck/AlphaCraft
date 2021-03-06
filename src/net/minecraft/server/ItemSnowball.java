package net.minecraft.server;

public class ItemSnowball extends Item {

    public ItemSnowball(int i) {
        super(i);
        this.aT = 16;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.a;
        world.a(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
        if (!world.x) {
        	world.a((Entity) (new EntitySnowball(world, entityhuman)));        	
        }
        return itemstack;
    }
}
