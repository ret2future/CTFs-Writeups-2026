package defpackage;

import android.view.ViewGroup;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class a3 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ n3 b;

    public /* synthetic */ a3(n3 n3Var, int i) {
        this.a = i;
        this.b = n3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewGroup viewGroup;
        int i = this.a;
        n3 n3Var = this.b;
        switch (i) {
            case 0:
                if ((n3Var.Z & 1) != 0) {
                    n3Var.w(0);
                }
                if ((n3Var.Z & 4096) != 0) {
                    n3Var.w(108);
                }
                n3Var.Y = false;
                n3Var.Z = 0;
                break;
            default:
                n3Var.w.showAtLocation(n3Var.v, 55, 0, 0);
                dt dtVar = n3Var.y;
                if (dtVar != null) {
                    dtVar.b();
                }
                if (n3Var.z && (viewGroup = n3Var.A) != null && viewGroup.isLaidOut()) {
                    n3Var.v.setAlpha(0.0f);
                    dt dtVarA = os.a(n3Var.v);
                    dtVarA.a(1.0f);
                    n3Var.y = dtVarA;
                    dtVarA.d(new c3(0, this));
                } else {
                    n3Var.v.setAlpha(1.0f);
                    n3Var.v.setVisibility(0);
                }
                break;
        }
    }
}
