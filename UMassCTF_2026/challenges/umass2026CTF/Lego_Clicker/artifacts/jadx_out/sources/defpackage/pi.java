package defpackage;

import android.view.ActionProvider;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class pi implements ActionProvider.VisibilityListener {
    public e0 a;
    public final ActionProvider b;

    public pi(si siVar, ActionProvider actionProvider) {
        this.b = actionProvider;
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z) {
        e0 e0Var = this.a;
        if (e0Var != null) {
            gi giVar = ((oi) e0Var.b).n;
            giVar.h = true;
            giVar.p(true);
        }
    }
}
