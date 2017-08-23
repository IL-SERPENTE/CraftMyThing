package fr.blueslime.craftmything.arena;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.api.schematics.ISchematic;
import net.samagames.api.schematics.Schematics;
import net.samagames.tools.JsonConfiguration;
import org.bukkit.Material;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
public class SchematicManager
{
    private final ArrayList<CMTSchematic> schematics;

    public SchematicManager()
    {
        this.schematics = new ArrayList<>();

        try
        {
            File folder = new File(CraftMyThing.getInstance().getDataFolder(), "schematics");
            JsonConfiguration jsonSchematicsConfiguration = new JsonConfiguration(new File(folder, "schematics.json"));
            JsonArray jsonSchematics = jsonSchematicsConfiguration.load().getAsJsonArray();

            for (int i = 0; i < jsonSchematics.size(); i++)
            {
                JsonObject jsonSchematic = jsonSchematics.get(i).getAsJsonObject();
                ISchematic schematic = Schematics.load(new File(folder, jsonSchematic.get("file").getAsString()));
                EnumZone zone = EnumZone.valueOf(jsonSchematic.get("zone").getAsString().toUpperCase());

                JsonArray jsonBlacklist = jsonSchematic.get("blacklist").getAsJsonArray();
                ArrayList<Material> blacklist = new ArrayList<>();

                for (int j = 0; j < jsonBlacklist.size(); j++)
                    blacklist.add(Material.valueOf(jsonBlacklist.get(j).getAsString()));

                this.schematics.add(new CMTSchematic(schematic, zone, blacklist));
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public CMTSchematic randomSchematic(CMTSchematic last)
    {
        ArrayList<CMTSchematic> schematicList = new ArrayList<>(this.schematics);

        if(last != null)
            schematicList.remove(last);

        return schematicList.get(new Random().nextInt(schematicList.size()));
    }
}
