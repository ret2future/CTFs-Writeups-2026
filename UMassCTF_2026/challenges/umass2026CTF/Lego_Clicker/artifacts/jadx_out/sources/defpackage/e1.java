package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import com.example.LegoClicker.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class e1 implements bj {
    public final Context a;
    public Context b;
    public gi c;
    public final LayoutInflater d;
    public aj e;
    public ej h;
    public d1 i;
    public Drawable j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public a1 s;
    public a1 t;
    public c1 u;
    public b1 v;
    public final int f = R.layout.abc_action_menu_layout;
    public final int g = R.layout.abc_action_menu_item_layout;
    public final SparseBooleanArray r = new SparseBooleanArray();
    public final e0 w = new e0(1, this);

    public e1(Context context) {
        this.a = context;
        this.d = LayoutInflater.from(context);
    }

    @Override // defpackage.bj
    public final void a(gi giVar, boolean z) {
        f();
        a1 a1Var = this.t;
        if (a1Var != null && a1Var.b()) {
            a1Var.i.dismiss();
        }
        aj ajVar = this.e;
        if (ajVar != null) {
            ajVar.a(giVar, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View b(oi oiVar, View view, ViewGroup viewGroup) {
        View actionView = oiVar.getActionView();
        if (actionView == null || oiVar.e()) {
            dj djVar = view instanceof dj ? (dj) view : (dj) this.d.inflate(this.g, viewGroup, false);
            djVar.c(oiVar);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) djVar;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.h);
            if (this.v == null) {
                this.v = new b1(this);
            }
            actionMenuItemView.setPopupCallback(this.v);
            actionView = (View) djVar;
        }
        actionView.setVisibility(oiVar.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        ((ActionMenuView) viewGroup).getClass();
        if (!(layoutParams instanceof g1)) {
            actionView.setLayoutParams(ActionMenuView.j(layoutParams));
        }
        return actionView;
    }

    @Override // defpackage.bj
    public final boolean c() {
        int size;
        ArrayList arrayListL;
        int i;
        boolean z;
        e1 e1Var = this;
        gi giVar = e1Var.c;
        if (giVar != null) {
            arrayListL = giVar.l();
            size = arrayListL.size();
        } else {
            size = 0;
            arrayListL = null;
        }
        int i2 = e1Var.p;
        int i3 = e1Var.o;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) e1Var.h;
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = 2;
            z = true;
            if (i4 >= size) {
                break;
            }
            oi oiVar = (oi) arrayListL.get(i4);
            int i7 = oiVar.y;
            if ((i7 & 2) == 2) {
                i5++;
            } else if ((i7 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (e1Var.q && oiVar.C) {
                i2 = 0;
            }
            i4++;
        }
        if (e1Var.l && (z2 || i6 + i5 > i2)) {
            i2--;
        }
        int i8 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = e1Var.r;
        sparseBooleanArray.clear();
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            oi oiVar2 = (oi) arrayListL.get(i9);
            int i11 = oiVar2.y;
            boolean z3 = (i11 & 2) == i ? z : false;
            int i12 = oiVar2.b;
            if (z3) {
                View viewB = e1Var.b(oiVar2, null, viewGroup);
                viewB.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewB.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i10 == 0) {
                    i10 = measuredWidth;
                }
                if (i12 != 0) {
                    sparseBooleanArray.put(i12, z);
                }
                oiVar2.f(z);
            } else if ((i11 & 1) == z) {
                boolean z4 = sparseBooleanArray.get(i12);
                boolean z5 = ((i8 > 0 || z4) && i3 > 0) ? z : false;
                if (z5) {
                    View viewB2 = e1Var.b(oiVar2, null, viewGroup);
                    viewB2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewB2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i10 == 0) {
                        i10 = measuredWidth2;
                    }
                    z5 &= i3 + i10 > 0;
                }
                if (z5 && i12 != 0) {
                    sparseBooleanArray.put(i12, true);
                } else if (z4) {
                    sparseBooleanArray.put(i12, false);
                    for (int i13 = 0; i13 < i9; i13++) {
                        oi oiVar3 = (oi) arrayListL.get(i13);
                        if (oiVar3.b == i12) {
                            if ((oiVar3.x & 32) == 32) {
                                i8++;
                            }
                            oiVar3.f(false);
                        }
                    }
                }
                if (z5) {
                    i8--;
                }
                oiVar2.f(z5);
            } else {
                oiVar2.f(false);
                i9++;
                i = 2;
                e1Var = this;
                z = true;
            }
            i9++;
            i = 2;
            e1Var = this;
            z = true;
        }
        return z;
    }

    @Override // defpackage.bj
    public final boolean d(oi oiVar) {
        return false;
    }

    @Override // defpackage.bj
    public final void e(aj ajVar) {
        throw null;
    }

    public final boolean f() {
        Object obj;
        c1 c1Var = this.u;
        if (c1Var != null && (obj = this.h) != null) {
            ((View) obj).removeCallbacks(c1Var);
            this.u = null;
            return true;
        }
        a1 a1Var = this.s;
        if (a1Var == null) {
            return false;
        }
        if (a1Var.b()) {
            a1Var.i.dismiss();
        }
        return true;
    }

    @Override // defpackage.bj
    public final boolean g(oi oiVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.bj
    public final void h() {
        int i;
        ViewGroup viewGroup = (ViewGroup) this.h;
        ArrayList arrayList = null;
        boolean z = false;
        if (viewGroup != null) {
            gi giVar = this.c;
            if (giVar != null) {
                giVar.i();
                ArrayList arrayListL = this.c.l();
                int size = arrayListL.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    oi oiVar = (oi) arrayListL.get(i2);
                    if ((oiVar.x & 32) == 32) {
                        View childAt = viewGroup.getChildAt(i);
                        oi itemData = childAt instanceof dj ? ((dj) childAt).getItemData() : null;
                        View viewB = b(oiVar, childAt, viewGroup);
                        if (oiVar != itemData) {
                            viewB.setPressed(false);
                            viewB.jumpDrawablesToCurrentState();
                        }
                        if (viewB != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) viewB.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(viewB);
                            }
                            ((ViewGroup) this.h).addView(viewB, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.i) {
                    i++;
                } else {
                    viewGroup.removeViewAt(i);
                }
            }
        }
        ((View) this.h).requestLayout();
        gi giVar2 = this.c;
        if (giVar2 != null) {
            giVar2.i();
            ArrayList arrayList2 = giVar2.i;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                pi piVar = ((oi) arrayList2.get(i3)).A;
            }
        }
        gi giVar3 = this.c;
        if (giVar3 != null) {
            giVar3.i();
            arrayList = giVar3.j;
        }
        if (this.l && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z = !((oi) arrayList.get(0)).C;
            } else if (size3 > 0) {
                z = true;
            }
        }
        d1 d1Var = this.i;
        if (z) {
            if (d1Var == null) {
                this.i = new d1(this, this.a);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.i.getParent();
            if (viewGroup3 != this.h) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.i);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.h;
                d1 d1Var2 = this.i;
                actionMenuView.getClass();
                g1 g1VarI = ActionMenuView.i();
                g1VarI.a = true;
                actionMenuView.addView(d1Var2, g1VarI);
            }
        } else if (d1Var != null) {
            Object parent = d1Var.getParent();
            Object obj = this.h;
            if (parent == obj) {
                ((ViewGroup) obj).removeView(this.i);
            }
        }
        ((ActionMenuView) this.h).setOverflowReserved(this.l);
    }

    @Override // defpackage.bj
    public final void i(Context context, gi giVar) {
        this.b = context;
        LayoutInflater.from(context);
        this.c = giVar;
        Resources resources = context.getResources();
        if (!this.m) {
            this.l = true;
        }
        int i = 2;
        this.n = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600 || ((i2 > 960 && i3 > 720) || (i2 > 720 && i3 > 960))) {
            i = 5;
        } else if (i2 >= 500 || ((i2 > 640 && i3 > 480) || (i2 > 480 && i3 > 640))) {
            i = 4;
        } else if (i2 >= 360) {
            i = 3;
        }
        this.p = i;
        int measuredWidth = this.n;
        if (this.l) {
            if (this.i == null) {
                d1 d1Var = new d1(this, this.a);
                this.i = d1Var;
                if (this.k) {
                    d1Var.setImageDrawable(this.j);
                    this.j = null;
                    this.k = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.o = measuredWidth;
        float f = resources.getDisplayMetrics().density;
    }

    public final boolean j() {
        a1 a1Var = this.s;
        return a1Var != null && a1Var.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.bj
    public final boolean k(bp bpVar) {
        boolean z;
        if (bpVar.hasVisibleItems()) {
            bp bpVar2 = bpVar;
            while (true) {
                gi giVar = bpVar2.z;
                if (giVar == this.c) {
                    break;
                }
                bpVar2 = (bp) giVar;
            }
            oi oiVar = bpVar2.A;
            ViewGroup viewGroup = (ViewGroup) this.h;
            View view = null;
            view = null;
            if (viewGroup != null) {
                int childCount = viewGroup.getChildCount();
                int i = 0;
                while (true) {
                    if (i >= childCount) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i);
                    if ((childAt instanceof dj) && ((dj) childAt).getItemData() == oiVar) {
                        view = childAt;
                        break;
                    }
                    i++;
                }
            }
            if (view != null) {
                bpVar.A.getClass();
                int size = bpVar.f.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        z = false;
                        break;
                    }
                    MenuItem item = bpVar.getItem(i2);
                    if (item.isVisible() && item.getIcon() != null) {
                        z = true;
                        break;
                    }
                    i2++;
                }
                a1 a1Var = new a1(this, this.b, bpVar, view);
                this.t = a1Var;
                a1Var.g = z;
                ti tiVar = a1Var.i;
                if (tiVar != null) {
                    tiVar.o(z);
                }
                a1 a1Var2 = this.t;
                if (!a1Var2.b()) {
                    if (a1Var2.e == null) {
                        z6.o("MenuPopupHelper cannot be used without an anchor");
                        return false;
                    }
                    a1Var2.d(0, 0, false, false);
                }
                aj ajVar = this.e;
                if (ajVar != null) {
                    ajVar.s(bpVar);
                }
                return true;
            }
        }
        return false;
    }

    public final boolean l() {
        gi giVar;
        if (!this.l || j() || (giVar = this.c) == null || this.h == null || this.u != null) {
            return false;
        }
        giVar.i();
        if (giVar.j.isEmpty()) {
            return false;
        }
        c1 c1Var = new c1(this, new a1(this, this.b, this.c, this.i));
        this.u = c1Var;
        ((View) this.h).post(c1Var);
        return true;
    }
}
