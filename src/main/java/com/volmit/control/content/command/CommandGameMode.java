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

package com.volmit.control.content.command;

import com.volmit.control.api.command.ControlCommandCluster;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static art.arcane.amulet.MagicalSugar.*;

public class CommandGameMode extends ControlCommandCluster {
    public CommandGameMode() {
        alias("gamemode", "gm");
    }

    public void gamemode(GameMode gamemode) {
        player().setGameMode(gamemode);
    }

    public void gamemode(Player player, GameMode gamemode) {
        player.setGameMode(gamemode);
    }

    public void gamemode() {
        sender().sendMessage("Your Current Game Mode is "
                + (player().getGameMode().name()lc).capitalizeWords()
                + " (ID: " + player().getGameMode().ordinal() + ")");
    }
}
