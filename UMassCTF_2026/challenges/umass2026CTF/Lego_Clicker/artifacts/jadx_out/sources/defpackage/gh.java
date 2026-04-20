package defpackage;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class gh implements View.OnTouchListener {
    public final /* synthetic */ hh a;

    public gh(hh hhVar) {
        this.a = hhVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        hh hhVar = this.a;
        dh dhVar = hhVar.q;
        Handler handler = hhVar.u;
        y3 y3Var = hhVar.y;
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (action == 0 && y3Var != null && y3Var.isShowing() && x >= 0 && x < y3Var.getWidth() && y >= 0 && y < y3Var.getHeight()) {
            handler.postDelayed(dhVar, 250L);
            return false;
        }
        if (action != 1) {
            return false;
        }
        handler.removeCallbacks(dhVar);
        return false;
    }
}
