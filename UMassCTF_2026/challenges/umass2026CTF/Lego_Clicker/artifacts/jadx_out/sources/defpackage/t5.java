package defpackage;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class t5 {
    public zh a;
    public zh b;
    public p5 c;
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ t5(int i, Object obj) {
        this.d = i;
        this.e = obj;
    }

    public static boolean h(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() == set2.size()) {
                return set.containsAll(set2);
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final void a() {
        int i = this.d;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((u5) obj).clear();
                break;
            default:
                ((w5) obj).clear();
                break;
        }
    }

    public final Object b(int i, int i2) {
        int i3 = this.d;
        Object obj = this.e;
        switch (i3) {
            case 0:
                return ((u5) obj).b[(i << 1) + i2];
            default:
                return ((w5) obj).b[i];
        }
    }

    public final Map c() {
        switch (this.d) {
            case 0:
                return (u5) this.e;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    public final int d() {
        int i = this.d;
        Object obj = this.e;
        switch (i) {
            case 0:
                return ((u5) obj).c;
            default:
                return ((w5) obj).c;
        }
    }

    public final int e(Object obj) {
        int i = this.d;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                return ((u5) obj2).d(obj);
            default:
                w5 w5Var = (w5) obj2;
                return obj == null ? w5Var.d() : w5Var.c(obj.hashCode(), obj);
        }
    }

    public final int f(Object obj) {
        int i = this.d;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                return ((u5) obj2).f(obj);
            default:
                w5 w5Var = (w5) obj2;
                return obj == null ? w5Var.d() : w5Var.c(obj.hashCode(), obj);
        }
    }

    public final void g(int i) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((u5) obj).h(i);
                break;
            default:
                ((w5) obj).e(i);
                break;
        }
    }

    public final Object[] i(Object[] objArr, int i) {
        int iD = d();
        if (objArr.length < iD) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), iD);
        }
        for (int i2 = 0; i2 < iD; i2++) {
            objArr[i2] = b(i2, i);
        }
        if (objArr.length > iD) {
            objArr[iD] = null;
        }
        return objArr;
    }
}
