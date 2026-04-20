package defpackage;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ug implements Iterator {
    public wg a;
    public wg b = null;
    public int c;
    public final /* synthetic */ xg d;
    public final /* synthetic */ int e;

    public ug(xg xgVar, int i) {
        this.e = i;
        this.d = xgVar;
        this.a = xgVar.f.d;
        this.c = xgVar.e;
    }

    public final Object a() {
        return b();
    }

    public final wg b() {
        wg wgVar = this.a;
        xg xgVar = this.d;
        if (wgVar == xgVar.f) {
            throw new NoSuchElementException();
        }
        if (xgVar.e != this.c) {
            throw new ConcurrentModificationException();
        }
        this.a = wgVar.d;
        this.b = wgVar;
        return wgVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.a != this.d.f;
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.e) {
            case 1:
                return b().f;
            default:
                return a();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        wg wgVar = this.b;
        if (wgVar == null) {
            throw new IllegalStateException();
        }
        xg xgVar = this.d;
        xgVar.c(wgVar, true);
        this.b = null;
        this.c = xgVar.e;
    }
}
