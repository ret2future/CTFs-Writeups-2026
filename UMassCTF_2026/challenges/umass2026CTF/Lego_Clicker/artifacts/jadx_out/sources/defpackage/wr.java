package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class wr extends mt {
    public v9 k;
    public i6 l;

    @Override // defpackage.t9
    public final void a(t9 t9Var) {
        float f;
        float f2;
        float f3;
        int i;
        if (lo.c(this.j) == 3) {
            k8 k8Var = this.b;
            l(k8Var.I, k8Var.K, 1);
            return;
        }
        aa aaVar = this.e;
        if (aaVar.c && !aaVar.j && this.d == 3) {
            k8 k8Var2 = this.b;
            int i2 = k8Var2.s;
            if (i2 == 2) {
                k8 k8Var3 = k8Var2.S;
                if (k8Var3 != null) {
                    if (k8Var3.e.e.j) {
                        aaVar.d((int) ((r5.g * k8Var2.z) + 0.5f));
                    }
                }
            } else if (i2 == 3) {
                aa aaVar2 = k8Var2.d.e;
                if (aaVar2.j) {
                    int i3 = k8Var2.W;
                    if (i3 == -1) {
                        f = aaVar2.g;
                        f2 = k8Var2.V;
                    } else if (i3 == 0) {
                        f3 = aaVar2.g * k8Var2.V;
                        i = (int) (f3 + 0.5f);
                        aaVar.d(i);
                    } else if (i3 != 1) {
                        i = 0;
                        aaVar.d(i);
                    } else {
                        f = aaVar2.g;
                        f2 = k8Var2.V;
                    }
                    f3 = f / f2;
                    i = (int) (f3 + 0.5f);
                    aaVar.d(i);
                }
            }
        }
        v9 v9Var = this.h;
        boolean z = v9Var.c;
        ArrayList arrayList = v9Var.l;
        if (z) {
            v9 v9Var2 = this.i;
            boolean z2 = v9Var2.c;
            ArrayList arrayList2 = v9Var2.l;
            if (z2) {
                if (v9Var.j && v9Var2.j && aaVar.j) {
                    return;
                }
                if (!aaVar.j && this.d == 3) {
                    k8 k8Var4 = this.b;
                    if (k8Var4.r == 0 && !k8Var4.w()) {
                        v9 v9Var3 = (v9) arrayList.get(0);
                        v9 v9Var4 = (v9) arrayList2.get(0);
                        int i4 = v9Var3.g + v9Var.f;
                        int i5 = v9Var4.g + v9Var2.f;
                        v9Var.d(i4);
                        v9Var2.d(i5);
                        aaVar.d(i5 - i4);
                        return;
                    }
                }
                if (!aaVar.j && this.d == 3 && this.a == 1 && arrayList.size() > 0 && arrayList2.size() > 0) {
                    v9 v9Var5 = (v9) arrayList.get(0);
                    int i6 = (((v9) arrayList2.get(0)).g + v9Var2.f) - (v9Var5.g + v9Var.f);
                    int i7 = aaVar.m;
                    if (i6 < i7) {
                        aaVar.d(i6);
                    } else {
                        aaVar.d(i7);
                    }
                }
                if (aaVar.j && arrayList.size() > 0 && arrayList2.size() > 0) {
                    v9 v9Var6 = (v9) arrayList.get(0);
                    v9 v9Var7 = (v9) arrayList2.get(0);
                    int i8 = v9Var6.g;
                    int i9 = v9Var.f + i8;
                    int i10 = v9Var7.g;
                    int i11 = v9Var2.f + i10;
                    float f4 = this.b.d0;
                    if (v9Var6 == v9Var7) {
                        f4 = 0.5f;
                    } else {
                        i8 = i9;
                        i10 = i11;
                    }
                    v9Var.d((int) ((((i10 - i8) - aaVar.g) * f4) + i8 + 0.5f));
                    v9Var2.d(v9Var.g + aaVar.g);
                }
            }
        }
    }

    @Override // defpackage.mt
    public final void d() {
        k8 k8Var;
        k8 k8Var2;
        k8 k8Var3;
        k8 k8Var4;
        v9 v9Var = this.k;
        k8 k8Var5 = this.b;
        boolean z = k8Var5.a;
        aa aaVar = this.e;
        if (z) {
            aaVar.d(k8Var5.i());
        }
        boolean z2 = aaVar.j;
        ArrayList arrayList = aaVar.k;
        ArrayList arrayList2 = aaVar.l;
        v9 v9Var2 = this.i;
        v9 v9Var3 = this.h;
        if (!z2) {
            k8 k8Var6 = this.b;
            this.d = k8Var6.o0[1];
            if (k8Var6.E) {
                this.l = new i6(this);
            }
            int i = this.d;
            if (i != 3) {
                if (i == 4 && (k8Var4 = this.b.S) != null && k8Var4.o0[1] == 1) {
                    int i2 = (k8Var4.i() - this.b.I.d()) - this.b.K.d();
                    mt.b(v9Var3, k8Var4.e.h, this.b.I.d());
                    mt.b(v9Var2, k8Var4.e.i, -this.b.K.d());
                    aaVar.d(i2);
                    return;
                }
                if (i == 1) {
                    aaVar.d(this.b.i());
                }
            }
        } else if (this.d == 4 && (k8Var2 = (k8Var = this.b).S) != null && k8Var2.o0[1] == 1) {
            mt.b(v9Var3, k8Var2.e.h, k8Var.I.d());
            mt.b(v9Var2, k8Var2.e.i, -this.b.K.d());
            return;
        }
        boolean z3 = aaVar.j;
        if (z3) {
            k8 k8Var7 = this.b;
            if (k8Var7.a) {
                v7[] v7VarArr = k8Var7.P;
                v7 v7Var = v7VarArr[2];
                v7 v7Var2 = v7Var.f;
                if (v7Var2 != null && v7VarArr[3].f != null) {
                    boolean zW = k8Var7.w();
                    k8 k8Var8 = this.b;
                    if (zW) {
                        v9Var3.f = k8Var8.P[2].d();
                        v9Var2.f = -this.b.P[3].d();
                    } else {
                        v9 v9VarH = mt.h(k8Var8.P[2]);
                        if (v9VarH != null) {
                            mt.b(v9Var3, v9VarH, this.b.P[2].d());
                        }
                        v9 v9VarH2 = mt.h(this.b.P[3]);
                        if (v9VarH2 != null) {
                            mt.b(v9Var2, v9VarH2, -this.b.P[3].d());
                        }
                        v9Var3.b = true;
                        v9Var2.b = true;
                    }
                    k8 k8Var9 = this.b;
                    if (k8Var9.E) {
                        mt.b(v9Var, v9Var3, k8Var9.Z);
                        return;
                    }
                    return;
                }
                if (v7Var2 != null) {
                    v9 v9VarH3 = mt.h(v7Var);
                    if (v9VarH3 != null) {
                        mt.b(v9Var3, v9VarH3, this.b.P[2].d());
                        mt.b(v9Var2, v9Var3, aaVar.g);
                        k8 k8Var10 = this.b;
                        if (k8Var10.E) {
                            mt.b(v9Var, v9Var3, k8Var10.Z);
                            return;
                        }
                        return;
                    }
                    return;
                }
                v7 v7Var3 = v7VarArr[3];
                if (v7Var3.f != null) {
                    v9 v9VarH4 = mt.h(v7Var3);
                    if (v9VarH4 != null) {
                        mt.b(v9Var2, v9VarH4, -this.b.P[3].d());
                        mt.b(v9Var3, v9Var2, -aaVar.g);
                    }
                    k8 k8Var11 = this.b;
                    if (k8Var11.E) {
                        mt.b(v9Var, v9Var3, k8Var11.Z);
                        return;
                    }
                    return;
                }
                v7 v7Var4 = v7VarArr[4];
                if (v7Var4.f != null) {
                    v9 v9VarH5 = mt.h(v7Var4);
                    if (v9VarH5 != null) {
                        mt.b(v9Var, v9VarH5, 0);
                        mt.b(v9Var3, v9Var, -this.b.Z);
                        mt.b(v9Var2, v9Var3, aaVar.g);
                        return;
                    }
                    return;
                }
                if ((k8Var7 instanceof h6) || k8Var7.S == null || k8Var7.g(7).f != null) {
                    return;
                }
                k8 k8Var12 = this.b;
                mt.b(v9Var3, k8Var12.S.e.h, k8Var12.q());
                mt.b(v9Var2, v9Var3, aaVar.g);
                k8 k8Var13 = this.b;
                if (k8Var13.E) {
                    mt.b(v9Var, v9Var3, k8Var13.Z);
                    return;
                }
                return;
            }
        }
        if (z3 || this.d != 3) {
            aaVar.b(this);
        } else {
            k8 k8Var14 = this.b;
            int i3 = k8Var14.s;
            if (i3 == 2) {
                k8 k8Var15 = k8Var14.S;
                if (k8Var15 != null) {
                    aa aaVar2 = k8Var15.e.e;
                    arrayList2.add(aaVar2);
                    aaVar2.k.add(aaVar);
                    aaVar.b = true;
                    arrayList.add(v9Var3);
                    arrayList.add(v9Var2);
                }
            } else if (i3 == 3 && !k8Var14.w()) {
                k8 k8Var16 = this.b;
                if (k8Var16.r != 3) {
                    aa aaVar3 = k8Var16.d.e;
                    arrayList2.add(aaVar3);
                    aaVar3.k.add(aaVar);
                    aaVar.b = true;
                    arrayList.add(v9Var3);
                    arrayList.add(v9Var2);
                }
            }
        }
        k8 k8Var17 = this.b;
        v7[] v7VarArr2 = k8Var17.P;
        v7 v7Var5 = v7VarArr2[2];
        v7 v7Var6 = v7Var5.f;
        if (v7Var6 != null && v7VarArr2[3].f != null) {
            boolean zW2 = k8Var17.w();
            k8 k8Var18 = this.b;
            if (zW2) {
                v9Var3.f = k8Var18.P[2].d();
                v9Var2.f = -this.b.P[3].d();
            } else {
                v9 v9VarH6 = mt.h(k8Var18.P[2]);
                v9 v9VarH7 = mt.h(this.b.P[3]);
                if (v9VarH6 != null) {
                    v9VarH6.b(this);
                }
                if (v9VarH7 != null) {
                    v9VarH7.b(this);
                }
                this.j = 4;
            }
            if (this.b.E) {
                c(v9Var, v9Var3, 1, this.l);
            }
        } else if (v7Var6 != null) {
            v9 v9VarH8 = mt.h(v7Var5);
            if (v9VarH8 != null) {
                mt.b(v9Var3, v9VarH8, this.b.P[2].d());
                c(v9Var2, v9Var3, 1, aaVar);
                if (this.b.E) {
                    c(v9Var, v9Var3, 1, this.l);
                }
                if (this.d == 3) {
                    k8 k8Var19 = this.b;
                    if (k8Var19.V > 0.0f) {
                        xe xeVar = k8Var19.d;
                        if (xeVar.d == 3) {
                            xeVar.e.k.add(aaVar);
                            arrayList2.add(this.b.d.e);
                            aaVar.a = this;
                        }
                    }
                }
            }
        } else {
            v7 v7Var7 = v7VarArr2[3];
            if (v7Var7.f != null) {
                v9 v9VarH9 = mt.h(v7Var7);
                if (v9VarH9 != null) {
                    mt.b(v9Var2, v9VarH9, -this.b.P[3].d());
                    c(v9Var3, v9Var2, -1, aaVar);
                    if (this.b.E) {
                        c(v9Var, v9Var3, 1, this.l);
                    }
                }
            } else {
                v7 v7Var8 = v7VarArr2[4];
                if (v7Var8.f != null) {
                    v9 v9VarH10 = mt.h(v7Var8);
                    if (v9VarH10 != null) {
                        mt.b(v9Var, v9VarH10, 0);
                        c(v9Var3, v9Var, -1, this.l);
                        c(v9Var2, v9Var3, 1, aaVar);
                    }
                } else if (!(k8Var17 instanceof h6) && (k8Var3 = k8Var17.S) != null) {
                    mt.b(v9Var3, k8Var3.e.h, k8Var17.q());
                    c(v9Var2, v9Var3, 1, aaVar);
                    if (this.b.E) {
                        c(v9Var, v9Var3, 1, this.l);
                    }
                    if (this.d == 3) {
                        k8 k8Var20 = this.b;
                        if (k8Var20.V > 0.0f) {
                            xe xeVar2 = k8Var20.d;
                            if (xeVar2.d == 3) {
                                xeVar2.e.k.add(aaVar);
                                arrayList2.add(this.b.d.e);
                                aaVar.a = this;
                            }
                        }
                    }
                }
            }
        }
        if (arrayList2.size() == 0) {
            aaVar.c = true;
        }
    }

    @Override // defpackage.mt
    public final void e() {
        v9 v9Var = this.h;
        if (v9Var.j) {
            this.b.Y = v9Var.g;
        }
    }

    @Override // defpackage.mt
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.mt
    public final boolean k() {
        return this.d != 3 || this.b.s == 0;
    }

    public final void m() {
        this.g = false;
        v9 v9Var = this.h;
        v9Var.c();
        v9Var.j = false;
        v9 v9Var2 = this.i;
        v9Var2.c();
        v9Var2.j = false;
        v9 v9Var3 = this.k;
        v9Var3.c();
        v9Var3.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.b.g0;
    }
}
