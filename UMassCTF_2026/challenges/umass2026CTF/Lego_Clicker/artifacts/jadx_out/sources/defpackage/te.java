package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class te extends k8 {
    public float p0 = -1.0f;
    public int q0 = -1;
    public int r0 = -1;
    public v7 s0 = this.I;
    public int t0 = 0;
    public boolean u0;

    public te() {
        this.Q.clear();
        this.Q.add(this.s0);
        int length = this.P.length;
        for (int i = 0; i < length; i++) {
            this.P[i] = this.s0;
        }
    }

    @Override // defpackage.k8
    public final void M(tg tgVar, boolean z) {
        if (this.S == null) {
            return;
        }
        v7 v7Var = this.s0;
        tgVar.getClass();
        int iN = tg.n(v7Var);
        if (this.t0 == 1) {
            this.X = iN;
            this.Y = 0;
            H(this.S.i());
            K(0);
            return;
        }
        this.X = 0;
        this.Y = iN;
        K(this.S.o());
        H(0);
    }

    public final void N(int i) {
        this.s0.i(i);
        this.u0 = true;
    }

    public final void O(int i) {
        if (this.t0 == i) {
            return;
        }
        this.t0 = i;
        ArrayList arrayList = this.Q;
        arrayList.clear();
        if (this.t0 == 1) {
            this.s0 = this.H;
        } else {
            this.s0 = this.I;
        }
        arrayList.add(this.s0);
        v7[] v7VarArr = this.P;
        int length = v7VarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            v7VarArr[i2] = this.s0;
        }
    }

    @Override // defpackage.k8
    public final void b(tg tgVar, boolean z) {
        l8 l8Var = (l8) this.S;
        if (l8Var == null) {
            return;
        }
        Object objG = l8Var.g(2);
        Object objG2 = l8Var.g(4);
        k8 k8Var = this.S;
        boolean z2 = k8Var != null && k8Var.o0[0] == 2;
        if (this.t0 == 0) {
            objG = l8Var.g(3);
            objG2 = l8Var.g(5);
            k8 k8Var2 = this.S;
            z2 = k8Var2 != null && k8Var2.o0[1] == 2;
        }
        if (this.u0) {
            v7 v7Var = this.s0;
            if (v7Var.c) {
                mo moVarK = tgVar.k(v7Var);
                tgVar.d(moVarK, this.s0.c());
                if (this.q0 != -1) {
                    if (z2) {
                        tgVar.f(tgVar.k(objG2), moVarK, 0, 5);
                    }
                } else if (this.r0 != -1 && z2) {
                    mo moVarK2 = tgVar.k(objG2);
                    tgVar.f(moVarK, tgVar.k(objG), 0, 5);
                    tgVar.f(moVarK2, moVarK, 0, 5);
                }
                this.u0 = false;
                return;
            }
        }
        if (this.q0 != -1) {
            mo moVarK3 = tgVar.k(this.s0);
            tgVar.e(moVarK3, tgVar.k(objG), this.q0, 8);
            if (z2) {
                tgVar.f(tgVar.k(objG2), moVarK3, 0, 5);
                return;
            }
            return;
        }
        if (this.r0 != -1) {
            mo moVarK4 = tgVar.k(this.s0);
            mo moVarK5 = tgVar.k(objG2);
            tgVar.e(moVarK4, moVarK5, -this.r0, 8);
            if (z2) {
                tgVar.f(moVarK4, tgVar.k(objG), 0, 5);
                tgVar.f(moVarK5, moVarK4, 0, 5);
                return;
            }
            return;
        }
        if (this.p0 != -1.0f) {
            mo moVarK6 = tgVar.k(this.s0);
            mo moVarK7 = tgVar.k(objG2);
            float f = this.p0;
            v5 v5VarL = tgVar.l();
            v5VarL.d.g(moVarK6, -1.0f);
            v5VarL.d.g(moVarK7, f);
            tgVar.c(v5VarL);
        }
    }

    @Override // defpackage.k8
    public final boolean c() {
        return true;
    }

    @Override // defpackage.k8
    public final v7 g(int i) {
        int iC = lo.c(i);
        if (iC != 1) {
            if (iC != 2) {
                if (iC != 3) {
                    if (iC != 4) {
                        return null;
                    }
                }
            }
            if (this.t0 == 0) {
                return this.s0;
            }
            return null;
        }
        if (this.t0 == 1) {
            return this.s0;
        }
        return null;
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
