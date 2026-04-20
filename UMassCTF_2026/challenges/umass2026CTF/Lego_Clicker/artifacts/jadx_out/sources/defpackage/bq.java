package defpackage;

import android.view.MenuItem;
import android.view.Window;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class bq implements zp, ei {
    public final /* synthetic */ dq a;

    public /* synthetic */ bq(dq dqVar) {
        this.a = dqVar;
    }

    @Override // defpackage.ei
    public void k(gi giVar) {
        dq dqVar = this.a;
        boolean zO = dqVar.a.a.o();
        Window.Callback callback = dqVar.b;
        if (zO) {
            callback.onPanelClosed(108, giVar);
        } else if (callback.onPreparePanel(0, null, giVar)) {
            callback.onMenuOpened(108, giVar);
        }
    }

    @Override // defpackage.ei
    public boolean n(gi giVar, MenuItem menuItem) {
        return false;
    }
}
