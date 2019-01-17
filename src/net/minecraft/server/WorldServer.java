package net.minecraft.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorldServer extends World {

    public ChunkProviderServer y;
    public boolean z = false;
    public boolean A;
    private boolean B;
    private SpawnerCreature C = new SpawnerMonsters(this, 200, IMonster.class, new Class[] { EntityZombie.class, EntitySkeleton.class, EntityCreeper.class, EntitySpider.class, EntitySlime.class});
    private SpawnerCreature D = new SpawnerCreature(15, EntityAnimal.class, new Class[] { EntitySheep.class, EntityPig.class, EntityCow.class, EntityChicken.class});
    private MinecraftServer server;
    private EntityList E = new EntityList(); // ALPHACRAFT

    public WorldServer(MinecraftServer minecraftserver, File file1, String s, boolean flag) {
        super(file1, s);
        this.B = flag;
        this.server = minecraftserver;
    }

    public void e() {
        super.e();
        if (this.B) {
            this.C.a(this);
        }

        this.D.a(this);
    }

    public void a(Entity entity, boolean flag) {

        if (entity.f == null || !(entity.f instanceof EntityHuman)) {
            super.e(entity, flag);
        }
    }

    public void b(Entity entity, boolean flag) {
        super.e(entity, flag);
    }

    protected IChunkProvider a(File file1) {
        this.y = new ChunkProviderServer(this, new ChunkLoader(file1, true), new ChunkProviderGenerate(this, this.t));
        return this.y;
    }

    public List d(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.b.size(); ++k1) {
            TileEntity tileentity = (TileEntity) this.b.get(k1);

            if (tileentity.b >= i && tileentity.c >= j && tileentity.d >= k && tileentity.b < l && tileentity.c < i1 && tileentity.d < j1) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    // ALPHACRAFT
    public boolean a(EntityHuman entityhuman, int i, int j, int k) {
        int l = (int) MathHelper.e((float) (i - this.n));
        int i1 = (int) MathHelper.e((float) (k - this.p));

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.server.f.g(entityhuman.aq);
    }

    protected void b(Entity entity) {
        super.b(entity);
        this.E.a(entity.c, entity);
    }

    protected void c(Entity entity) {
        super.c(entity);
        this.E.d(entity.c);
    }

    public Entity a(int i) {
        return (Entity) this.E.a(i);
    }

    public Explosion a(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
        Explosion explosion = super.a(entity, d0, d1, d2, f, flag);

        return explosion;
    }
}
