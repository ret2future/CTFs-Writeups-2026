package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class mt implements t9 {
    public int a;
    public k8 b;
    public in c;
    public int d;
    public final aa e = new aa(this);
    public int f = 0;
    public boolean g = false;
    public final v9 h = new v9(this);
    public final v9 i = new v9(this);
    public int j = 1;

    public mt(k8 k8Var) {
        this.b = k8Var;
    }

    public static void b(v9 v9Var, v9 v9Var2, int i) {
        v9Var.l.add(v9Var2);
        v9Var.f = i;
        v9Var2.k.add(v9Var);
    }

    public static v9 h(v7 v7Var) {
        v7 v7Var2 = v7Var.f;
        if (v7Var2 == null) {
            return null;
        }
        k8 k8Var = v7Var2.d;
        int iC = lo.c(v7Var2.e);
        if (iC == 1) {
            return k8Var.d.h;
        }
        if (iC == 2) {
            return k8Var.e.h;
        }
        if (iC == 3) {
            return k8Var.d.i;
        }
        if (iC == 4) {
            return k8Var.e.i;
        }
        if (iC != 5) {
            return null;
        }
        return k8Var.e.k;
    }

    public static v9 i(v7 v7Var, int i) {
        v7 v7Var2 = v7Var.f;
        if (v7Var2 == null) {
            return null;
        }
        k8 k8Var = v7Var2.d;
        mt mtVar = i == 0 ? k8Var.d : k8Var.e;
        int iC = lo.c(v7Var2.e);
        if (iC == 1 || iC == 2) {
            return mtVar.h;
        }
        if (iC == 3 || iC == 4) {
            return mtVar.i;
        }
        return null;
    }

    public final void c(v9 v9Var, v9 v9Var2, int i, aa aaVar) {
        v9Var.l.add(v9Var2);
        v9Var.l.add(this.e);
        v9Var.h = i;
        v9Var.i = aaVar;
        v9Var2.k.add(v9Var);
        aaVar.k.add(v9Var);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        k8 k8Var = this.b;
        if (i2 == 0) {
            int i3 = k8Var.v;
            int iMax = Math.max(k8Var.u, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax != i) {
                return iMax;
            }
        } else {
            int i4 = k8Var.y;
            int iMax2 = Math.max(k8Var.x, i);
            if (i4 > 0) {
                iMax2 = Math.min(i4, i);
            }
            if (iMax2 != i) {
                return iMax2;
            }
        }
        return i;
    }

    public long j() {
        if (this.e.j) {
            return r2.g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(defpackage.v7 r12, defpackage.v7 r13, int r14) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mt.l(v7, v7, int):void");
    }
}
