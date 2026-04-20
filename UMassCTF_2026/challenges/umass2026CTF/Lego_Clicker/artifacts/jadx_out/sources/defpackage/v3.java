package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class v3 {
    public final ImageView a;
    public sp b;
    public int c = 0;

    public v3(ImageView imageView) {
        this.a = imageView;
    }

    public final void a() {
        sp spVar;
        ImageView imageView = this.a;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            ea.a(drawable);
        }
        if (drawable == null || (spVar = this.b) == null) {
            return;
        }
        q3.d(drawable, spVar, imageView.getDrawableState());
    }

    public final void b(AttributeSet attributeSet, int i) {
        int resourceId;
        ImageView imageView = this.a;
        Context context = imageView.getContext();
        int[] iArr = fl.f;
        j5 j5VarQ = j5.q(context, attributeSet, iArr, i);
        TypedArray typedArray = (TypedArray) j5VarQ.a;
        os.g(imageView, imageView.getContext(), iArr, attributeSet, (TypedArray) j5VarQ.a, i);
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null && (resourceId = typedArray.getResourceId(1, -1)) != -1 && (drawable = d.w(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                ea.a(drawable);
            }
            if (typedArray.hasValue(2)) {
                bf.c(imageView, j5VarQ.j(2));
            }
            if (typedArray.hasValue(3)) {
                bf.d(imageView, ea.b(typedArray.getInt(3, -1), null));
            }
            j5VarQ.s();
        } catch (Throwable th) {
            j5VarQ.s();
            throw th;
        }
    }
}
