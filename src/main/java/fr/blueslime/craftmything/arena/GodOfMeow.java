package fr.blueslime.craftmything.arena;

import de.slikey.effectlib.effect.CubeEffect;
import de.slikey.effectlib.util.ParticleEffect;
import fr.blueslime.craftmything.CraftMyThing;
import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;

/*
 * This file is part of CraftMyThing.
 *
 * CraftMyThing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CraftMyThing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CraftMyThing.  If not, see <http://www.gnu.org/licenses/>.
 */
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
