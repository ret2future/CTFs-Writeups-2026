package com.example.LegoClicker.model;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public enum BuildingType {
    BRICK_MOLDER("Brick Molder", 15.0d, 0.1d, "Stamps out bricks one at a time"),
    LEGO_ASSEMBLER("Lego Assembler", 100.0d, 1.0d, "Snaps bricks together automatically"),
    MINIFIG_WORKSHOP("Mini-Fig Workshop", 1100.0d, 8.0d, "Tiny workers building tiny things"),
    LEGO_STORE("Lego Store", 12000.0d, 47.0d, "A retail outlet selling sets"),
    DESIGN_STUDIO("Design Studio", 130000.0d, 260.0d, "Where master builders dream up sets"),
    LEGO_FACTORY("Lego Factory", 1400000.0d, 1400.0d, "Industrial-scale brick production");

    public final double baseBps;
    public final double baseCost;
    public final String description;
    public final String displayName;

    BuildingType(String str, double d, double d2, String str2) {
        this.displayName = str;
        this.baseCost = d;
        this.baseBps = d2;
        this.description = str2;
    }
}
