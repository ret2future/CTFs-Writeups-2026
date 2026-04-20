package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class y6 extends mt {
    public final ArrayList k;
    public int l;

    public y6(k8 k8Var, int i) {
        k8 k8Var2;
        super(k8Var);
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        this.f = i;
        k8 k8Var3 = this.b;
        k8 k8VarK = k8Var3.k(i);
        while (true) {
            k8Var2 = k8Var3;
            k8Var3 = k8VarK;
            if (k8Var3 == null) {
                break;
            } else {
                k8VarK = k8Var3.k(this.f);
            }
        }
        this.b = k8Var2;
        int i2 = this.f;
        arrayList.add(i2 == 0 ? k8Var2.d : i2 == 1 ? k8Var2.e : null);
        k8 k8VarJ = k8Var2.j(this.f);
        while (k8VarJ != null) {
            int i3 = this.f;
            arrayList.add(i3 == 0 ? k8VarJ.d : i3 == 1 ? k8VarJ.e : null);
            k8VarJ = k8VarJ.j(this.f);
        }
        int size = arrayList.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList.get(i4);
            i4++;
            mt mtVar = (mt) obj;
            int i5 = this.f;
            if (i5 == 0) {
                mtVar.b.b = this;
            } else if (i5 == 1) {
                mtVar.b.c = this;
            }
        }
        if (this.f == 0 && ((l8) this.b.S).u0 && arrayList.size() > 1) {
            this.b = ((mt) arrayList.get(arrayList.size() - 1)).b;
        }
        int i6 = this.f;
        k8 k8Var4 = this.b;
        this.l = i6 == 0 ? k8Var4.h0 : k8Var4.i0;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00dd  */
    @Override // defpackage.t9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(defpackage.t9 r28) {
        /*
            Method dump skipped, instruction units count: 943
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.y6.a(t9):void");
    }

    @Override // defpackage.mt
    public final void d() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((mt) obj).d();
        }
        int size2 = arrayList.size();
        if (size2 < 1) {
            return;
        }
        k8 k8Var = ((mt) arrayList.get(0)).b;
        k8 k8Var2 = ((mt) arrayList.get(size2 - 1)).b;
        int i2 = this.f;
        v9 v9Var = this.i;
        v9 v9Var2 = this.h;
        if (i2 == 0) {
            v7 v7Var = k8Var.H;
            v7 v7Var2 = k8Var2.J;
            v9 v9VarI = mt.i(v7Var, 0);
            int iD = v7Var.d();
            k8 k8VarM = m();
            if (k8VarM != null) {
                iD = k8VarM.H.d();
            }
            if (v9VarI != null) {
                mt.b(v9Var2, v9VarI, iD);
            }
            v9 v9VarI2 = mt.i(v7Var2, 0);
            int iD2 = v7Var2.d();
            k8 k8VarN = n();
            if (k8VarN != null) {
                iD2 = k8VarN.J.d();
            }
            if (v9VarI2 != null) {
                mt.b(v9Var, v9VarI2, -iD2);
            }
        } else {
            v7 v7Var3 = k8Var.I;
            v7 v7Var4 = k8Var2.K;
            v9 v9VarI3 = mt.i(v7Var3, 1);
            int iD3 = v7Var3.d();
            k8 k8VarM2 = m();
            if (k8VarM2 != null) {
                iD3 = k8VarM2.I.d();
            }
            if (v9VarI3 != null) {
                mt.b(v9Var2, v9VarI3, iD3);
            }
            v9 v9VarI4 = mt.i(v7Var4, 1);
            int iD4 = v7Var4.d();
            k8 k8VarN2 = n();
            if (k8VarN2 != null) {
                iD4 = k8VarN2.K.d();
            }
            if (v9VarI4 != null) {
                mt.b(v9Var, v9VarI4, -iD4);
            }
        }
        v9Var2.a = this;
        v9Var.a = this;
    }

    @Override // defpackage.mt
    public final void e() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return;
            }
            ((mt) arrayList.get(i)).e();
            i++;
        }
    }

    @Override // defpackage.mt
    public final void f() {
        this.c = null;
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((mt) obj).f();
        }
    }

    @Override // defpackage.mt
    public final long j() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            mt mtVar = (mt) arrayList.get(i);
            j = ((long) mtVar.i.f) + mtVar.j() + j + ((long) mtVar.h.f);
        }
        return j;
    }

    @Override // defpackage.mt
    public final boolean k() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (!((mt) arrayList.get(i)).k()) {
                return false;
            }
        }
        return true;
    }

    public final k8 m() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return null;
            }
            k8 k8Var = ((mt) arrayList.get(i)).b;
            if (k8Var.f0 != 8) {
                return k8Var;
            }
            i++;
        }
    }

    public final k8 n() {
        ArrayList arrayList = this.k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            k8 k8Var = ((mt) arrayList.get(size)).b;
            if (k8Var.f0 != 8) {
                return k8Var;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f == 0 ? "horizontal : " : "vertical : ");
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            sb.append("<");
            sb.append((mt) obj);
            sb.append("> ");
        }
        return sb.toString();
    }
}
