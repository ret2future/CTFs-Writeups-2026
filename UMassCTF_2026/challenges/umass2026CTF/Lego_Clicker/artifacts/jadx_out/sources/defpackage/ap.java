package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ap extends ti implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public final Context b;
    public final gi c;
    public final di d;
    public final boolean e;
    public final int f;
    public final int g;
    public final zi h;
    public PopupWindow.OnDismissListener k;
    public View l;
    public View m;
    public aj n;
    public ViewTreeObserver o;
    public boolean p;
    public boolean q;
    public int r;
    public boolean t;
    public final g4 i = new g4(3, this);
    public final t6 j = new t6(this, 1);
    public int s = 0;

    public ap(Context context, gi giVar, View view, int i, boolean z) {
        this.b = context;
        this.c = giVar;
        this.e = z;
        this.d = new di(giVar, LayoutInflater.from(context), z, R.layout.abc_popup_menu_item_layout);
        this.g = i;
        Resources resources = context.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.l = view;
        this.h = new zi(context, null, i);
        giVar.b(this, context);
    }

    @Override // defpackage.bj
    public final void a(gi giVar, boolean z) {
        if (giVar != this.c) {
            return;
        }
        dismiss();
        aj ajVar = this.n;
        if (ajVar != null) {
            ajVar.a(giVar, z);
        }
    }

    @Override // defpackage.jo
    public final boolean b() {
        return !this.p && this.h.y.isShowing();
    }

    @Override // defpackage.bj
    public final boolean c() {
        return false;
    }

    @Override // defpackage.jo
    public final void dismiss() {
        if (b()) {
            this.h.dismiss();
        }
    }

    @Override // defpackage.bj
    public final void e(aj ajVar) {
        this.n = ajVar;
    }

    @Override // defpackage.jo
    public final void f() {
        View view;
        if (b()) {
            return;
        }
        if (this.p || (view = this.l) == null) {
            z6.o("StandardMenuPopup cannot be used without an anchor");
            return;
        }
        this.m = view;
        zi ziVar = this.h;
        y3 y3Var = ziVar.y;
        y3 y3Var2 = ziVar.y;
        y3Var.setOnDismissListener(this);
        ziVar.p = this;
        ziVar.x = true;
        y3Var2.setFocusable(true);
        View view2 = this.m;
        boolean z = this.o == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.o = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.i);
        }
        view2.addOnAttachStateChangeListener(this.j);
        ziVar.o = view2;
        ziVar.l = this.s;
        boolean z2 = this.q;
        Context context = this.b;
        di diVar = this.d;
        if (!z2) {
            this.r = ti.m(diVar, context, this.f);
            this.q = true;
        }
        ziVar.q(this.r);
        y3Var2.setInputMethodMode(2);
        Rect rect = this.a;
        ziVar.w = rect != null ? new Rect(rect) : null;
        ziVar.f();
        ka kaVar = ziVar.c;
        kaVar.setOnKeyListener(this);
        if (this.t) {
            gi giVar = this.c;
            if (giVar.m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) kaVar, false);
                TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                if (textView != null) {
                    textView.setText(giVar.m);
                }
                frameLayout.setEnabled(false);
                kaVar.addHeaderView(frameLayout, null, false);
            }
        }
        ziVar.n(diVar);
        ziVar.f();
    }

    @Override // defpackage.bj
    public final void h() {
        this.q = false;
        di diVar = this.d;
        if (diVar != null) {
            diVar.notifyDataSetChanged();
        }
    }

    @Override // defpackage.jo
    public final ka j() {
        return this.h.c;
    }

    @Override // defpackage.bj
    public final boolean k(bp bpVar) {
        boolean z;
        if (bpVar.hasVisibleItems()) {
            vi viVar = new vi(this.b, bpVar, this.m, this.e, this.g, 0);
            aj ajVar = this.n;
            viVar.h = ajVar;
            ti tiVar = viVar.i;
            if (tiVar != null) {
                tiVar.e(ajVar);
            }
            int size = bpVar.f.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    z = false;
                    break;
                }
                MenuItem item = bpVar.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
                i++;
            }
            viVar.g = z;
            ti tiVar2 = viVar.i;
            if (tiVar2 != null) {
                tiVar2.o(z);
            }
            viVar.j = this.k;
            this.k = null;
            this.c.c(false);
            zi ziVar = this.h;
            int width = ziVar.f;
            int iG = ziVar.g();
            if ((Gravity.getAbsoluteGravity(this.s, this.l.getLayoutDirection()) & 7) == 5) {
                width += this.l.getWidth();
            }
            if (!viVar.b()) {
                if (viVar.e != null) {
                    viVar.d(width, iG, true, true);
                }
            }
            aj ajVar2 = this.n;
            if (ajVar2 != null) {
                ajVar2.s(bpVar);
            }
            return true;
        }
        return false;
    }

    @Override // defpackage.ti
    public final void n(View view) {
        this.l = view;
    }

    @Override // defpackage.ti
    public final void o(boolean z) {
        this.d.c = z;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.p = true;
        this.c.c(true);
        ViewTreeObserver viewTreeObserver = this.o;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.o = this.m.getViewTreeObserver();
            }
            this.o.removeGlobalOnLayoutListener(this.i);
            this.o = null;
        }
        this.m.removeOnAttachStateChangeListener(this.j);
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // defpackage.ti
    public final void p(int i) {
        this.s = i;
    }

    @Override // defpackage.ti
    public final void q(int i) {
        this.h.f = i;
    }

    @Override // defpackage.ti
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    @Override // defpackage.ti
    public final void s(boolean z) {
        this.t = z;
    }

    @Override // defpackage.ti
    public final void t(int i) {
        this.h.m(i);
    }

    @Override // defpackage.ti
    public final void l(gi giVar) {
    }
}
