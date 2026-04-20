package defpackage;

import android.app.Activity;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g3 {
    public static OnBackInvokedDispatcher a(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }

    public static OnBackInvokedCallback b(Object obj, n3 n3Var) {
        Objects.requireNonNull(n3Var);
        f3 f3Var = new f3(0, n3Var);
        w.e(obj).registerOnBackInvokedCallback(1000000, f3Var);
        return f3Var;
    }

    public static void c(Object obj, Object obj2) {
        w.e(obj).unregisterOnBackInvokedCallback(w.b(obj2));
    }
}
