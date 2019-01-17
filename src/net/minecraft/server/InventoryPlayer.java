package net.minecraft.server;

public class InventoryPlayer implements IInventory {

    public ItemStack[] items = new ItemStack[37];
    public ItemStack[] armor = new ItemStack[4];
    public ItemStack[] crafting = new ItemStack[4];
    public int itemInHandIndex = 0;
    private EntityHuman f;
    public boolean e = false;

    public InventoryPlayer(EntityHuman entityhuman) {
        this.f = entityhuman;
    }

    public ItemStack b() {
    	return this.items[this.itemInHandIndex];
    }

    private int d(int i) {
        for (int j = 0; j < this.items.length; ++j) {
            if (this.items[j] != null && this.items[j].c == i) {
                return j;
            }
        }

        return -1;
    }

    private int c(int i) {
        for (int j = 0; j < this.items.length; ++j) {
            if (this.items[j] != null && this.items[j].c == i && this.items[j].a < this.items[j].b() && this.items[j].a < this.d()) {
                return j;
            }
        }

        return -1;
    }

    private int g() {
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == null) {
                return i;
            }
        }

        return -1;
    }

    private int a(int i, int j) {
        int k = this.c(i);

        if (k < 0) {
            k = this.g();
        }

        if (k < 0) {
            return j;
        } else {
            if (this.items[k] == null) {
                this.items[k] = new ItemStack(i, 0);
            }

            int l = j;

            if (j > this.items[k].b() - this.items[k].a) {
                l = this.items[k].b() - this.items[k].a;
            }

            if (l > this.d() - this.items[k].a) {
                l = this.d() - this.items[k].a;
            }

            if (l == 0) {
                return j;
            } else {
                j -= l;
                this.items[k].a += l;
                this.items[k].b = 5;
                return j;
            }
        }
    }

    public void c() {
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null && this.items[i].b > 0) {
                --this.items[i].b;
            }
        }
    }

    public boolean bNew(int i) {
        int j = this.d(i);

        if (j < 0) {
            return false;
        } else {
            if (--this.items[j].a <= 0) {
                this.items[j] = null;
            }

            return true;
        }
    }

    public boolean a(ItemStack itemstack) {
        if (itemstack.d == 0) {
            itemstack.a = this.a(itemstack.c, itemstack.a);
            if (itemstack.a == 0) {
                return true;
            }
        }

        int i = this.g();

        if (i >= 0) {
            this.items[i] = itemstack;
            this.items[i].b = 5;
            return true;
        } else {
            return false;
        }
    }

    public void a(int i, ItemStack itemstack) {
        ItemStack[] aitemstack = this.items;

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.armor;
        }

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.crafting;
        }

        aitemstack[i] = itemstack;
    }

    public float a(Block block) {
        float f = 1.0F;

        if (this.items[this.itemInHandIndex] != null) {
            f *= this.items[this.itemInHandIndex].a(block);
        }

        return f;
    }

    public NBTTagList a(NBTTagList nbttaglist) {
        int i;
        NBTTagCompound nbttagcompound;

        for (i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) i);
                this.items[i].a(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        for (i = 0; i < this.armor.length; ++i) {
            if (this.armor[i] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) (i + 100));
                this.armor[i].a(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        for (i = 0; i < this.crafting.length; ++i) {
            if (this.crafting[i] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) (i + 80));
                this.crafting[i].a(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        return nbttaglist;
    }

    public void b(NBTTagList nbttaglist) {
        this.items = new ItemStack[36];
        this.armor = new ItemStack[4];
        this.crafting = new ItemStack[4];

        for (int i = 0; i < nbttaglist.b(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.a(i);
            int j = nbttagcompound.b("Slot") & 255;

            if (j >= 0 && j < this.items.length) {
                this.items[j] = new ItemStack(nbttagcompound);
            }

            if (j >= 80 && j < this.crafting.length + 80) {
                this.crafting[j - 80] = new ItemStack(nbttagcompound);
            }

            if (j >= 100 && j < this.armor.length + 100) {
                this.armor[j - 100] = new ItemStack(nbttagcompound);
            }
        }
    }

    public int a() {
        return this.items.length + 4;
    }

    public ItemStack a(int i) {
        ItemStack[] aitemstack = this.items;

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.armor;
        }

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.crafting;
        }

        return aitemstack[i];
    }

    public int d() {
        return 64;
    }

    public int a(Entity entity) {
        ItemStack itemstack = this.a(this.itemInHandIndex);

        return itemstack != null ? itemstack.a(entity) : 1;
    }

    public boolean b(Block block) {
        if (block.bn != Material.d && block.bn != Material.e && block.bn != Material.t && block.bn != Material.s) {
            return true;
        } else {
            ItemStack itemstack = this.a(this.itemInHandIndex);

            return itemstack != null ? itemstack.b(block) : false;
        }
    }

    public int e() {
        int i = 0;
        int j = 0;
        int k = 0;

        for (int l = 0; l < this.armor.length; ++l) {
            if (this.armor[l] != null && this.armor[l].a() instanceof ItemArmor) {
                int i1 = this.armor[l].c();
                int j1 = this.armor[l].d;
                int k1 = i1 - j1;

                j += k1;
                k += i1;
                int l1 = ((ItemArmor) this.armor[l].a()).aY;

                i += l1;
            }
        }

        if (k == 0) {
            return 0;
        } else {
            return (i - 1) * j / k + 1;
        }
    }

    public void b(int i) {
        for (int j = 0; j < this.armor.length; ++j) {
            if (this.armor[j] != null && this.armor[j].a() instanceof ItemArmor) {
                this.armor[j].a(i);
                if (this.armor[j].a == 0) {
                    this.armor[j].a(this.f);
                    this.armor[j] = null;
                }
            }
        }
    }

    public void f() {
        int i;

        for (i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                this.f.a(this.items[i], true);
                this.items[i] = null;
            }
        }

        for (i = 0; i < this.armor.length; ++i) {
            if (this.armor[i] != null) {
                this.f.a(this.armor[i], true);
                this.armor[i] = null;
            }
        }
    }
}
