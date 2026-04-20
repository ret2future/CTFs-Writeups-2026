package defpackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class v7 {
    public int b;
    public boolean c;
    public final k8 d;
    public final int e;
    public v7 f;
    public mo i;
    public HashSet a = null;
    public int g = 0;
    public int h = Integer.MIN_VALUE;

    public v7(k8 k8Var, int i) {
        this.d = k8Var;
        this.e = i;
    }

    public final void a(v7 v7Var, int i, int i2) {
        if (v7Var == null) {
            g();
            return;
        }
        this.f = v7Var;
        if (v7Var.a == null) {
            v7Var.a = new HashSet();
        }
        HashSet hashSet = this.f.a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.g = i;
        this.h = i2;
    }

    public final void b(int i, lt ltVar, ArrayList arrayList) {
        HashSet hashSet = this.a;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                hb.y(((v7) it.next()).d, i, arrayList, ltVar);
            }
        }
    }

    public final int c() {
        if (this.c) {
            return this.b;
        }
        return 0;
    }

    public final int d() {
        v7 v7Var;
        if (this.d.f0 == 8) {
            return 0;
        }
        int i = this.h;
        return (i == Integer.MIN_VALUE || (v7Var = this.f) == null || v7Var.d.f0 != 8) ? this.g : i;
    }

    public final boolean e() {
        v7 v7Var;
        HashSet<v7> hashSet = this.a;
        if (hashSet == null) {
            return false;
        }
        for (v7 v7Var2 : hashSet) {
            k8 k8Var = v7Var2.d;
            int i = v7Var2.e;
            switch (lo.c(i)) {
                case 0:
                case 5:
                case 6:
                case 7:
                case 8:
                    v7Var = null;
                    break;
                case 1:
                    v7Var = k8Var.J;
                    break;
                case 2:
                    v7Var = k8Var.K;
                    break;
                case 3:
                    v7Var = k8Var.H;
                    break;
                case 4:
                    v7Var = k8Var.I;
                    break;
                default:
                    throw new AssertionError(lo.b(i));
            }
            if (v7Var.f()) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return this.f != null;
    }

    public final void g() {
        HashSet hashSet;
        v7 v7Var = this.f;
        if (v7Var != null && (hashSet = v7Var.a) != null) {
            hashSet.remove(this);
            if (this.f.a.size() == 0) {
                this.f.a = null;
            }
        }
        this.a = null;
        this.f = null;
        this.g = 0;
        this.h = Integer.MIN_VALUE;
        this.c = false;
        this.b = 0;
    }

    public final void h() {
        mo moVar = this.i;
        if (moVar == null) {
            this.i = new mo(1);
        } else {
            moVar.c();
        }
    }

    public final void i(int i) {
        this.b = i;
        this.c = true;
    }

    public final String toString() {
        return this.d.g0 + ":" + lo.b(this.e);
    }
}
