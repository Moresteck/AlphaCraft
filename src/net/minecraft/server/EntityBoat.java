package net.minecraft.server;

import java.util.List;

public class EntityBoat extends Entity {

    public int a = 0;
    public int b = 0;
    public int ad = 1;
    private int dNew;
    private double eNew;
    private double fNew;
    private double aiNew;
    private double ajNew;
    private double akNew;

    public EntityBoat(World world) {
        super(world);
        this.e = true;
        this.a(1.5F, 0.6F);
        this.C = this.E / 2.0F;
        this.H = false;
    }

    public AxisAlignedBB d(Entity entity) {
        return entity.v;
    }

    public AxisAlignedBB n() {
        return this.v;
    }

    public boolean r() {
        return true;
    }

    public EntityBoat(World world, double d0, double d1, double d2) {
        this(world);
        this.a(d0, d1 + (double) this.G, d2);
        this.o = 0.0D;
        this.p = 0.0D;
        this.q = 0.0D;
        this.i = d0;
        this.j = d1;
        this.k = d2;
    }

    public double h() {
        return (double) this.E * 0.0D - 0.30000001192092896D;
    }

    public boolean a(Entity entity, int i) {
/*        this.ad = -this.ad;
        this.b = 10;
        this.a += i * 10;
        if (this.a > 40) {
            int j;

            for (j = 0; j < 3; ++j) {
                this.a(Block.WOOD.bc, 1, 0.0F);
            }

            for (j = 0; j < 2; ++j) {
                this.a(Item.STICK.aS, 1, 0.0F);
            }

            this.j();
        }

        return true;*/
    	// ALPHACRAFT
    	if (!this.h.x && !this.B) {
            this.ad = -this.ad;
            this.b = 10;
            this.a += i * 10;
            this.uNew();
            if (this.a > 40) {
                int j;

                for (j = 0; j < 3; ++j) {
                    this.a(Block.WOOD.bc, 1, 0.0F);
                }

                for (j = 0; j < 2; ++j) {
                    this.a(Item.STICK.aS, 1, 0.0F);
                }

                this.j();
            }

            return true;
        } else {
        	return true;
        }
    }

    public boolean c_() {
        return !this.B;
    }

    public void b_() {
        /*super.b_();
        if (this.b > 0) {
            --this.b;
        }

        if (this.a > 0) {
            --this.a;
        }

        this.i = this.l;
        this.j = this.m;
        this.k = this.n;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.v.b + (this.v.e - this.v.b) * (double) (i + 0) / (double) b0 - 0.125D;
            double d2 = this.v.b + (this.v.e - this.v.b) * (double) (i + 1) / (double) b0 - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.b(this.v.a, d1, this.v.c, this.v.d, d2, this.v.f);

            if (this.h.b(axisalignedbb, Material.f)) {
                d0 += 1.0D / (double) b0;
            }
        }

        double d3 = d0 * 2.0D - 1.0D;

        this.p += 0.03999999910593033D * d3;
        if (this.f != null) {
            this.o += this.f.o * 0.2D;
            this.q += this.f.q * 0.2D;
        }

        double d4 = 0.4D;

        if (this.o < -d4) {
            this.o = -d4;
        }

        if (this.o > d4) {
            this.o = d4;
        }

        if (this.q < -d4) {
            this.q = -d4;
        }

        if (this.q > d4) {
            this.q = d4;
        }

        if (this.w) {
            this.o *= 0.5D;
            this.p *= 0.5D;
            this.q *= 0.5D;
        }

        this.c(this.o, this.p, this.q);
        double d5 = Math.sqrt(this.o * this.o + this.q * this.q);
        double d6;
        double d7;

        if (d5 > 0.15D) {
            d6 = Math.cos((double) this.r * 3.141592653589793D / 180.0D);
            d7 = Math.sin((double) this.r * 3.141592653589793D / 180.0D);

            for (int j = 0; (double) j < 1.0D + d5 * 60.0D; ++j) {
                double d8 = (double) (this.R.nextFloat() * 2.0F - 1.0F);
                double d9 = (double) (this.R.nextInt(2) * 2 - 1) * 0.7D;
                double d10;
                double d11;

                if (this.R.nextBoolean()) {
                    d10 = this.l - d6 * d8 * 0.8D + d7 * d9;
                    d11 = this.n - d7 * d8 * 0.8D - d6 * d9;
                    this.h.a("splash", d10, this.m - 0.125D, d11, this.o, this.p, this.q);
                } else {
                    d10 = this.l + d6 + d7 * d8 * 0.7D;
                    d11 = this.n + d7 - d6 * d8 * 0.7D;
                    this.h.a("splash", d10, this.m - 0.125D, d11, this.o, this.p, this.q);
                }
            }
        }

        if (this.x && d5 > 0.15D) {
            this.j();

            int k;

            for (k = 0; k < 3; ++k) {
                this.a(Block.WOOD.bc, 1, 0.0F);
            }

            for (k = 0; k < 2; ++k) {
                this.a(Item.STICK.aS, 1, 0.0F);
            }
        } else {
            this.o *= 0.9900000095367432D;
            this.p *= 0.949999988079071D;
            this.q *= 0.9900000095367432D;
        }

        this.s = 0.0F;
        d6 = (double) this.r;
        d7 = this.i - this.l;
        double d12 = this.k - this.n;

        if (d7 * d7 + d12 * d12 > 0.0010D) {
            d6 = (double) ((float) (Math.atan2(d12, d7) * 180.0D / 3.141592653589793D));
        }

        double d13;

        for (d13 = d6 - (double) this.r; d13 >= 180.0D; d13 -= 360.0D) {
            ;
        }

        while (d13 < -180.0D) {
            d13 += 360.0D;
        }

        if (d13 > 20.0D) {
            d13 = 20.0D;
        }

        if (d13 < -20.0D) {
            d13 = -20.0D;
        }

        this.r = (float) ((double) this.r + d13);
        this.b(this.r, this.s);
        List list = this.h.b((Entity) this, this.v.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && list.size() > 0) {
            for (int l = 0; l < list.size(); ++l) {
                Entity entity = (Entity) list.get(l);

                if (entity != this.f && entity.r() && entity instanceof EntityBoat) {
                    entity.c((Entity) this);
                }
            }
        }

        if (this.f != null && this.f.B) {
            this.f = null;
        }*/
        // -------------------------------- ALPHACRAFT
        super.b_();
        if (this.b > 0) {
            --this.b;
        }

        if (this.a > 0) {
            --this.a;
        }

        this.i = this.l;
        this.j = this.m;
        this.k = this.n;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.v.b + (this.v.e - this.v.b) * (double) (i + 0) / (double) b0 - 0.125D;
            double d2 = this.v.b + (this.v.e - this.v.b) * (double) (i + 1) / (double) b0 - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.b(this.v.a, d1, this.v.c, this.v.d, d2, this.v.f);

            if (this.h.b(axisalignedbb, Material.f)) {
                d0 += 1.0D / (double) b0;
            }
        }

        double d3;
        double d4;
        double d5;
        double d6;

        if (this.h.x) {
            if (this.ae > 0) {
                d3 = this.l + (this.eNew - this.l) / (double) this.ae;
                d4 = this.m + (this.fNew - this.m) / (double) this.ae;
                d5 = this.n + (this.aiNew - this.n) / (double) this.ae;

                for (d6 = this.ajNew - (double) this.r; d6 < -180.0D; d6 += 360.0D) {
                    ;
                }

                while (d6 >= 180.0D) {
                    d6 -= 360.0D;
                }

                this.r = (float) ((double) this.r + d6 / (double) this.ae);
                this.s = (float) ((double) this.s + (this.akNew - (double) this.s) / (double) this.ae);
                --this.ae;
                this.a(d3, d4, d5);
                this.b(this.r, this.s);
            } else {
                d3 = this.l + this.o;
                d4 = this.m + this.p;
                d5 = this.n + this.q;
                this.a(d3, d4, d5);
                if (this.w) {
                    this.o *= 0.5D;
                    this.p *= 0.5D;
                    this.q *= 0.5D;
                }

                this.o *= 0.9900000095367432D;
                this.p *= 0.949999988079071D;
                this.q *= 0.9900000095367432D;
            }
        } else {
            d3 = d0 * 2.0D - 1.0D;
            this.p += 0.03999999910593033D * d3;
            if (this.f != null) {
                this.o += this.f.o * 0.2D;
                this.q += this.f.q * 0.2D;
            }

            d4 = 0.4D;
            if (this.o < -d4) {
                this.o = -d4;
            }

            if (this.o > d4) {
                this.o = d4;
            }

            if (this.q < -d4) {
                this.q = -d4;
            }

            if (this.q > d4) {
                this.q = d4;
            }

            if (this.w) {
                this.o *= 0.5D;
                this.p *= 0.5D;
                this.q *= 0.5D;
            }

            this.c(this.o, this.p, this.q);
            d5 = Math.sqrt(this.o * this.o + this.q * this.q);
            double d7;

            if (d5 > 0.15D) {
                d6 = Math.cos((double) this.r * 3.141592653589793D / 180.0D);
                d7 = Math.sin((double) this.r * 3.141592653589793D / 180.0D);

                for (int j = 0; (double) j < 1.0D + d5 * 60.0D; ++j) {
                    double d8 = (double) (this.R.nextFloat() * 2.0F - 1.0F);
                    double d9 = (double) (this.R.nextInt(2) * 2 - 1) * 0.7D;
                    double d10;
                    double d11;

                    if (this.R.nextBoolean()) {
                        d10 = this.l - d6 * d8 * 0.8D + d7 * d9;
                        d11 = this.n - d7 * d8 * 0.8D - d6 * d9;
                        this.h.a("splash", d10, this.m - 0.125D, d11, this.o, this.p, this.q);
                    } else {
                        d10 = this.l + d6 + d7 * d8 * 0.7D;
                        d11 = this.n + d7 - d6 * d8 * 0.7D;
                        this.h.a("splash", d10, this.m - 0.125D, d11, this.o, this.p, this.q);
                    }
                }
            }

            if (this.x && d5 > 0.15D) {
                if (!this.h.x) {
                    this.l();

                    int k;

                    for (k = 0; k < 3; ++k) {
                        this.a(Block.WOOD.bc, 1, 0.0F);
                    }

                    for (k = 0; k < 2; ++k) {
                        this.a(Item.STICK.aS, 1, 0.0F);
                    }
                }
            } else {
                this.o *= 0.9900000095367432D;
                this.p *= 0.949999988079071D;
                this.q *= 0.9900000095367432D;
            }

            this.s = 0.0F;
            d6 = (double) this.n;
            d7 = this.i - this.l;
            double d12 = this.k - this.n;

            if (d7 * d7 + d12 * d12 > 0.0010D) {
                d6 = (double) ((float) (Math.atan2(d12, d7) * 180.0D / 3.141592653589793D));
            }

            double d13;

            for (d13 = d6 - (double) this.r; d13 >= 180.0D; d13 -= 360.0D) {
                ;
            }

            while (d13 < -180.0D) {
                d13 += 360.0D;
            }

            if (d13 > 20.0D) {
                d13 = 20.0D;
            }

            if (d13 < -20.0D) {
                d13 = -20.0D;
            }

            this.r = (float) ((double) this.r + d13);
            this.b(this.r, this.s);
            List list = this.h.b((Entity) this, this.v.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int l = 0; l < list.size(); ++l) {
                    Entity entity = (Entity) list.get(l);

                    if (entity != this.f && entity.r() && entity instanceof EntityBoat) {
                        entity.c((Entity) this);
                    }
                }
            }

            if (this.f != null && this.f.B) {
                this.f = null;
            }
        }
    }

    public void w() {
    	// ALPHACRAFT
    	if (this.f == null) {
    		return;
    	}
        double d0 = Math.cos((double) this.r * 3.141592653589793D / 180.0D) * 0.4D;
        double d1 = Math.sin((double) this.r * 3.141592653589793D / 180.0D) * 0.4D;

        this.f.a(this.l + d0, this.m + this.h() + this.f.x(), this.n + d1);
    }

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}

    public boolean aNew(EntityHuman entityhuman) {
        if (this.f != null && this.f instanceof EntityHuman && this.f != entityhuman) {
            return true;
        } else {
            if (!this.h.x) {
                entityhuman.e(this);
            }

            return true;
        }
    }
}
