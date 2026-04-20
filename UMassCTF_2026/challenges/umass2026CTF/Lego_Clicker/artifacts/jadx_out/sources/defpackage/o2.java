package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class o2 extends Button {
    public final n2 a;
    public final w4 b;
    public t3 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
        rp.a(context);
        qp.a(this, getContext());
        n2 n2Var = new n2(this);
        this.a = n2Var;
        n2Var.d(attributeSet, R.attr.buttonStyle);
        w4 w4Var = new w4(this);
        this.b = w4Var;
        w4Var.f(attributeSet, R.attr.buttonStyle);
        w4Var.b();
        getEmojiTextViewHelper().a(attributeSet, R.attr.buttonStyle);
    }

    private t3 getEmojiTextViewHelper() {
        if (this.c == null) {
            this.c = new t3(this);
        }
        return this.c;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        n2 n2Var = this.a;
        if (n2Var != null) {
            n2Var.a();
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            w4Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (jt.c) {
            return super.getAutoSizeMaxTextSize();
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            return Math.round(w4Var.i.e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (jt.c) {
            return super.getAutoSizeMinTextSize();
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            return Math.round(w4Var.i.d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (jt.c) {
            return super.getAutoSizeStepGranularity();
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            return Math.round(w4Var.i.c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (jt.c) {
            return super.getAutoSizeTextAvailableSizes();
        }
        w4 w4Var = this.b;
        return w4Var != null ? w4Var.i.f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (jt.c) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            return w4Var.i.a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return hb.a0(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        n2 n2Var = this.a;
        if (n2Var != null) {
            return n2Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        n2 n2Var = this.a;
        if (n2Var != null) {
            return n2Var.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.b.e();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        w4 w4Var = this.b;
        if (w4Var == null || jt.c) {
            return;
        }
        w4Var.i.a();
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        w4 w4Var = this.b;
        if (w4Var != null) {
            f5 f5Var = w4Var.i;
            if (jt.c || !f5Var.f()) {
                return;
            }
            f5Var.a();
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().b(z);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (jt.c) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            w4Var.i(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (jt.c) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            w4Var.j(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (jt.c) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            w4Var.k(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        n2 n2Var = this.a;
        if (n2Var != null) {
            n2Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        n2 n2Var = this.a;
        if (n2Var != null) {
            n2Var.f(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(hb.c0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((hb) getEmojiTextViewHelper().b.b).C(inputFilterArr));
    }

    public void setSupportAllCaps(boolean z) {
        w4 w4Var = this.b;
        if (w4Var != null) {
            w4Var.a.setAllCaps(z);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        n2 n2Var = this.a;
        if (n2Var != null) {
            n2Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        n2 n2Var = this.a;
        if (n2Var != null) {
            n2Var.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        w4 w4Var = this.b;
        w4Var.l(colorStateList);
        w4Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        w4 w4Var = this.b;
        w4Var.m(mode);
        w4Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        w4 w4Var = this.b;
        if (w4Var != null) {
            w4Var.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        boolean z = jt.c;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        w4 w4Var = this.b;
        if (w4Var != null) {
            f5 f5Var = w4Var.i;
            if (z || f5Var.f()) {
                return;
            }
            f5Var.g(i, f);
        }
    }
}
