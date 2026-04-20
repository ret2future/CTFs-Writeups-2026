package defpackage;

import android.os.Build;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class pa extends d {
    public final /* synthetic */ qa H;

    public pa(qa qaVar) {
        this.H = qaVar;
    }

    @Override // defpackage.d
    public final void H(Throwable th) {
        this.H.a.d(th);
    }

    @Override // defpackage.d
    public final void I(kd kdVar) {
        qa qaVar = this.H;
        qaVar.c = kdVar;
        kd kdVar2 = qaVar.c;
        va vaVar = qaVar.a;
        fr frVar = vaVar.g;
        i9 i9Var = vaVar.i;
        Set<int[]> setA = Build.VERSION.SDK_INT >= 34 ? za.a() : d.x();
        j5 j5Var = new j5();
        j5Var.a = frVar;
        j5Var.b = kdVar2;
        j5Var.c = i9Var;
        if (!setA.isEmpty()) {
            for (int[] iArr : setA) {
                String str = new String(iArr, 0, iArr.length);
                j5Var.r(str, 0, str.length(), 1, true, new n8(2, str));
            }
        }
        qaVar.b = j5Var;
        va vaVar2 = qaVar.a;
        ArrayList arrayList = new ArrayList();
        vaVar2.a.writeLock().lock();
        try {
            vaVar2.c = 1;
            arrayList.addAll(vaVar2.b);
            vaVar2.b.clear();
            vaVar2.a.writeLock().unlock();
            vaVar2.d.post(new ta(arrayList, vaVar2.c, null));
        } catch (Throwable th) {
            vaVar2.a.writeLock().unlock();
            throw th;
        }
    }
}
