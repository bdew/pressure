/*
 * Copyright (c) bdew, 2013 - 2015
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.blocks.router.gui

import net.bdew.lib.Misc
import net.bdew.lib.gui.widgets.Widget
import net.bdew.lib.gui.{Point, Rect}
import net.bdew.pressure.PressureResourceProvider
import net.bdew.pressure.blocks.router.{RouterIcons, TileRouter}
import net.minecraftforge.common.util.ForgeDirection

import scala.collection.mutable

class RouterSideIcon(p: Point, te: TileRouter, side: ForgeDirection) extends Widget {
  val rect = new Rect(p, 16, 16)

  override def draw(mouse: Point) {
    parent.drawTexture(rect, RouterIcons.modeIcons(te.sideModes.get(side)), PressureResourceProvider.outputColors(side.ordinal()))
  }

  override def handleTooltip(p: Point, tip: mutable.MutableList[String]) {
    tip += "%s (%s)".format(Misc.toLocal("pressure.router.side." + side.ordinal()), Misc.toLocal("bdlib.multiblock.face." + side.toString.toLowerCase))
    tip += Misc.toLocal("pressure.router.mode." + te.sideModes.get(side).toString.toLowerCase)
  }
}