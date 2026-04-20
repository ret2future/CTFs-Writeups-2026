package com.example.LegoClicker.model;

import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class GameState {
    public double bricks;
    public Map<String, Integer> buildingOwned;
    public double clickMultiplier;
    public long lastSaveTimestamp;
    public List<String> purchasedUpgrades;
    public double totalBricksEarned;
}
