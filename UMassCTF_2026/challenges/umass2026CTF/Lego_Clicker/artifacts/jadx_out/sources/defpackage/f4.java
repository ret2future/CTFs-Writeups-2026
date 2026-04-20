package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class f4 extends oc {
    public final /* synthetic */ m4 j;
    public final /* synthetic */ p4 k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f4(p4 p4Var, p4 p4Var2, m4 m4Var) {
        super(p4Var2);
        this.k = p4Var;
        this.j = m4Var;
    }

    @Override // defpackage.oc
    public final jo b() {
        return this.j;
    }

    @Override // defpackage.oc
    public final boolean c() {
        p4 p4Var = this.k;
        if (p4Var.getInternalPopup().b()) {
            return true;
        }
        p4Var.f.e(p4Var.getTextDirection(), p4Var.getTextAlignment());
        return true;
    }
}
