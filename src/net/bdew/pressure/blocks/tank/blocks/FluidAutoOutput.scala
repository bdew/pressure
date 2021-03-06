/*
 * Copyright (c) bdew, 2013 - 2016
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.blocks.tank.blocks

import net.bdew.lib.PimpVanilla._
import net.bdew.lib.capabilities.helpers.FluidHelper
import net.bdew.lib.multiblock.block.BlockOutput
import net.bdew.lib.multiblock.data.OutputConfigFluid
import net.bdew.pressure.blocks.tank.{BaseModule, TileFluidOutputBase}
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

object BlockFluidAutoOutput extends BaseModule("TankFluidAutoOutput", "FluidOutput", classOf[TileFluidAutoOutput]) with BlockOutput[TileFluidAutoOutput] {
  override def canConnectRedstone(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing): Boolean = true
}

class TileFluidAutoOutput extends TileFluidOutputBase {
  override def doOutput(face: EnumFacing, cfg: OutputConfigFluid) {
    for {
      core <- getCore if checkCanOutput(cfg)
      target <- FluidHelper.getFluidHandler(worldObj, pos.offset(face), face.getOpposite)
    } {
      for (handler <- core.getOutputTanks) {
        val filled = FluidHelper.pushFluid(handler, target)
        if (filled != null) addOutput(face, filled)
      }
    }
  }
}
