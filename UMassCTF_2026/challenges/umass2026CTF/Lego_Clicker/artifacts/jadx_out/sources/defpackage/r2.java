package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class r2 extends CheckedTextView {
    public final s2 a;
    public final n2 b;
    public final w4 c;
    public t3 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r2(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        rp.a(context);
        qp.a(this, getContext());
        w4 w4Var = new w4(this);
        this.c = w4Var;
        w4Var.f(attributeSet, R.attr.checkedTextViewStyle);
        w4Var.b();
        n2 n2Var = new n2(this);
        this.b = n2Var;
        n2Var.d(attributeSet, R.attr.checkedTextViewStyle);
        this.a = new s2(this);
        Context context2 = getContext();
        int[] iArr = fl.l;
        j5 j5VarQ = j5.q(context2, attributeSet, iArr, R.attr.checkedTextViewStyle);
        TypedArray typedArray = (TypedArray) j5VarQ.a;
        os.g(this, getContext(), iArr, attributeSet, (TypedArray) j5VarQ.a, R.attr.checkedTextViewStyle);
        try {
            if (typedArray.hasValue(1) && (resourceId2 = typedArray.getResourceId(1, 0)) != 0) {
                try {
                    setCheckMarkDrawable(d.w(getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(0)) {
                        setCheckMarkDrawable(d.w(getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
                setCheckMarkDrawable(d.w(getContext(), resourceId));
            }
            if (typedArray.hasValue(2)) {
                setCheckMarkTintList(j5VarQ.j(2));
            }
            if (typedArray.hasValue(3)) {
                setCheckMarkTintMode(ea.b(typedArray.getInt(3, -1), null));
            }
            j5VarQ.s();
            getEmojiTextViewHelper().a(attributeSet, R.attr.checkedTextViewStyle);
        } catch (Throwable th) {
            j5VarQ.s();
            throw th;
        }
    }

    private t3 getEmojiTextViewHelper() {
        if (this.d == null) {
            this.d = new t3(this);
        }
        return this.d;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w4 w4Var = this.c;
        if (w4Var != null) {
            w4Var.b();
        }
        n2 n2Var = this.b;
        if (n2Var != null) {
            n2Var.a();
        }
        s2 s2Var = this.a;
        if (s2Var != null) {
            s2Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return hb.a0(super.getCustomSelectionActionModeCallback());
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

    public ColorStateList getSupportCheckMarkTintList() {
        s2 s2Var = this.a;
        if (s2Var != null) {
            return s2Var.a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
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

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        d.G(editorInfo, inputConnectionOnCreateInputConnection, this);
        return inputConnectionOnCreateInputConnection;
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

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        s2 s2Var = this.a;
        if (s2Var != null) {
            if (s2Var.e) {
                s2Var.e = false;
            } else {
                s2Var.e = true;
                s2Var.b();
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

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(hb.c0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().c(z);
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

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        s2 s2Var = this.a;
        if (s2Var != null) {
            s2Var.a = colorStateList;
            s2Var.c = true;
            s2Var.b();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        s2 s2Var = this.a;
        if (s2Var != null) {
            s2Var.b = mode;
            s2Var.d = true;
            s2Var.b();
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

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        w4 w4Var = this.c;
        if (w4Var != null) {
            w4Var.g(context, i);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(d.w(getContext(), i));
    }
}
