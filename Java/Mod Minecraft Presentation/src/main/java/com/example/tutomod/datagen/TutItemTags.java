package com.example.tutomod.datagen;

import com.example.tutomod.tutomod;
import com.example.tutomod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider {

    public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, tutomod.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.ORES)
                .add(Registration.PLATINUM_ORE_ITEM.get())
                .add(Registration.PLATINUM_ORE_DEEPSLATE_ITEM.get());

        tag(Tags.Items.INGOTS)
                .add(Registration.PLATINUM_INGOT.get());

        tag(Tags.Items.RAW_MATERIALS)
                .add(Registration.RAW_PLATINUM.get());


        tag(Registration.PLATINUM_ORES_ITEM)
                .add(Registration.PLATINUM_ORE_ITEM.get())
                .add(Registration.PLATINUM_ORE_DEEPSLATE_ITEM.get());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
