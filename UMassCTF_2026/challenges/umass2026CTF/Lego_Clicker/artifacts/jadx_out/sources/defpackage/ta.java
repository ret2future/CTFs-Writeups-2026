package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ta implements Runnable {
    public final ArrayList a;
    public final int b;

    public ta(List list, int i, Throwable th) {
        hb.i(list, "initCallbacks cannot be null");
        this.a = new ArrayList(list);
        this.b = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = this.a;
        int size = arrayList.size();
        int i = 0;
        if (this.b != 1) {
            while (i < size) {
                ((sa) arrayList.get(i)).getClass();
                i++;
            }
        } else {
            while (i < size) {
                ((sa) arrayList.get(i)).a();
                i++;
            }
        }
    }
}
