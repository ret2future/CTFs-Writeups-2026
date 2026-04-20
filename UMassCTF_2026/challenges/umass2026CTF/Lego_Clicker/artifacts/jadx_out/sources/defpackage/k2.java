package defpackage;

import androidx.activity.b;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class k2 implements ck {
    public final /* synthetic */ l2 a;

    public k2(l2 l2Var) {
        this.a = l2Var;
    }

    @Override // defpackage.ck
    public final void a(b bVar) {
        l2 l2Var = this.a;
        z2 delegate = l2Var.getDelegate();
        delegate.a();
        l2Var.getSavedStateRegistry().a("androidx:appcompat");
        delegate.d();
    }
}
