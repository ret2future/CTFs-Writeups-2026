package defpackage;

import android.content.SharedPreferences;
import com.example.LegoClicker.model.LeaderboardEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class pn {
    public static final String d = b(86, 95, 93, 85, 101, 89, 86, 83, 89, 81, 95, 72, 101, 74, 72, 95, 92, 73);
    public static final String e = b(93, 75, 87, 95, 101, 89, 94, 75, 94, 95);
    public static final String f = b(86, 95, 75, 78, 95, 72, 88, 85, 75, 72, 78);
    public static final String g = b(86, 95, 75, 78, 95, 72, 88, 85, 75, 72, 78, 101, 89, 95, 95, 78, 101, 76, 13);
    public static final String[] h = {"BrickMaster3000", "NinjagoKai", "LloydTheGreen", "MasterWu", "EmmetBrickowski", "WyldStyle", "GoodCopBadCop", "UniKitty", "MetalBeard", "VitruviusX", "ZaneTitan", "ColeRocksteady", "JayLightning", "NyaWater", "PixalBot", "GarmadonLord", "EvergreenBrick", "StudsOfFury", "BrickNinja99", "LegoLegend"};
    public final SharedPreferences a;
    public final Random c = new Random();
    public final re b = new re();

    public pn(l2 l2Var) {
        this.a = l2Var.getSharedPreferences(d, 0);
    }

    public static String b(int... iArr) {
        char[] cArr = new char[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            cArr[i] = (char) (iArr[i] ^ 58);
        }
        return new String(cArr);
    }

    public final ArrayList a() {
        String str = g;
        SharedPreferences sharedPreferences = this.a;
        if (!sharedPreferences.getBoolean(str, false)) {
            return d();
        }
        String string = sharedPreferences.getString(f, null);
        if (string == null) {
            return d();
        }
        try {
            LeaderboardEntry[] leaderboardEntryArr = (LeaderboardEntry[]) this.b.b(string, LeaderboardEntry[].class);
            if (leaderboardEntryArr != null && leaderboardEntryArr.length > 0) {
                return new ArrayList(Arrays.asList(leaderboardEntryArr));
            }
        } catch (Exception unused) {
        }
        return d();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c(double d2) {
        String str;
        ArrayList arrayListA = a();
        int size = arrayListA.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                str = null;
                break;
            }
            Object obj = arrayListA.get(i);
            i++;
            LeaderboardEntry leaderboardEntry = (LeaderboardEntry) obj;
            if (leaderboardEntry.isPlayer) {
                str = leaderboardEntry.username;
                break;
            }
        }
        if (str == null) {
            Random random = this.c;
            String[] strArr = h;
            str = strArr[random.nextInt(strArr.length)];
        }
        arrayListA.removeIf(new on());
        arrayListA.add(new LeaderboardEntry(d2, System.currentTimeMillis(), str, true));
        arrayListA.sort(new ac(1));
        int size2 = arrayListA.size();
        List listSubList = arrayListA;
        if (size2 > 10) {
            listSubList = arrayListA.subList(0, 10);
        }
        this.a.edit().putString(f, this.b.e(listSubList)).apply();
    }

    public final ArrayList d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LeaderboardEntry(1.0E12d, 0L, "THE_BRICK_GOD", false));
        arrayList.add(new LeaderboardEntry(7.5E11d, 0L, "BenSimmons", false));
        arrayList.add(new LeaderboardEntry(4.85E10d, 0L, "GarmadonLord", false));
        arrayList.add(new LeaderboardEntry(1.23E10d, 0L, "NinjagoKai", false));
        arrayList.add(new LeaderboardEntry(3.75E9d, 0L, "EmmetBrickowski", false));
        arrayList.add(new LeaderboardEntry(9.0E8d, 0L, "MasterWu", false));
        this.a.edit().putString(f, this.b.e(arrayList)).putBoolean(g, true).commit();
        return new ArrayList(arrayList);
    }
}
