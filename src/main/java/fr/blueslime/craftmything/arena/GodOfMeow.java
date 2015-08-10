package fr.blueslime.craftmything.arena;

import de.slikey.effectlib.effect.CubeEffect;
import de.slikey.effectlib.util.ParticleEffect;
import fr.blueslime.craftmything.CraftMyThing;
import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;

public class GodOfMeow extends EntityOcelot
{
    private final CubeEffect cubeEffect;

    public GodOfMeow(World world, Location location)
    {
        super(world);

        this.cubeEffect = new CubeEffect(CraftMyThing.getInstance().getEffectManager());
        this.cubeEffect.setLocation(location.clone().subtract(0.0D, 5.0D, 0.0D));
        this.cubeEffect.particle = ParticleEffect.FIREWORKS_SPARK;
        this.cubeEffect.infinite();
        this.cubeEffect.start();
    }

    @Override
    public void g(float sideMot, float forMot)
    {
        this.k(0.0F);
        this.motY = 0.0D;
        this.motX = 0.0D;
        this.motZ = 0.0D;

        super.g(sideMot, forMot);
    }

    @Override
    protected String z()
    {
        return "";
    }

    @Override
    protected String bp()
    {
        return "";
    }

    @Override
    protected String bo()
    {
        return "";
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean c(NBTTagCompound nbttagcompound)
    {
        return false;
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean d(NBTTagCompound nbttagcompound)
    {
        return false;
    }

    @Override
    public void e(NBTTagCompound nbttagcompound) {}

    public CubeEffect getCubeEffect()
    {
        return this.cubeEffect;
    }
}
