package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class u6 implements Runnable {
    public final /* synthetic */ v6 a;
    public final /* synthetic */ oi b;
    public final /* synthetic */ gi c;
    public final /* synthetic */ e0 d;

    public u6(e0 e0Var, v6 v6Var, oi oiVar, gi giVar) {
        this.d = e0Var;
        this.a = v6Var;
        this.b = oiVar;
        this.c = giVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        w6 w6Var = (w6) this.d.b;
        v6 v6Var = this.a;
        if (v6Var != null) {
            w6Var.z = true;
            v6Var.b.c(false);
            w6Var.z = false;
        }
        oi oiVar = this.b;
        if (oiVar.isEnabled() && oiVar.hasSubMenu()) {
            this.c.q(oiVar, null, 4);
        }
    }
}
