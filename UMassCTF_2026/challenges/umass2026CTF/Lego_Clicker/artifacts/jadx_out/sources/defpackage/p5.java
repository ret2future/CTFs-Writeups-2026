package defpackage;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class p5 implements Collection {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ p5(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Collection
    public final void clear() {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                ((t5) this.b).a();
                return;
        }
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        int i;
        int i2 = this.a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                Object[] objArr = (Object[]) obj2;
                if (obj == null) {
                    int length = objArr.length;
                    i = 0;
                    while (i < length) {
                        if (objArr[i] != null) {
                            i++;
                        }
                    }
                    i = -1;
                } else {
                    int length2 = objArr.length;
                    for (int i3 = 0; i3 < length2; i3++) {
                        if (obj.equals(objArr[i3])) {
                            i = i3;
                        }
                    }
                    i = -1;
                }
                if (i >= 0) {
                }
                break;
            default:
                if (((t5) obj2).f(obj) >= 0) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.a) {
            case 0:
                collection.getClass();
                if (!collection.isEmpty()) {
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        if (!contains(it.next())) {
                            break;
                        }
                    }
                    break;
                }
                break;
            default:
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!contains(it2.next())) {
                        break;
                    }
                }
                break;
        }
        return false;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                if (((Object[]) obj).length == 0) {
                }
                break;
            default:
                if (((t5) obj).d() == 0) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return new r5(0, (Object[]) obj);
            default:
                return new yh((t5) obj, 1);
        }
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                t5 t5Var = (t5) this.b;
                int iF = t5Var.f(obj);
                if (iF < 0) {
                    return false;
                }
                t5Var.g(iF);
                return true;
        }
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                t5 t5Var = (t5) this.b;
                int iD = t5Var.d();
                int i = 0;
                boolean z = false;
                while (i < iD) {
                    if (collection.contains(t5Var.b(i, 1))) {
                        t5Var.g(i);
                        i--;
                        iD--;
                        z = true;
                    }
                    i++;
                }
                return z;
        }
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                t5 t5Var = (t5) this.b;
                int iD = t5Var.d();
                int i = 0;
                boolean z = false;
                while (i < iD) {
                    if (!collection.contains(t5Var.b(i, 1))) {
                        t5Var.g(i);
                        i--;
                        iD--;
                        z = true;
                    }
                    i++;
                }
                return z;
        }
    }

    @Override // java.util.Collection
    public final int size() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return ((Object[]) obj).length;
            default:
                return ((t5) obj).d();
        }
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                Object[] objArr = (Object[]) obj;
                Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
                objArrCopyOf.getClass();
                return objArrCopyOf;
            default:
                t5 t5Var = (t5) obj;
                int iD = t5Var.d();
                Object[] objArr2 = new Object[iD];
                for (int i2 = 0; i2 < iD; i2++) {
                    objArr2[i2] = t5Var.b(i2, 1);
                }
                return objArr2;
        }
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.a) {
            case 0:
                objArr.getClass();
                return d.b0(this, objArr);
            default:
                return ((t5) this.b).i(objArr, 1);
        }
    }
}
