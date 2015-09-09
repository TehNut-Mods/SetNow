package tehnut.setnow;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import tehnut.setnow.proxy.CommonProxy;
import tehnut.setnow.util.EventHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.DEPEND)
public class SetNow {

    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabPact = new CreativeTabs(ModInformation.ID + ".creativeTab") {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(Items.apple);
        }

        @Override
        public Item getTabIconItem() {
            return Items.apple;
        }
    };

    @Mod.Instance
    public static SetNow instance;

    public static List<Integer> blacklistDims = new ArrayList<Integer>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(new File(event.getModConfigurationDirectory(), ModInformation.ID + ".cfg"));

        for (int dim : ConfigHandler.blacklistDims)
            blacklistDims.add(dim);

        EventHandler eventHandler = new EventHandler();
        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);

        proxy.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}