package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zh implements Set {
    public final /* synthetic */ int a;
    public final /* synthetic */ t5 b;

    public /* synthetic */ zh(t5 t5Var, int i) {
        this.a = i;
        this.b = t5Var;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.a) {
            case 0:
                t5 t5Var = this.b;
                int iD = t5Var.d();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    switch (t5Var.d) {
                        case 0:
                            ((u5) t5Var.e).put(key, value);
                            break;
                        default:
                            ((w5) t5Var.e).add(key);
                            break;
                    }
                }
                return iD != t5Var.d();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        int i = this.a;
        t5 t5Var = this.b;
        switch (i) {
            case 0:
                t5Var.a();
                break;
            default:
                t5Var.a();
                break;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        int i = this.a;
        t5 t5Var = this.b;
        switch (i) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    int iE = t5Var.e(entry.getKey());
                    if (iE >= 0) {
                        Object objB = t5Var.b(iE, 1);
                        Object value = entry.getValue();
                        if (objB == value || (objB != null && objB.equals(value))) {
                        }
                        break;
                    }
                }
                break;
            default:
                if (t5Var.e(obj) >= 0) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.a) {
            case 0:
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        break;
                    }
                }
                break;
            default:
                Map mapC = this.b.c();
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!mapC.containsKey(it2.next())) {
                        break;
                    }
                }
                break;
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        switch (this.a) {
        }
        return t5.h(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        int i = this.a;
        t5 t5Var = this.b;
        switch (i) {
            case 0:
                int iHashCode = 0;
                for (int iD = t5Var.d() - 1; iD >= 0; iD--) {
                    Object objB = t5Var.b(iD, 0);
                    Object objB2 = t5Var.b(iD, 1);
                    iHashCode += (objB == null ? 0 : objB.hashCode()) ^ (objB2 == null ? 0 : objB2.hashCode());
                }
                return iHashCode;
            default:
                int iHashCode2 = 0;
                for (int iD2 = t5Var.d() - 1; iD2 >= 0; iD2--) {
                    Object objB3 = t5Var.b(iD2, 0);
                    iHashCode2 += objB3 == null ? 0 : objB3.hashCode();
                }
                return iHashCode2;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        int i = this.a;
        t5 t5Var = this.b;
        switch (i) {
            case 0:
                if (t5Var.d() == 0) {
                }
                break;
            default:
                if (t5Var.d() == 0) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i = this.a;
        t5 t5Var = this.b;
        switch (i) {
            case 0:
                return new ai(t5Var);
            default:
                return new yh(t5Var, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                t5 t5Var = this.b;
                int iE = t5Var.e(obj);
                if (iE < 0) {
                    return false;
                }
                t5Var.g(iE);
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                Map mapC = this.b.c();
                int size = mapC.size();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    mapC.remove(it.next());
                }
                return size != mapC.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                Map mapC = this.b.c();
                int size = mapC.size();
                Iterator it = mapC.keySet().iterator();
                while (it.hasNext()) {
                    if (!collection.contains(it.next())) {
                        it.remove();
                    }
                }
                return size != mapC.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        int i = this.a;
        t5 t5Var = this.b;
        switch (i) {
        }
        return t5Var.d();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                t5 t5Var = this.b;
                int iD = t5Var.d();
                Object[] objArr = new Object[iD];
                for (int i = 0; i < iD; i++) {
                    objArr[i] = t5Var.b(i, 0);
                }
                return objArr;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return this.b.i(objArr, 0);
        }
    }
}
