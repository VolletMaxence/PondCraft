package net.XenceV.pondcraft.entity.koi;

import java.util.Arrays;
import java.util.Comparator;

public enum KoiVariant {
    //Classic Variant
    TANCHO(0),
    KUMONRYU(1),
    KOHAKU(2),
    REDMATSUBA(3),
    YELLOWMATSUBA(4),
    SILVERMATSUBA(5),
    PATCH(6),
    HIHITSURI(7),
    BLUE(8),
    AYAWAKABA(9),
    GOSHIKI(10);

    //Custom Biome Variant

    //Magicarp Variant

    private static final KoiVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(KoiVariant::getId)).toArray(KoiVariant[]::new);
    private final int id;

    KoiVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static KoiVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
