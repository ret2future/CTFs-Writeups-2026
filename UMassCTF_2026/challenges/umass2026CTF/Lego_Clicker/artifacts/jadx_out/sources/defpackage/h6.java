package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class h6 extends k8 {
    public k8[] p0;
    public int q0;
    public int r0;
    public boolean s0;
    public int t0;
    public boolean u0;

    public final void N(int i, lt ltVar, ArrayList arrayList) {
        for (int i2 = 0; i2 < this.q0; i2++) {
            k8 k8Var = this.p0[i2];
            ArrayList arrayList2 = ltVar.a;
            if (!arrayList2.contains(k8Var)) {
                arrayList2.add(k8Var);
            }
        }
        for (int i3 = 0; i3 < this.q0; i3++) {
            hb.y(this.p0[i3], i, arrayList, ltVar);
        }
    }

    public final boolean O() {
        int i;
        int i2;
        int i3;
        boolean z = true;
        int i4 = 0;
        while (true) {
            i = this.q0;
            if (i4 >= i) {
                break;
            }
            k8 k8Var = this.p0[i4];
            if ((this.s0 || k8Var.c()) && ((((i2 = this.r0) == 0 || i2 == 1) && !k8Var.y()) || (((i3 = this.r0) == 2 || i3 == 3) && !k8Var.z()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.q0; i5++) {
            k8 k8Var2 = this.p0[i5];
            if (this.s0 || k8Var2.c()) {
                if (!z2) {
                    int i6 = this.r0;
                    if (i6 == 0) {
                        iMax = k8Var2.g(2).c();
                    } else if (i6 == 1) {
                        iMax = k8Var2.g(4).c();
                    } else if (i6 == 2) {
                        iMax = k8Var2.g(3).c();
                    } else if (i6 == 3) {
                        iMax = k8Var2.g(5).c();
                    }
                    z2 = true;
                }
                int i7 = this.r0;
                if (i7 == 0) {
                    iMax = Math.min(iMax, k8Var2.g(2).c());
                } else if (i7 == 1) {
                    iMax = Math.max(iMax, k8Var2.g(4).c());
                } else if (i7 == 2) {
                    iMax = Math.min(iMax, k8Var2.g(3).c());
                } else if (i7 == 3) {
                    iMax = Math.max(iMax, k8Var2.g(5).c());
                }
            }
        }
        int i8 = iMax + this.t0;
        int i9 = this.r0;
        if (i9 == 0 || i9 == 1) {
            F(i8, i8);
        } else {
            G(i8, i8);
        }
        this.u0 = true;
        return true;
    }

    public final int P() {
        int i = this.r0;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    @Override // defpackage.k8
    public final void b(tg tgVar, boolean z) {
        boolean z2;
        int i;
        int i2;
        v7[] v7VarArr = this.P;
        v7 v7Var = this.H;
        v7VarArr[0] = v7Var;
        int i3 = 2;
        v7 v7Var2 = this.I;
        v7VarArr[2] = v7Var2;
        v7 v7Var3 = this.J;
        v7VarArr[1] = v7Var3;
        v7 v7Var4 = this.K;
        v7VarArr[3] = v7Var4;
        for (v7 v7Var5 : v7VarArr) {
            v7Var5.i = tgVar.k(v7Var5);
        }
        int i4 = this.r0;
        if (i4 < 0 || i4 >= 4) {
            return;
        }
        v7 v7Var6 = v7VarArr[i4];
        if (!this.u0) {
            O();
        }
        if (this.u0) {
            this.u0 = false;
            int i5 = this.r0;
            if (i5 == 0 || i5 == 1) {
                tgVar.d(v7Var.i, this.X);
                tgVar.d(v7Var3.i, this.X);
                return;
            } else {
                if (i5 == 2 || i5 == 3) {
                    tgVar.d(v7Var2.i, this.Y);
                    tgVar.d(v7Var4.i, this.Y);
                    return;
                }
                return;
            }
        }
        for (int i6 = 0; i6 < this.q0; i6++) {
            k8 k8Var = this.p0[i6];
            if ((this.s0 || k8Var.c()) && ((((i2 = this.r0) == 0 || i2 == 1) && k8Var.o0[0] == 3 && k8Var.H.f != null && k8Var.J.f != null) || ((i2 == 2 || i2 == 3) && k8Var.o0[1] == 3 && k8Var.I.f != null && k8Var.K.f != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = v7Var.e() || v7Var3.e();
        boolean z4 = v7Var2.e() || v7Var4.e();
        int i7 = !(!z2 && (((i = this.r0) == 0 && z3) || ((i == 2 && z4) || ((i == 1 && z3) || (i == 3 && z4))))) ? 4 : 5;
        int i8 = 0;
        while (i8 < this.q0) {
            k8 k8Var2 = this.p0[i8];
            if (this.s0 || k8Var2.c()) {
                mo moVarK = tgVar.k(k8Var2.P[this.r0]);
                v7[] v7VarArr2 = k8Var2.P;
                int i9 = this.r0;
                v7 v7Var7 = v7VarArr2[i9];
                v7Var7.i = moVarK;
                v7 v7Var8 = v7Var7.f;
                int i10 = (v7Var8 == null || v7Var8.d != this) ? 0 : v7Var7.g;
                if (i9 == 0 || i9 == i3) {
                    mo moVar = v7Var6.i;
                    int i11 = this.t0 - i10;
                    v5 v5VarL = tgVar.l();
                    mo moVarM = tgVar.m();
                    moVarM.d = 0;
                    v5VarL.c(moVar, moVarK, moVarM, i11);
                    tgVar.c(v5VarL);
                } else {
                    mo moVar2 = v7Var6.i;
                    int i12 = this.t0 + i10;
                    v5 v5VarL2 = tgVar.l();
                    mo moVarM2 = tgVar.m();
                    moVarM2.d = 0;
                    v5VarL2.b(moVar2, moVarK, moVarM2, i12);
                    tgVar.c(v5VarL2);
                }
                tgVar.e(v7Var6.i, moVarK, this.t0 + i10, i7);
            }
            i8++;
            i3 = 2;
        }
        int i13 = this.r0;
        if (i13 == 0) {
            tgVar.e(v7Var3.i, v7Var.i, 0, 8);
            tgVar.e(v7Var.i, this.S.J.i, 0, 4);
            tgVar.e(v7Var.i, this.S.H.i, 0, 0);
            return;
        }
        if (i13 == 1) {
            tgVar.e(v7Var.i, v7Var3.i, 0, 8);
            tgVar.e(v7Var.i, this.S.H.i, 0, 4);
            tgVar.e(v7Var.i, this.S.J.i, 0, 0);
        } else if (i13 == 2) {
            tgVar.e(v7Var4.i, v7Var2.i, 0, 8);
            tgVar.e(v7Var2.i, this.S.K.i, 0, 4);
            tgVar.e(v7Var2.i, this.S.I.i, 0, 0);
        } else if (i13 == 3) {
            tgVar.e(v7Var2.i, v7Var4.i, 0, 8);
            tgVar.e(v7Var2.i, this.S.I.i, 0, 4);
            tgVar.e(v7Var2.i, this.S.K.i, 0, 0);
        }
    }

    @Override // defpackage.k8
    public final boolean c() {
        return true;
    }

    @Override // defpackage.k8
    public final String toString() {
        String strConcat = "[Barrier] " + this.g0 + " {";
        for (int i = 0; i < this.q0; i++) {
            k8 k8Var = this.p0[i];
            if (i > 0) {
                strConcat = strConcat.concat(", ");
            }
            strConcat = strConcat + k8Var.g0;
        }
        return strConcat.concat("}");
    }

    @Override // defpackage.k8
    public final boolean y() {
        return this.u0;
    }

    @Override // defpackage.k8
    public final boolean z() {
        return this.u0;
    }
}
