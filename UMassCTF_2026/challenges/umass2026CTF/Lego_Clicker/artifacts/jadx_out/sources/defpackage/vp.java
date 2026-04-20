package defpackage;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class vp implements h1, ei {
    public final /* synthetic */ Toolbar a;

    public /* synthetic */ vp(Toolbar toolbar) {
        this.a = toolbar;
    }

    @Override // defpackage.ei
    public void k(gi giVar) {
        Toolbar toolbar = this.a;
        e1 e1Var = toolbar.a.t;
        if (e1Var == null || !e1Var.j()) {
            toolbar.G.c();
        }
        bq bqVar = toolbar.O;
        if (bqVar != null) {
            bqVar.k(giVar);
        }
    }

    @Override // defpackage.ei
    public boolean n(gi giVar, MenuItem menuItem) {
        return false;
    }
}
