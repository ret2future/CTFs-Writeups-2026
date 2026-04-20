package com.example.LegoClicker.model;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class Upgrade {
    public boolean purchased = false;
    public final UpgradeType type;

    public Upgrade(UpgradeType upgradeType) {
        this.type = upgradeType;
    }

    public boolean isUnlocked(Map<BuildingType, Building> map) {
        Building building = map.get(this.type.unlockBuildingType);
        return building != null && building.owned >= this.type.unlockBuildingCount;
    }
}
