package defpackage;

import android.view.View;
import androidx.appcompat.view.menu.ActionMenuItemView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class y0 extends oc {
    public final /* synthetic */ int j = 0;
    public final /* synthetic */ View k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.k = actionMenuItemView;
    }

    @Override // defpackage.oc
    public final jo b() {
        a1 a1Var;
        int i = this.j;
        View view = this.k;
        switch (i) {
            case 0:
                z0 z0Var = ((ActionMenuItemView) view).m;
                if (z0Var == null || (a1Var = ((b1) z0Var).a.t) == null) {
                    return null;
                }
                return a1Var.a();
            default:
                a1 a1Var2 = ((d1) view).d.s;
                if (a1Var2 == null) {
                    return null;
                }
                return a1Var2.a();
        }
    }

    @Override // defpackage.oc
    public final boolean c() {
        jo joVarB;
        int i = this.j;
        View view = this.k;
        switch (i) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) view;
                fi fiVar = actionMenuItemView.k;
                if (fiVar == null || !fiVar.a(actionMenuItemView.h) || (joVarB = b()) == null || !joVarB.b()) {
                }
                break;
            default:
                ((d1) view).d.l();
                break;
        }
        return true;
    }

    @Override // defpackage.oc
    public boolean d() {
        switch (this.j) {
            case 1:
                e1 e1Var = ((d1) this.k).d;
                if (e1Var.u != null) {
                    return false;
                }
                e1Var.f();
                return true;
            default:
                return super.d();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(d1 d1Var, d1 d1Var2) {
        super(d1Var2);
        this.k = d1Var;
    }
}
