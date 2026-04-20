package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class m4 extends hh implements o4 {
    public CharSequence B;
    public j4 C;
    public final Rect D;
    public int E;
    public final /* synthetic */ p4 F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m4(p4 p4Var, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle);
        this.F = p4Var;
        this.D = new Rect();
        this.o = p4Var;
        this.x = true;
        this.y.setFocusable(true);
        this.p = new k4(this);
    }

    @Override // defpackage.o4
    public final void e(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        y3 y3Var = this.y;
        boolean zIsShowing = y3Var.isShowing();
        r();
        y3Var.setInputMethodMode(2);
        f();
        ka kaVar = this.c;
        kaVar.setChoiceMode(1);
        kaVar.setTextDirection(i);
        kaVar.setTextAlignment(i2);
        p4 p4Var = this.F;
        int selectedItemPosition = p4Var.getSelectedItemPosition();
        ka kaVar2 = this.c;
        if (y3Var.isShowing() && kaVar2 != null) {
            kaVar2.setListSelectionHidden(false);
            kaVar2.setSelection(selectedItemPosition);
            if (kaVar2.getChoiceMode() != 0) {
                kaVar2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (zIsShowing || (viewTreeObserver = p4Var.getViewTreeObserver()) == null) {
            return;
        }
        g4 g4Var = new g4(1, this);
        viewTreeObserver.addOnGlobalLayoutListener(g4Var);
        y3Var.setOnDismissListener(new l4(this, g4Var));
    }

    @Override // defpackage.o4
    public final CharSequence i() {
        return this.B;
    }

    @Override // defpackage.o4
    public final void k(CharSequence charSequence) {
        this.B = charSequence;
    }

    @Override // defpackage.hh, defpackage.o4
    public final void n(ListAdapter listAdapter) {
        super.n(listAdapter);
        this.C = (j4) listAdapter;
    }

    @Override // defpackage.o4
    public final void o(int i) {
        this.E = i;
    }

    public final void r() {
        int i;
        y3 y3Var = this.y;
        Drawable background = y3Var.getBackground();
        p4 p4Var = this.F;
        Rect rect = p4Var.h;
        if (background != null) {
            background.getPadding(rect);
            boolean z = jt.a;
            i = p4Var.getLayoutDirection() == 1 ? rect.right : -rect.left;
        } else {
            i = 0;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = p4Var.getPaddingLeft();
        int paddingRight = p4Var.getPaddingRight();
        int width = p4Var.getWidth();
        int i2 = p4Var.g;
        if (i2 == -2) {
            int iA = p4Var.a(this.C, y3Var.getBackground());
            int i3 = (p4Var.getContext().getResources().getDisplayMetrics().widthPixels - rect.left) - rect.right;
            if (iA > i3) {
                iA = i3;
            }
            q(Math.max(iA, (width - paddingLeft) - paddingRight));
        } else if (i2 == -1) {
            q((width - paddingLeft) - paddingRight);
        } else {
            q(i2);
        }
        boolean z2 = jt.a;
        this.f = p4Var.getLayoutDirection() == 1 ? (((width - paddingRight) - this.e) - this.E) + i : paddingLeft + this.E + i;
    }
}
