package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class qk extends v5 {
    public mo[] f;
    public mo[] g;
    public int h;
    public z3 i;

    @Override // defpackage.v5
    public final mo d(boolean[] zArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.h; i2++) {
            mo[] moVarArr = this.f;
            mo moVar = moVarArr[i2];
            if (!zArr[moVar.b]) {
                z3 z3Var = this.i;
                z3Var.b = moVar;
                int i3 = 8;
                if (i == -1) {
                    while (i3 >= 0) {
                        float f = ((mo) z3Var.b).h[i3];
                        if (f <= 0.0f) {
                            if (f < 0.0f) {
                                i = i2;
                                break;
                            }
                            i3--;
                        }
                    }
                } else {
                    mo moVar2 = moVarArr[i];
                    while (true) {
                        if (i3 >= 0) {
                            float f2 = moVar2.h[i3];
                            float f3 = ((mo) z3Var.b).h[i3];
                            if (f3 == f2) {
                                i3--;
                            } else if (f3 < f2) {
                            }
                        }
                    }
                }
            }
        }
        if (i == -1) {
            return null;
        }
        return this.f[i];
    }

    @Override // defpackage.v5
    public final boolean e() {
        return this.h == 0;
    }

    @Override // defpackage.v5
    public final void i(tg tgVar, v5 v5Var, boolean z) {
        mo moVar = v5Var.a;
        if (moVar == null) {
            return;
        }
        float[] fArr = moVar.h;
        s5 s5Var = v5Var.d;
        int iD = s5Var.d();
        for (int i = 0; i < iD; i++) {
            mo moVarE = s5Var.e(i);
            float f = s5Var.f(i);
            z3 z3Var = this.i;
            z3Var.b = moVarE;
            if (moVarE.a) {
                boolean z2 = true;
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr2 = ((mo) z3Var.b).h;
                    float f2 = (fArr[i2] * f) + fArr2[i2];
                    fArr2[i2] = f2;
                    if (Math.abs(f2) < 1.0E-4f) {
                        ((mo) z3Var.b).h[i2] = 0.0f;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    ((qk) z3Var.c).k((mo) z3Var.b);
                }
            } else {
                for (int i3 = 0; i3 < 9; i3++) {
                    float f3 = fArr[i3];
                    if (f3 != 0.0f) {
                        float f4 = f3 * f;
                        if (Math.abs(f4) < 1.0E-4f) {
                            f4 = 0.0f;
                        }
                        ((mo) z3Var.b).h[i3] = f4;
                    } else {
                        ((mo) z3Var.b).h[i3] = 0.0f;
                    }
                }
                j(moVarE);
            }
            this.b = (v5Var.b * f) + this.b;
        }
        k(moVar);
    }

    public final void j(mo moVar) {
        int i;
        mo[] moVarArr;
        int i2 = this.h + 1;
        mo[] moVarArr2 = this.f;
        if (i2 > moVarArr2.length) {
            mo[] moVarArr3 = (mo[]) Arrays.copyOf(moVarArr2, moVarArr2.length * 2);
            this.f = moVarArr3;
            this.g = (mo[]) Arrays.copyOf(moVarArr3, moVarArr3.length * 2);
        }
        mo[] moVarArr4 = this.f;
        int i3 = this.h;
        moVarArr4[i3] = moVar;
        int i4 = i3 + 1;
        this.h = i4;
        if (i4 > 1 && moVarArr4[i3].b > moVar.b) {
            int i5 = 0;
            while (true) {
                i = this.h;
                moVarArr = this.g;
                if (i5 >= i) {
                    break;
                }
                moVarArr[i5] = this.f[i5];
                i5++;
            }
            Arrays.sort(moVarArr, 0, i, new ke(2));
            for (int i6 = 0; i6 < this.h; i6++) {
                this.f[i6] = this.g[i6];
            }
        }
        moVar.a = true;
        moVar.a(this);
    }

    public final void k(mo moVar) {
        int i = 0;
        while (i < this.h) {
            if (this.f[i] == moVar) {
                while (true) {
                    int i2 = this.h;
                    if (i >= i2 - 1) {
                        this.h = i2 - 1;
                        moVar.a = false;
                        return;
                    } else {
                        mo[] moVarArr = this.f;
                        int i3 = i + 1;
                        moVarArr[i] = moVarArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // defpackage.v5
    public final String toString() {
        z3 z3Var = this.i;
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.h; i++) {
            z3Var.b = this.f[i];
            str = str + z3Var + " ";
        }
        return str;
    }
}
