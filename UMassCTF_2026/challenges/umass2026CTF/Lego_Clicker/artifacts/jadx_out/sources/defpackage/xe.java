package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xe extends mt {
    public static final int[] k = new int[2];

    public static void m(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
        if (i8 <= i6) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else if (i9 <= i7) {
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    @Override // defpackage.t9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(defpackage.t9 r24) {
        /*
            Method dump skipped, instruction units count: 901
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xe.a(t9):void");
    }

    @Override // defpackage.mt
    public final void d() {
        k8 k8Var;
        k8 k8Var2;
        int i;
        k8 k8Var3;
        k8 k8Var4;
        int i2;
        k8 k8Var5 = this.b;
        boolean z = k8Var5.a;
        aa aaVar = this.e;
        if (z) {
            aaVar.d(k8Var5.o());
        }
        boolean z2 = aaVar.j;
        ArrayList arrayList = aaVar.k;
        ArrayList arrayList2 = aaVar.l;
        v9 v9Var = this.i;
        v9 v9Var2 = this.h;
        if (!z2) {
            k8 k8Var6 = this.b;
            int i3 = k8Var6.o0[0];
            this.d = i3;
            if (i3 != 3) {
                if (i3 == 4 && (k8Var4 = k8Var6.S) != null && ((i2 = k8Var4.o0[0]) == 1 || i2 == 4)) {
                    int iO = (k8Var4.o() - this.b.H.d()) - this.b.J.d();
                    mt.b(v9Var2, k8Var4.d.h, this.b.H.d());
                    mt.b(v9Var, k8Var4.d.i, -this.b.J.d());
                    aaVar.d(iO);
                    return;
                }
                if (i3 == 1) {
                    aaVar.d(k8Var6.o());
                }
            }
        } else if (this.d == 4 && (k8Var2 = (k8Var = this.b).S) != null && ((i = k8Var2.o0[0]) == 1 || i == 4)) {
            mt.b(v9Var2, k8Var2.d.h, k8Var.H.d());
            mt.b(v9Var, k8Var2.d.i, -this.b.J.d());
            return;
        }
        if (aaVar.j) {
            k8 k8Var7 = this.b;
            if (k8Var7.a) {
                v7[] v7VarArr = k8Var7.P;
                v7 v7Var = v7VarArr[0];
                v7 v7Var2 = v7Var.f;
                if (v7Var2 != null && v7VarArr[1].f != null) {
                    boolean zV = k8Var7.v();
                    k8 k8Var8 = this.b;
                    if (zV) {
                        v9Var2.f = k8Var8.P[0].d();
                        v9Var.f = -this.b.P[1].d();
                        return;
                    }
                    v9 v9VarH = mt.h(k8Var8.P[0]);
                    if (v9VarH != null) {
                        mt.b(v9Var2, v9VarH, this.b.P[0].d());
                    }
                    v9 v9VarH2 = mt.h(this.b.P[1]);
                    if (v9VarH2 != null) {
                        mt.b(v9Var, v9VarH2, -this.b.P[1].d());
                    }
                    v9Var2.b = true;
                    v9Var.b = true;
                    return;
                }
                if (v7Var2 != null) {
                    v9 v9VarH3 = mt.h(v7Var);
                    if (v9VarH3 != null) {
                        mt.b(v9Var2, v9VarH3, this.b.P[0].d());
                        mt.b(v9Var, v9Var2, aaVar.g);
                        return;
                    }
                    return;
                }
                v7 v7Var3 = v7VarArr[1];
                if (v7Var3.f != null) {
                    v9 v9VarH4 = mt.h(v7Var3);
                    if (v9VarH4 != null) {
                        mt.b(v9Var, v9VarH4, -this.b.P[1].d());
                        mt.b(v9Var2, v9Var, -aaVar.g);
                        return;
                    }
                    return;
                }
                if ((k8Var7 instanceof h6) || k8Var7.S == null || k8Var7.g(7).f != null) {
                    return;
                }
                k8 k8Var9 = this.b;
                mt.b(v9Var2, k8Var9.S.d.h, k8Var9.p());
                mt.b(v9Var, v9Var2, aaVar.g);
                return;
            }
        }
        if (this.d == 3) {
            k8 k8Var10 = this.b;
            int i4 = k8Var10.r;
            if (i4 == 2) {
                k8 k8Var11 = k8Var10.S;
                if (k8Var11 != null) {
                    aa aaVar2 = k8Var11.e.e;
                    arrayList2.add(aaVar2);
                    aaVar2.k.add(aaVar);
                    aaVar.b = true;
                    arrayList.add(v9Var2);
                    arrayList.add(v9Var);
                }
            } else if (i4 == 3) {
                if (k8Var10.s == 3) {
                    v9Var2.a = this;
                    v9Var.a = this;
                    wr wrVar = k8Var10.e;
                    wrVar.h.a = this;
                    wrVar.i.a = this;
                    aaVar.a = this;
                    if (k8Var10.w()) {
                        arrayList2.add(this.b.e.e);
                        this.b.e.e.k.add(aaVar);
                        wr wrVar2 = this.b.e;
                        wrVar2.e.a = this;
                        arrayList2.add(wrVar2.h);
                        arrayList2.add(this.b.e.i);
                        this.b.e.h.k.add(aaVar);
                        this.b.e.i.k.add(aaVar);
                    } else {
                        boolean zV2 = this.b.v();
                        k8 k8Var12 = this.b;
                        if (zV2) {
                            k8Var12.e.e.l.add(aaVar);
                            arrayList.add(this.b.e.e);
                        } else {
                            k8Var12.e.e.l.add(aaVar);
                        }
                    }
                } else {
                    aa aaVar3 = k8Var10.e.e;
                    arrayList2.add(aaVar3);
                    aaVar3.k.add(aaVar);
                    this.b.e.h.k.add(aaVar);
                    this.b.e.i.k.add(aaVar);
                    aaVar.b = true;
                    arrayList.add(v9Var2);
                    arrayList.add(v9Var);
                    v9Var2.l.add(aaVar);
                    v9Var.l.add(aaVar);
                }
            }
        }
        k8 k8Var13 = this.b;
        v7[] v7VarArr2 = k8Var13.P;
        v7 v7Var4 = v7VarArr2[0];
        v7 v7Var5 = v7Var4.f;
        if (v7Var5 != null && v7VarArr2[1].f != null) {
            boolean zV3 = k8Var13.v();
            k8 k8Var14 = this.b;
            if (zV3) {
                v9Var2.f = k8Var14.P[0].d();
                v9Var.f = -this.b.P[1].d();
                return;
            }
            v9 v9VarH5 = mt.h(k8Var14.P[0]);
            v9 v9VarH6 = mt.h(this.b.P[1]);
            if (v9VarH5 != null) {
                v9VarH5.b(this);
            }
            if (v9VarH6 != null) {
                v9VarH6.b(this);
            }
            this.j = 4;
            return;
        }
        if (v7Var5 != null) {
            v9 v9VarH7 = mt.h(v7Var4);
            if (v9VarH7 != null) {
                mt.b(v9Var2, v9VarH7, this.b.P[0].d());
                c(v9Var, v9Var2, 1, aaVar);
                return;
            }
            return;
        }
        v7 v7Var6 = v7VarArr2[1];
        if (v7Var6.f != null) {
            v9 v9VarH8 = mt.h(v7Var6);
            if (v9VarH8 != null) {
                mt.b(v9Var, v9VarH8, -this.b.P[1].d());
                c(v9Var2, v9Var, -1, aaVar);
                return;
            }
            return;
        }
        if ((k8Var13 instanceof h6) || (k8Var3 = k8Var13.S) == null) {
            return;
        }
        mt.b(v9Var2, k8Var3.d.h, k8Var13.p());
        c(v9Var, v9Var2, 1, aaVar);
    }

    @Override // defpackage.mt
    public final void e() {
        v9 v9Var = this.h;
        if (v9Var.j) {
            this.b.X = v9Var.g;
        }
    }

    @Override // defpackage.mt
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.mt
    public final boolean k() {
        return this.d != 3 || this.b.r == 0;
    }

    public final void n() {
        this.g = false;
        v9 v9Var = this.h;
        v9Var.c();
        v9Var.j = false;
        v9 v9Var2 = this.i;
        v9Var2.c();
        v9Var2.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.b.g0;
    }
}
