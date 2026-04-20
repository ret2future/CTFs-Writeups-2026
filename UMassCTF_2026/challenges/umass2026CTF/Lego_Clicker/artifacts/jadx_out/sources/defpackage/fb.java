package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class fb {
    public int a = 1;
    public final ij b;
    public ij c;
    public ij d;
    public int e;
    public int f;

    public fb(ij ijVar) {
        this.b = ijVar;
        this.c = ijVar;
    }

    public final void a() {
        this.a = 1;
        this.c = this.b;
        this.f = 0;
    }

    public final boolean b() {
        gj gjVarB = this.c.b.b();
        int iA = gjVarB.a(6);
        return !(iA == 0 || ((ByteBuffer) gjVarB.d).get(iA + gjVarB.a) == 0) || this.e == 65039;
    }
}
