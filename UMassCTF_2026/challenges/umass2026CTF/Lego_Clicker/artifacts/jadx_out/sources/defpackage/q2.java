package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class q2 extends CheckBox {
    public final s2 a;
    public final n2 b;
    public final w4 c;
    public t3 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.checkboxStyle);
        rp.a(context);
        qp.a(this, getContext());
        s2 s2Var = new s2(this);
        this.a = s2Var;
        s2Var.c(attributeSet, R.attr.checkboxStyle);
        n2 n2Var = new n2(this);
        this.b = n2Var;
        n2Var.d(attributeSet, R.attr.checkboxStyle);
        w4 w4Var = new w4(this);
        this.c = w4Var;
        w4Var.f(attributeSet, R.attr.checkboxStyle);
        getEmojiTextViewHelper().a(attributeSet, R.attr.checkboxStyle);
    }

    private t3 getEmojiTextViewHelper() {
        if (this.d == null) {
            this.d = new t3(this);
        }
        return this.d;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        n2 n2Var = this.b;
        if (n2Var != null) {
            n2Var.a();
        }
        w4 w4Var = this.c;
        if (w4Var != null) {
            w4Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        n2 n2Var = this.b;
        if (n2Var != null) {
            return n2Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        n2 n2Var = this.b;
        if (n2Var != null) {
            return n2Var.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        s2 s2Var = this.a;
        if (s2Var != null) {
            return s2Var.a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        s2 s2Var = this.a;
        if (s2Var != null) {
            return s2Var.b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.c.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().b(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        n2 n2Var = this.b;
        if (n2Var != null) {
            n2Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        n2 n2Var = this.b;
        if (n2Var != null) {
            n2Var.f(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        s2 s2Var = this.a;
        if (s2Var != null) {
            if (s2Var.e) {
                s2Var.e = false;
            } else {
                s2Var.e = true;
                s2Var.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        w4 w4Var = this.c;
        if (w4Var != null) {
            w4Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        w4 w4Var = this.c;
        if (w4Var != null) {
            w4Var.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((hb) getEmojiTextViewHelper().b.b).C(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        n2 n2Var = this.b;
        if (n2Var != null) {
            n2Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        n2 n2Var = this.b;
        if (n2Var != null) {
            n2Var.i(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        s2 s2Var = this.a;
        if (s2Var != null) {
            s2Var.a = colorStateList;
            s2Var.c = true;
            s2Var.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        s2 s2Var = this.a;
        if (s2Var != null) {
            s2Var.b = mode;
            s2Var.d = true;
            s2Var.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        w4 w4Var = this.c;
        w4Var.l(colorStateList);
        w4Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        w4 w4Var = this.c;
        w4Var.m(mode);
        w4Var.b();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(d.w(getContext(), i));
    }
}
