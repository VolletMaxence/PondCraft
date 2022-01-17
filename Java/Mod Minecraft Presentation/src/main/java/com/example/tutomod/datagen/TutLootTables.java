package com.example.tutomod.datagen;

import com.example.tutomod.setup.Registration;
import net.minecraft.data.DataGenerator;

public class TutLootTables extends BaseLootTableProvider {

    public TutLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.PLATINUM_ORE.get(), createSilkTouchTable("mysterious_ore", Registration.PLATINUM_ORE.get(), Registration.RAW_PLATINUM.get(), 1, 3));
        lootTables.put(Registration.PLATINUM_ORE_DEEPSLATE.get(), createSilkTouchTable("mysterious_ore_deepslate", Registration.PLATINUM_ORE_DEEPSLATE.get(), Registration.RAW_PLATINUM.get(), 1, 3));
    }
}
