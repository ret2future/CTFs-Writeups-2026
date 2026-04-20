package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dg {
    private static final /* synthetic */ dg[] $VALUES;
    public static final bg Companion;
    public static final dg ON_ANY;
    public static final dg ON_CREATE;
    public static final dg ON_DESTROY;
    public static final dg ON_PAUSE;
    public static final dg ON_RESUME;
    public static final dg ON_START;
    public static final dg ON_STOP;

    static {
        dg dgVar = new dg("ON_CREATE", 0);
        ON_CREATE = dgVar;
        dg dgVar2 = new dg("ON_START", 1);
        ON_START = dgVar2;
        dg dgVar3 = new dg("ON_RESUME", 2);
        ON_RESUME = dgVar3;
        dg dgVar4 = new dg("ON_PAUSE", 3);
        ON_PAUSE = dgVar4;
        dg dgVar5 = new dg("ON_STOP", 4);
        ON_STOP = dgVar5;
        dg dgVar6 = new dg("ON_DESTROY", 5);
        ON_DESTROY = dgVar6;
        dg dgVar7 = new dg("ON_ANY", 6);
        ON_ANY = dgVar7;
        $VALUES = new dg[]{dgVar, dgVar2, dgVar3, dgVar4, dgVar5, dgVar6, dgVar7};
        Companion = new bg();
    }

    public static dg valueOf(String str) {
        return (dg) Enum.valueOf(dg.class, str);
    }

    public static dg[] values() {
        return (dg[]) $VALUES.clone();
    }

    public final eg a() {
        switch (cg.a[ordinal()]) {
            case 1:
            case 2:
                return eg.c;
            case 3:
            case 4:
                return eg.d;
            case 5:
                return eg.e;
            case 6:
                return eg.a;
            default:
                throw new IllegalArgumentException(this + " has no target state");
        }
    }
}
