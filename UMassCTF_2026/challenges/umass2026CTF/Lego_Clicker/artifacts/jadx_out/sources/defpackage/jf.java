package defpackage;

import android.graphics.Insets;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class jf {
    public static final jf e = new jf(0, 0, 0, 0);
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public jf(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static jf a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? e : new jf(i, i2, i3, i4);
    }

    public final Insets b() {
        return hf.a(this.a, this.b, this.c, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || jf.class != obj.getClass()) {
            return false;
        }
        jf jfVar = (jf) obj;
        return this.d == jfVar.d && this.a == jfVar.a && this.c == jfVar.c && this.b == jfVar.b;
    }

    public final int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        return "Insets{left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + '}';
    }
}
