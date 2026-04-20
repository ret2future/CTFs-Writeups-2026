package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class qe extends pq {
    public pq a;

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        pq pqVar = this.a;
        if (pqVar != null) {
            return pqVar.a(sfVar);
        }
        z6.o("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        return null;
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) {
        pq pqVar = this.a;
        if (pqVar != null) {
            pqVar.b(tfVar, obj);
        } else {
            z6.o("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }
    }
}
