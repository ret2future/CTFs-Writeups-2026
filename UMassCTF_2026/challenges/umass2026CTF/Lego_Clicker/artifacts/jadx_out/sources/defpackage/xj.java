package defpackage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xj extends pq {
    public static final x5 b = new x5(2);
    public final re a;

    public xj(re reVar) {
        this.a = reVar;
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        Object arrayList;
        Serializable arrayList2;
        int iV = sfVar.v();
        int iC = lo.c(iV);
        if (iC == 0) {
            sfVar.a();
            arrayList = new ArrayList();
        } else if (iC != 2) {
            arrayList = null;
        } else {
            sfVar.b();
            arrayList = new xg(true);
        }
        if (arrayList == null) {
            return c(sfVar, iV);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (sfVar.i()) {
                String strP = arrayList instanceof Map ? sfVar.p() : null;
                int iV2 = sfVar.v();
                int iC2 = lo.c(iV2);
                if (iC2 == 0) {
                    sfVar.a();
                    arrayList2 = new ArrayList();
                } else if (iC2 != 2) {
                    arrayList2 = null;
                } else {
                    sfVar.b();
                    arrayList2 = new xg(true);
                }
                boolean z = arrayList2 != null;
                if (arrayList2 == null) {
                    arrayList2 = c(sfVar, iV2);
                }
                if (arrayList instanceof List) {
                    ((List) arrayList).add(arrayList2);
                } else {
                    ((Map) arrayList).put(strP, arrayList2);
                }
                if (z) {
                    arrayDeque.addLast(arrayList);
                    arrayList = arrayList2;
                }
            } else {
                if (arrayList instanceof List) {
                    sfVar.e();
                } else {
                    sfVar.f();
                }
                if (arrayDeque.isEmpty()) {
                    return arrayList;
                }
                arrayList = arrayDeque.removeLast();
            }
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        if (obj == null) {
            tfVar.i();
            return;
        }
        pq pqVarC = this.a.c(new wq(obj.getClass()));
        if (!(pqVarC instanceof xj)) {
            pqVarC.b(tfVar, obj);
        } else {
            tfVar.c();
            tfVar.f();
        }
    }

    public final Serializable c(sf sfVar, int i) {
        int iC = lo.c(i);
        if (iC == 5) {
            return sfVar.t();
        }
        if (iC == 6) {
            return Double.valueOf(sfVar.m());
        }
        if (iC == 7) {
            return Boolean.valueOf(sfVar.l());
        }
        if (iC == 8) {
            sfVar.r();
            return null;
        }
        z6.o("Unexpected token: ".concat(lo.d(i)));
        return null;
    }
}
