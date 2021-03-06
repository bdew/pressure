/*
 * Copyright (c) bdew, 2013 - 2016
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.compat.jei

import java.util

import mezz.jei.api._
import net.bdew.pressure.config.Config
import net.bdew.pressure.items.Canister
import net.bdew.pressure.misc.PressureCreativeTabs
import net.minecraft.item.ItemStack

@JEIPlugin
class PressureJeiPlugin extends BlankModPlugin {
  override def register(registry: IModRegistry): Unit = {
    if (!Config.showCanisters) {
      import scala.collection.JavaConversions._
      val list = new util.ArrayList[ItemStack]()
      Canister.getSubItems(Canister, PressureCreativeTabs.canisters, list)
      list.foreach(registry.getJeiHelpers.getItemBlacklist.addItemToBlacklist)
    }
  }
}
