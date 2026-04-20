package com.example.LegoClicker.model;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class LeaderboardEntry {
    public boolean isPlayer;
    public long timestamp;
    public double totalBricksEarned;
    public String username;

    public LeaderboardEntry(double d, long j, String str, boolean z) {
        this.totalBricksEarned = d;
        this.timestamp = j;
        this.username = str;
        this.isPlayer = z;
    }

    public LeaderboardEntry() {
    }
}
