package com.github.technus.tectech.nei;

import static com.github.technus.tectech.Reference.MODID;
import static net.minecraft.util.EnumChatFormatting.*;

import appeng.util.ReadableNumberConverter;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.github.technus.tectech.recipe.EyeOfHarmonyRecipe;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregtech.api.enums.GT_Values;
import gregtech.api.util.GT_Recipe;
import gregtech.nei.GT_NEI_DefaultHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class TT_NEI_EyeOfHarmonyHandler extends GT_NEI_DefaultHandler {

    public TT_NEI_EyeOfHarmonyHandler(final GT_Recipe.GT_Recipe_Map tMap) {
        super(tMap);
        if (!NEI_TT_Config.sIsAdded) {

            FMLInterModComms.sendRuntimeMessage(
                    GT_Values.GT,
                    "NEIPlugins",
                    "register-crafting-handler",
                    MODID + "@" + this.getRecipeName() + "@" + this.getOverlayIdentifier());
            GuiCraftingRecipe.craftinghandlers.add(this);
            GuiUsageRecipe.usagehandlers.add(this);
        }
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new TT_NEI_EyeOfHarmonyHandler(this.mRecipeMap);
    }
}
