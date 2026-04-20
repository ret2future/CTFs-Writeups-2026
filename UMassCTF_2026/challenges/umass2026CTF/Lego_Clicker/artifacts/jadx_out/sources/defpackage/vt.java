package defpackage;

import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class vt extends xt {
    public final WindowInsets.Builder a;

    public vt(eu euVar) {
        super(euVar);
        WindowInsets windowInsetsB = euVar.b();
        this.a = windowInsetsB != null ? t.c(windowInsetsB) : t.b();
    }

    @Override // defpackage.xt
    public eu b() {
        a();
        eu euVarC = eu.c(this.a.build(), null);
        euVarC.a.k(null);
        return euVarC;
    }

    @Override // defpackage.xt
    public void c(jf jfVar) {
        this.a.setStableInsets(jfVar.b());
    }

    @Override // defpackage.xt
    public void d(jf jfVar) {
        this.a.setSystemWindowInsets(jfVar.b());
    }

    public vt() {
        this.a = t.b();
    }
}
