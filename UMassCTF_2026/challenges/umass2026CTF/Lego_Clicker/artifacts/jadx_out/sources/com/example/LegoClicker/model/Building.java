package com.example.LegoClicker.model;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class Building {
    public int owned = 0;
    public final BuildingType type;

    public Building(BuildingType buildingType) {
        this.type = buildingType;
    }

    public double getCurrentCost() {
        return Math.pow(1.15d, this.owned) * this.type.baseCost;
    }

    public double getTotalBps(double d) {
        return this.type.baseBps * ((double) this.owned) * d;
    }
}
