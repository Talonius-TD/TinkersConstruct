package tconstruct.plugins.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.blocks.logic.CastingChannelLogic;

import java.util.List;

public class CastingChannelDataProvider implements IWailaDataProvider
{

    @Override
    public ItemStack getWailaStack (IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead (ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody (ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        if (accessor.getTileEntity() instanceof CastingChannelLogic)
        {
            CastingChannelLogic te = (CastingChannelLogic) accessor.getTileEntity();
            if (te.liquid != null && te.liquid.amount > 0)
            {
                FluidStack fs = te.liquid;
                currenttip.add(StatCollector.translateToLocal("tconstruct.waila.liquidtag") + WailaRegistrar.fluidNameHelper(fs));
                currenttip.add(StatCollector.translateToLocal("tconstruct.waila.amounttag") + fs.amount + "/" + te.getCapacity());
            }
            else
            {
                currenttip.add("§o" + StatCollector.translateToLocal("tconstruct.waila.empty")); // "§o" == Italics
            }
        }

        return currenttip;
    }

}
