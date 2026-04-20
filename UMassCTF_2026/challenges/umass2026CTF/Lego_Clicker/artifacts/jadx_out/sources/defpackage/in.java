package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class in {
    public mt a;
    public ArrayList b;

    public static long a(v9 v9Var, long j) {
        mt mtVar = v9Var.d;
        ArrayList arrayList = v9Var.k;
        if (mtVar instanceof we) {
            return j;
        }
        int size = arrayList.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            t9 t9Var = (t9) arrayList.get(i);
            if (t9Var instanceof v9) {
                v9 v9Var2 = (v9) t9Var;
                if (v9Var2.d != mtVar) {
                    jMin = Math.min(jMin, a(v9Var2, ((long) v9Var2.f) + j));
                }
            }
        }
        v9 v9Var3 = mtVar.i;
        v9 v9Var4 = mtVar.h;
        if (v9Var != v9Var3) {
            return jMin;
        }
        long j2 = j - mtVar.j();
        return Math.min(Math.min(jMin, a(v9Var4, j2)), j2 - ((long) v9Var4.f));
    }

    public static long b(v9 v9Var, long j) {
        mt mtVar = v9Var.d;
        ArrayList arrayList = v9Var.k;
        if (mtVar instanceof we) {
            return j;
        }
        int size = arrayList.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            t9 t9Var = (t9) arrayList.get(i);
            if (t9Var instanceof v9) {
                v9 v9Var2 = (v9) t9Var;
                if (v9Var2.d != mtVar) {
                    jMax = Math.max(jMax, b(v9Var2, ((long) v9Var2.f) + j));
                }
            }
        }
        v9 v9Var3 = mtVar.h;
        v9 v9Var4 = mtVar.i;
        if (v9Var != v9Var3) {
            return jMax;
        }
        long j2 = mtVar.j() + j;
        return Math.max(Math.max(jMax, b(v9Var4, j2)), j2 - ((long) v9Var4.f));
    }
}
