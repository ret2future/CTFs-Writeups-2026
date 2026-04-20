package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class eg {
    public static final eg a;
    public static final eg b;
    public static final eg c;
    public static final eg d;
    public static final eg e;
    public static final /* synthetic */ eg[] f;

    static {
        eg egVar = new eg("DESTROYED", 0);
        a = egVar;
        eg egVar2 = new eg("INITIALIZED", 1);
        b = egVar2;
        eg egVar3 = new eg("CREATED", 2);
        c = egVar3;
        eg egVar4 = new eg("STARTED", 3);
        d = egVar4;
        eg egVar5 = new eg("RESUMED", 4);
        e = egVar5;
        f = new eg[]{egVar, egVar2, egVar3, egVar4, egVar5};
    }

    public static eg valueOf(String str) {
        return (eg) Enum.valueOf(eg.class, str);
    }

    public static eg[] values() {
        return (eg[]) f.clone();
    }
}
