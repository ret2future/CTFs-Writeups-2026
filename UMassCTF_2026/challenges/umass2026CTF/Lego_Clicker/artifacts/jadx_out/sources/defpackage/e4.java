package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.example.LegoClicker.R;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class e4 extends z3 {
    public final d4 e;
    public Drawable f;
    public ColorStateList g;
    public PorterDuff.Mode h;
    public boolean i;
    public boolean j;

    public e4(d4 d4Var) {
        super(d4Var);
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.e = d4Var;
    }

    public final void A(Canvas canvas) {
        if (this.f != null) {
            int max = this.e.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f.getIntrinsicWidth();
                int intrinsicHeight = this.f.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f.setBounds(-i, -i2, i, i2);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    @Override // defpackage.z3
    public final void o(AttributeSet attributeSet, int i) {
        super.o(attributeSet, R.attr.seekBarStyle);
        d4 d4Var = this.e;
        Context context = d4Var.getContext();
        int[] iArr = fl.g;
        j5 j5VarQ = j5.q(context, attributeSet, iArr, R.attr.seekBarStyle);
        TypedArray typedArray = (TypedArray) j5VarQ.a;
        os.g(d4Var, d4Var.getContext(), iArr, attributeSet, (TypedArray) j5VarQ.a, R.attr.seekBarStyle);
        Drawable drawableL = j5VarQ.l(0);
        if (drawableL != null) {
            d4Var.setThumb(drawableL);
        }
        Drawable drawableK = j5VarQ.k(1);
        Drawable drawable = this.f;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f = drawableK;
        if (drawableK != null) {
            drawableK.setCallback(d4Var);
            da.b(drawableK, d4Var.getLayoutDirection());
            if (drawableK.isStateful()) {
                drawableK.setState(d4Var.getDrawableState());
            }
            z();
        }
        d4Var.invalidate();
        if (typedArray.hasValue(3)) {
            this.h = ea.b(typedArray.getInt(3, -1), this.h);
            this.j = true;
        }
        if (typedArray.hasValue(2)) {
            this.g = j5VarQ.j(2);
            this.i = true;
        }
        j5VarQ.s();
        z();
    }

    public final void z() {
        Drawable drawable = this.f;
        if (drawable != null) {
            if (this.i || this.j) {
                Drawable drawableMutate = drawable.mutate();
                this.f = drawableMutate;
                if (this.i) {
                    ca.h(drawableMutate, this.g);
                }
                if (this.j) {
                    ca.i(this.f, this.h);
                }
                if (this.f.isStateful()) {
                    this.f.setState(this.e.getDrawableState());
                }
            }
        }
    }
}
