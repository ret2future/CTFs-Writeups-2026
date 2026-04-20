package com.example.LegoClicker.model;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'STRONGER_FINGERS' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class UpgradeType {
    private static final /* synthetic */ UpgradeType[] $VALUES;
    public static final UpgradeType AMBIDEXTROUS;
    public static final UpgradeType AUTOMATION;
    public static final UpgradeType CAD_SOFTWARE;
    public static final UpgradeType FRANCHISE_LICENSE;
    public static final UpgradeType PLASTIC_ALLOY;
    public static final UpgradeType REINFORCED_SNAPPER;
    public static final UpgradeType STRONGER_FINGERS;
    public static final UpgradeType TINY_HARD_HATS;
    public final double buildingMultiplier;
    public final double clickMultiplier;
    public final double cost;
    public final String description;
    public final String displayName;
    public final BuildingType targetBuilding;
    public final int unlockBuildingCount;
    public final BuildingType unlockBuildingType;

    private static /* synthetic */ UpgradeType[] $values() {
        return new UpgradeType[]{STRONGER_FINGERS, PLASTIC_ALLOY, REINFORCED_SNAPPER, AMBIDEXTROUS, TINY_HARD_HATS, FRANCHISE_LICENSE, CAD_SOFTWARE, AUTOMATION};
    }

    static {
        BuildingType buildingType = BuildingType.BRICK_MOLDER;
        STRONGER_FINGERS = new UpgradeType("STRONGER_FINGERS", 0, "Stronger Fingers", "Your clicking muscles bulk up.", 100.0d, 2.0d, null, 1.0d, buildingType, 1);
        PLASTIC_ALLOY = new UpgradeType("PLASTIC_ALLOY", 1, "Plastic Alloy", "Better materials for the Brick Molder.", 500.0d, 1.0d, buildingType, 2.0d, buildingType, 1);
        BuildingType buildingType2 = BuildingType.LEGO_ASSEMBLER;
        REINFORCED_SNAPPER = new UpgradeType("REINFORCED_SNAPPER", 2, "Reinforced Snapper", "Upgraded Assembler clamps.", 1000.0d, 1.0d, buildingType2, 2.0d, buildingType2, 1);
        AMBIDEXTROUS = new UpgradeType("AMBIDEXTROUS", 3, "Ambidextrous", "Use both hands! Click power doubles.", 5000.0d, 2.0d, null, 1.0d, buildingType, 10);
        BuildingType buildingType3 = BuildingType.MINIFIG_WORKSHOP;
        TINY_HARD_HATS = new UpgradeType("TINY_HARD_HATS", 4, "Tiny Hard Hats", "Safety gear boosts Mini-Fig output.", 10000.0d, 1.0d, buildingType3, 2.0d, buildingType3, 1);
        BuildingType buildingType4 = BuildingType.LEGO_STORE;
        FRANCHISE_LICENSE = new UpgradeType("FRANCHISE_LICENSE", 5, "Franchise License", "More Lego Stores open across the city.", 50000.0d, 1.0d, buildingType4, 2.0d, buildingType4, 1);
        BuildingType buildingType5 = BuildingType.DESIGN_STUDIO;
        CAD_SOFTWARE = new UpgradeType("CAD_SOFTWARE", 6, "CAD Software", "Design Studio runs cutting-edge 3D software.", 500000.0d, 1.0d, buildingType5, 2.0d, buildingType5, 1);
        BuildingType buildingType6 = BuildingType.LEGO_FACTORY;
        AUTOMATION = new UpgradeType("AUTOMATION", 7, "Full Automation", "Robots take over the Lego Factory.", 5000000.0d, 1.0d, buildingType6, 2.0d, buildingType6, 1);
        $VALUES = $values();
    }

    private UpgradeType(String str, int i, String str2, String str3, double d, double d2, BuildingType buildingType, double d3, BuildingType buildingType2, int i2) {
        this.displayName = str2;
        this.description = str3;
        this.cost = d;
        this.clickMultiplier = d2;
        this.targetBuilding = buildingType;
        this.buildingMultiplier = d3;
        this.unlockBuildingType = buildingType2;
        this.unlockBuildingCount = i2;
    }

    public static UpgradeType valueOf(String str) {
        return (UpgradeType) Enum.valueOf(UpgradeType.class, str);
    }

    public static UpgradeType[] values() {
        return (UpgradeType[]) $VALUES.clone();
    }
}
