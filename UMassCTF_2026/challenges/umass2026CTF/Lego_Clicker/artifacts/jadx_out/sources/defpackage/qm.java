package defpackage;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import androidx.lifecycle.a;
import defpackage.sm;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class qm {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, dg dgVar) {
        dgVar.getClass();
        if (activity instanceof kg) {
            fg lifecycle = ((kg) activity).getLifecycle();
            if (lifecycle instanceof a) {
                ((a) lifecycle).e(dgVar);
            }
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            sm.a.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new sm.a());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new sm(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
