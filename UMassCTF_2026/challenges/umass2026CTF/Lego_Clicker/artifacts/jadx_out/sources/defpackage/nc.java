package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class nc implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ oc b;

    public /* synthetic */ nc(oc ocVar, int i) {
        this.a = i;
        this.b = ocVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        oc ocVar = this.b;
        switch (i) {
            case 0:
                ViewParent parent = ocVar.d.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                ocVar.a();
                View view = ocVar.d;
                if (view.isEnabled() && !view.isLongClickable() && ocVar.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    ocVar.g = true;
                    break;
                }
                break;
        }
    }
}
