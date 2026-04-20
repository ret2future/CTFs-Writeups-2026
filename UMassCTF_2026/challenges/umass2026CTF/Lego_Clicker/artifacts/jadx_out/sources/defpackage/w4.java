package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class w4 {
    public final TextView a;
    public sp b;
    public sp c;
    public sp d;
    public sp e;
    public sp f;
    public sp g;
    public sp h;
    public final f5 i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    public w4(TextView textView) {
        this.a = textView;
        this.i = new f5(textView);
    }

    public static sp c(Context context, q3 q3Var, int i) {
        ColorStateList colorStateListG;
        synchronized (q3Var) {
            colorStateListG = q3Var.a.g(context, i);
        }
        if (colorStateListG == null) {
            return null;
        }
        sp spVar = new sp();
        spVar.d = true;
        spVar.a = colorStateListG;
        return spVar;
    }

    public static void h(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30 || inputConnection == null) {
            return;
        }
        CharSequence text = textView.getText();
        if (i >= 30) {
            oa.a(editorInfo, text);
            return;
        }
        text.getClass();
        if (i >= 30) {
            oa.a(editorInfo, text);
            return;
        }
        int i2 = editorInfo.initialSelStart;
        int i3 = editorInfo.initialSelEnd;
        int i4 = i2 > i3 ? i3 : i2;
        if (i2 <= i3) {
            i2 = i3;
        }
        int length = text.length();
        if (i4 < 0 || i2 > length) {
            d.V(editorInfo, null, 0, 0);
            return;
        }
        int i5 = editorInfo.inputType & 4095;
        if (i5 == 129 || i5 == 225 || i5 == 18) {
            d.V(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            d.V(editorInfo, text, i4, i2);
            return;
        }
        int i6 = i2 - i4;
        int i7 = i6 > 1024 ? 0 : i6;
        int i8 = 2048 - i7;
        int iMin = Math.min(text.length() - i2, i8 - Math.min(i4, (int) (((double) i8) * 0.8d)));
        int iMin2 = Math.min(i4, i8 - iMin);
        int i9 = i4 - iMin2;
        if (Character.isLowSurrogate(text.charAt(i9))) {
            i9++;
            iMin2--;
        }
        if (Character.isHighSurrogate(text.charAt((i2 + iMin) - 1))) {
            iMin--;
        }
        int i10 = iMin2 + i7;
        d.V(editorInfo, i7 != i6 ? TextUtils.concat(text.subSequence(i9, i9 + iMin2), text.subSequence(i2, iMin + i2)) : text.subSequence(i9, i10 + iMin + i9), iMin2, i10);
    }

    public final void a(Drawable drawable, sp spVar) {
        if (drawable == null || spVar == null) {
            return;
        }
        q3.d(drawable, spVar, this.a.getDrawableState());
    }

    public final void b() {
        sp spVar = this.b;
        TextView textView = this.a;
        if (spVar != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f == null && this.g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f);
        a(compoundDrawablesRelative[2], this.g);
    }

    public final ColorStateList d() {
        sp spVar = this.h;
        if (spVar != null) {
            return spVar.a;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        sp spVar = this.h;
        if (spVar != null) {
            return spVar.b;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:231:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:246:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(android.util.AttributeSet r24, int r25) {
        /*
            Method dump skipped, instruction units count: 980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w4.f(android.util.AttributeSet, int):void");
    }

    public final void g(Context context, int i) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, fl.v);
        j5 j5Var = new j5(context, typedArrayObtainStyledAttributes);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(14);
        TextView textView = this.a;
        if (zHasValue) {
            textView.setAllCaps(typedArrayObtainStyledAttributes.getBoolean(14, false));
        }
        if (typedArrayObtainStyledAttributes.hasValue(0) && typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        n(context, j5Var);
        if (Build.VERSION.SDK_INT >= 26 && typedArrayObtainStyledAttributes.hasValue(13) && (string = typedArrayObtainStyledAttributes.getString(13)) != null) {
            u4.d(textView, string);
        }
        j5Var.s();
        Typeface typeface = this.l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.j);
        }
    }

    public final void i(int i, int i2, int i3, int i4) {
        f5 f5Var = this.i;
        if (f5Var.j()) {
            DisplayMetrics displayMetrics = f5Var.j.getResources().getDisplayMetrics();
            f5Var.k(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (f5Var.h()) {
                f5Var.a();
            }
        }
    }

    public final void j(int[] iArr, int i) {
        f5 f5Var = this.i;
        if (f5Var.j()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = f5Var.j.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArrCopyOf[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                f5Var.f = f5.b(iArrCopyOf);
                if (!f5Var.i()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                f5Var.g = false;
            }
            if (f5Var.h()) {
                f5Var.a();
            }
        }
    }

    public final void k(int i) {
        f5 f5Var = this.i;
        if (f5Var.j()) {
            if (i == 0) {
                f5Var.a = 0;
                f5Var.d = -1.0f;
                f5Var.e = -1.0f;
                f5Var.c = -1.0f;
                f5Var.f = new int[0];
                f5Var.b = false;
                return;
            }
            if (i != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i);
            }
            DisplayMetrics displayMetrics = f5Var.j.getResources().getDisplayMetrics();
            f5Var.k(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (f5Var.h()) {
                f5Var.a();
            }
        }
    }

    public final void l(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new sp();
        }
        sp spVar = this.h;
        spVar.a = colorStateList;
        spVar.d = colorStateList != null;
        this.b = spVar;
        this.c = spVar;
        this.d = spVar;
        this.e = spVar;
        this.f = spVar;
        this.g = spVar;
    }

    public final void m(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new sp();
        }
        sp spVar = this.h;
        spVar.b = mode;
        spVar.c = mode != null;
        this.b = spVar;
        this.c = spVar;
        this.d = spVar;
        this.e = spVar;
        this.f = spVar;
        this.g = spVar;
    }

    public final void n(Context context, j5 j5Var) {
        String string;
        int i = this.j;
        TypedArray typedArray = (TypedArray) j5Var.a;
        this.j = typedArray.getInt(2, i);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            int i3 = typedArray.getInt(11, -1);
            this.k = i3;
            if (i3 != -1) {
                this.j &= 2;
            }
        }
        if (!typedArray.hasValue(10) && !typedArray.hasValue(12)) {
            if (typedArray.hasValue(1)) {
                this.m = false;
                int i4 = typedArray.getInt(1, 1);
                if (i4 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (i4 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (i4 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i5 = typedArray.hasValue(12) ? 12 : 10;
        int i6 = this.k;
        int i7 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceM = j5Var.m(i5, this.j, new r4(this, i6, i7, new WeakReference(this.a)));
                if (typefaceM != null) {
                    if (i2 < 28 || this.k == -1) {
                        this.l = typefaceM;
                    } else {
                        this.l = v4.a(Typeface.create(typefaceM, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (string = typedArray.getString(i5)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(string, this.j);
        } else {
            this.l = v4.a(Typeface.create(string, 0), this.k, (this.j & 2) != 0);
        }
    }
}
