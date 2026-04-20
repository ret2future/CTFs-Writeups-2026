package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class t6 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ ti b;

    public /* synthetic */ t6(ti tiVar, int i) {
        this.a = i;
        this.b = tiVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        int i = this.a;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        int i = this.a;
        ti tiVar = this.b;
        switch (i) {
            case 0:
                w6 w6Var = (w6) tiVar;
                ViewTreeObserver viewTreeObserver = w6Var.x;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        w6Var.x = view.getViewTreeObserver();
                    }
                    w6Var.x.removeGlobalOnLayoutListener(w6Var.i);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            default:
                ap apVar = (ap) tiVar;
                ViewTreeObserver viewTreeObserver2 = apVar.o;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        apVar.o = view.getViewTreeObserver();
                    }
                    apVar.o.removeGlobalOnLayoutListener(apVar.i);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }

    private final void a(View view) {
    }

    private final void b(View view) {
    }
}
