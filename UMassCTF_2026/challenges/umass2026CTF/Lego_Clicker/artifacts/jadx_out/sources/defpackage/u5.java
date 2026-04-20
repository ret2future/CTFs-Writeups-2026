package defpackage;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class u5 extends ko implements Map {
    public t5 h;

    @Override // java.util.Map
    public final Set entrySet() {
        int i = 0;
        if (this.h == null) {
            this.h = new t5(0, this);
        }
        t5 t5Var = this.h;
        if (t5Var.a == null) {
            t5Var.a = new zh(t5Var, i);
        }
        return t5Var.a;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.h == null) {
            this.h = new t5(0, this);
        }
        t5 t5Var = this.h;
        if (t5Var.b == null) {
            t5Var.b = new zh(t5Var, 1);
        }
        return t5Var.b;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = map.size() + this.c;
        int i = this.c;
        int[] iArr = this.a;
        if (iArr.length < size) {
            Object[] objArr = this.b;
            a(size);
            if (this.c > 0) {
                System.arraycopy(iArr, 0, this.a, 0, i);
                System.arraycopy(objArr, 0, this.b, 0, i << 1);
            }
            ko.b(iArr, objArr, i);
        }
        if (this.c != i) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.h == null) {
            this.h = new t5(0, this);
        }
        t5 t5Var = this.h;
        if (t5Var.c == null) {
            t5Var.c = new p5(1, t5Var);
        }
        return t5Var.c;
    }
}
