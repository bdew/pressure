/*
 * Copyright (c) bdew, 2013 - 2017
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.blocks.router.data

import net.bdew.lib.data.base.{DataSlot, DataSlotContainer, UpdateKind}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.fluids.{Fluid, FluidRegistry}

case class DataSlotSideFilters(name: String, parent: DataSlotContainer) extends DataSlot {
  var map = Map.empty[EnumFacing, Fluid]

  def get(d: EnumFacing) = map(d)
  def isSet(d: EnumFacing) = map.isDefinedAt(d)
  def set(d: EnumFacing, f: Fluid) = {
    map += d -> f
    parent.dataSlotChanged(this)
  }
  def clear(d: EnumFacing) = {
    map -= d
    parent.dataSlotChanged(this)
  }

  override def save(t: NBTTagCompound, kind: UpdateKind.Value): Unit = {
    val tag = new NBTTagCompound
    for ((dir, filter) <- map) tag.setString(dir.ordinal().toString, filter.getName)
    t.setTag(name, tag)
  }

  override def load(t: NBTTagCompound, kind: UpdateKind.Value): Unit = {
    map = Map.empty
    if (t.hasKey(name)) {
      for {
        dir <- EnumFacing.values()
        fName <- Option(t.getCompoundTag(name).getString(dir.ordinal().toString)) if fName.nonEmpty && FluidRegistry.isFluidRegistered(fName)
        fluid <- Option(FluidRegistry.getFluid(fName))
      } {
        map += dir -> fluid
      }
    }
  }
}
