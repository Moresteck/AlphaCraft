package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Chunk {

    public static boolean a;
    public byte[] b;
    public boolean c;
    public World d;
    public NibbleArray e;
    public NibbleArray f;
    public NibbleArray g;
    public byte[] h;
    public int i;
    public final int j;
    public final int k;
    public Map l;
    public List[] m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public long s;

    public Chunk(World world, int i, int j) {
        this.l = new HashMap();
        this.m = new List[8];
        this.n = false;
        this.o = false;
        this.q = false;
        this.r = false;
        this.s = 0L;
        this.d = world;
        this.j = i;
        this.k = j;
        this.h = new byte[256];

        for (int k = 0; k < this.m.length; ++k) {
            this.m[k] = new ArrayList();
        }
    }

    public Chunk(World world, byte[] abyte, int i, int j) {
        this(world, i, j);
        this.b = abyte;
        this.e = new NibbleArray(abyte.length);
        this.f = new NibbleArray(abyte.length);
        this.g = new NibbleArray(abyte.length);
    }

    public boolean a(int i, int j) {
        return i == this.j && j == this.k;
    }

    public int b(int i, int j) {
        return this.h[j << 4 | i] & 255;
    }

    public void a() {}

    public void b() {
        int i = 127;

        int j;
        int k;

        for (j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                this.h[k << 4 | j] = -128;
                this.g(j, 127, k);
                if ((this.h[k << 4 | j] & 255) < i) {
                    i = this.h[k << 4 | j] & 255;
                }
            }
        }

        this.i = i;

        for (j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                this.c(j, k);
            }
        }

        this.o = true;
    }

    private void c(int i, int j) {
        int k = this.b(i, j);
        int l = this.j * 16 + i;
        int i1 = this.k * 16 + j;

        this.f(l - 1, i1, k);
        this.f(l + 1, i1, k);
        this.f(l, i1 - 1, k);
        this.f(l, i1 + 1, k);
    }

    private void f(int i, int j, int k) {
        int l = this.d.c(i, j);

        if (l > k) {
            this.d.a(EnumSkyBlock.SKY, i, k, j, i, l, j);
        } else if (l < k) {
            this.d.a(EnumSkyBlock.SKY, i, l, j, i, k, j);
        }

        this.o = true;
    }

    private void g(int i, int j, int k) {
        int l = this.h[k << 4 | i] & 255;
        int i1 = l;

        if (j > l) {
            i1 = j;
        }

        for (int j1 = i << 11 | k << 7; i1 > 0 && Block.r[this.b[j1 + i1 - 1]] == 0; --i1) {
            ;
        }

        if (i1 != l) {
            this.d.f(i, k, i1, l);
            this.h[k << 4 | i] = (byte) i1;
            int k1;
            int l1;
            int i2;

            if (i1 < this.i) {
                this.i = i1;
            } else {
                k1 = 127;

                for (l1 = 0; l1 < 16; ++l1) {
                    for (i2 = 0; i2 < 16; ++i2) {
                        if ((this.h[i2 << 4 | l1] & 255) < k1) {
                            k1 = this.h[i2 << 4 | l1] & 255;
                        }
                    }
                }

                this.i = k1;
            }

            k1 = this.j * 16 + i;
            l1 = this.k * 16 + k;
            if (i1 < l) {
                for (i2 = i1; i2 < l; ++i2) {
                    this.f.a(i, i2, k, 15);
                }
            } else {
                this.d.a(EnumSkyBlock.SKY, k1, l, l1, k1, i1, l1);

                for (i2 = l; i2 < i1; ++i2) {
                    this.f.a(i, i2, k, 0);
                }
            }

            i2 = 15;

            int j2;

            for (j2 = i1; i1 > 0 && i2 > 0; this.f.a(i, i1, k, i2)) {
                --i1;
                int k2 = Block.r[this.a(i, i1, k)];

                if (k2 == 0) {
                    k2 = 1;
                }

                i2 -= k2;
                if (i2 < 0) {
                    i2 = 0;
                }
            }

            while (i1 > 0 && Block.r[this.a(i, i1 - 1, k)] == 0) {
                --i1;
            }

            if (i1 != j2) {
                this.d.a(EnumSkyBlock.SKY, k1 - 1, i1, l1 - 1, k1 + 1, j2, l1 + 1);
            }

            this.o = true;
        }
    }

    public int a(int i, int j, int k) {
        return this.b[i << 11 | k << 7 | j];
    }

    public boolean a(int i, int j, int k, int l, int i1) {
        byte b0 = (byte) l;
        int j1 = this.h[k << 4 | i] & 255;
        int k1 = this.b[i << 11 | k << 7 | j] & 255;

        if (k1 == l && this.e.a(i, j, k) == i1) {
            return false;
        } else {
            int l1 = this.j * 16 + i;
            int i2 = this.k * 16 + k;

            this.b[i << 11 | k << 7 | j] = b0;
            if (k1 != 0 && !this.d.x) {
                Block.n[k1].b(this.d, l1, j, i2);
            }

            this.e.a(i, j, k, i1);
            if (Block.r[b0] != 0) {
                if (j >= j1) {
                    this.g(i, j + 1, k);
                }
            } else if (j == j1 - 1) {
                this.g(i, j, k);
            }

            this.d.a(EnumSkyBlock.SKY, l1, j, i2, l1, j, i2);
            this.d.a(EnumSkyBlock.BLOCK, l1, j, i2, l1, j, i2);
            this.c(i, k);
            if (l != 0) {
                Block.n[l].e(this.d, l1, j, i2);
            }

            this.e.a(i, j, k, i1);
            this.o = true;
            return true;
        }
    }

    public boolean a(int i, int j, int k, int l) {
        byte b0 = (byte) l;
        int i1 = this.h[k << 4 | i] & 255;
        int j1 = this.b[i << 11 | k << 7 | j] & 255;

        if (j1 == l) {
            return false;
        } else {
            int k1 = this.j * 16 + i;
            int l1 = this.k * 16 + k;

            this.b[i << 11 | k << 7 | j] = b0;
            if (j1 != 0) {
                Block.n[j1].b(this.d, k1, j, l1);
            }

            this.e.a(i, j, k, 0);
            if (Block.r[b0] != 0) {
                if (j >= i1) {
                    this.g(i, j + 1, k);
                }
            } else if (j == i1 - 1) {
                this.g(i, j, k);
            }

            this.d.a(EnumSkyBlock.SKY, k1, j, l1, k1, j, l1);
            this.d.a(EnumSkyBlock.BLOCK, k1, j, l1, k1, j, l1);
            this.c(i, k);
            if (l != 0 && !this.d.x) {
                Block.n[l].e(this.d, k1, j, l1);
            }

            this.o = true;
            return true;
        }
    }

    public int b(int i, int j, int k) {
        return this.e.a(i, j, k);
    }

    public void b(int i, int j, int k, int l) {
        this.o = true;
        this.e.a(i, j, k, l);
    }

    public int a(EnumSkyBlock enumskyblock, int i, int j, int k) {
        return enumskyblock == EnumSkyBlock.SKY ? this.f.a(i, j, k) : (enumskyblock == EnumSkyBlock.BLOCK ? this.g.a(i, j, k) : 0);
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l) {
        this.o = true;
        if (enumskyblock == EnumSkyBlock.SKY) {
            this.f.a(i, j, k, l);
        } else {
            if (enumskyblock != EnumSkyBlock.BLOCK) {
                return;
            }

            this.g.a(i, j, k, l);
        }
    }

    public int c(int i, int j, int k, int l) {
        int i1 = this.f.a(i, j, k);

        if (i1 > 0) {
            a = true;
        }

        i1 -= l;
        int j1 = this.g.a(i, j, k);

        if (j1 > i1) {
            i1 = j1;
        }

        return i1;
    }

    public void a(Entity entity) {
        if (!this.q) {
            this.r = true;
            int i = MathHelper.b(entity.l / 16.0D);
            int j = MathHelper.b(entity.n / 16.0D);

            if (i != this.j || j != this.k) {
                System.out.println("Wrong location! " + entity);
            }

            int k = MathHelper.b(entity.m / 16.0D);

            if (k < 0) {
                k = 0;
            }

            if (k >= this.m.length) {
                k = this.m.length - 1;
            }

            entity.Z = true;
            entity.aa = this.j;
            entity.ab = k;
            entity.ac = this.k;
            this.m[k].add(entity);
        }
    }

    public void b(Entity entity) {
        this.a(entity, entity.ab);
    }

    public void a(Entity entity, int i) {
        if (i < 0) {
            i = 0;
        }

        if (i >= this.m.length) {
            i = this.m.length - 1;
        }

        this.m[i].remove(entity);
    }

    public boolean c(int i, int j, int k) {
        return j >= (this.h[k << 4 | i] & 255);
    }

    public TileEntity d(int i, int j, int k) {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);
        TileEntity tileentity = (TileEntity) this.l.get(chunkposition);

        if (tileentity == null) {
            int l = this.a(i, j, k);

            if (!Block.q[l]) {
                return null;
            }

            BlockContainer blockcontainer = (BlockContainer) Block.n[l];

            blockcontainer.e(this.d, this.j * 16 + i, j, this.k * 16 + k);
            tileentity = (TileEntity) this.l.get(chunkposition);
        }

        return tileentity;
    }

    public void a(TileEntity tileentity) {
        int i = tileentity.b - this.j * 16;
        int j = tileentity.c;
        int k = tileentity.d - this.k * 16;

        this.a(i, j, k, tileentity);
    }

    public void a(int i, int j, int k, TileEntity tileentity) {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);

        tileentity.a = this.d;
        tileentity.b = this.j * 16 + i;
        tileentity.c = j;
        tileentity.d = this.k * 16 + k;
        if (this.a(i, j, k) != 0 && Block.n[this.a(i, j, k)] instanceof BlockContainer) {
            if (this.c) {
                if (this.l.get(chunkposition) != null) {
                    this.d.b.remove(this.l.get(chunkposition));
                }

                this.d.b.add(tileentity);
            }

            this.l.put(chunkposition, tileentity);
        } else {
            System.out.println("Attempted to place a tile entity where there was no entity tile!");
        }
    }

    public void e(int i, int j, int k) {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);

        if (this.c) {
            this.d.b.remove(this.l.remove(chunkposition));
        }
    }

    public void c() {
        this.c = true;
        this.d.b.addAll(this.l.values());

        for (int i = 0; i < this.m.length; ++i) {
            this.d.a(this.m[i]);
        }
    }

    public void d() {
        this.c = false;
        this.d.b.removeAll(this.l.values());

        for (int i = 0; i < this.m.length; ++i) {
            this.d.b(this.m[i]);
        }
    }

    public void e() {
        this.o = true;
    }

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list) {
        int i = MathHelper.b((axisalignedbb.b - 2.0D) / 16.0D);
        int j = MathHelper.b((axisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        }

        if (j >= this.m.length) {
            j = this.m.length - 1;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.m[k];

            for (int l = 0; l < list1.size(); ++l) {
                Entity entity1 = (Entity) list1.get(l);

                if (entity1 != entity && entity1.v.a(axisalignedbb)) {
                    list.add(entity1);
                }
            }
        }
    }

    public void a(Class oclass, AxisAlignedBB axisalignedbb, List list) {
        int i = MathHelper.b((axisalignedbb.b - 2.0D) / 16.0D);
        int j = MathHelper.b((axisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        }

        if (j >= this.m.length) {
            j = this.m.length - 1;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.m[k];

            for (int l = 0; l < list1.size(); ++l) {
                Entity entity = (Entity) list1.get(l);

                if (oclass.isAssignableFrom(entity.getClass()) && entity.v.a(axisalignedbb)) {
                    list.add(entity);
                }
            }
        }
    }

    public boolean a(boolean flag) {
        return this.p ? false : (this.r && this.d.c != this.s ? true : this.o);
    }

    public int a(byte[] abyte, int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1;
        int i2;
        int j2;
        int k2;

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = l1 << 11 | i2 << 7 | j;
                k2 = i1 - j;
                System.arraycopy(this.b, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = (l1 << 11 | i2 << 7 | j) >> 1;
                k2 = (i1 - j) / 2;
                System.arraycopy(this.e.a, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = (l1 << 11 | i2 << 7 | j) >> 1;
                k2 = (i1 - j) / 2;
                System.arraycopy(this.g.a, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = (l1 << 11 | i2 << 7 | j) >> 1;
                k2 = (i1 - j) / 2;
                System.arraycopy(this.f.a, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        return k1;
    }

    public Random a(long i) {
        return new Random(this.d.t + (long) (this.j * this.j * 4987142) + (long) (this.j * 5947611) + (long) (this.k * this.k) * 4392871L + (long) (this.k * 389711) ^ i);
    }
}
