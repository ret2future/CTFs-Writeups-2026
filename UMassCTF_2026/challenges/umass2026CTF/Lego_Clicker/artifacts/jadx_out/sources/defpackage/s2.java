package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class s2 {
    public ColorStateList a = null;
    public PorterDuff.Mode b = null;
    public boolean c = false;
    public boolean d = false;
    public boolean e;
    public final TextView f;

    public /* synthetic */ s2(TextView textView) {
        this.f = textView;
    }

    public void a() {
        CompoundButton compoundButton = (CompoundButton) this.f;
        Drawable drawableA = s7.a(compoundButton);
        if (drawableA != null) {
            if (this.c || this.d) {
                Drawable drawableMutate = drawableA.mutate();
                if (this.c) {
                    ca.h(drawableMutate, this.a);
                }
                if (this.d) {
                    ca.i(drawableMutate, this.b);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public void b() {
        r2 r2Var = (r2) this.f;
        Drawable checkMarkDrawable = r2Var.getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.c || this.d) {
                Drawable drawableMutate = checkMarkDrawable.mutate();
                if (this.c) {
                    ca.h(drawableMutate, this.a);
                }
                if (this.d) {
                    ca.i(drawableMutate, this.b);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(r2Var.getDrawableState());
                }
                r2Var.setCheckMarkDrawable(drawableMutate);
            }
        }
    }

    public void c(AttributeSet attributeSet, int i) {
        int resourceId;
        int resourceId2;
        CompoundButton compoundButton = (CompoundButton) this.f;
        Context context = compoundButton.getContext();
        int[] iArr = fl.m;
        j5 j5VarQ = j5.q(context, attributeSet, iArr, i);
        TypedArray typedArray = (TypedArray) j5VarQ.a;
        os.g(compoundButton, compoundButton.getContext(), iArr, attributeSet, (TypedArray) j5VarQ.a, i);
        try {
            if (typedArray.hasValue(1) && (resourceId2 = typedArray.getResourceId(1, 0)) != 0) {
                try {
                    compoundButton.setButtonDrawable(d.w(compoundButton.getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(0)) {
                        compoundButton.setButtonDrawable(d.w(compoundButton.getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
                compoundButton.setButtonDrawable(d.w(compoundButton.getContext(), resourceId));
            }
            if (typedArray.hasValue(2)) {
                r7.c(compoundButton, j5VarQ.j(2));
            }
            if (typedArray.hasValue(3)) {
                r7.d(compoundButton, ea.b(typedArray.getInt(3, -1), null));
            }
            j5VarQ.s();
        } catch (Throwable th) {
            j5VarQ.s();
            throw th;
        }
    }
}
