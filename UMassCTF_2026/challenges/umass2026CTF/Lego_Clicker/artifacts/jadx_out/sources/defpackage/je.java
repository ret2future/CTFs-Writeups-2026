package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.example.LegoClicker.model.Building;
import com.example.LegoClicker.model.BuildingType;
import com.example.LegoClicker.model.GameState;
import com.example.LegoClicker.model.Upgrade;
import com.example.LegoClicker.model.UpgradeType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class je {
    public b6 h;
    public wh j;
    public double a = 0.0d;
    public double b = 0.0d;
    public double c = 1.0d;
    public final EnumMap d = new EnumMap(BuildingType.class);
    public final EnumMap e = new EnumMap(BuildingType.class);
    public final EnumMap f = new EnumMap(UpgradeType.class);
    public final Handler g = new Handler(Looper.getMainLooper());
    public boolean i = false;

    public je() {
        for (BuildingType buildingType : BuildingType.values()) {
            this.d.put(buildingType, new Building(buildingType));
            this.e.put(buildingType, Double.valueOf(1.0d));
        }
        for (UpgradeType upgradeType : UpgradeType.values()) {
            this.f.put(upgradeType, new Upgrade(upgradeType));
        }
    }

    public final boolean a(UpgradeType upgradeType) {
        Upgrade upgrade = (Upgrade) this.f.get(upgradeType);
        if (upgrade == null || upgrade.purchased) {
            return false;
        }
        double d = this.a;
        double d2 = upgradeType.cost;
        if (d < d2) {
            return false;
        }
        this.a = d - d2;
        upgrade.purchased = true;
        this.c *= upgradeType.clickMultiplier;
        BuildingType buildingType = upgradeType.targetBuilding;
        if (buildingType != null) {
            EnumMap enumMap = this.e;
            Double d3 = (Double) enumMap.get(buildingType);
            enumMap.put(upgradeType.targetBuilding, Double.valueOf((d3 != null ? d3.doubleValue() : 1.0d) * upgradeType.buildingMultiplier));
        }
        wh whVar = this.j;
        if (whVar != null) {
            whVar.e();
        }
        return true;
    }

    public final void b(GameState gameState) {
        EnumMap enumMap;
        this.a = gameState.bricks;
        this.b = gameState.totalBricksEarned;
        this.c = gameState.clickMultiplier;
        Map<String, Integer> map = gameState.buildingOwned;
        if (map != null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                try {
                    Building building = (Building) this.d.get(BuildingType.valueOf(entry.getKey()));
                    if (building != null) {
                        building.owned = entry.getValue().intValue();
                    }
                } catch (IllegalArgumentException unused) {
                }
            }
        }
        BuildingType[] buildingTypeArrValues = BuildingType.values();
        int length = buildingTypeArrValues.length;
        int i = 0;
        while (true) {
            enumMap = this.e;
            if (i >= length) {
                break;
            }
            enumMap.put(buildingTypeArrValues[i], Double.valueOf(1.0d));
            i++;
        }
        this.c = 1.0d;
        List<String> list = gameState.purchasedUpgrades;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                try {
                    UpgradeType upgradeTypeValueOf = UpgradeType.valueOf(it.next());
                    Upgrade upgrade = (Upgrade) this.f.get(upgradeTypeValueOf);
                    if (upgrade != null) {
                        upgrade.purchased = true;
                        this.c *= upgradeTypeValueOf.clickMultiplier;
                        BuildingType buildingType = upgradeTypeValueOf.targetBuilding;
                        if (buildingType != null) {
                            Double d = (Double) enumMap.get(buildingType);
                            enumMap.put(upgradeTypeValueOf.targetBuilding, Double.valueOf((d != null ? d.doubleValue() : 1.0d) * upgradeTypeValueOf.buildingMultiplier));
                        }
                    }
                } catch (IllegalArgumentException unused2) {
                }
            }
        }
    }

    public final ArrayList c() {
        ArrayList arrayList = new ArrayList();
        for (Upgrade upgrade : this.f.values()) {
            if (!upgrade.purchased && upgrade.isUnlocked(this.d)) {
                arrayList.add(upgrade);
            }
        }
        return arrayList;
    }

    public final double d() {
        double totalBps = 0.0d;
        for (BuildingType buildingType : BuildingType.values()) {
            Building building = (Building) this.d.get(buildingType);
            Double d = (Double) this.e.get(buildingType);
            if (building != null && d != null) {
                totalBps = building.getTotalBps(d.doubleValue()) + totalBps;
            }
        }
        return totalBps;
    }

    public final GameState e() {
        GameState gameState = new GameState();
        gameState.bricks = this.a;
        gameState.totalBricksEarned = this.b;
        gameState.clickMultiplier = this.c;
        gameState.lastSaveTimestamp = System.currentTimeMillis();
        HashMap map = new HashMap();
        for (Map.Entry entry : this.d.entrySet()) {
            map.put(((BuildingType) entry.getKey()).name(), Integer.valueOf(((Building) entry.getValue()).owned));
        }
        gameState.buildingOwned = map;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry2 : this.f.entrySet()) {
            if (((Upgrade) entry2.getValue()).purchased) {
                arrayList.add(((UpgradeType) entry2.getKey()).name());
            }
        }
        gameState.purchasedUpgrades = arrayList;
        return gameState;
    }
}
