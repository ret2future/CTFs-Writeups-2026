package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class sq implements qq {
    public final /* synthetic */ Class a;
    public final /* synthetic */ Class b;
    public final /* synthetic */ pq c;

    public sq(Class cls, Class cls2, pq pqVar) {
        this.a = cls;
        this.b = cls2;
        this.c = pqVar;
    }

    @Override // defpackage.qq
    public final pq a(re reVar, wq wqVar) {
        Class cls = wqVar.a;
        if (cls == this.a || cls == this.b) {
            return this.c;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.b.getName() + "+" + this.a.getName() + ",adapter=" + this.c + "]";
    }
}
