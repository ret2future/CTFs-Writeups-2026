package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dr {
    public static final ThreadLocal d = new ThreadLocal();
    public final int a;
    public final kd b;
    public volatile int c = 0;

    public dr(kd kdVar, int i) {
        this.b = kdVar;
        this.a = i;
    }

    public final int a(int i) {
        gj gjVarB = b();
        int iA = gjVarB.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = (ByteBuffer) gjVarB.d;
        int i2 = iA + gjVarB.a;
        return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
    }

    public final gj b() {
        ThreadLocal threadLocal = d;
        gj gjVar = (gj) threadLocal.get();
        if (gjVar == null) {
            gjVar = new gj();
            threadLocal.set(gjVar);
        }
        hj hjVar = (hj) this.b.a;
        int iA = hjVar.a(6);
        if (iA != 0) {
            int i = iA + hjVar.a;
            int i2 = (this.a * 4) + ((ByteBuffer) hjVar.d).getInt(i) + i + 4;
            int i3 = ((ByteBuffer) hjVar.d).getInt(i2) + i2;
            ByteBuffer byteBuffer = (ByteBuffer) hjVar.d;
            gjVar.d = byteBuffer;
            if (byteBuffer != null) {
                gjVar.a = i3;
                int i4 = i3 - byteBuffer.getInt(i3);
                gjVar.b = i4;
                gjVar.c = ((ByteBuffer) gjVar.d).getShort(i4);
                return gjVar;
            }
            gjVar.a = 0;
            gjVar.b = 0;
            gjVar.c = 0;
        }
        return gjVar;
    }

    public final String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        gj gjVarB = b();
        int iA = gjVarB.a(4);
        sb.append(Integer.toHexString(iA != 0 ? ((ByteBuffer) gjVarB.d).getInt(iA + gjVarB.a) : 0));
        sb.append(", codepoints:");
        gj gjVarB2 = b();
        int iA2 = gjVarB2.a(16);
        if (iA2 != 0) {
            int i2 = iA2 + gjVarB2.a;
            i = ((ByteBuffer) gjVarB2.d).getInt(((ByteBuffer) gjVarB2.d).getInt(i2) + i2);
        } else {
            i = 0;
        }
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(Integer.toHexString(a(i3)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
