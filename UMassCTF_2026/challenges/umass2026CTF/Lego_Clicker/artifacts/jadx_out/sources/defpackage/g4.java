package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class g4 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g4(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                p4 p4Var = (p4) obj;
                if (!p4Var.getInternalPopup().b()) {
                    p4Var.f.e(p4Var.getTextDirection(), p4Var.getTextAlignment());
                }
                ViewTreeObserver viewTreeObserver = p4Var.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
                break;
            case 1:
                m4 m4Var = (m4) obj;
                p4 p4Var2 = m4Var.F;
                if (p4Var2.isAttachedToWindow() && p4Var2.getGlobalVisibleRect(m4Var.D)) {
                    m4Var.r();
                    m4Var.f();
                } else {
                    m4Var.dismiss();
                }
                break;
            case 2:
                w6 w6Var = (w6) obj;
                ArrayList arrayList = w6Var.h;
                if (w6Var.b() && arrayList.size() > 0) {
                    int i2 = 0;
                    if (!((v6) arrayList.get(0)).a.x) {
                        View view = w6Var.o;
                        if (view != null && view.isShown()) {
                            int size = arrayList.size();
                            while (i2 < size) {
                                Object obj2 = arrayList.get(i2);
                                i2++;
                                ((v6) obj2).a.f();
                            }
                        } else {
                            w6Var.dismiss();
                        }
                    }
                    break;
                }
                break;
            default:
                ap apVar = (ap) obj;
                zi ziVar = apVar.h;
                if (apVar.b() && !ziVar.x) {
                    View view2 = apVar.m;
                    if (view2 != null && view2.isShown()) {
                        ziVar.f();
                    } else {
                        apVar.dismiss();
                    }
                    break;
                }
                break;
        }
    }
}
