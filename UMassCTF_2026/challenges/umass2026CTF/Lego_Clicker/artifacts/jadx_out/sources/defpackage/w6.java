package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.example.LegoClicker.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class w6 extends ti implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public final Context b;
    public final int c;
    public final int d;
    public final boolean e;
    public final Handler f;
    public View n;
    public View o;
    public int p;
    public boolean q;
    public boolean r;
    public int s;
    public int t;
    public boolean v;
    public aj w;
    public ViewTreeObserver x;
    public PopupWindow.OnDismissListener y;
    public boolean z;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public final g4 i = new g4(2, this);
    public final t6 j = new t6(this, 0);
    public final e0 k = new e0(4, this);
    public int l = 0;
    public int m = 0;
    public boolean u = false;

    public w6(Context context, View view, int i, boolean z) {
        this.b = context;
        this.n = view;
        this.d = i;
        this.e = z;
        this.p = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f = new Handler();
    }

    @Override // defpackage.bj
    public final void a(gi giVar, boolean z) {
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (giVar == ((v6) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            return;
        }
        int i2 = i + 1;
        if (i2 < arrayList.size()) {
            ((v6) arrayList.get(i2)).b.c(false);
        }
        v6 v6Var = (v6) arrayList.remove(i);
        gi giVar2 = v6Var.b;
        zi ziVar = v6Var.a;
        y3 y3Var = ziVar.y;
        giVar2.r(this);
        if (this.z) {
            wi.b(y3Var, null);
            y3Var.setAnimationStyle(0);
        }
        ziVar.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.p = ((v6) arrayList.get(size2 - 1)).c;
        } else {
            this.p = this.n.getLayoutDirection() == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z) {
                ((v6) arrayList.get(0)).b.c(false);
                return;
            }
            return;
        }
        dismiss();
        aj ajVar = this.w;
        if (ajVar != null) {
            ajVar.a(giVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.x;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.x.removeGlobalOnLayoutListener(this.i);
            }
            this.x = null;
        }
        this.o.removeOnAttachStateChangeListener(this.j);
        this.y.onDismiss();
    }

    @Override // defpackage.jo
    public final boolean b() {
        ArrayList arrayList = this.h;
        return arrayList.size() > 0 && ((v6) arrayList.get(0)).a.y.isShowing();
    }

    @Override // defpackage.bj
    public final boolean c() {
        return false;
    }

    @Override // defpackage.jo
    public final void dismiss() {
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        if (size > 0) {
            v6[] v6VarArr = (v6[]) arrayList.toArray(new v6[size]);
            for (int i = size - 1; i >= 0; i--) {
                v6 v6Var = v6VarArr[i];
                if (v6Var.a.y.isShowing()) {
                    v6Var.a.dismiss();
                }
            }
        }
    }

    @Override // defpackage.bj
    public final void e(aj ajVar) {
        this.w = ajVar;
    }

    @Override // defpackage.jo
    public final void f() {
        if (b()) {
            return;
        }
        ArrayList arrayList = this.g;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            u((gi) obj);
        }
        arrayList.clear();
        View view = this.n;
        this.o = view;
        if (view != null) {
            boolean z = this.x == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.x = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.i);
            }
            this.o.addOnAttachStateChangeListener(this.j);
        }
    }

    @Override // defpackage.bj
    public final void h() {
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ListAdapter adapter = ((v6) obj).a.c.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((di) adapter).notifyDataSetChanged();
        }
    }

    @Override // defpackage.jo
    public final ka j() {
        ArrayList arrayList = this.h;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((v6) arrayList.get(arrayList.size() - 1)).a.c;
    }

    @Override // defpackage.bj
    public final boolean k(bp bpVar) {
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            v6 v6Var = (v6) obj;
            if (bpVar == v6Var.b) {
                v6Var.a.c.requestFocus();
                return true;
            }
        }
        if (!bpVar.hasVisibleItems()) {
            return false;
        }
        l(bpVar);
        aj ajVar = this.w;
        if (ajVar != null) {
            ajVar.s(bpVar);
        }
        return true;
    }

    @Override // defpackage.ti
    public final void l(gi giVar) {
        giVar.b(this, this.b);
        if (b()) {
            u(giVar);
        } else {
            this.g.add(giVar);
        }
    }

    @Override // defpackage.ti
    public final void n(View view) {
        if (this.n != view) {
            this.n = view;
            this.m = Gravity.getAbsoluteGravity(this.l, view.getLayoutDirection());
        }
    }

    @Override // defpackage.ti
    public final void o(boolean z) {
        this.u = z;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        v6 v6Var;
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                v6Var = null;
                break;
            }
            v6Var = (v6) arrayList.get(i);
            if (!v6Var.a.y.isShowing()) {
                break;
            } else {
                i++;
            }
        }
        if (v6Var != null) {
            v6Var.b.c(false);
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
        if (this.l != i) {
            this.l = i;
            this.m = Gravity.getAbsoluteGravity(i, this.n.getLayoutDirection());
        }
    }

    @Override // defpackage.ti
    public final void q(int i) {
        this.q = true;
        this.s = i;
    }

    @Override // defpackage.ti
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.y = onDismissListener;
    }

    @Override // defpackage.ti
    public final void s(boolean z) {
        this.v = z;
    }

    @Override // defpackage.ti
    public final void t(int i) {
        this.r = true;
        this.t = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(defpackage.gi r20) {
        /*
            Method dump skipped, instruction units count: 571
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w6.u(gi):void");
    }
}
