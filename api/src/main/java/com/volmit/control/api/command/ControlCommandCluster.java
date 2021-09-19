/*
 * Control is a Core/Essential Plugin for Minecraft Bukkit Servers
 * Copyright (c) 2021 Arcane Arts (Volmit Software)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.volmit.control.api.command;

import com.volmit.control.api.ControlAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ControlCommandCluster
{
    private final Map<String, Set<String>> aliasMap = new HashMap<>();

    public CommandSender sender()
    {
        return ControlAPI.kernel().contextSender();
    }

    public Player player()
    {
        return sender() instanceof Player p ? p : null;
    }

    public boolean isPlayer()
    {
        return sender() instanceof Player;
    }

    public World world()
    {
        return isPlayer() ? player().getWorld() : null;
    }

    public Location location()
    {
        return isPlayer() ? player().getLocation() : null;
    }

    public Location crosshair()
    {
        Block m = player().getTargetBlockExact(256);

        if(m == null)
        {
            return null;
        }

        return isPlayer() ? m.getLocation() : null;
    }

    public void alias(String name, String...aliases)
    {
        aliasMap.computeIfAbsent(name.toLowerCase(), (f) -> new HashSet<>())
                .addAll(Arrays.stream(aliases).collect(Collectors.toSet()));
    }
}
