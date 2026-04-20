package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xp implements bj {
    public gi a;
    public oi b;
    public final /* synthetic */ Toolbar c;

    public xp(Toolbar toolbar) {
        this.c = toolbar;
    }

    @Override // defpackage.bj
    public final boolean c() {
        return false;
    }

    @Override // defpackage.bj
    public final boolean d(oi oiVar) {
        Toolbar toolbar = this.c;
        KeyEvent.Callback callback = toolbar.i;
        if (callback instanceof g7) {
            ((qi) ((g7) callback)).a.onActionViewCollapsed();
        }
        toolbar.removeView(toolbar.i);
        toolbar.removeView(toolbar.h);
        toolbar.i = null;
        ArrayList arrayList = toolbar.E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.b = null;
        toolbar.requestLayout();
        oiVar.C = false;
        oiVar.n.p(false);
        toolbar.v();
        return true;
    }

    @Override // defpackage.bj
    public final boolean g(oi oiVar) {
        Toolbar toolbar = this.c;
        toolbar.c();
        ViewParent parent = toolbar.h.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.h);
            }
            toolbar.addView(toolbar.h);
        }
        View actionView = oiVar.getActionView();
        toolbar.i = actionView;
        this.b = oiVar;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.i);
            }
            yp ypVarH = Toolbar.h();
            ypVarH.a = (toolbar.n & 112) | 8388611;
            ypVarH.b = 2;
            toolbar.i.setLayoutParams(ypVarH);
            toolbar.addView(toolbar.i);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((yp) childAt.getLayoutParams()).b != 2 && childAt != toolbar.a) {
                toolbar.removeViewAt(childCount);
                toolbar.E.add(childAt);
            }
        }
        toolbar.requestLayout();
        oiVar.C = true;
        oiVar.n.p(false);
        KeyEvent.Callback callback = toolbar.i;
        if (callback instanceof g7) {
            ((qi) ((g7) callback)).a.onActionViewExpanded();
        }
        toolbar.v();
        return true;
    }

    @Override // defpackage.bj
    public final void h() {
        if (this.b != null) {
            gi giVar = this.a;
            if (giVar != null) {
                int size = giVar.f.size();
                for (int i = 0; i < size; i++) {
                    if (this.a.getItem(i) == this.b) {
                        return;
                    }
                }
            }
            d(this.b);
        }
    }

    @Override // defpackage.bj
    public final void i(Context context, gi giVar) {
        oi oiVar;
        gi giVar2 = this.a;
        if (giVar2 != null && (oiVar = this.b) != null) {
            giVar2.d(oiVar);
        }
        this.a = giVar;
    }

    @Override // defpackage.bj
    public final boolean k(bp bpVar) {
        return false;
    }

    @Override // defpackage.bj
    public final void a(gi giVar, boolean z) {
    }
}
