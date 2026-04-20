package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class l8 extends k8 {
    public x6[] A0;
    public x6[] B0;
    public int C0;
    public boolean D0;
    public boolean E0;
    public WeakReference F0;
    public WeakReference G0;
    public WeakReference H0;
    public WeakReference I0;
    public final HashSet J0;
    public final j6 K0;
    public ArrayList p0 = new ArrayList();
    public final j5 q0;
    public final u9 r0;
    public int s0;
    public a8 t0;
    public boolean u0;
    public final tg v0;
    public int w0;
    public int x0;
    public int y0;
    public int z0;

    public l8() {
        j5 j5Var = new j5();
        j5Var.a = new ArrayList();
        j5Var.b = new j6();
        j5Var.c = this;
        this.q0 = j5Var;
        u9 u9Var = new u9();
        u9Var.b = true;
        u9Var.c = true;
        u9Var.e = new ArrayList();
        new ArrayList();
        u9Var.f = null;
        u9Var.g = new j6();
        u9Var.h = new ArrayList();
        u9Var.a = this;
        u9Var.d = this;
        this.r0 = u9Var;
        this.t0 = null;
        this.u0 = false;
        this.v0 = new tg();
        this.y0 = 0;
        this.z0 = 0;
        this.A0 = new x6[4];
        this.B0 = new x6[4];
        this.C0 = 257;
        this.D0 = false;
        this.E0 = false;
        this.F0 = null;
        this.G0 = null;
        this.H0 = null;
        this.I0 = null;
        this.J0 = new HashSet();
        this.K0 = new j6();
    }

    public static void R(k8 k8Var, a8 a8Var, j6 j6Var) {
        int i;
        int i2;
        if (a8Var == null) {
            return;
        }
        int i3 = k8Var.f0;
        int[] iArr = k8Var.t;
        if (i3 == 8 || (k8Var instanceof te) || (k8Var instanceof h6)) {
            j6Var.e = 0;
            j6Var.f = 0;
            return;
        }
        int[] iArr2 = k8Var.o0;
        j6Var.a = iArr2[0];
        j6Var.b = iArr2[1];
        j6Var.c = k8Var.o();
        j6Var.d = k8Var.i();
        j6Var.i = false;
        j6Var.j = 0;
        boolean z = j6Var.a == 3;
        boolean z2 = j6Var.b == 3;
        boolean z3 = z && k8Var.V > 0.0f;
        boolean z4 = z2 && k8Var.V > 0.0f;
        if (z && k8Var.r(0) && k8Var.r == 0 && !z3) {
            j6Var.a = 2;
            if (z2 && k8Var.s == 0) {
                j6Var.a = 1;
            }
            z = false;
        }
        if (z2 && k8Var.r(1) && k8Var.s == 0 && !z4) {
            j6Var.b = 2;
            if (z && k8Var.r == 0) {
                j6Var.b = 1;
            }
            z2 = false;
        }
        if (k8Var.y()) {
            j6Var.a = 1;
            z = false;
        }
        if (k8Var.z()) {
            j6Var.b = 1;
            z2 = false;
        }
        if (z3) {
            if (iArr[0] == 4) {
                j6Var.a = 1;
            } else if (!z2) {
                if (j6Var.b == 1) {
                    i2 = j6Var.d;
                } else {
                    j6Var.a = 2;
                    a8Var.b(k8Var, j6Var);
                    i2 = j6Var.f;
                }
                j6Var.a = 1;
                j6Var.c = (int) (k8Var.V * i2);
            }
        }
        if (z4) {
            if (iArr[1] == 4) {
                j6Var.b = 1;
            } else if (!z) {
                if (j6Var.a == 1) {
                    i = j6Var.c;
                } else {
                    j6Var.b = 2;
                    a8Var.b(k8Var, j6Var);
                    i = j6Var.e;
                }
                j6Var.b = 1;
                int i4 = k8Var.W;
                float f = k8Var.V;
                if (i4 == -1) {
                    j6Var.d = (int) (i / f);
                } else {
                    j6Var.d = (int) (f * i);
                }
            }
        }
        a8Var.b(k8Var, j6Var);
        k8Var.K(j6Var.e);
        k8Var.H(j6Var.f);
        k8Var.E = j6Var.h;
        int i5 = j6Var.g;
        k8Var.Z = i5;
        k8Var.E = i5 > 0;
        j6Var.j = 0;
    }

    @Override // defpackage.k8
    public final void A() {
        this.v0.t();
        this.w0 = 0;
        this.x0 = 0;
        this.p0.clear();
        super.A();
    }

    @Override // defpackage.k8
    public final void C(j5 j5Var) {
        super.C(j5Var);
        int size = this.p0.size();
        for (int i = 0; i < size; i++) {
            ((k8) this.p0.get(i)).C(j5Var);
        }
    }

    @Override // defpackage.k8
    public final void L(boolean z, boolean z2) {
        super.L(z, z2);
        int size = this.p0.size();
        for (int i = 0; i < size; i++) {
            ((k8) this.p0.get(i)).L(z, z2);
        }
    }

    public final void N(k8 k8Var, int i) {
        if (i == 0) {
            int i2 = this.y0 + 1;
            x6[] x6VarArr = this.B0;
            if (i2 >= x6VarArr.length) {
                this.B0 = (x6[]) Arrays.copyOf(x6VarArr, x6VarArr.length * 2);
            }
            x6[] x6VarArr2 = this.B0;
            int i3 = this.y0;
            x6VarArr2[i3] = new x6(k8Var, 0, this.u0);
            this.y0 = i3 + 1;
            return;
        }
        if (i == 1) {
            int i4 = this.z0 + 1;
            x6[] x6VarArr3 = this.A0;
            if (i4 >= x6VarArr3.length) {
                this.A0 = (x6[]) Arrays.copyOf(x6VarArr3, x6VarArr3.length * 2);
            }
            x6[] x6VarArr4 = this.A0;
            int i5 = this.z0;
            x6VarArr4[i5] = new x6(k8Var, 1, this.u0);
            this.z0 = i5 + 1;
        }
    }

    public final void O(tg tgVar) {
        l8 l8Var;
        tg tgVar2;
        boolean zS = S(64);
        b(tgVar, zS);
        int size = this.p0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            k8 k8Var = (k8) this.p0.get(i);
            boolean[] zArr = k8Var.R;
            zArr[0] = false;
            zArr[1] = false;
            if (k8Var instanceof h6) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                k8 k8Var2 = (k8) this.p0.get(i2);
                if (k8Var2 instanceof h6) {
                    h6 h6Var = (h6) k8Var2;
                    for (int i3 = 0; i3 < h6Var.q0; i3++) {
                        k8 k8Var3 = h6Var.p0[i3];
                        if (h6Var.s0 || k8Var3.c()) {
                            int i4 = h6Var.r0;
                            if (i4 == 0 || i4 == 1) {
                                k8Var3.R[0] = true;
                            } else if (i4 == 2 || i4 == 3) {
                                k8Var3.R[1] = true;
                            }
                        }
                    }
                }
            }
        }
        HashSet hashSet = this.J0;
        hashSet.clear();
        for (int i5 = 0; i5 < size; i5++) {
            k8 k8Var4 = (k8) this.p0.get(i5);
            k8Var4.getClass();
            if (k8Var4 instanceof te) {
                k8Var4.b(tgVar, zS);
            }
        }
        while (hashSet.size() > 0) {
            int size2 = hashSet.size();
            Iterator it = hashSet.iterator();
            if (it.hasNext()) {
                ((k8) it.next()).getClass();
                z6.a();
                return;
            } else if (size2 == hashSet.size()) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((k8) it2.next()).b(tgVar, zS);
                }
                hashSet.clear();
            }
        }
        if (tg.q) {
            HashSet<k8> hashSet2 = new HashSet();
            for (int i6 = 0; i6 < size; i6++) {
                k8 k8Var5 = (k8) this.p0.get(i6);
                k8Var5.getClass();
                if (!(k8Var5 instanceof te)) {
                    hashSet2.add(k8Var5);
                }
            }
            l8Var = this;
            tgVar2 = tgVar;
            l8Var.a(this, tgVar2, hashSet2, this.o0[0] == 2 ? 0 : 1, false);
            for (k8 k8Var6 : hashSet2) {
                d.j(l8Var, tgVar2, k8Var6);
                k8Var6.b(tgVar2, zS);
            }
        } else {
            l8Var = this;
            tgVar2 = tgVar;
            for (int i7 = 0; i7 < size; i7++) {
                k8 k8Var7 = (k8) l8Var.p0.get(i7);
                if (k8Var7 instanceof l8) {
                    int[] iArr = k8Var7.o0;
                    int i8 = iArr[0];
                    int i9 = iArr[1];
                    if (i8 == 2) {
                        k8Var7.I(1);
                    }
                    if (i9 == 2) {
                        k8Var7.J(1);
                    }
                    k8Var7.b(tgVar2, zS);
                    if (i8 == 2) {
                        k8Var7.I(i8);
                    }
                    if (i9 == 2) {
                        k8Var7.J(i9);
                    }
                } else {
                    d.j(l8Var, tgVar2, k8Var7);
                    if (!(k8Var7 instanceof te)) {
                        k8Var7.b(tgVar2, zS);
                    }
                }
            }
        }
        if (l8Var.y0 > 0) {
            d.a(l8Var, tgVar2, null, 0);
        }
        if (l8Var.z0 > 0) {
            d.a(l8Var, tgVar2, null, 1);
        }
    }

    public final boolean P(int i, boolean z) {
        boolean z2;
        u9 u9Var = this.r0;
        ArrayList arrayList = u9Var.e;
        l8 l8Var = u9Var.a;
        boolean z3 = false;
        int iH = l8Var.h(0);
        int iH2 = l8Var.h(1);
        int iP = l8Var.p();
        int iQ = l8Var.q();
        if (z && (iH == 2 || iH2 == 2)) {
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                Object obj = arrayList.get(i2);
                i2++;
                mt mtVar = (mt) obj;
                if (mtVar.f == i && !mtVar.k()) {
                    z = false;
                    break;
                }
            }
            if (i == 0) {
                if (z && iH == 2) {
                    l8Var.I(1);
                    l8Var.K(u9Var.d(l8Var, 0));
                    l8Var.d.e.d(l8Var.o());
                }
            } else if (z && iH2 == 2) {
                l8Var.J(1);
                l8Var.H(u9Var.d(l8Var, 1));
                l8Var.e.e.d(l8Var.i());
            }
        }
        int[] iArr = l8Var.o0;
        if (i == 0) {
            int i3 = iArr[0];
            if (i3 == 1 || i3 == 4) {
                int iO = l8Var.o() + iP;
                l8Var.d.i.d(iO);
                l8Var.d.e.d(iO - iP);
                z2 = true;
            }
            z2 = false;
        } else {
            int i4 = iArr[1];
            if (i4 == 1 || i4 == 4) {
                int i5 = l8Var.i() + iQ;
                l8Var.e.i.d(i5);
                l8Var.e.e.d(i5 - iQ);
                z2 = true;
            }
            z2 = false;
        }
        u9Var.g();
        int size2 = arrayList.size();
        int i6 = 0;
        while (i6 < size2) {
            Object obj2 = arrayList.get(i6);
            i6++;
            mt mtVar2 = (mt) obj2;
            if (mtVar2.f == i && (mtVar2.b != l8Var || mtVar2.g)) {
                mtVar2.e();
            }
        }
        int size3 = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size3) {
                z3 = true;
                break;
            }
            Object obj3 = arrayList.get(i7);
            i7++;
            mt mtVar3 = (mt) obj3;
            if (mtVar3.f == i && (z2 || mtVar3.b != l8Var)) {
                if (!mtVar3.h.j || !mtVar3.i.j || (!(mtVar3 instanceof y6) && !mtVar3.e.j)) {
                    break;
                }
            }
        }
        l8Var.I(iH);
        l8Var.J(iH2);
        return z3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:332:0x05d1  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x07f1  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x080b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0818 A[LOOP:14: B:479:0x0816->B:480:0x0818, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:483:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x087f  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x089e  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x08a6  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x08de  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x08e2  */
    /* JADX WARN: Removed duplicated region for block: B:585:0x08e3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012c  */
    /* JADX WARN: Type inference failed for: r0v100 */
    /* JADX WARN: Type inference failed for: r0v101 */
    /* JADX WARN: Type inference failed for: r0v102 */
    /* JADX WARN: Type inference failed for: r0v103 */
    /* JADX WARN: Type inference failed for: r0v104 */
    /* JADX WARN: Type inference failed for: r0v105 */
    /* JADX WARN: Type inference failed for: r0v106 */
    /* JADX WARN: Type inference failed for: r0v107 */
    /* JADX WARN: Type inference failed for: r0v108 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v99 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r25v14 */
    /* JADX WARN: Type inference failed for: r25v15 */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r25v3 */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARN: Type inference failed for: r25v5 */
    /* JADX WARN: Type inference failed for: r25v6 */
    /* JADX WARN: Type inference failed for: r25v7 */
    /* JADX WARN: Type inference failed for: r25v8 */
    /* JADX WARN: Type inference failed for: r25v9 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r32v0, types: [k8, l8] */
    /* JADX WARN: Type inference failed for: r3v67, types: [int] */
    /* JADX WARN: Type inference failed for: r5v53, types: [int] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v74, types: [int] */
    /* JADX WARN: Type inference failed for: r8v22, types: [int] */
    /* JADX WARN: Type inference failed for: r9v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Q() {
        /*
            Method dump skipped, instruction units count: 2305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l8.Q():void");
    }

    public final boolean S(int i) {
        return (this.C0 & i) == i;
    }

    @Override // defpackage.k8
    public final void l(StringBuilder sb) {
        sb.append(this.j + ":{\n");
        StringBuilder sb2 = new StringBuilder("  actualWidth:");
        sb2.append(this.T);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("  actualHeight:" + this.U);
        sb.append("\n");
        ArrayList arrayList = this.p0;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((k8) obj).l(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
