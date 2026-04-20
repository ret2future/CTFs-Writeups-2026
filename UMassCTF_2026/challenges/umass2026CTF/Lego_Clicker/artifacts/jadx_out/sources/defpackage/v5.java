package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class v5 {
    public final s5 d;
    public mo a = null;
    public float b = 0.0f;
    public final ArrayList c = new ArrayList();
    public boolean e = false;

    public v5(j5 j5Var) {
        this.d = new s5(this, j5Var);
    }

    public final void a(tg tgVar, int i) {
        mo moVarJ = tgVar.j(i);
        s5 s5Var = this.d;
        s5Var.g(moVarJ, 1.0f);
        s5Var.g(tgVar.j(i), -1.0f);
    }

    public final void b(mo moVar, mo moVar2, mo moVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        s5 s5Var = this.d;
        if (z) {
            s5Var.g(moVar, 1.0f);
            s5Var.g(moVar2, -1.0f);
            s5Var.g(moVar3, -1.0f);
        } else {
            s5Var.g(moVar, -1.0f);
            s5Var.g(moVar2, 1.0f);
            s5Var.g(moVar3, 1.0f);
        }
    }

    public final void c(mo moVar, mo moVar2, mo moVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        s5 s5Var = this.d;
        if (z) {
            s5Var.g(moVar, 1.0f);
            s5Var.g(moVar2, -1.0f);
            s5Var.g(moVar3, 1.0f);
        } else {
            s5Var.g(moVar, -1.0f);
            s5Var.g(moVar2, 1.0f);
            s5Var.g(moVar3, -1.0f);
        }
    }

    public mo d(boolean[] zArr) {
        return f(zArr, null);
    }

    public boolean e() {
        return this.a == null && this.b == 0.0f && this.d.d() == 0;
    }

    public final mo f(boolean[] zArr, mo moVar) {
        int i;
        s5 s5Var = this.d;
        int iD = s5Var.d();
        mo moVar2 = null;
        float f = 0.0f;
        for (int i2 = 0; i2 < iD; i2++) {
            float f2 = s5Var.f(i2);
            if (f2 < 0.0f) {
                mo moVarE = s5Var.e(i2);
                if ((zArr == null || !zArr[moVarE.b]) && moVarE != moVar && (((i = moVarE.l) == 3 || i == 4) && f2 < f)) {
                    f = f2;
                    moVar2 = moVarE;
                }
            }
        }
        return moVar2;
    }

    public final void g(mo moVar) {
        mo moVar2 = this.a;
        s5 s5Var = this.d;
        if (moVar2 != null) {
            s5Var.g(moVar2, -1.0f);
            this.a.c = -1;
            this.a = null;
        }
        float fH = s5Var.h(moVar, true) * (-1.0f);
        this.a = moVar;
        if (fH == 1.0f) {
            return;
        }
        this.b /= fH;
        int i = s5Var.h;
        for (int i2 = 0; i != -1 && i2 < s5Var.a; i2++) {
            float[] fArr = s5Var.g;
            fArr[i] = fArr[i] / fH;
            i = s5Var.f[i];
        }
    }

    public final void h(tg tgVar, mo moVar, boolean z) {
        if (moVar.f) {
            s5 s5Var = this.d;
            float fC = s5Var.c(moVar);
            this.b = (moVar.e * fC) + this.b;
            s5Var.h(moVar, z);
            if (z) {
                moVar.b(this);
            }
            if (s5Var.d() == 0) {
                this.e = true;
                tgVar.b = true;
            }
        }
    }

    public void i(tg tgVar, v5 v5Var, boolean z) {
        s5 s5Var = this.d;
        s5Var.getClass();
        float fC = s5Var.c(v5Var.a);
        s5Var.h(v5Var.a, z);
        s5 s5Var2 = v5Var.d;
        int iD = s5Var2.d();
        for (int i = 0; i < iD; i++) {
            mo moVarE = s5Var2.e(i);
            s5Var.a(moVarE, s5Var2.c(moVarE) * fC, z);
        }
        this.b = (v5Var.b * fC) + this.b;
        if (z) {
            v5Var.a.b(this);
        }
        if (this.a == null || s5Var.d() != 0) {
            return;
        }
        this.e = true;
        tgVar.b = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            mo r0 = r10.a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            mo r1 = r10.a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = r0.concat(r1)
            float r1 = r10.b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L39
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            float r0 = r10.b
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = r4
            goto L3a
        L39:
            r1 = r3
        L3a:
            s5 r10 = r10.d
            int r5 = r10.d()
        L40:
            if (r3 >= r5) goto L9c
            mo r6 = r10.e(r3)
            if (r6 != 0) goto L49
            goto L99
        L49:
            float r7 = r10.f(r3)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L52
            goto L99
        L52:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L66
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L76
            java.lang.String r1 = "- "
            java.lang.String r0 = r0.concat(r1)
        L64:
            float r7 = r7 * r9
            goto L76
        L66:
            if (r8 <= 0) goto L6f
            java.lang.String r1 = " + "
            java.lang.String r0 = r0.concat(r1)
            goto L76
        L6f:
            java.lang.String r1 = " - "
            java.lang.String r0 = r0.concat(r1)
            goto L64
        L76:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L81
            java.lang.String r0 = r0.concat(r6)
            goto L98
        L81:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L98:
            r1 = r4
        L99:
            int r3 = r3 + 1
            goto L40
        L9c:
            if (r1 != 0) goto La5
            java.lang.String r10 = "0.0"
            java.lang.String r10 = r0.concat(r10)
            return r10
        La5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.v5.toString():java.lang.String");
    }
}
