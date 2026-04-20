package defpackage;

import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements ft {
    public boolean a = false;
    public int b;
    public final /* synthetic */ ActionBarContextView c;

    public e(ActionBarContextView actionBarContextView) {
        this.c = actionBarContextView;
    }

    @Override // defpackage.ft
    public final void b() {
        if (this.a) {
            return;
        }
        ActionBarContextView actionBarContextView = this.c;
        actionBarContextView.f = null;
        super/*android.view.View*/.setVisibility(this.b);
    }

    @Override // defpackage.ft
    public final void c() {
        this.a = true;
    }

    @Override // defpackage.ft
    public final void e() {
        super/*android.view.View*/.setVisibility(0);
        this.a = false;
    }
}
