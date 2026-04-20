package defpackage;

import android.widget.AbsListView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class fh implements AbsListView.OnScrollListener {
    public final /* synthetic */ hh a;

    public fh(hh hhVar) {
        this.a = hhVar;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i) {
        hh hhVar = this.a;
        dh dhVar = hhVar.q;
        y3 y3Var = hhVar.y;
        if (i != 1 || y3Var.getInputMethodMode() == 2 || y3Var.getContentView() == null) {
            return;
        }
        hhVar.u.removeCallbacks(dhVar);
        dhVar.run();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
