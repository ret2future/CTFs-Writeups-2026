package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class we extends mt {
    @Override // defpackage.t9
    public final void a(t9 t9Var) {
        h6 h6Var = (h6) this.b;
        int i = h6Var.r0;
        v9 v9Var = this.h;
        ArrayList arrayList = v9Var.l;
        int size = arrayList.size();
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList.get(i4);
            i4++;
            int i5 = ((v9) obj).g;
            if (i3 == -1 || i5 < i3) {
                i3 = i5;
            }
            if (i2 < i5) {
                i2 = i5;
            }
        }
        if (i == 0 || i == 2) {
            v9Var.d(i3 + h6Var.t0);
        } else {
            v9Var.d(i2 + h6Var.t0);
        }
    }

    @Override // defpackage.mt
    public final void d() {
        k8 k8Var = this.b;
        if (k8Var instanceof h6) {
            v9 v9Var = this.h;
            v9Var.b = true;
            ArrayList arrayList = v9Var.l;
            h6 h6Var = (h6) k8Var;
            int i = h6Var.r0;
            boolean z = h6Var.s0;
            int i2 = 0;
            if (i == 0) {
                v9Var.e = 4;
                while (i2 < h6Var.q0) {
                    k8 k8Var2 = h6Var.p0[i2];
                    if (z || k8Var2.f0 != 8) {
                        v9 v9Var2 = k8Var2.d.h;
                        v9Var2.k.add(v9Var);
                        arrayList.add(v9Var2);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 1) {
                v9Var.e = 5;
                while (i2 < h6Var.q0) {
                    k8 k8Var3 = h6Var.p0[i2];
                    if (z || k8Var3.f0 != 8) {
                        v9 v9Var3 = k8Var3.d.i;
                        v9Var3.k.add(v9Var);
                        arrayList.add(v9Var3);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 2) {
                v9Var.e = 6;
                while (i2 < h6Var.q0) {
                    k8 k8Var4 = h6Var.p0[i2];
                    if (z || k8Var4.f0 != 8) {
                        v9 v9Var4 = k8Var4.e.h;
                        v9Var4.k.add(v9Var);
                        arrayList.add(v9Var4);
                    }
                    i2++;
                }
                m(this.b.e.h);
                m(this.b.e.i);
                return;
            }
            if (i != 3) {
                return;
            }
            v9Var.e = 7;
            while (i2 < h6Var.q0) {
                k8 k8Var5 = h6Var.p0[i2];
                if (z || k8Var5.f0 != 8) {
                    v9 v9Var5 = k8Var5.e.i;
                    v9Var5.k.add(v9Var);
                    arrayList.add(v9Var5);
                }
                i2++;
            }
            m(this.b.e.h);
            m(this.b.e.i);
        }
    }

    @Override // defpackage.mt
    public final void e() {
        k8 k8Var = this.b;
        if (k8Var instanceof h6) {
            int i = ((h6) k8Var).r0;
            v9 v9Var = this.h;
            if (i == 0 || i == 1) {
                k8Var.X = v9Var.g;
            } else {
                k8Var.Y = v9Var.g;
            }
        }
    }

    @Override // defpackage.mt
    public final void f() {
        this.c = null;
        this.h.c();
    }

    @Override // defpackage.mt
    public final boolean k() {
        return false;
    }

    public final void m(v9 v9Var) {
        v9 v9Var2 = this.h;
        v9Var2.k.add(v9Var);
        v9Var.l.add(v9Var2);
    }
}
