package defpackage;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class c1 implements Runnable {
    public final /* synthetic */ int a;
    public final Object b;
    public final /* synthetic */ Object c;

    public c1(e1 e1Var, a1 a1Var) {
        this.a = 0;
        this.c = e1Var;
        this.b = a1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ei eiVar;
        int i = this.a;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                a1 a1Var = (a1) obj2;
                e1 e1Var = (e1) obj;
                gi giVar = e1Var.c;
                if (giVar != null && (eiVar = giVar.e) != null) {
                    eiVar.k(giVar);
                }
                View view = (View) e1Var.h;
                if (view != null && view.getWindowToken() != null) {
                    if (a1Var.b()) {
                        e1Var.s = a1Var;
                    } else if (a1Var.e != null) {
                        a1Var.d(0, 0, false, false);
                        e1Var.s = a1Var;
                    }
                }
                e1Var.u = null;
                return;
            case 1:
                ((n1) obj2).a = obj;
                return;
            case 2:
                ((Application) obj2).unregisterActivityLifecycleCallbacks((n1) obj);
                return;
            case 3:
                try {
                    Method method = o1.d;
                    if (method != null) {
                        method.invoke(obj2, obj, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        o1.e.invoke(obj2, obj, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e) {
                    if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                        throw e;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
            case 4:
                ((r4) ((e0) obj2).b).b((Typeface) obj);
                return;
            default:
                ((ec) obj2).accept(obj);
                return;
        }
    }

    public /* synthetic */ c1(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }
}
