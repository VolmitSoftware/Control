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

package com.volmit.control.api.service;

import com.volmit.control.api.ControlModule;
import org.bukkit.event.Listener;

public abstract class ControlService implements ControlModule, Listener
{
    public ControlService()
    {
        register();
        start();
    }

    public abstract void start();

    public abstract void stop();
}
