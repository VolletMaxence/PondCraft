package com.example.tutomod.datagen;

import com.example.tutomod.tutomod;
import com.example.tutomod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider {

    public TutBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, tutomod.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.PLATINUM_ORE.get())
                .add(Registration.PLATINUM_ORE_DEEPSLATE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.PLATINUM_ORE.get())
                .add(Registration.PLATINUM_ORE_DEEPSLATE.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.PLATINUM_ORE.get())
                .add(Registration.PLATINUM_ORE_DEEPSLATE.get());

        tag(Registration.PLATINUM_ORES)
                .add(Registration.PLATINUM_ORE.get())
                .add(Registration.PLATINUM_ORE_DEEPSLATE.get());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
