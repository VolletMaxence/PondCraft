package com.example.tutomod.datagen;

import com.example.tutomod.tutomod;
import com.example.tutomod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, tutomod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.PLATINUM_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/platinum_ore"));
        withExistingParent(Registration.PLATINUM_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/platinum_ore_deepslate"));

        singleTexture(Registration.RAW_PLATINUM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
        "layer0", modLoc("item/raw_platinum"));
        singleTexture(Registration.PLATINUM_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/platinum_ingot"));
    }
}
