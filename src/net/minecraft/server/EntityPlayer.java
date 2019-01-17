package net.minecraft.server;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pl.moresteck.alphacraft.PlayerSettings;

public class EntityPlayer extends EntityHuman {

    public NetServerHandler a;
    public MinecraftServer b;
    public ItemInWorldManager ad;
    public double ae;
    public double af;
    public List ag = new LinkedList();
    public Set ah = new HashSet();
    public double ai;
    public boolean al = false; // ALPHACRAFT
    private int bu = -99999999;
    private int bw = 60;
    public PlayerSettings settings; // ALPHACRAFT
    public boolean customClient; // ALPHACRAFT

    public EntityPlayer(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager) {
        super(world);
        this.settings = new PlayerSettings(s); // ALPHACRAFT
        int i = world.n; // ALPHACRAFT - exact spawn
        int j = world.p;
        int k = world.d(i, j);

        this.c((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.b = minecraftserver;
        this.N = 0.0F;
        iteminworldmanager.a = this;
        this.aq = s;
        this.ad = iteminworldmanager;
        this.C = 0.0F;
    }

    public void b_() {
    	--this.bw;
    }

    public void f(Entity entity) {
    	this.aj.f();
    }

    public boolean a(Entity entity, int i) {
    	if (this.bw > 0) {
    		return false;
    	}
    	if (entity instanceof EntityHuman) {
            return false;
        }

        if (entity instanceof EntityArrow) {
            EntityArrow entityarrow = (EntityArrow) entity;

            if (entityarrow.ah instanceof EntityHuman) {
                return false;
            }
        }

        return super.a(entity, i);
    }

    public void restoreHealth(int i) {
    	super.restoreHealth(i);
    }

    public void i() {
        super.b_();
        ChunkCoordIntPair chunkcoordintpair = null;
        double d0 = 0.0D;

        for (int i = 0; i < this.ag.size(); ++i) {
            ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) this.ag.get(i);
            double d1 = chunkcoordintpair1.a(this);

            if (i == 0 || d1 < d0) {
                chunkcoordintpair = chunkcoordintpair1;
                d0 = chunkcoordintpair1.a(this);
            }
        }

        if (chunkcoordintpair != null) {
            boolean flag = false;

            if (d0 < 1024.0D) {
                flag = true;
            }

            if (this.a.b() < 2) {
                flag = true;
            }

            if (flag) {
                this.ag.remove(chunkcoordintpair);
                this.a.b((Packet) (new Packet51MapChunk(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, 16, 128, 16, this.b.e)));
                List list = this.b.e.d(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, chunkcoordintpair.a * 16 + 16, 128, chunkcoordintpair.b * 16 + 16);

                for (int j = 0; j < list.size(); ++j) {
                    TileEntity tileentity = (TileEntity) list.get(j);

                    this.a.b((Packet) (new Packet59ComplexEntity(tileentity.b, tileentity.c, tileentity.d, tileentity)));
                }
            }
        }

        if (this.aM != this.bu) { // ALPHACRAFT
            this.a.b((Packet) (new Packet3Chat("Zdrowie: " + this.aM + " / 20")));
            this.bu = this.aM;
        }
    }

    public void y() {
        this.o = this.p = this.q = 0.0D;
        this.bg = false;
        super.y();
    }

    public void c(Entity entity, int i) {
        if (!entity.B) {
        	if (entity instanceof EntityItem) {
        		this.a.b((Packet) (new Packet17AddToInventory(((EntityItem) entity).a, i)));
                this.b.k.a(entity, new Packet22Collect(entity.c, this.c));
        	}

            if (entity instanceof EntityArrow) {
                this.a.b((Packet) (new Packet17AddToInventory(new ItemStack(Item.ARROW), 1)));
                this.b.k.a(entity, new Packet22Collect(entity.c, this.c));
            }
        }

        super.c(entity, i);
    }

    public void z() {
    	ItemStack hand = this.aj.b();
    	if (hand == null) {
    	} else if (hand.a() instanceof ItemBow && this.settings.getArrowShoot()) {
    		((ItemBow) hand.a()).a(hand, this.h, this);
    		this.a.updateInventory();
    	} else if (hand.a() instanceof ItemFood && this.settings.getEatFood()) {
    		((ItemFood) hand.a()).a(hand, this.h, this);
    		this.a.updateInventory();
    	}
        if (!this.ao) {
            this.ap = -1;
            this.ao = true;
            this.b.k.a(this, new Packet18ArmAnimation(this, 1));
        }
    }

    public float p() {
        return 1.62F;
    }

    public void e(Entity entity) {
        super.e(entity);
        this.a.a(this.l, this.m, this.n, this.r, this.s);
    }

    /*public void G() {
        this.aM = 20;
        int i = this.h.n;
        int j = this.h.p;
        int k = this.h.o;

        k = this.h.d(i, j);

        this.a.a((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.a.b((Packet) (new Packet6SpawnPosition(i, k, j)));
        this.bu = -1;
        this.U = 0;
        this.w = true;
        this.Y = 300;
        this.V = 300;
        this.aV = false;
        this.B = false;
    }*/

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    public boolean m() {
        return this.al;
    }
}
