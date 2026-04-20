package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class kh extends vs {
    public static final fr e = new fr(23);
    public final po d = new po();

    @Override // defpackage.vs
    public final void b() {
        po poVar = this.d;
        int i = poVar.c;
        Object[] objArr = poVar.b;
        if (i > 0) {
            objArr[0].getClass();
            z6.a();
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        poVar.c = 0;
    }
}
