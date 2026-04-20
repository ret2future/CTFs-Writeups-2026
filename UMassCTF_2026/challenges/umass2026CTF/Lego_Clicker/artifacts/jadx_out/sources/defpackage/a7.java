package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class a7 {
    public long a = 0;
    public a7 b;

    public final void a(int i) {
        if (i < 64) {
            this.a &= ~(1 << i);
            return;
        }
        a7 a7Var = this.b;
        if (a7Var != null) {
            a7Var.a(i - 64);
        }
    }

    public final int b(int i) {
        a7 a7Var = this.b;
        if (a7Var == null) {
            long j = this.a;
            return i >= 64 ? Long.bitCount(j) : Long.bitCount(((1 << i) - 1) & j);
        }
        if (i < 64) {
            return Long.bitCount(((1 << i) - 1) & this.a);
        }
        return Long.bitCount(this.a) + a7Var.b(i - 64);
    }

    public final void c() {
        if (this.b == null) {
            this.b = new a7();
        }
    }

    public final boolean d(int i) {
        if (i < 64) {
            return ((1 << i) & this.a) != 0;
        }
        c();
        return this.b.d(i - 64);
    }

    public final void e(int i, boolean z) {
        if (i >= 64) {
            c();
            this.b.e(i - 64, z);
            return;
        }
        long j = this.a;
        boolean z2 = (Long.MIN_VALUE & j) != 0;
        long j2 = (1 << i) - 1;
        this.a = ((j & (~j2)) << 1) | (j & j2);
        if (z) {
            h(i);
        } else {
            a(i);
        }
        if (z2 || this.b != null) {
            c();
            this.b.e(0, z2);
        }
    }

    public final boolean f(int i) {
        if (i >= 64) {
            c();
            return this.b.f(i - 64);
        }
        long j = 1 << i;
        long j2 = this.a;
        boolean z = (j2 & j) != 0;
        long j3 = j2 & (~j);
        this.a = j3;
        long j4 = j - 1;
        this.a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
        a7 a7Var = this.b;
        if (a7Var != null) {
            if (a7Var.d(0)) {
                h(63);
            }
            this.b.f(0);
        }
        return z;
    }

    public final void g() {
        this.a = 0L;
        a7 a7Var = this.b;
        if (a7Var != null) {
            a7Var.g();
        }
    }

    public final void h(int i) {
        if (i < 64) {
            this.a |= 1 << i;
        } else {
            c();
            this.b.h(i - 64);
        }
    }

    public final String toString() {
        if (this.b == null) {
            return Long.toBinaryString(this.a);
        }
        return this.b.toString() + "xx" + Long.toBinaryString(this.a);
    }
}
