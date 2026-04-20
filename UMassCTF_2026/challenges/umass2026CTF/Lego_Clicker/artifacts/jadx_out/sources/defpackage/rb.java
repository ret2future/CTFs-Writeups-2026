package defpackage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class rb extends pq {
    public pq a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ re d;
    public final /* synthetic */ wq e;
    public final /* synthetic */ sb f;

    public rb(sb sbVar, boolean z, boolean z2, re reVar, wq wqVar) {
        this.f = sbVar;
        this.b = z;
        this.c = z2;
        this.d = reVar;
        this.e = wqVar;
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) throws IOException {
        if (this.b) {
            sfVar.B();
            return null;
        }
        pq pqVar = this.a;
        if (pqVar == null) {
            re reVar = this.d;
            List list = reVar.e;
            qq qqVar = this.f;
            if (!list.contains(qqVar)) {
                qqVar = reVar.d;
            }
            Iterator it = list.iterator();
            boolean z = false;
            while (true) {
                boolean zHasNext = it.hasNext();
                wq wqVar = this.e;
                if (!zHasNext) {
                    z6.n(wqVar, "GSON cannot serialize ");
                    return null;
                }
                qq qqVar2 = (qq) it.next();
                if (z) {
                    pq pqVarA = qqVar2.a(reVar, wqVar);
                    if (pqVarA != null) {
                        this.a = pqVarA;
                        pqVar = pqVarA;
                        break;
                    }
                } else if (qqVar2 == qqVar) {
                    z = true;
                }
            }
        }
        return pqVar.a(sfVar);
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        if (this.c) {
            tfVar.i();
            return;
        }
        pq pqVar = this.a;
        if (pqVar == null) {
            re reVar = this.d;
            List list = reVar.e;
            qq qqVar = this.f;
            if (!list.contains(qqVar)) {
                qqVar = reVar.d;
            }
            Iterator it = list.iterator();
            boolean z = false;
            while (true) {
                boolean zHasNext = it.hasNext();
                wq wqVar = this.e;
                if (!zHasNext) {
                    z6.n(wqVar, "GSON cannot serialize ");
                    return;
                }
                qq qqVar2 = (qq) it.next();
                if (z) {
                    pq pqVarA = qqVar2.a(reVar, wqVar);
                    if (pqVarA != null) {
                        this.a = pqVarA;
                        pqVar = pqVarA;
                        break;
                    }
                } else if (qqVar2 == qqVar) {
                    z = true;
                }
            }
        }
        pqVar.b(tfVar, obj);
    }
}
