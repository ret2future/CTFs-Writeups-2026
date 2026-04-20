package defpackage;

import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class zt extends yt {
    public jf k;

    public zt(eu euVar, WindowInsets windowInsets) {
        super(euVar, windowInsets);
        this.k = null;
    }

    @Override // defpackage.du
    public eu b() {
        return eu.c(this.c.consumeStableInsets(), null);
    }

    @Override // defpackage.du
    public eu c() {
        return eu.c(this.c.consumeSystemWindowInsets(), null);
    }

    @Override // defpackage.du
    public final jf f() {
        if (this.k == null) {
            WindowInsets windowInsets = this.c;
            this.k = jf.a(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.k;
    }

    @Override // defpackage.du
    public boolean i() {
        return this.c.isConsumed();
    }

    @Override // defpackage.du
    public void m(jf jfVar) {
        this.k = jfVar;
    }
}
