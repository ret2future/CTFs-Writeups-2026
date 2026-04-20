package defpackage;

import android.util.SparseArray;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class vl {
    public SparseArray a;
    public int b;
    public Set c;

    public final ul a(int i) {
        SparseArray sparseArray = this.a;
        ul ulVar = (ul) sparseArray.get(i);
        if (ulVar != null) {
            return ulVar;
        }
        ul ulVar2 = new ul();
        sparseArray.put(i, ulVar2);
        return ulVar2;
    }
}
