/*
 * Copyright (c) bdew, 2013 - 2017
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.api;

import net.bdew.pressure.api.properties.IFilterable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IFilterableProvider {
    /**
     * Provide IFilterable for something in the world
     *
     * @return IFilterable instance or null if not applicable
     */
    IFilterable getFilterableForWorldCoordinates(World world, BlockPos pos, EnumFacing side);
}
