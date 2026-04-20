package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class aa extends v9 {
    public int m;

    public aa(mt mtVar) {
        super(mtVar);
        if (mtVar instanceof xe) {
            this.e = 2;
        } else {
            this.e = 3;
        }
    }

    @Override // defpackage.v9
    public final void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            t9 t9Var = (t9) obj;
            t9Var.a(t9Var);
        }
    }
}
