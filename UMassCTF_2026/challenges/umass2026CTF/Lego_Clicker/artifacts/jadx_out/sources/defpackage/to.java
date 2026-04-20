package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class to {
    public static final boolean a;
    public static final x5 b;
    public static final x5 c;
    public static final x5 d;

    static {
        boolean z;
        try {
            Class.forName("java.sql.Date");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        a = z;
        if (z) {
            b = ro.c;
            c = ro.d;
            d = so.c;
        } else {
            b = null;
            c = null;
            d = null;
        }
    }
}
