package shagejack.shagecraft.client.render.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItem;
import shagejack.shagecraft.tile.TileEntityForge;

public class TileEntityRendererForge extends ShageTileEntityRendererBase<TileEntityForge> {
    private EntityItem item;

    @Override
    public void render(TileEntityForge te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        if (!te.getStackInSlot(0).isEmpty())
            renderStillItem(te, te.getStackInSlot(0), 0.5F, 1.2F, 0.5F, 2F);
        GlStateManager.popMatrix();

        super.render(te, x, y + 1, z, partialTicks, destroyStage, alpha);
    }

}