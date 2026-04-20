package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class vi {
    public final Context a;
    public final gi b;
    public final boolean c;
    public final int d;
    public View e;
    public boolean g;
    public aj h;
    public ti i;
    public PopupWindow.OnDismissListener j;
    public int f = 8388611;
    public final ui k = new ui(this);

    public vi(Context context, gi giVar, View view, boolean z, int i, int i2) {
        this.a = context;
        this.b = giVar;
        this.e = view;
        this.c = z;
        this.d = i;
    }

    public final ti a() {
        ti apVar;
        if (this.i == null) {
            Context context = this.a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            int iMin = Math.min(point.x, point.y);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width);
            Context context2 = this.a;
            if (iMin >= dimensionPixelSize) {
                apVar = new w6(context2, this.e, this.d, this.c);
            } else {
                apVar = new ap(context2, this.b, this.e, this.d, this.c);
            }
            apVar.l(this.b);
            apVar.r(this.k);
            apVar.n(this.e);
            apVar.e(this.h);
            apVar.o(this.g);
            apVar.p(this.f);
            this.i = apVar;
        }
        return this.i;
    }

    public final boolean b() {
        ti tiVar = this.i;
        return tiVar != null && tiVar.b();
    }

    public void c() {
        this.i = null;
        PopupWindow.OnDismissListener onDismissListener = this.j;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i, int i2, boolean z, boolean z2) {
        ti tiVarA = a();
        tiVarA.s(z2);
        if (z) {
            if ((Gravity.getAbsoluteGravity(this.f, this.e.getLayoutDirection()) & 7) == 5) {
                i -= this.e.getWidth();
            }
            tiVarA.q(i);
            tiVarA.t(i2);
            int i3 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            tiVarA.a = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        tiVarA.f();
    }
}
