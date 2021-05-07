package gregtech.common.tileentities.machines.steam;

import gregtech.api.GregTech_API;
import gregtech.api.gui.GT_GUIContainer_BasicMachine;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Steel;
import gregtech.api.objects.GT_RenderedGlowTexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.InventoryPlayer;

import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_STEAM_COMPRESSOR;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_STEAM_COMPRESSOR_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_STEAM_COMPRESSOR;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_STEAM_COMPRESSOR_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_STEAM_COMPRESSOR_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_STEAM_COMPRESSOR;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_STEAM_COMPRESSOR_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_STEAM_COMPRESSOR;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_STEAM_COMPRESSOR_ACTIVE;

public class GT_MetaTileEntity_Compressor_Steel extends GT_MetaTileEntity_BasicMachine_Steel {
    public GT_MetaTileEntity_Compressor_Steel(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional, "Compressing Items", 1, 1, false);
    }

    public GT_MetaTileEntity_Compressor_Steel(String aName, String aDescription, ITexture[][][] aTextures) {
        super(aName, aDescription, aTextures, 1, 1, false);
    }

    public GT_MetaTileEntity_Compressor_Steel(String aName, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aDescription, aTextures, 1, 1, false);
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_BasicMachine(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "SteelCompressor.png", GT_Recipe.GT_Recipe_Map.sCompressorRecipes.mUnlocalizedName);
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Compressor_Steel(this.mName, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public int checkRecipe() {
        GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sCompressorRecipes.findRecipe(getBaseMetaTileEntity(), false, gregtech.api.enums.GT_Values.V[2], null, getAllInputs());
        if ((tRecipe != null) && (canOutput(tRecipe.mOutputs)) && (tRecipe.isRecipeInputEqual(true, null, getAllInputs()))) {
            this.mOutputItems[0] = tRecipe.getOutput(0);
            this.mEUt = (tRecipe.mEUt * 2);
            this.mMaxProgresstime = tRecipe.mDuration;
            return 2;
        }
        return 0;
    }

    @Override
    public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
        super.startSoundLoop(aIndex, aX, aY, aZ);
        if (aIndex == 1) {
            GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(203), 10, 1.0F, aX, aY, aZ);
        }
    }

    @Override
    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public ITexture[] getSideFacingActive(byte aColor) {
        return new ITexture[]{
                super.getSideFacingActive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_SIDE_STEAM_COMPRESSOR_ACTIVE)};
    }

    @Override
    public ITexture[] getSideFacingInactive(byte aColor) {
        return new ITexture[]{
                super.getSideFacingInactive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_SIDE_STEAM_COMPRESSOR)};
    }

    @Override
    public ITexture[] getFrontFacingActive(byte aColor) {
        return new ITexture[]{
                super.getFrontFacingActive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_FRONT_STEAM_COMPRESSOR_ACTIVE),
                new GT_RenderedGlowTexture(OVERLAY_FRONT_STEAM_COMPRESSOR_ACTIVE_GLOW)};
    }

    @Override
    public ITexture[] getFrontFacingInactive(byte aColor) {
        return new ITexture[]{
                super.getFrontFacingInactive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_FRONT_STEAM_COMPRESSOR)};
    }

    @Override
    public ITexture[] getTopFacingActive(byte aColor) {
        return new ITexture[]{
                super.getTopFacingActive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_TOP_STEAM_COMPRESSOR_ACTIVE)};
    }

    @Override
    public ITexture[] getTopFacingInactive(byte aColor) {
        return new ITexture[]{
                super.getTopFacingInactive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_TOP_STEAM_COMPRESSOR)};
    }

    @Override
    public ITexture[] getBottomFacingActive(byte aColor) {
        return new ITexture[]{
                super.getBottomFacingActive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_BOTTOM_STEAM_COMPRESSOR_ACTIVE)};
    }

    @Override
    public ITexture[] getBottomFacingInactive(byte aColor) {
        return new ITexture[]{
                super.getBottomFacingInactive(aColor)[0],
                new GT_RenderedTexture(OVERLAY_BOTTOM_STEAM_COMPRESSOR)};
    }
}
