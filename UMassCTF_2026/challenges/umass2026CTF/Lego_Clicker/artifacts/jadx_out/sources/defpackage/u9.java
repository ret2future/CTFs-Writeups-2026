package defpackage;

import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class u9 {
    public l8 a;
    public boolean b;
    public boolean c;
    public l8 d;
    public ArrayList e;
    public a8 f;
    public j6 g;
    public ArrayList h;

    public final void a(v9 v9Var, int i, ArrayList arrayList, in inVar) {
        mt mtVar = v9Var.d;
        in inVar2 = mtVar.c;
        v9 v9Var2 = mtVar.i;
        v9 v9Var3 = mtVar.h;
        if (inVar2 == null) {
            l8 l8Var = this.a;
            if (mtVar == l8Var.d || mtVar == l8Var.e) {
                return;
            }
            if (inVar == null) {
                inVar = new in();
                inVar.a = null;
                inVar.b = new ArrayList();
                inVar.a = mtVar;
                arrayList.add(inVar);
            }
            mtVar.c = inVar;
            inVar.b.add(mtVar);
            ArrayList arrayList2 = v9Var3.k;
            int size = arrayList2.size();
            int i2 = 0;
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList2.get(i3);
                i3++;
                t9 t9Var = (t9) obj;
                if (t9Var instanceof v9) {
                    a((v9) t9Var, i, arrayList, inVar);
                }
            }
            ArrayList arrayList3 = v9Var2.k;
            int size2 = arrayList3.size();
            int i4 = 0;
            while (i4 < size2) {
                Object obj2 = arrayList3.get(i4);
                i4++;
                t9 t9Var2 = (t9) obj2;
                if (t9Var2 instanceof v9) {
                    a((v9) t9Var2, i, arrayList, inVar);
                }
            }
            if (i == 1 && (mtVar instanceof wr)) {
                ArrayList arrayList4 = ((wr) mtVar).k.k;
                int size3 = arrayList4.size();
                int i5 = 0;
                while (i5 < size3) {
                    Object obj3 = arrayList4.get(i5);
                    i5++;
                    t9 t9Var3 = (t9) obj3;
                    if (t9Var3 instanceof v9) {
                        a((v9) t9Var3, i, arrayList, inVar);
                    }
                }
            }
            ArrayList arrayList5 = v9Var3.l;
            int size4 = arrayList5.size();
            int i6 = 0;
            while (i6 < size4) {
                Object obj4 = arrayList5.get(i6);
                i6++;
                a((v9) obj4, i, arrayList, inVar);
            }
            ArrayList arrayList6 = v9Var2.l;
            int size5 = arrayList6.size();
            int i7 = 0;
            while (i7 < size5) {
                Object obj5 = arrayList6.get(i7);
                i7++;
                a((v9) obj5, i, arrayList, inVar);
            }
            if (i == 1 && (mtVar instanceof wr)) {
                ArrayList arrayList7 = ((wr) mtVar).k.l;
                int size6 = arrayList7.size();
                while (i2 < size6) {
                    Object obj6 = arrayList7.get(i2);
                    i2++;
                    a((v9) obj6, i, arrayList, inVar);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00c9, code lost:
    
        if (r6 == 2) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(defpackage.l8 r25) {
        /*
            Method dump skipped, instruction units count: 860
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u9.b(l8):void");
    }

    public final void c() {
        l8 l8Var = this.a;
        ArrayList arrayList = this.h;
        ArrayList arrayList2 = this.e;
        arrayList2.clear();
        l8 l8Var2 = this.d;
        l8Var2.d.f();
        l8Var2.e.f();
        arrayList2.add(l8Var2.d);
        arrayList2.add(l8Var2.e);
        ArrayList arrayList3 = l8Var2.p0;
        int size = arrayList3.size();
        HashSet hashSet = null;
        int i = 0;
        while (i < size) {
            Object obj = arrayList3.get(i);
            i++;
            k8 k8Var = (k8) obj;
            if (k8Var instanceof te) {
                ue ueVar = new ue(k8Var);
                k8Var.d.f();
                k8Var.e.f();
                ueVar.f = ((te) k8Var).t0;
                arrayList2.add(ueVar);
            } else {
                if (k8Var.v()) {
                    if (k8Var.b == null) {
                        k8Var.b = new y6(k8Var, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(k8Var.b);
                } else {
                    arrayList2.add(k8Var.d);
                }
                if (k8Var.w()) {
                    if (k8Var.c == null) {
                        k8Var.c = new y6(k8Var, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(k8Var.c);
                } else {
                    arrayList2.add(k8Var.e);
                }
                if (k8Var instanceof h6) {
                    arrayList2.add(new we(k8Var));
                }
            }
        }
        if (hashSet != null) {
            arrayList2.addAll(hashSet);
        }
        int size2 = arrayList2.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            ((mt) obj2).f();
        }
        int size3 = arrayList2.size();
        int i3 = 0;
        while (i3 < size3) {
            Object obj3 = arrayList2.get(i3);
            i3++;
            mt mtVar = (mt) obj3;
            if (mtVar.b != l8Var2) {
                mtVar.d();
            }
        }
        arrayList.clear();
        e(l8Var.d, 0, arrayList);
        e(l8Var.e, 1, arrayList);
        this.b = false;
    }

    public final int d(l8 l8Var, int i) {
        ArrayList arrayList;
        int i2;
        long jMax;
        float f;
        l8 l8Var2 = l8Var;
        ArrayList arrayList2 = this.h;
        int size = arrayList2.size();
        long j = 0;
        int i3 = 0;
        long jMax2 = 0;
        while (i3 < size) {
            mt mtVar = ((in) arrayList2.get(i3)).a;
            if (!(mtVar instanceof y6) ? !(i != 0 ? (mtVar instanceof wr) : (mtVar instanceof xe)) : ((y6) mtVar).f != i) {
                v9 v9Var = (i == 0 ? l8Var2.d : l8Var2.e).h;
                v9 v9Var2 = (i == 0 ? l8Var2.d : l8Var2.e).i;
                v9 v9Var3 = mtVar.h;
                v9 v9Var4 = mtVar.i;
                boolean zContains = v9Var3.l.contains(v9Var);
                boolean zContains2 = v9Var4.l.contains(v9Var2);
                long j2 = mtVar.j();
                if (zContains && zContains2) {
                    long jB = in.b(v9Var3, j);
                    arrayList = arrayList2;
                    long jA = in.a(v9Var4, j);
                    long j3 = jB - j2;
                    int i4 = v9Var4.f;
                    i2 = i3;
                    if (j3 >= (-i4)) {
                        j3 += (long) i4;
                    }
                    long j4 = v9Var3.f;
                    long j5 = ((-jA) - j2) - j4;
                    if (j5 >= j4) {
                        j5 -= j4;
                    }
                    k8 k8Var = mtVar.b;
                    if (i == 0) {
                        f = k8Var.c0;
                    } else if (i == 1) {
                        f = k8Var.d0;
                    } else {
                        k8Var.getClass();
                        f = -1.0f;
                    }
                    float f2 = f > 0.0f ? (long) ((j3 / (1.0f - f)) + (j5 / f)) : 0L;
                    jMax = (((long) v9Var3.f) + ((((long) ((f2 * f) + 0.5f)) + j2) + ((long) (((1.0f - f) * f2) + 0.5f)))) - ((long) v9Var4.f);
                } else {
                    arrayList = arrayList2;
                    i2 = i3;
                    jMax = zContains ? Math.max(in.b(v9Var3, v9Var3.f), ((long) v9Var3.f) + j2) : zContains2 ? Math.max(-in.a(v9Var4, v9Var4.f), ((long) (-v9Var4.f)) + j2) : (mtVar.j() + ((long) v9Var3.f)) - ((long) v9Var4.f);
                }
            } else {
                arrayList = arrayList2;
                jMax = j;
                i2 = i3;
            }
            jMax2 = Math.max(jMax2, jMax);
            i3 = i2 + 1;
            arrayList2 = arrayList;
            l8Var2 = l8Var;
            j = 0;
        }
        return (int) jMax2;
    }

    public final void e(mt mtVar, int i, ArrayList arrayList) {
        v9 v9Var = mtVar.h;
        v9 v9Var2 = mtVar.i;
        ArrayList arrayList2 = v9Var.k;
        int size = arrayList2.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayList2.get(i3);
            i3++;
            t9 t9Var = (t9) obj;
            if (t9Var instanceof v9) {
                a((v9) t9Var, i, arrayList, null);
            } else if (t9Var instanceof mt) {
                a(((mt) t9Var).h, i, arrayList, null);
            }
        }
        ArrayList arrayList3 = v9Var2.k;
        int size2 = arrayList3.size();
        int i4 = 0;
        while (i4 < size2) {
            Object obj2 = arrayList3.get(i4);
            i4++;
            t9 t9Var2 = (t9) obj2;
            if (t9Var2 instanceof v9) {
                a((v9) t9Var2, i, arrayList, null);
            } else if (t9Var2 instanceof mt) {
                a(((mt) t9Var2).i, i, arrayList, null);
            }
        }
        if (i == 1) {
            ArrayList arrayList4 = ((wr) mtVar).k.k;
            int size3 = arrayList4.size();
            while (i2 < size3) {
                Object obj3 = arrayList4.get(i2);
                i2++;
                t9 t9Var3 = (t9) obj3;
                if (t9Var3 instanceof v9) {
                    a((v9) t9Var3, i, arrayList, null);
                }
            }
        }
    }

    public final void f(int i, int i2, int i3, int i4, k8 k8Var) {
        j6 j6Var = this.g;
        j6Var.a = i;
        j6Var.b = i3;
        j6Var.c = i2;
        j6Var.d = i4;
        this.f.b(k8Var, j6Var);
        k8Var.K(j6Var.e);
        k8Var.H(j6Var.f);
        k8Var.E = j6Var.h;
        int i5 = j6Var.g;
        k8Var.Z = i5;
        k8Var.E = i5 > 0;
    }

    public final void g() {
        i6 i6Var;
        u9 u9Var = this;
        ArrayList arrayList = u9Var.a.p0;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            k8 k8Var = (k8) arrayList.get(i);
            if (!k8Var.a) {
                int[] iArr = k8Var.o0;
                int i3 = iArr[0];
                int i4 = iArr[1];
                int i5 = k8Var.r;
                int i6 = k8Var.s;
                boolean z = i3 == 2 || (i3 == 3 && i5 == 1);
                boolean z2 = i4 == 2 || (i4 == 3 && i6 == 1);
                aa aaVar = k8Var.d.e;
                boolean z3 = aaVar.j;
                aa aaVar2 = k8Var.e.e;
                boolean z4 = aaVar2.j;
                boolean z5 = z;
                if (z3 && z4) {
                    u9Var.f(1, aaVar.g, 1, aaVar2.g, k8Var);
                    k8Var.a = true;
                } else if (z3 && z2) {
                    f(1, aaVar.g, 2, aaVar2.g, k8Var);
                    wr wrVar = k8Var.e;
                    if (i4 == 3) {
                        wrVar.e.m = k8Var.i();
                    } else {
                        wrVar.e.d(k8Var.i());
                        k8Var.a = true;
                    }
                } else if (z4 && z5) {
                    f(2, aaVar.g, 1, aaVar2.g, k8Var);
                    xe xeVar = k8Var.d;
                    if (i3 == 3) {
                        xeVar.e.m = k8Var.o();
                    } else {
                        xeVar.e.d(k8Var.o());
                        k8Var.a = true;
                    }
                }
                if (k8Var.a && (i6Var = k8Var.e.l) != null) {
                    i6Var.d(k8Var.Z);
                }
                u9Var = this;
            }
            i = i2;
        }
    }
}
