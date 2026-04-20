package defpackage;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class qt extends hb {
    public final /* synthetic */ int o;
    public final /* synthetic */ st p;

    public /* synthetic */ qt(st stVar, int i) {
        this.o = i;
        this.p = stVar;
    }

    @Override // defpackage.ft
    public final void b() {
        View view;
        int i = this.o;
        st stVar = this.p;
        switch (i) {
            case 0:
                if (stVar.o && (view = stVar.g) != null) {
                    view.setTranslationY(0.0f);
                    stVar.d.setTranslationY(0.0f);
                }
                stVar.d.setVisibility(8);
                stVar.d.setTransitioning(false);
                stVar.s = null;
                z3 z3Var = stVar.k;
                if (z3Var != null) {
                    z3Var.f(stVar.j);
                    stVar.j = null;
                    stVar.k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = stVar.c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = os.a;
                    bs.c(actionBarOverlayLayout);
                }
                break;
            default:
                stVar.s = null;
                stVar.d.requestLayout();
                break;
        }
    }
}
