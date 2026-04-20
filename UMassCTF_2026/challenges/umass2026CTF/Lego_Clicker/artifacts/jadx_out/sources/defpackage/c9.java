package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class c9 {
    public static final c9 a;
    public static final /* synthetic */ c9[] b;

    static {
        c9 c9Var = new c9("COROUTINE_SUSPENDED", 0);
        a = c9Var;
        b = new c9[]{c9Var, new c9("UNDECIDED", 1), new c9("RESUMED", 2)};
    }

    public static c9 valueOf(String str) {
        return (c9) Enum.valueOf(c9.class, str);
    }

    public static c9[] values() {
        return (c9[]) b.clone();
    }
}
