package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static final i b;
    public static final i c;
    public final Throwable a;

    static {
        if (o.d) {
            c = null;
            b = null;
        } else {
            c = new i(false, null);
            b = new i(true, null);
        }
    }

    public i(boolean z, Throwable th) {
        this.a = th;
    }
}
