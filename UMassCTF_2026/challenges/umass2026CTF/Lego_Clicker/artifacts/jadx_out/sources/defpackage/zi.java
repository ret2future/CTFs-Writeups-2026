package defpackage;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zi extends hh implements ni {
    public static final Method C;
    public e0 B;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                C = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    @Override // defpackage.hh
    public final ka a(Context context, boolean z) {
        yi yiVar = new yi(context, z);
        yiVar.setHoverListener(this);
        return yiVar;
    }

    @Override // defpackage.ni
    public final void p(gi giVar, MenuItem menuItem) {
        e0 e0Var = this.B;
        if (e0Var != null) {
            e0Var.p(giVar, menuItem);
        }
    }

    @Override // defpackage.ni
    public final void t(gi giVar, oi oiVar) {
        e0 e0Var = this.B;
        if (e0Var != null) {
            e0Var.t(giVar, oiVar);
        }
    }
}
