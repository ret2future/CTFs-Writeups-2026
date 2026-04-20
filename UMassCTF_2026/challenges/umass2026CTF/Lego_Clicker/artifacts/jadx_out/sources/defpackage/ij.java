package defpackage;

import android.util.SparseArray;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ij {
    public final SparseArray a;
    public dr b;

    public ij(int i) {
        this.a = new SparseArray(i);
    }

    public final void a(dr drVar, int i, int i2) {
        int iA = drVar.a(i);
        SparseArray sparseArray = this.a;
        ij ijVar = sparseArray == null ? null : (ij) sparseArray.get(iA);
        if (ijVar == null) {
            ijVar = new ij(1);
            sparseArray.put(drVar.a(i), ijVar);
        }
        if (i2 > i) {
            ijVar.a(drVar, i + 1, i2);
        } else {
            ijVar.b = drVar;
        }
    }
}
