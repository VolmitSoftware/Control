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

package com.volmit.control;

import art.arcane.amulet.logging.LogListener;
import com.volmit.control.api.ControlAPI;
import com.volmit.control.api.ControlKernel;
import com.volmit.control.api.ControlModule;
import com.volmit.control.api.service.ControlService;
import com.volmit.control.system.service.ContextService;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Control extends JavaPlugin implements ControlKernel, ControlModule, LogListener {
    private List<ControlService> services;
    @Inject
    public ContextService contextService;

    public void onEnable() {
        ControlAPI.registerKernel(this);
        services = new ArrayList<>();
        LogListener.listener.set(this);
    }

    public void onDisable() {
        services.forEach(HandlerList::unregisterAll);
        services.forEach(ControlService::stop);
        services.clear();
        HandlerList.unregisterAll(this);
    }

    @Override
    public CommandSender contextSender() {
        return contextService.sender();
    }

    @Override
    public void registerService(ControlService service) {
        services.add(service);
    }

    private CommandSender console() {
        return getServer().getConsoleSender();
    }

    @Override
    public void i(String tag, Object f) {
        console().sendMessage(ChatColor.WHITE + "[I/" + tag + "]: " + ChatColor.GRAY + f);
    }

    @Override
    public void f(String tag, Object f) {
        console().sendMessage(ChatColor.RED + "[F/" + tag + "]: " + ChatColor.RED + f);
    }

    @Override
    public void w(String tag, Object f) {
        console().sendMessage(ChatColor.GOLD + "[W/" + tag + "]: " + ChatColor.WHITE + f);
    }

    @Override
    public void d(String tag, Object f) {
        console().sendMessage(ChatColor.LIGHT_PURPLE + "[D/" + tag + "]: " + ChatColor.GRAY + f);
    }
}
