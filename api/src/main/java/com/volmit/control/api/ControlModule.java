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

package com.volmit.control.api;

import com.volmit.control.api.service.ControlService;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public interface ControlModule {
    default Plugin plugin() {
        return Bukkit.getPluginManager().getPlugin("Control");
    }

    default void register() {
        register(this);
    }

    default String name() {
        return getClass().getSimpleName().replaceAll("\\QService\\E", "");
    }

    default Server server() {
        return plugin().getServer();
    }

    default PluginManager pluginManager() {
        return plugin().getServer().getPluginManager();
    }

    default void registerListener(Listener l) {
        pluginManager().registerEvents(l, plugin());
    }

    default void register(Object registerable) {
        if (registerable instanceof ControlService s) {
            ControlAPI.kernel().registerService(s);
        }

        if (registerable instanceof Listener l) {
            registerListener(l);
        }
    }
}
