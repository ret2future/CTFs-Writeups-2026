package defpackage;

import android.content.Context;
import android.view.View;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class a1 extends vi {
    public final /* synthetic */ int l = 0;
    public final /* synthetic */ e1 m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a1(e1 e1Var, Context context, bp bpVar, View view) {
        super(context, bpVar, view, false, R.attr.actionOverflowMenuStyle, 0);
        this.m = e1Var;
        if ((bpVar.A.x & 32) != 32) {
            View view2 = e1Var.i;
            this.e = view2 == null ? (View) e1Var.h : view2;
        }
        e0 e0Var = e1Var.w;
        this.h = e0Var;
        ti tiVar = this.i;
        if (tiVar != null) {
            tiVar.e(e0Var);
        }
    }

    @Override // defpackage.vi
    public final void c() {
        int i = this.l;
        e1 e1Var = this.m;
        switch (i) {
            case 0:
                e1Var.t = null;
                super.c();
                break;
            default:
                gi giVar = e1Var.c;
                if (giVar != null) {
                    giVar.c(true);
                }
                e1Var.s = null;
                super.c();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a1(e1 e1Var, Context context, gi giVar, View view) {
        super(context, giVar, view, true, R.attr.actionOverflowMenuStyle, 0);
        this.m = e1Var;
        this.f = 8388613;
        e0 e0Var = e1Var.w;
        this.h = e0Var;
        ti tiVar = this.i;
        if (tiVar != null) {
            tiVar.e(e0Var);
        }
    }
}
