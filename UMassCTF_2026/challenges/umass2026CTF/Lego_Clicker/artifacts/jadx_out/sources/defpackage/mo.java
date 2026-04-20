package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class mo implements Comparable {
    public boolean a;
    public float e;
    public int l;
    public int b = -1;
    public int c = -1;
    public int d = 0;
    public boolean f = false;
    public final float[] g = new float[9];
    public final float[] h = new float[9];
    public v5[] i = new v5[16];
    public int j = 0;
    public int k = 0;

    public mo(int i) {
        this.l = i;
    }

    public final void a(v5 v5Var) {
        int i = 0;
        while (true) {
            int i2 = this.j;
            v5[] v5VarArr = this.i;
            if (i >= i2) {
                if (i2 >= v5VarArr.length) {
                    this.i = (v5[]) Arrays.copyOf(v5VarArr, v5VarArr.length * 2);
                }
                v5[] v5VarArr2 = this.i;
                int i3 = this.j;
                v5VarArr2[i3] = v5Var;
                this.j = i3 + 1;
                return;
            }
            if (v5VarArr[i] == v5Var) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void b(v5 v5Var) {
        int i = this.j;
        int i2 = 0;
        while (i2 < i) {
            if (this.i[i2] == v5Var) {
                while (i2 < i - 1) {
                    v5[] v5VarArr = this.i;
                    int i3 = i2 + 1;
                    v5VarArr[i2] = v5VarArr[i3];
                    i2 = i3;
                }
                this.j--;
                return;
            }
            i2++;
        }
    }

    public final void c() {
        this.l = 5;
        this.d = 0;
        this.b = -1;
        this.c = -1;
        this.e = 0.0f;
        this.f = false;
        int i = this.j;
        for (int i2 = 0; i2 < i; i2++) {
            this.i[i2] = null;
        }
        this.j = 0;
        this.k = 0;
        this.a = false;
        Arrays.fill(this.h, 0.0f);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.b - ((mo) obj).b;
    }

    public final void d(tg tgVar, float f) {
        this.e = f;
        this.f = true;
        int i = this.j;
        this.c = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.i[i2].h(tgVar, this, false);
        }
        this.j = 0;
    }

    public final void e(tg tgVar, v5 v5Var) {
        int i = this.j;
        for (int i2 = 0; i2 < i; i2++) {
            this.i[i2].i(tgVar, v5Var, false);
        }
        this.j = 0;
    }

    public final String toString() {
        return "" + this.b;
    }
}
