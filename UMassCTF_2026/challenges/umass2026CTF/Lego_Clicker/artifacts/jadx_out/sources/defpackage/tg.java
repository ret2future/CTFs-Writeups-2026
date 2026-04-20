package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class tg {
    public static boolean q = false;
    public final qk d;
    public final j5 m;
    public v5 p;
    public int a = 1000;
    public boolean b = false;
    public int c = 0;
    public int e = 32;
    public int f = 32;
    public boolean h = false;
    public boolean[] i = new boolean[32];
    public int j = 1;
    public int k = 0;
    public int l = 32;
    public mo[] n = new mo[1000];
    public int o = 0;
    public v5[] g = new v5[32];

    public tg() {
        s();
        j5 j5Var = new j5();
        j5Var.a = new mk();
        j5Var.b = new mk();
        j5Var.c = new mo[32];
        this.m = j5Var;
        qk qkVar = new qk(j5Var);
        qkVar.f = new mo[128];
        qkVar.g = new mo[128];
        qkVar.h = 0;
        qkVar.i = new z3(qkVar);
        this.d = qkVar;
        this.p = new v5(j5Var);
    }

    public static int n(Object obj) {
        mo moVar = ((v7) obj).i;
        if (moVar != null) {
            return (int) (moVar.e + 0.5f);
        }
        return 0;
    }

    public final mo a(int i) {
        mk mkVar = (mk) this.m.b;
        int i2 = mkVar.b;
        Object obj = null;
        if (i2 > 0) {
            int i3 = i2 - 1;
            Object[] objArr = mkVar.a;
            Object obj2 = objArr[i3];
            objArr[i3] = null;
            mkVar.b = i3;
            obj = obj2;
        }
        mo moVar = (mo) obj;
        if (moVar == null) {
            moVar = new mo(i);
            moVar.l = i;
        } else {
            moVar.c();
            moVar.l = i;
        }
        int i4 = this.o;
        int i5 = this.a;
        if (i4 >= i5) {
            int i6 = i5 * 2;
            this.a = i6;
            this.n = (mo[]) Arrays.copyOf(this.n, i6);
        }
        mo[] moVarArr = this.n;
        int i7 = this.o;
        this.o = i7 + 1;
        moVarArr[i7] = moVar;
        return moVar;
    }

    public final void b(mo moVar, mo moVar2, int i, float f, mo moVar3, mo moVar4, int i2, int i3) {
        v5 v5VarL = l();
        if (moVar2 == moVar3) {
            v5VarL.d.g(moVar, 1.0f);
            v5VarL.d.g(moVar4, 1.0f);
            v5VarL.d.g(moVar2, -2.0f);
        } else {
            s5 s5Var = v5VarL.d;
            if (f == 0.5f) {
                s5Var.g(moVar, 1.0f);
                v5VarL.d.g(moVar2, -1.0f);
                v5VarL.d.g(moVar3, -1.0f);
                v5VarL.d.g(moVar4, 1.0f);
                if (i > 0 || i2 > 0) {
                    v5VarL.b = (-i) + i2;
                }
            } else if (f <= 0.0f) {
                s5Var.g(moVar, -1.0f);
                v5VarL.d.g(moVar2, 1.0f);
                v5VarL.b = i;
            } else if (f >= 1.0f) {
                s5Var.g(moVar4, -1.0f);
                v5VarL.d.g(moVar3, 1.0f);
                v5VarL.b = -i2;
            } else {
                float f2 = 1.0f - f;
                s5Var.g(moVar, f2 * 1.0f);
                v5VarL.d.g(moVar2, f2 * (-1.0f));
                v5VarL.d.g(moVar3, (-1.0f) * f);
                v5VarL.d.g(moVar4, 1.0f * f);
                if (i > 0 || i2 > 0) {
                    v5VarL.b = (i2 * f) + ((-i) * f2);
                }
            }
        }
        if (i3 != 8) {
            v5VarL.a(this, i3);
        }
        c(v5VarL);
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(defpackage.v5 r18) {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tg.c(v5):void");
    }

    public final void d(mo moVar, int i) {
        int i2 = moVar.c;
        if (i2 == -1) {
            moVar.d(this, i);
            for (int i3 = 0; i3 < this.c + 1; i3++) {
                mo moVar2 = ((mo[]) this.m.c)[i3];
            }
            return;
        }
        if (i2 == -1) {
            v5 v5VarL = l();
            v5VarL.a = moVar;
            float f = i;
            moVar.e = f;
            v5VarL.b = f;
            v5VarL.e = true;
            c(v5VarL);
            return;
        }
        v5 v5Var = this.g[i2];
        if (v5Var.e) {
            v5Var.b = i;
            return;
        }
        if (v5Var.d.d() == 0) {
            v5Var.e = true;
            v5Var.b = i;
            return;
        }
        v5 v5VarL2 = l();
        if (i < 0) {
            v5VarL2.b = i * (-1);
            v5VarL2.d.g(moVar, 1.0f);
        } else {
            v5VarL2.b = i;
            v5VarL2.d.g(moVar, -1.0f);
        }
        c(v5VarL2);
    }

    public final void e(mo moVar, mo moVar2, int i, int i2) {
        if (i2 == 8 && moVar2.f && moVar.c == -1) {
            moVar.d(this, moVar2.e + i);
            return;
        }
        v5 v5VarL = l();
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            v5VarL.b = i;
        }
        s5 s5Var = v5VarL.d;
        if (z) {
            s5Var.g(moVar, 1.0f);
            v5VarL.d.g(moVar2, -1.0f);
        } else {
            s5Var.g(moVar, -1.0f);
            v5VarL.d.g(moVar2, 1.0f);
        }
        if (i2 != 8) {
            v5VarL.a(this, i2);
        }
        c(v5VarL);
    }

    public final void f(mo moVar, mo moVar2, int i, int i2) {
        v5 v5VarL = l();
        mo moVarM = m();
        moVarM.d = 0;
        v5VarL.b(moVar, moVar2, moVarM, i);
        if (i2 != 8) {
            v5VarL.d.g(j(i2), (int) (v5VarL.d.c(moVarM) * (-1.0f)));
        }
        c(v5VarL);
    }

    public final void g(mo moVar, mo moVar2, int i, int i2) {
        v5 v5VarL = l();
        mo moVarM = m();
        moVarM.d = 0;
        v5VarL.c(moVar, moVar2, moVarM, i);
        if (i2 != 8) {
            v5VarL.d.g(j(i2), (int) (v5VarL.d.c(moVarM) * (-1.0f)));
        }
        c(v5VarL);
    }

    public final void h(v5 v5Var) {
        int i;
        if (v5Var.e) {
            v5Var.a.d(this, v5Var.b);
        } else {
            v5[] v5VarArr = this.g;
            int i2 = this.k;
            v5VarArr[i2] = v5Var;
            mo moVar = v5Var.a;
            moVar.c = i2;
            this.k = i2 + 1;
            moVar.e(this, v5Var);
        }
        if (this.b) {
            int i3 = 0;
            while (i3 < this.k) {
                if (this.g[i3] == null) {
                    System.out.println("WTF");
                }
                v5 v5Var2 = this.g[i3];
                if (v5Var2 != null && v5Var2.e) {
                    v5Var2.a.d(this, v5Var2.b);
                    ((mk) this.m.a).b(v5Var2);
                    this.g[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.k;
                        if (i4 >= i) {
                            break;
                        }
                        v5[] v5VarArr2 = this.g;
                        int i6 = i4 - 1;
                        v5 v5Var3 = v5VarArr2[i4];
                        v5VarArr2[i6] = v5Var3;
                        mo moVar2 = v5Var3.a;
                        if (moVar2.c == i4) {
                            moVar2.c = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.g[i5] = null;
                    }
                    this.k = i - 1;
                    i3--;
                }
                i3++;
            }
            this.b = false;
        }
    }

    public final void i() {
        for (int i = 0; i < this.k; i++) {
            v5 v5Var = this.g[i];
            v5Var.a.e = v5Var.b;
        }
    }

    public final mo j(int i) {
        if (this.j + 1 >= this.f) {
            o();
        }
        mo moVarA = a(4);
        float[] fArr = moVarA.h;
        int i2 = this.c + 1;
        this.c = i2;
        this.j++;
        moVarA.b = i2;
        moVarA.d = i;
        ((mo[]) this.m.c)[i2] = moVarA;
        qk qkVar = this.d;
        qkVar.i.b = moVarA;
        Arrays.fill(fArr, 0.0f);
        fArr[moVarA.d] = 1.0f;
        qkVar.j(moVarA);
        return moVarA;
    }

    public final mo k(Object obj) {
        if (obj == null) {
            return null;
        }
        if (this.j + 1 >= this.f) {
            o();
        }
        if (!(obj instanceof v7)) {
            return null;
        }
        v7 v7Var = (v7) obj;
        mo moVar = v7Var.i;
        if (moVar == null) {
            v7Var.h();
            moVar = v7Var.i;
        }
        int i = moVar.b;
        j5 j5Var = this.m;
        if (i != -1 && i <= this.c && ((mo[]) j5Var.c)[i] != null) {
            return moVar;
        }
        if (i != -1) {
            moVar.c();
        }
        int i2 = this.c + 1;
        this.c = i2;
        this.j++;
        moVar.b = i2;
        moVar.l = 1;
        ((mo[]) j5Var.c)[i2] = moVar;
        return moVar;
    }

    public final v5 l() {
        Object obj;
        j5 j5Var = this.m;
        mk mkVar = (mk) j5Var.a;
        int i = mkVar.b;
        if (i > 0) {
            int i2 = i - 1;
            Object[] objArr = mkVar.a;
            obj = objArr[i2];
            objArr[i2] = null;
            mkVar.b = i2;
        } else {
            obj = null;
        }
        v5 v5Var = (v5) obj;
        if (v5Var == null) {
            return new v5(j5Var);
        }
        v5Var.a = null;
        v5Var.d.b();
        v5Var.b = 0.0f;
        v5Var.e = false;
        return v5Var;
    }

    public final mo m() {
        if (this.j + 1 >= this.f) {
            o();
        }
        mo moVarA = a(3);
        int i = this.c + 1;
        this.c = i;
        this.j++;
        moVarA.b = i;
        ((mo[]) this.m.c)[i] = moVarA;
        return moVarA;
    }

    public final void o() {
        int i = this.e * 2;
        this.e = i;
        this.g = (v5[]) Arrays.copyOf(this.g, i);
        j5 j5Var = this.m;
        j5Var.c = (mo[]) Arrays.copyOf((mo[]) j5Var.c, this.e);
        int i2 = this.e;
        this.i = new boolean[i2];
        this.f = i2;
        this.l = i2;
    }

    public final void p() {
        qk qkVar = this.d;
        if (qkVar.e()) {
            i();
            return;
        }
        if (!this.h) {
            q(qkVar);
            return;
        }
        for (int i = 0; i < this.k; i++) {
            if (!this.g[i].e) {
                q(qkVar);
                return;
            }
        }
        i();
    }

    public final void q(qk qkVar) {
        int i = 0;
        while (true) {
            if (i >= this.k) {
                break;
            }
            v5 v5Var = this.g[i];
            int i2 = 1;
            if (v5Var.a.l != 1) {
                float f = 0.0f;
                if (v5Var.b < 0.0f) {
                    boolean z = false;
                    int i3 = 0;
                    while (!z) {
                        i3 += i2;
                        float f2 = Float.MAX_VALUE;
                        int i4 = -1;
                        int i5 = -1;
                        int i6 = 0;
                        int i7 = 0;
                        while (i6 < this.k) {
                            v5 v5Var2 = this.g[i6];
                            if (v5Var2.a.l != i2 && !v5Var2.e && v5Var2.b < f) {
                                int iD = v5Var2.d.d();
                                int i8 = 0;
                                while (i8 < iD) {
                                    mo moVarE = v5Var2.d.e(i8);
                                    float fC = v5Var2.d.c(moVarE);
                                    if (fC > f) {
                                        for (int i9 = 0; i9 < 9; i9++) {
                                            float f3 = moVarE.g[i9] / fC;
                                            if ((f3 < f2 && i9 == i7) || i9 > i7) {
                                                i7 = i9;
                                                i5 = moVarE.b;
                                                i4 = i6;
                                                f2 = f3;
                                            }
                                        }
                                    }
                                    i8++;
                                    f = 0.0f;
                                }
                            }
                            i6++;
                            f = 0.0f;
                            i2 = 1;
                        }
                        if (i4 != -1) {
                            v5 v5Var3 = this.g[i4];
                            v5Var3.a.c = -1;
                            v5Var3.g(((mo[]) this.m.c)[i5]);
                            mo moVar = v5Var3.a;
                            moVar.c = i4;
                            moVar.e(this, v5Var3);
                        } else {
                            z = true;
                        }
                        if (i3 > this.j / 2) {
                            z = true;
                        }
                        f = 0.0f;
                        i2 = 1;
                    }
                }
            }
            i++;
        }
        r(qkVar);
        i();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0091 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void r(defpackage.v5 r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 0
            r3 = r2
        L6:
            int r4 = r0.j
            if (r3 >= r4) goto L11
            boolean[] r4 = r0.i
            r4[r3] = r2
            int r3 = r3 + 1
            goto L6
        L11:
            r3 = r2
            r4 = r3
        L13:
            if (r3 != 0) goto Lae
            r5 = 1
            int r4 = r4 + r5
            int r6 = r0.j
            int r6 = r6 * 2
            if (r4 < r6) goto L1f
            goto Lae
        L1f:
            mo r6 = r1.a
            if (r6 == 0) goto L29
            boolean[] r7 = r0.i
            int r6 = r6.b
            r7[r6] = r5
        L29:
            boolean[] r6 = r0.i
            mo r6 = r1.d(r6)
            if (r6 == 0) goto L3d
            boolean[] r7 = r0.i
            int r8 = r6.b
            boolean r9 = r7[r8]
            if (r9 == 0) goto L3b
            goto Lae
        L3b:
            r7[r8] = r5
        L3d:
            if (r6 == 0) goto Laa
            r7 = -1
            r8 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r9 = r2
            r10 = r7
        L45:
            int r11 = r0.k
            if (r9 >= r11) goto L95
            v5[] r11 = r0.g
            r11 = r11[r9]
            mo r12 = r11.a
            int r12 = r12.l
            if (r12 != r5) goto L54
            goto L91
        L54:
            boolean r12 = r11.e
            if (r12 == 0) goto L59
            goto L91
        L59:
            s5 r12 = r11.d
            int r13 = r12.h
            if (r13 != r7) goto L60
            goto L79
        L60:
            r14 = r2
        L61:
            if (r13 == r7) goto L79
            int r15 = r12.a
            if (r14 >= r15) goto L79
            int[] r15 = r12.e
            r15 = r15[r13]
            int r2 = r6.b
            if (r15 != r2) goto L71
            r2 = r5
            goto L7a
        L71:
            int[] r2 = r12.f
            r13 = r2[r13]
            int r14 = r14 + 1
            r2 = 0
            goto L61
        L79:
            r2 = 0
        L7a:
            if (r2 == 0) goto L91
            s5 r2 = r11.d
            float r2 = r2.c(r6)
            r12 = 0
            int r12 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r12 >= 0) goto L91
            float r11 = r11.b
            float r11 = -r11
            float r11 = r11 / r2
            int r2 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r2 >= 0) goto L91
            r10 = r9
            r8 = r11
        L91:
            int r9 = r9 + 1
            r2 = 0
            goto L45
        L95:
            if (r10 <= r7) goto Lab
            v5[] r2 = r0.g
            r2 = r2[r10]
            mo r5 = r2.a
            r5.c = r7
            r2.g(r6)
            mo r5 = r2.a
            r5.c = r10
            r5.e(r0, r2)
            goto Lab
        Laa:
            r3 = r5
        Lab:
            r2 = 0
            goto L13
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tg.r(v5):void");
    }

    public final void s() {
        for (int i = 0; i < this.k; i++) {
            v5 v5Var = this.g[i];
            if (v5Var != null) {
                ((mk) this.m.a).b(v5Var);
            }
            this.g[i] = null;
        }
    }

    public final void t() {
        j5 j5Var;
        int i = 0;
        while (true) {
            j5Var = this.m;
            mo[] moVarArr = (mo[]) j5Var.c;
            if (i >= moVarArr.length) {
                break;
            }
            mo moVar = moVarArr[i];
            if (moVar != null) {
                moVar.c();
            }
            i++;
        }
        mk mkVar = (mk) j5Var.b;
        mo[] moVarArr2 = this.n;
        int length = this.o;
        mkVar.getClass();
        if (length > moVarArr2.length) {
            length = moVarArr2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            mo moVar2 = moVarArr2[i2];
            int i3 = mkVar.b;
            Object[] objArr = mkVar.a;
            if (i3 < objArr.length) {
                objArr[i3] = moVar2;
                mkVar.b = i3 + 1;
            }
        }
        this.o = 0;
        Arrays.fill((mo[]) j5Var.c, (Object) null);
        this.c = 0;
        qk qkVar = this.d;
        qkVar.h = 0;
        qkVar.b = 0.0f;
        this.j = 1;
        for (int i4 = 0; i4 < this.k; i4++) {
            v5 v5Var = this.g[i4];
        }
        s();
        this.k = 0;
        this.p = new v5(j5Var);
    }
}
