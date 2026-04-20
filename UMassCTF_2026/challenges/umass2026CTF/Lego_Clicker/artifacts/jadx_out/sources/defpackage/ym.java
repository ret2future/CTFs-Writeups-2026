package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import com.example.LegoClicker.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ym {
    public static ym g;
    public WeakHashMap a;
    public final WeakHashMap b = new WeakHashMap(0);
    public TypedValue c;
    public boolean d;
    public p3 e;
    public static final PorterDuff.Mode f = PorterDuff.Mode.SRC_IN;
    public static final xm h = new xm(6);

    public static synchronized ym c() {
        try {
            if (g == null) {
                g = new ym();
            }
        } catch (Throwable th) {
            throw th;
        }
        return g;
    }

    public static synchronized PorterDuffColorFilter f(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        xm xmVar = h;
        xmVar.getClass();
        int i2 = (31 + i) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) xmVar.a(Integer.valueOf(mode.hashCode() + i2));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
        }
        return porterDuffColorFilter;
    }

    public final void a(Context context, int i, ColorStateList colorStateList) {
        if (this.a == null) {
            this.a = new WeakHashMap();
        }
        po poVar = (po) this.a.get(context);
        if (poVar == null) {
            poVar = new po();
            this.a.put(context, poVar);
        }
        int i2 = poVar.c;
        if (i2 != 0) {
            int[] iArr = poVar.a;
            if (i <= iArr[i2 - 1]) {
                int iB = d.b(i2, i, iArr);
                if (iB >= 0) {
                    poVar.b[iB] = colorStateList;
                    return;
                }
                int i3 = ~iB;
                int i4 = poVar.c;
                if (i3 < i4) {
                    Object[] objArr = poVar.b;
                    if (objArr[i3] == po.d) {
                        poVar.a[i3] = i;
                        objArr[i3] = colorStateList;
                        return;
                    }
                }
                if (i4 >= poVar.a.length) {
                    int i5 = (i4 + 1) * 4;
                    int i6 = 4;
                    while (true) {
                        if (i6 >= 32) {
                            break;
                        }
                        int i7 = (1 << i6) - 12;
                        if (i5 <= i7) {
                            i5 = i7;
                            break;
                        }
                        i6++;
                    }
                    int i8 = i5 / 4;
                    int[] iArr2 = new int[i8];
                    Object[] objArr2 = new Object[i8];
                    int[] iArr3 = poVar.a;
                    System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
                    Object[] objArr3 = poVar.b;
                    System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
                    poVar.a = iArr2;
                    poVar.b = objArr2;
                }
                int i9 = poVar.c - i3;
                if (i9 != 0) {
                    int[] iArr4 = poVar.a;
                    int i10 = i3 + 1;
                    System.arraycopy(iArr4, i3, iArr4, i10, i9);
                    Object[] objArr4 = poVar.b;
                    System.arraycopy(objArr4, i3, objArr4, i10, poVar.c - i3);
                }
                poVar.a[i3] = i;
                poVar.b[i3] = colorStateList;
                poVar.c++;
                return;
            }
        }
        if (i2 >= poVar.a.length) {
            int i11 = (i2 + 1) * 4;
            int i12 = 4;
            while (true) {
                if (i12 >= 32) {
                    break;
                }
                int i13 = (1 << i12) - 12;
                if (i11 <= i13) {
                    i11 = i13;
                    break;
                }
                i12++;
            }
            int i14 = i11 / 4;
            int[] iArr5 = new int[i14];
            Object[] objArr5 = new Object[i14];
            int[] iArr6 = poVar.a;
            System.arraycopy(iArr6, 0, iArr5, 0, iArr6.length);
            Object[] objArr6 = poVar.b;
            System.arraycopy(objArr6, 0, objArr5, 0, objArr6.length);
            poVar.a = iArr5;
            poVar.b = objArr5;
        }
        poVar.a[i2] = i;
        poVar.b[i2] = colorStateList;
        poVar.c = i2 + 1;
    }

    public final Drawable b(Context context, int i) {
        LayerDrawable layerDrawableC;
        Object obj;
        Drawable drawableNewDrawable;
        if (this.c == null) {
            this.c = new TypedValue();
        }
        TypedValue typedValue = this.c;
        context.getResources().getValue(i, typedValue, true);
        long j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        synchronized (this) {
            rh rhVar = (rh) this.b.get(context);
            layerDrawableC = null;
            if (rhVar != null) {
                int iC = d.c(rhVar.b, rhVar.d, j);
                if (iC < 0 || (obj = rhVar.c[iC]) == rh.e) {
                    obj = null;
                }
                WeakReference weakReference = (WeakReference) obj;
                if (weakReference != null) {
                    Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
                    if (constantState != null) {
                        drawableNewDrawable = constantState.newDrawable(context.getResources());
                    } else {
                        int iC2 = d.c(rhVar.b, rhVar.d, j);
                        if (iC2 >= 0) {
                            Object[] objArr = rhVar.c;
                            Object obj2 = objArr[iC2];
                            Object obj3 = rh.e;
                            if (obj2 != obj3) {
                                objArr[iC2] = obj3;
                                rhVar.a = true;
                            }
                        }
                    }
                }
            }
            drawableNewDrawable = null;
        }
        if (drawableNewDrawable != null) {
            return drawableNewDrawable;
        }
        if (this.e != null) {
            if (i == R.drawable.abc_cab_background_top_material) {
                layerDrawableC = new LayerDrawable(new Drawable[]{d(context, R.drawable.abc_cab_background_internal_bg), d(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
            } else if (i == R.drawable.abc_ratingbar_material) {
                layerDrawableC = p3.c(this, context, R.dimen.abc_star_big);
            } else if (i == R.drawable.abc_ratingbar_indicator_material) {
                layerDrawableC = p3.c(this, context, R.dimen.abc_star_medium);
            } else if (i == R.drawable.abc_ratingbar_small_material) {
                layerDrawableC = p3.c(this, context, R.dimen.abc_star_small);
            }
        }
        if (layerDrawableC == null) {
            return layerDrawableC;
        }
        layerDrawableC.setChangingConfigurations(typedValue.changingConfigurations);
        synchronized (this) {
            try {
                Drawable.ConstantState constantState2 = layerDrawableC.getConstantState();
                if (constantState2 == null) {
                    return layerDrawableC;
                }
                rh rhVar2 = (rh) this.b.get(context);
                if (rhVar2 == null) {
                    rhVar2 = new rh();
                    this.b.put(context, rhVar2);
                }
                rhVar2.b(j, new WeakReference(constantState2));
                return layerDrawableC;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized Drawable d(Context context, int i) {
        return e(context, i, false);
    }

    public final synchronized Drawable e(Context context, int i, boolean z) {
        Drawable drawableB;
        try {
            if (!this.d) {
                this.d = true;
                Drawable drawableD = d(context, R.drawable.abc_vector_test);
                if (drawableD == null || !"android.graphics.drawable.VectorDrawable".equals(drawableD.getClass().getName())) {
                    this.d = false;
                    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
                }
            }
            drawableB = b(context, i);
            if (drawableB == null) {
                drawableB = y8.b(context, i);
            }
            if (drawableB != null) {
                drawableB = h(context, i, z, drawableB);
            }
            if (drawableB != null) {
                ea.a(drawableB);
            }
        } catch (Throwable th) {
            throw th;
        }
        return drawableB;
    }

    public final synchronized ColorStateList g(Context context, int i) {
        ColorStateList colorStateList;
        po poVar;
        Object obj;
        WeakHashMap weakHashMap = this.a;
        ColorStateList colorStateListD = null;
        if (weakHashMap == null || (poVar = (po) weakHashMap.get(context)) == null) {
            colorStateList = null;
        } else {
            int iB = d.b(poVar.c, i, poVar.a);
            if (iB < 0 || (obj = poVar.b[iB]) == po.d) {
                obj = null;
            }
            colorStateList = (ColorStateList) obj;
        }
        if (colorStateList == null) {
            p3 p3Var = this.e;
            if (p3Var != null) {
                colorStateListD = p3Var.d(context, i);
            }
            if (colorStateListD != null) {
                a(context, i, colorStateListD);
            }
            colorStateList = colorStateListD;
        }
        return colorStateList;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.drawable.Drawable h(android.content.Context r9, int r10, boolean r11, android.graphics.drawable.Drawable r12) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ym.h(android.content.Context, int, boolean, android.graphics.drawable.Drawable):android.graphics.drawable.Drawable");
    }
}
