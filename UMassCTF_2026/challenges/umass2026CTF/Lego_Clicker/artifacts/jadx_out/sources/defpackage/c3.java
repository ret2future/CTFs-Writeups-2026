package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class c3 extends hb {
    public final /* synthetic */ int o;
    public final /* synthetic */ Object p;

    public /* synthetic */ c3(int i, Object obj) {
        this.o = i;
        this.p = obj;
    }

    @Override // defpackage.ft
    public final void b() {
        int i = this.o;
        Object obj = this.p;
        switch (i) {
            case 0:
                n3 n3Var = ((a3) obj).b;
                n3Var.v.setAlpha(1.0f);
                n3Var.y.d(null);
                n3Var.y = null;
                break;
            case 1:
                n3 n3Var2 = (n3) obj;
                n3Var2.v.setAlpha(1.0f);
                n3Var2.y.d(null);
                n3Var2.y = null;
                break;
            default:
                n3 n3Var3 = (n3) ((z3) obj).c;
                n3Var3.v.setVisibility(8);
                PopupWindow popupWindow = n3Var3.w;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (n3Var3.v.getParent() instanceof View) {
                    View view = (View) n3Var3.v.getParent();
                    WeakHashMap weakHashMap = os.a;
                    bs.c(view);
                }
                n3Var3.v.e();
                n3Var3.y.d(null);
                n3Var3.y = null;
                ViewGroup viewGroup = n3Var3.A;
                WeakHashMap weakHashMap2 = os.a;
                bs.c(viewGroup);
                break;
        }
    }

    @Override // defpackage.hb, defpackage.ft
    public void e() {
        int i = this.o;
        Object obj = this.p;
        switch (i) {
            case 0:
                ((a3) obj).b.v.setVisibility(0);
                break;
            case 1:
                n3 n3Var = (n3) obj;
                n3Var.v.setVisibility(0);
                if (n3Var.v.getParent() instanceof View) {
                    View view = (View) n3Var.v.getParent();
                    WeakHashMap weakHashMap = os.a;
                    bs.c(view);
                }
                break;
        }
    }
}
