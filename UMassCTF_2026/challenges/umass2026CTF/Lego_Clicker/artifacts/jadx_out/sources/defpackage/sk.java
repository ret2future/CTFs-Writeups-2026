package defpackage;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class sk extends mb {
    final /* synthetic */ tk this$0;

    /* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
    public static final class a extends mb {
        final /* synthetic */ tk this$0;

        public a(tk tkVar) {
            this.this$0 = tkVar;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            activity.getClass();
            this.this$0.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            activity.getClass();
            tk tkVar = this.this$0;
            int i = tkVar.a + 1;
            tkVar.a = i;
            if (i == 1 && tkVar.d) {
                tkVar.f.e(dg.ON_START);
                tkVar.d = false;
            }
        }
    }

    public sk(tk tkVar) {
        this.this$0 = tkVar;
    }

    @Override // defpackage.mb, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        activity.getClass();
        if (Build.VERSION.SDK_INT < 29) {
            int i = sm.b;
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            fragmentFindFragmentByTag.getClass();
            ((sm) fragmentFindFragmentByTag).a = this.this$0.h;
        }
    }

    @Override // defpackage.mb, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        activity.getClass();
        tk tkVar = this.this$0;
        int i = tkVar.b - 1;
        tkVar.b = i;
        if (i == 0) {
            Handler handler = tkVar.e;
            handler.getClass();
            handler.postDelayed(tkVar.g, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        activity.getClass();
        rk.a(activity, new a(this.this$0));
    }

    @Override // defpackage.mb, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        activity.getClass();
        tk tkVar = this.this$0;
        int i = tkVar.a - 1;
        tkVar.a = i;
        if (i == 0 && tkVar.c) {
            tkVar.f.e(dg.ON_STOP);
            tkVar.d = true;
        }
    }
}
