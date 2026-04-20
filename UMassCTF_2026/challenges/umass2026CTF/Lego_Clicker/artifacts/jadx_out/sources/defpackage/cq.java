package defpackage;

import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class cq implements aj {
    public boolean a;
    public final /* synthetic */ dq b;

    public cq(dq dqVar) {
        this.b = dqVar;
    }

    @Override // defpackage.aj
    public final void a(gi giVar, boolean z) {
        e1 e1Var;
        if (this.a) {
            return;
        }
        this.a = true;
        dq dqVar = this.b;
        ActionMenuView actionMenuView = dqVar.a.a.a;
        if (actionMenuView != null && (e1Var = actionMenuView.t) != null) {
            e1Var.f();
            a1 a1Var = e1Var.t;
            if (a1Var != null && a1Var.b()) {
                a1Var.i.dismiss();
            }
        }
        dqVar.b.onPanelClosed(108, giVar);
        this.a = false;
    }

    @Override // defpackage.aj
    public final boolean s(gi giVar) {
        this.b.b.onMenuOpened(108, giVar);
        return true;
    }
}
