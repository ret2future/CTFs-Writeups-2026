package defpackage;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class q5 extends AbstractList implements List {
    public static final Object[] d = new Object[0];
    public int a;
    public Object[] b = d;
    public int c;

    public final void a(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.b.length;
        while (i < length && it.hasNext()) {
            this.b[i] = it.next();
            i++;
        }
        int i2 = this.a;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.b[i3] = it.next();
        }
        this.c = collection.size() + this.c;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int length;
        int i2 = this.c;
        if (i < 0 || i > i2) {
            z6.g("index: ", i, ", size: ", i2);
            return;
        }
        if (i == i2) {
            addLast(obj);
            return;
        }
        if (i == 0) {
            addFirst(obj);
            return;
        }
        g();
        b(this.c + 1);
        int iF = f(this.a + i);
        int i3 = this.c;
        if (i < ((i3 + 1) >> 1)) {
            if (iF == 0) {
                Object[] objArr = this.b;
                objArr.getClass();
                length = objArr.length - 1;
            } else {
                length = iF - 1;
            }
            int length2 = this.a;
            if (length2 == 0) {
                Object[] objArr2 = this.b;
                objArr2.getClass();
                length2 = objArr2.length;
            }
            int i4 = length2 - 1;
            int i5 = this.a;
            Object[] objArr3 = this.b;
            if (length >= i5) {
                objArr3[i4] = objArr3[i5];
                z5.l0(objArr3, objArr3, i5, i5 + 1, length + 1);
            } else {
                z5.l0(objArr3, objArr3, i5 - 1, i5, objArr3.length);
                Object[] objArr4 = this.b;
                objArr4[objArr4.length - 1] = objArr4[0];
                z5.l0(objArr4, objArr4, 0, 1, length + 1);
            }
            this.b[length] = obj;
            this.a = i4;
        } else {
            int iF2 = f(this.a + i3);
            Object[] objArr5 = this.b;
            if (iF < iF2) {
                z5.l0(objArr5, objArr5, iF + 1, iF, iF2);
            } else {
                z5.l0(objArr5, objArr5, 1, 0, iF2);
                Object[] objArr6 = this.b;
                objArr6[0] = objArr6[objArr6.length - 1];
                z5.l0(objArr6, objArr6, iF + 1, iF, objArr6.length - 1);
            }
            this.b[iF] = obj;
        }
        this.c++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        collection.getClass();
        int i2 = this.c;
        if (i < 0 || i > i2) {
            z6.g("index: ", i, ", size: ", i2);
            return false;
        }
        if (collection.isEmpty()) {
            return false;
        }
        if (i == this.c) {
            return addAll(collection);
        }
        g();
        b(collection.size() + this.c);
        int iF = f(this.a + this.c);
        int iF2 = f(this.a + i);
        int size = collection.size();
        if (i >= ((this.c + 1) >> 1)) {
            int i3 = iF2 + size;
            Object[] objArr = this.b;
            if (iF2 < iF) {
                int i4 = size + iF;
                if (i4 <= objArr.length) {
                    z5.l0(objArr, objArr, i3, iF2, iF);
                } else if (i3 >= objArr.length) {
                    z5.l0(objArr, objArr, i3 - objArr.length, iF2, iF);
                } else {
                    int length = iF - (i4 - objArr.length);
                    z5.l0(objArr, objArr, 0, length, iF);
                    Object[] objArr2 = this.b;
                    z5.l0(objArr2, objArr2, i3, iF2, length);
                }
            } else {
                z5.l0(objArr, objArr, size, 0, iF);
                Object[] objArr3 = this.b;
                if (i3 >= objArr3.length) {
                    z5.l0(objArr3, objArr3, i3 - objArr3.length, iF2, objArr3.length);
                } else {
                    z5.l0(objArr3, objArr3, 0, objArr3.length - size, objArr3.length);
                    Object[] objArr4 = this.b;
                    z5.l0(objArr4, objArr4, i3, iF2, objArr4.length - size);
                }
            }
            a(iF2, collection);
            return true;
        }
        int i5 = this.a;
        int length2 = i5 - size;
        Object[] objArr5 = this.b;
        if (iF2 < i5) {
            z5.l0(objArr5, objArr5, length2, i5, objArr5.length);
            Object[] objArr6 = this.b;
            if (size >= iF2) {
                z5.l0(objArr6, objArr6, objArr6.length - size, 0, iF2);
            } else {
                z5.l0(objArr6, objArr6, objArr6.length - size, 0, size);
                Object[] objArr7 = this.b;
                z5.l0(objArr7, objArr7, 0, size, iF2);
            }
        } else if (length2 >= 0) {
            z5.l0(objArr5, objArr5, length2, i5, iF2);
        } else {
            length2 += objArr5.length;
            int i6 = iF2 - i5;
            int length3 = objArr5.length - length2;
            if (length3 >= i6) {
                z5.l0(objArr5, objArr5, length2, i5, iF2);
            } else {
                z5.l0(objArr5, objArr5, length2, i5, i5 + length3);
                Object[] objArr8 = this.b;
                z5.l0(objArr8, objArr8, 0, this.a + length3, iF2);
            }
        }
        this.a = length2;
        a(d(iF2 - size), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        g();
        b(this.c + 1);
        int length = this.a;
        if (length == 0) {
            Object[] objArr = this.b;
            objArr.getClass();
            length = objArr.length;
        }
        int i = length - 1;
        this.a = i;
        this.b[i] = obj;
        this.c++;
    }

    public final void addLast(Object obj) {
        g();
        b(this.c + 1);
        this.b[f(this.a + this.c)] = obj;
        this.c++;
    }

    public final void b(int i) {
        if (i < 0) {
            z6.o("Deque is too big.");
            return;
        }
        Object[] objArr = this.b;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == d) {
            if (i < 10) {
                i = 10;
            }
            this.b = new Object[i];
            return;
        }
        int length = objArr.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i2];
        z5.l0(objArr, objArr2, 0, this.a, objArr.length);
        Object[] objArr3 = this.b;
        int length2 = objArr3.length;
        int i3 = this.a;
        z5.l0(objArr3, objArr2, length2 - i3, 0, i3);
        this.a = 0;
        this.b = objArr2;
    }

    public final int c(int i) {
        this.b.getClass();
        if (i == r0.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            g();
            e(this.a, f(this.a + this.c));
        }
        this.a = 0;
        this.c = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i) {
        return i < 0 ? i + this.b.length : i;
    }

    public final void e(int i, int i2) {
        Object[] objArr = this.b;
        if (i < i2) {
            objArr.getClass();
            Arrays.fill(objArr, i, i2, (Object) null);
        } else {
            Arrays.fill(objArr, i, objArr.length, (Object) null);
            Object[] objArr2 = this.b;
            objArr2.getClass();
            Arrays.fill(objArr2, 0, i2, (Object) null);
        }
    }

    public final int f(int i) {
        Object[] objArr = this.b;
        return i >= objArr.length ? i - objArr.length : i;
    }

    public final void g() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int i2 = this.c;
        if (i >= 0 && i < i2) {
            return this.b[f(this.a + i)];
        }
        z6.g("index: ", i, ", size: ", i2);
        return null;
    }

    public final Object h(int i) {
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            z6.g("index: ", i, ", size: ", i2);
            return null;
        }
        if (i == i2 - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        g();
        int iF = f(this.a + i);
        Object[] objArr = this.b;
        Object obj = objArr[iF];
        int i3 = this.c;
        int i4 = i3 >> 1;
        int i5 = this.a;
        if (i < i4) {
            if (iF >= i5) {
                z5.l0(objArr, objArr, i5 + 1, i5, iF);
            } else {
                z5.l0(objArr, objArr, 1, 0, iF);
                Object[] objArr2 = this.b;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i6 = this.a;
                z5.l0(objArr2, objArr2, i6 + 1, i6, objArr2.length - 1);
            }
            Object[] objArr3 = this.b;
            int i7 = this.a;
            objArr3[i7] = null;
            this.a = c(i7);
        } else {
            int iF2 = f((i3 - 1) + i5);
            Object[] objArr4 = this.b;
            if (iF <= iF2) {
                z5.l0(objArr4, objArr4, iF, iF + 1, iF2 + 1);
            } else {
                z5.l0(objArr4, objArr4, iF, iF + 1, objArr4.length);
                Object[] objArr5 = this.b;
                objArr5[objArr5.length - 1] = objArr5[0];
                z5.l0(objArr5, objArr5, 0, 1, iF2 + 1);
            }
            this.b[iF2] = null;
        }
        this.c--;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int iF = f(this.a + this.c);
        int length = this.a;
        if (length < iF) {
            while (length < iF) {
                if (hb.f(obj, this.b[length])) {
                    i = this.a;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iF) {
            return -1;
        }
        int length2 = this.b.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iF; i2++) {
                    if (hb.f(obj, this.b[i2])) {
                        length = i2 + this.b.length;
                        i = this.a;
                    }
                }
                return -1;
            }
            if (hb.f(obj, this.b[length])) {
                i = this.a;
                break;
            }
            length++;
        }
        return length - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int iF = f(this.a + this.c);
        int i2 = this.a;
        if (i2 < iF) {
            length = iF - 1;
            if (i2 <= length) {
                while (!hb.f(obj, this.b[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.a;
                return length - i;
            }
            return -1;
        }
        if (i2 > iF) {
            while (true) {
                iF--;
                Object[] objArr = this.b;
                if (-1 >= iF) {
                    objArr.getClass();
                    length = objArr.length - 1;
                    int i3 = this.a;
                    if (i3 <= length) {
                        while (!hb.f(obj, this.b[length])) {
                            if (length != i3) {
                                length--;
                            }
                        }
                        i = this.a;
                    }
                } else if (hb.f(obj, objArr[iF])) {
                    length = iF + this.b.length;
                    i = this.a;
                    break;
                }
            }
            return length - i;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        h(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iF;
        Object[] objArr;
        collection.getClass();
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.b.length != 0) {
            int iF2 = f(this.a + this.c);
            int i = this.a;
            if (i < iF2) {
                iF = i;
                while (true) {
                    objArr = this.b;
                    if (i >= iF2) {
                        break;
                    }
                    Object obj = objArr[i];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.b[iF] = obj;
                        iF++;
                    }
                    i++;
                }
                objArr.getClass();
                Arrays.fill(objArr, iF, iF2, (Object) null);
            } else {
                int length = this.b.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr2 = this.b;
                    Object obj2 = objArr2[i];
                    objArr2[i] = null;
                    if (collection.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.b[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                iF = f(i2);
                for (int i3 = 0; i3 < iF2; i3++) {
                    Object[] objArr3 = this.b;
                    Object obj3 = objArr3[i3];
                    objArr3[i3] = null;
                    if (collection.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.b[iF] = obj3;
                        iF = c(iF);
                    }
                }
                z = z2;
            }
            if (z) {
                g();
                this.c = d(iF - this.a);
            }
        }
        return z;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        Object[] objArr = this.b;
        int i = this.a;
        Object obj = objArr[i];
        objArr[i] = null;
        this.a = c(i);
        this.c--;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        int iF = f((size() - 1) + this.a);
        Object[] objArr = this.b;
        Object obj = objArr[iF];
        objArr[iF] = null;
        this.c--;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        d.l(i, i2, this.c);
        int i3 = i2 - i;
        if (i3 == 0) {
            return;
        }
        if (i3 == this.c) {
            clear();
            return;
        }
        if (i3 == 1) {
            h(i);
            return;
        }
        g();
        int i4 = this.c - i2;
        int i5 = this.a;
        if (i < i4) {
            int iF = f((i - 1) + i5);
            int iF2 = f(this.a + (i2 - 1));
            while (i > 0) {
                int i6 = iF + 1;
                int iMin = Math.min(i, Math.min(i6, iF2 + 1));
                Object[] objArr = this.b;
                int i7 = iF2 - iMin;
                int i8 = iF - iMin;
                z5.l0(objArr, objArr, i7 + 1, i8 + 1, i6);
                iF = d(i8);
                iF2 = d(i7);
                i -= iMin;
            }
            int iF3 = f(this.a + i3);
            e(this.a, iF3);
            this.a = iF3;
        } else {
            int iF4 = f(i5 + i2);
            int iF5 = f(this.a + i);
            int i9 = this.c;
            while (true) {
                i9 -= i2;
                if (i9 <= 0) {
                    break;
                }
                Object[] objArr2 = this.b;
                i2 = Math.min(i9, Math.min(objArr2.length - iF4, objArr2.length - iF5));
                Object[] objArr3 = this.b;
                int i10 = iF4 + i2;
                z5.l0(objArr3, objArr3, iF5, iF4, i10);
                iF4 = f(i10);
                iF5 = f(iF5 + i2);
            }
            int iF6 = f(this.a + this.c);
            e(d(iF6 - i3), iF6);
        }
        this.c -= i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iF;
        Object[] objArr;
        collection.getClass();
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.b.length != 0) {
            int iF2 = f(this.a + this.c);
            int i = this.a;
            if (i < iF2) {
                iF = i;
                while (true) {
                    objArr = this.b;
                    if (i >= iF2) {
                        break;
                    }
                    Object obj = objArr[i];
                    if (collection.contains(obj)) {
                        this.b[iF] = obj;
                        iF++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                objArr.getClass();
                Arrays.fill(objArr, iF, iF2, (Object) null);
            } else {
                int length = this.b.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr2 = this.b;
                    Object obj2 = objArr2[i];
                    objArr2[i] = null;
                    if (collection.contains(obj2)) {
                        this.b[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iF = f(i2);
                for (int i3 = 0; i3 < iF2; i3++) {
                    Object[] objArr3 = this.b;
                    Object obj3 = objArr3[i3];
                    objArr3[i3] = null;
                    if (collection.contains(obj3)) {
                        this.b[iF] = obj3;
                        iF = c(iF);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                g();
                this.c = d(iF - this.a);
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            z6.g("index: ", i, ", size: ", i2);
            return null;
        }
        int iF = f(this.a + i);
        Object[] objArr = this.b;
        Object obj2 = objArr[iF];
        objArr[iF] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        int length = objArr.length;
        int i = this.c;
        if (length < i) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i);
            objNewInstance.getClass();
            objArr = (Object[]) objNewInstance;
        }
        int iF = f(this.a + this.c);
        int i2 = this.a;
        if (i2 < iF) {
            z5.l0(this.b, objArr, 0, i2, iF);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.b;
            z5.l0(objArr2, objArr, 0, this.a, objArr2.length);
            Object[] objArr3 = this.b;
            z5.l0(objArr3, objArr, objArr3.length - this.a, 0, iF);
        }
        int i3 = this.c;
        if (i3 < objArr.length) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i) {
        return h(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[this.c]);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        collection.getClass();
        if (collection.isEmpty()) {
            return false;
        }
        g();
        b(collection.size() + this.c);
        a(f(this.a + this.c), collection);
        return true;
    }
}
