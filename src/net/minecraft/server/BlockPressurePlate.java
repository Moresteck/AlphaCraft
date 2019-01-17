package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockPressurePlate extends Block {

    private EnumMobType a;

    protected BlockPressurePlate(int i, int j, EnumMobType enummobtype) {
        super(i, j, Material.d);
        this.a = enummobtype;
        this.a(true);
        float f = 0.0625F;

        this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int c() {
        return 20;
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public boolean b() {
        return false;
    }

    public boolean a(World world, int i, int j, int k) {
        return world.d(i, j - 1, k);
    }

    public void e(World world, int i, int j, int k) {}

    public void b(World world, int i, int j, int k, int l) {
        boolean flag = false;

        if (!world.d(i, j - 1, k)) {
            flag = true;
        }

        if (flag) {
            this.a_(world, i, j, k, world.b(i, j, k));
            world.d(i, j, k, 0);
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (world.b(i, j, k) != 0) {
            this.g(world, i, j, k);
        }
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        if (world.b(i, j, k) != 1) {
            this.g(world, i, j, k);
        }
    }

    private void g(World world, int i, int j, int k) {
        boolean flag = world.b(i, j, k) == 1;
        boolean flag1 = false;
        float f = 0.125F;
        List list = null;

        if (this.a == EnumMobType.EVERYTHING) {
            list = world.b((Entity) null, AxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (this.a == EnumMobType.MOBS) {
            list = world.a(EntityLiving.class, AxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (this.a == EnumMobType.PLAYERS) {
            list = world.a(EntityHuman.class, AxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (list.size() > 0) {
            flag1 = true;
        }

        if (flag1 && !flag) {
            world.b(i, j, k, 1);
            world.g(i, j, k, this.bc);
            world.g(i, j - 1, k, this.bc);
            world.b(i, j, k, i, j, k);
            world.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (!flag1 && flag) {
            world.b(i, j, k, 0);
            world.g(i, j, k, this.bc);
            world.g(i, j - 1, k, this.bc);
            world.b(i, j, k, i, j, k);
            world.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (flag1) {
            world.h(i, j, k, this.bc);
        }
    }

    public void b(World world, int i, int j, int k) {
        int l = world.b(i, j, k);

        if (l > 0) {
            world.g(i, j, k, this.bc);
            world.g(i, j - 1, k, this.bc);
        }

        super.b(world, i, j, k);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        boolean flag = iblockaccess.b(i, j, k) == 1;
        float f = 0.0625F;

        if (flag) {
            this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            this.a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return iblockaccess.b(i, j, k) > 0;
    }

    public boolean d(World world, int i, int j, int k, int l) {
        return world.b(i, j, k) == 0 ? false : l == 1;
    }

    public boolean d() {
        return true;
    }
}
