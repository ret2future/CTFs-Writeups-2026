package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class qf extends nf {
    public final xg a;

    public qf() {
        ke keVar = xg.i;
        this.a = new xg(false);
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof qf) && ((qf) obj).a.equals(this.a);
        }
        return true;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
