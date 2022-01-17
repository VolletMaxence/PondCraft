package com.example.tutomod.datagen;

import com.example.tutomod.tutomod;
import com.example.tutomod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.example.tutomod.setup.ModSetup.TAB_NAME;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(DataGenerator gen, String locale) {
        super(gen, tutomod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME , "Tutorial");

        add(Registration.PLATINUM_ORE.get(), "Platinum Ore");
        add(Registration.PLATINUM_ORE_DEEPSLATE.get(), "Platinum Ore");

        add(Registration.RAW_PLATINUM.get(), "Raw Platinum");
        add(Registration.PLATINUM_INGOT.get(), "Platinum Ingot");
    }
}
