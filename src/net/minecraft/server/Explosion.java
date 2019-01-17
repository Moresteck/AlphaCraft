package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Explosion {
	public boolean a = false;
    private Random h = new Random();
    private World i;
    public double b;
    public double c;
    public double d;
    public Entity e;
    public float f;
    public Set g = new HashSet();

    public Explosion(World world, Entity entity, double d0, double d1, double d2, float f) {
        this.i = world;
        this.e = entity;
        this.f = f;
        this.b = d0;
        this.c = d1;
        this.d = d2;
    }

    public void a() {
        float f = this.f;
        byte b0 = 16;

        int i;
        int j;
        int k;
        double d0;
        double d1;
        double d2;

        for (i = 0; i < b0; ++i) {
            for (j = 0; j < b0; ++j) {
                for (k = 0; k < b0; ++k) {
                    if (i == 0 || i == b0 - 1 || j == 0 || j == b0 - 1 || k == 0 || k == b0 - 1) {
                        double d3 = (double) ((float) i / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d4 = (double) ((float) j / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d5 = (double) ((float) k / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = this.f * (0.7F + this.i.m.nextFloat() * 0.6F);

                        d0 = this.b;
                        d1 = this.c;
                        d2 = this.d;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                            int l = MathHelper.b(d0);
                            int i1 = MathHelper.b(d1);
                            int j1 = MathHelper.b(d2);
                            int k1 = this.i.a(l, i1, j1);

                            if (k1 > 0) {
                                f1 -= (Block.n[k1].a(this.e) + 0.3F) * f2;
                            }

                            if (f1 > 0.0F) {
                                this.g.add(new ChunkPosition(l, i1, j1));
                            }

                            d0 += d3 * (double) f2;
                            d1 += d4 * (double) f2;
                            d2 += d5 * (double) f2;
                        }
                    }
                }
            }
        }

        this.f *= 2.0F;
        i = MathHelper.b(this.b - (double) this.f - 1.0D);
        j = MathHelper.b(this.b + (double) this.f + 1.0D);
        k = MathHelper.b(this.c - (double) this.f - 1.0D);
        int l1 = MathHelper.b(this.c + (double) this.f + 1.0D);
        int i2 = MathHelper.b(this.d - (double) this.f - 1.0D);
        int j2 = MathHelper.b(this.d + (double) this.f + 1.0D);
        List list = this.i.b(this.e, AxisAlignedBB.b((double) i, (double) k, (double) i2, (double) j, (double) l1, (double) j2));
        Vec3D vec3d = Vec3D.b(this.b, this.c, this.d);

        for (int k2 = 0; k2 < list.size(); ++k2) {
            Entity entity = (Entity) list.get(k2);
            double d7 = entity.e(this.b, this.c, this.d) / (double) this.f;

            if (d7 <= 1.0D) {
                d0 = entity.p - this.b;
                d1 = entity.q - this.c;
                d2 = entity.r - this.d;
                double d8 = (double) MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

                d0 /= d8;
                d1 /= d8;
                d2 /= d8;
                double d9 = (double) this.i.a(vec3d, entity.v);
                double d10 = (1.0D - d7) * d9;

                entity.a(this.e, (int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) this.f + 1.0D));
                entity.o += d0 * d10;
                entity.p += d1 * d10;
                entity.q += d2 * d10;
            }
        }

        this.f = f;
        ArrayList arraylist = new ArrayList();

        arraylist.addAll(this.g);
        if (this.a) {
            for (int l2 = arraylist.size() - 1; l2 >= 0; --l2) {
                ChunkPosition chunkposition = (ChunkPosition) arraylist.get(l2);
                int i3 = chunkposition.a;
                int j3 = chunkposition.b;
                int k3 = chunkposition.c;
                int l3 = this.i.a(i3, j3, k3);
                int i4 = this.i.a(i3, j3 - 1, k3);

                if (l3 == 0 && Block.p[i4] && this.h.nextInt(3) == 0) {
                    this.i.d(i3, j3, k3, Block.FIRE.bc);
                }
            }
        }
    }

    public void b() {
        this.i.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.i.m.nextFloat() - this.i.m.nextFloat()) * 0.2F) * 0.7F);
        ArrayList arraylist = new ArrayList();

        arraylist.addAll(this.g);

        for (int i = arraylist.size() - 1; i >= 0; --i) {
            ChunkPosition chunkposition = (ChunkPosition) arraylist.get(i);
            int j = chunkposition.a;
            int k = chunkposition.b;
            int l = chunkposition.c;
            int i1 = this.i.a(j, k, l);

            for (int j1 = 0; j1 < 1; ++j1) {
                double d0 = (double) ((float) j + this.i.m.nextFloat());
                double d1 = (double) ((float) k + this.i.m.nextFloat());
                double d2 = (double) ((float) l + this.i.m.nextFloat());
                double d3 = d0 - this.b;
                double d4 = d1 - this.c;
                double d5 = d2 - this.d;
                double d6 = (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

                d3 /= d6;
                d4 /= d6;
                d5 /= d6;
                double d7 = 0.5D / (d6 / (double) this.f + 0.1D);

                d7 *= (double) (this.i.m.nextFloat() * this.i.m.nextFloat() + 0.3F);
                d3 *= d7;
                d4 *= d7;
                d5 *= d7;
                this.i.a("explode", (d0 + this.b * 1.0D) / 2.0D, (d1 + this.c * 1.0D) / 2.0D, (d2 + this.d * 1.0D) / 2.0D, d3, d4, d5);
                this.i.a("smoke", d0, d1, d2, d3, d4, d5);
            }

            if (i1 > 0) {
                Block.n[i1].a(this.i, j, k, l, this.i.b(j, k, l), 0.3F);
                this.i.d(j, k, l, 0);
                Block.n[i1].c(this.i, j, k, l);
            }
        }
    }

    /*public Explosion() {}

    public void a(World world, Entity entity, double d0, double d1, double d2, float f) {
        world.a(d0, d1, d2, "random.explode", 4.0F, (1.0F + (world.m.nextFloat() - world.m.nextFloat()) * 0.2F) * 0.7F);
        HashSet hashset = new HashSet();
        float f1 = f;
        byte b0 = 16;

        int i;
        int j;
        int k;
        double d3;
        double d4;
        double d5;

        for (i = 0; i < b0; ++i) {
            for (j = 0; j < b0; ++j) {
                for (k = 0; k < b0; ++k) {
                    if (i == 0 || i == b0 - 1 || j == 0 || j == b0 - 1 || k == 0 || k == b0 - 1) {
                        double d6 = (double) ((float) i / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d7 = (double) ((float) j / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d8 = (double) ((float) k / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d9 = Math.sqrt(d6 * d6 + d7 * d7 + d8 * d8);

                        d6 /= d9;
                        d7 /= d9;
                        d8 /= d9;
                        float f2 = f * (0.7F + world.m.nextFloat() * 0.6F);

                        d3 = d0;
                        d4 = d1;
                        d5 = d2;

                        for (float f3 = 0.3F; f2 > 0.0F; f2 -= f3 * 0.75F) {
                            int l = MathHelper.b(d3);
                            int i1 = MathHelper.b(d4);
                            int j1 = MathHelper.b(d5);
                            int k1 = world.a(l, i1, j1);

                            if (k1 > 0) {
                                f2 -= (Block.n[k1].a(entity) + 0.3F) * f3;
                            }

                            if (f2 > 0.0F) {
                                hashset.add(new ChunkPosition(l, i1, j1));
                            }

                            d3 += d6 * (double) f3;
                            d4 += d7 * (double) f3;
                            d5 += d8 * (double) f3;
                        }
                    }
                }
            }
        }

        f *= 2.0F;
        i = MathHelper.b(d0 - (double) f - 1.0D);
        j = MathHelper.b(d0 + (double) f + 1.0D);
        k = MathHelper.b(d1 - (double) f - 1.0D);
        int l1 = MathHelper.b(d1 + (double) f + 1.0D);
        int i2 = MathHelper.b(d2 - (double) f - 1.0D);
        int j2 = MathHelper.b(d2 + (double) f + 1.0D);
        List list = world.b(entity, AxisAlignedBB.b((double) i, (double) k, (double) i2, (double) j, (double) l1, (double) j2));
        Vec3D vec3d = Vec3D.b(d0, d1, d2);

        double d10;
        double d11;
        double d12;

        for (int k2 = 0; k2 < list.size(); ++k2) {
            Entity entity1 = (Entity) list.get(k2);
            double d13 = entity1.e(d0, d1, d2) / (double) f;

            if (d13 <= 1.0D) {
                d3 = entity1.l - d0;
                d4 = entity1.m - d1;
                d5 = entity1.n - d2;
                d11 = (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
                d3 /= d11;
                d4 /= d11;
                d5 /= d11;
                d10 = (double) world.a(vec3d, entity1.v);
                d12 = (1.0D - d13) * d10;
                entity1.a(entity, (int) ((d12 * d12 + d12) / 2.0D * 8.0D * (double) f + 1.0D));
                entity1.o += d3 * d12;
                entity1.p += d4 * d12;
                entity1.q += d5 * d12;
            }
        }

        f = f1;
        ArrayList arraylist = new ArrayList();

        arraylist.addAll(hashset);

        for (int l2 = arraylist.size() - 1; l2 >= 0; --l2) {
            ChunkPosition chunkposition = (ChunkPosition) arraylist.get(l2);
            int i3 = chunkposition.a;
            int j3 = chunkposition.b;
            int k3 = chunkposition.c;
            int l3 = world.a(i3, j3, k3);

            for (int i4 = 0; i4 < 1; ++i4) {
                d5 = (double) ((float) i3 + world.m.nextFloat());
                d11 = (double) ((float) j3 + world.m.nextFloat());
                d10 = (double) ((float) k3 + world.m.nextFloat());
                d12 = d5 - d0;
                double d14 = d11 - d1;
                double d15 = d10 - d2;
                double d16 = (double) MathHelper.a(d12 * d12 + d14 * d14 + d15 * d15);

                d12 /= d16;
                d14 /= d16;
                d15 /= d16;
                double d17 = 0.5D / (d16 / (double) f + 0.1D);

                d17 *= (double) (world.m.nextFloat() * world.m.nextFloat() + 0.3F);
                d12 *= d17;
                d14 *= d17;
                d15 *= d17;
                world.a("explode", (d5 + d0 * 1.0D) / 2.0D, (d11 + d1 * 1.0D) / 2.0D, (d10 + d2 * 1.0D) / 2.0D, d12, d14, d15);
                world.a("smoke", d5, d11, d10, d12, d14, d15);
            }

            if (l3 > 0) {
                Block.n[l3].a(world, i3, j3, k3, world.b(i3, j3, k3), 0.3F);
                world.d(i3, j3, k3, 0);
                Block.n[l3].c(world, i3, j3, k3);
            }
        }
    }*/
}
