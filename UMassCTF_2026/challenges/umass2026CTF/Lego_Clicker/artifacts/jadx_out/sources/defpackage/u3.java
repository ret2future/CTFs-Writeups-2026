package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class u3 extends ImageButton {
    public final n2 a;
    public final v3 b;
    public boolean c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u3(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        rp.a(context);
        this.c = false;
        qp.a(this, getContext());
        n2 n2Var = new n2(this);
        this.a = n2Var;
        n2Var.d(attributeSet, i);
        v3 v3Var = new v3(this);
        this.b = v3Var;
        v3Var.b(attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        n2 n2Var = this.a;
        if (n2Var != null) {
            n2Var.a();
        }
        v3 v3Var = this.b;
        if (v3Var != null) {
            v3Var.a();
        }
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

    public ColorStateList getSupportImageTintList() {
        sp spVar;
        v3 v3Var = this.b;
        if (v3Var == null || (spVar = v3Var.b) == null) {
            return null;
        }
        return spVar.a;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        sp spVar;
        v3 v3Var = this.b;
        if (v3Var == null || (spVar = v3Var.b) == null) {
            return null;
        }
        return spVar.b;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return !(this.b.a.getBackground() instanceof RippleDrawable) && super.hasOverlappingRendering();
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

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        v3 v3Var = this.b;
        if (v3Var != null) {
            v3Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        v3 v3Var = this.b;
        if (v3Var != null && drawable != null && !this.c) {
            v3Var.c = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (v3Var != null) {
            v3Var.a();
            if (this.c) {
                return;
            }
            ImageView imageView = v3Var.a;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(v3Var.c);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i) {
        super.setImageLevel(i);
        this.c = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        v3 v3Var = this.b;
        ImageView imageView = v3Var.a;
        if (i != 0) {
            Drawable drawableW = d.w(imageView.getContext(), i);
            if (drawableW != null) {
                ea.a(drawableW);
            }
            imageView.setImageDrawable(drawableW);
        } else {
            imageView.setImageDrawable(null);
        }
        v3Var.a();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        v3 v3Var = this.b;
        if (v3Var != null) {
            v3Var.a();
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

    public void setSupportImageTintList(ColorStateList colorStateList) {
        v3 v3Var = this.b;
        if (v3Var != null) {
            if (v3Var.b == null) {
                v3Var.b = new sp();
            }
            sp spVar = v3Var.b;
            spVar.a = colorStateList;
            spVar.d = true;
            v3Var.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        v3 v3Var = this.b;
        if (v3Var != null) {
            if (v3Var.b == null) {
                v3Var.b = new sp();
            }
            sp spVar = v3Var.b;
            spVar.b = mode;
            spVar.c = true;
            v3Var.a();
        }
    }
}
