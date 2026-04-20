package defpackage;

import android.content.Context;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dq extends o0 {
    public final gq a;
    public final Window.Callback b;
    public final bq c;
    public boolean d;
    public boolean e;
    public boolean f;
    public final ArrayList g = new ArrayList();
    public final b6 h = new b6(10, this);

    public dq(Toolbar toolbar, CharSequence charSequence, h3 h3Var) {
        bq bqVar = new bq(this);
        gq gqVar = new gq(toolbar, false);
        this.a = gqVar;
        h3Var.getClass();
        this.b = h3Var;
        gqVar.k = h3Var;
        toolbar.setOnMenuItemClickListener(bqVar);
        boolean z = gqVar.g;
        if (!z) {
            gqVar.h = charSequence;
            if ((gqVar.b & 8) != 0) {
                toolbar.setTitle(charSequence);
                if (z) {
                    os.i(toolbar.getRootView(), charSequence);
                }
            }
        }
        this.c = new bq(this);
    }

    @Override // defpackage.o0
    public final boolean a() {
        e1 e1Var;
        ActionMenuView actionMenuView = this.a.a.a;
        return (actionMenuView == null || (e1Var = actionMenuView.t) == null || !e1Var.f()) ? false : true;
    }

    @Override // defpackage.o0
    public final boolean b() {
        oi oiVar;
        xp xpVar = this.a.a.M;
        if (xpVar == null || (oiVar = xpVar.b) == null) {
            return false;
        }
        if (xpVar == null) {
            oiVar = null;
        }
        if (oiVar == null) {
            return true;
        }
        oiVar.collapseActionView();
        return true;
    }

    @Override // defpackage.o0
    public final void c(boolean z) {
        if (z == this.f) {
            return;
        }
        this.f = z;
        ArrayList arrayList = this.g;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        z6.a();
    }

    @Override // defpackage.o0
    public final int d() {
        return this.a.b;
    }

    @Override // defpackage.o0
    public final Context e() {
        return this.a.a.getContext();
    }

    @Override // defpackage.o0
    public final boolean f() {
        gq gqVar = this.a;
        Toolbar toolbar = gqVar.a;
        b6 b6Var = this.h;
        toolbar.removeCallbacks(b6Var);
        Toolbar toolbar2 = gqVar.a;
        WeakHashMap weakHashMap = os.a;
        toolbar2.postOnAnimation(b6Var);
        return true;
    }

    @Override // defpackage.o0
    public final void h() {
        this.a.a.removeCallbacks(this.h);
    }

    @Override // defpackage.o0
    public final boolean i(int i, KeyEvent keyEvent) {
        Menu menuP = p();
        if (menuP == null) {
            return false;
        }
        menuP.setQwertyMode(KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() != 1);
        return menuP.performShortcut(i, keyEvent, 0);
    }

    @Override // defpackage.o0
    public final boolean j(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            k();
        }
        return true;
    }

    @Override // defpackage.o0
    public final boolean k() {
        return this.a.a.u();
    }

    @Override // defpackage.o0
    public final void n(CharSequence charSequence) {
        gq gqVar = this.a;
        if (gqVar.g) {
            return;
        }
        Toolbar toolbar = gqVar.a;
        gqVar.h = charSequence;
        if ((gqVar.b & 8) != 0) {
            toolbar.setTitle(charSequence);
            if (gqVar.g) {
                os.i(toolbar.getRootView(), charSequence);
            }
        }
    }

    public final Menu p() {
        boolean z = this.e;
        gq gqVar = this.a;
        if (!z) {
            cq cqVar = new cq(this);
            bq bqVar = new bq(this);
            Toolbar toolbar = gqVar.a;
            toolbar.N = cqVar;
            toolbar.O = bqVar;
            ActionMenuView actionMenuView = toolbar.a;
            if (actionMenuView != null) {
                actionMenuView.u = cqVar;
                actionMenuView.v = bqVar;
            }
            this.e = true;
        }
        return gqVar.a.getMenu();
    }

    @Override // defpackage.o0
    public final void l(boolean z) {
    }

    @Override // defpackage.o0
    public final void m(boolean z) {
    }

    @Override // defpackage.o0
    public final void g() {
    }
}
