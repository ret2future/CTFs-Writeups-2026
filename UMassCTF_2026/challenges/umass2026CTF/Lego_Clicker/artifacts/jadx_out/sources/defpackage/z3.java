package defpackage;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsSeekBar;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class z3 implements i1, eb {
    public static final int[] d = {R.attr.indeterminateDrawable, R.attr.progressDrawable};
    public final /* synthetic */ int a;
    public Object b;
    public Object c;

    public z3(EditText editText, int i) {
        this.a = i;
        switch (i) {
            case 6:
                this.b = editText;
                kb kbVar = new kb(editText);
                this.c = kbVar;
                editText.addTextChangedListener(kbVar);
                if (ya.b == null) {
                    synchronized (ya.a) {
                        try {
                            if (ya.b == null) {
                                ya yaVar = new ya();
                                try {
                                    ya.c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, ya.class.getClassLoader());
                                    break;
                                } catch (Throwable unused) {
                                }
                                ya.b = yaVar;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                editText.setEditableFactory(ya.b);
                return;
            default:
                this.b = editText;
                this.c = new e0(editText);
                return;
        }
    }

    public static int l(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            i3++;
            if (i3 == i2) {
                i4++;
                i3 = 0;
            } else if (i3 > i2) {
                i4++;
                i3 = 1;
            }
        }
        return i3 + 1 > i2 ? i4 + 1 : i4;
    }

    @Override // defpackage.eb
    public Object a() {
        return (hr) this.b;
    }

    @Override // defpackage.i1
    public boolean b(j1 j1Var, Menu menu) {
        return ((i1) this.b).b(j1Var, menu);
    }

    @Override // defpackage.i1
    public boolean c(j1 j1Var, MenuItem menuItem) {
        return ((i1) this.b).c(j1Var, menuItem);
    }

    @Override // defpackage.eb
    public boolean d(CharSequence charSequence, int i, int i2, dr drVar) {
        if ((drVar.c & 4) > 0) {
            return true;
        }
        if (((hr) this.b) == null) {
            this.b = new hr(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
        }
        ((fr) this.c).getClass();
        ((hr) this.b).setSpan(new er(drVar), i, i2, 33);
        return true;
    }

    @Override // defpackage.i1
    public boolean e(j1 j1Var, Menu menu) {
        ViewGroup viewGroup = ((n3) this.c).A;
        WeakHashMap weakHashMap = os.a;
        bs.c(viewGroup);
        return ((i1) this.b).e(j1Var, menu);
    }

    @Override // defpackage.i1
    public void f(j1 j1Var) {
        ((i1) this.b).f(j1Var);
        n3 n3Var = (n3) this.c;
        if (n3Var.w != null) {
            n3Var.l.getDecorView().removeCallbacks(n3Var.x);
        }
        if (n3Var.v != null) {
            dt dtVar = n3Var.y;
            if (dtVar != null) {
                dtVar.b();
            }
            dt dtVarA = os.a(n3Var.v);
            dtVarA.a(0.0f);
            n3Var.y = dtVarA;
            dtVarA.d(new c3(2, this));
        }
        n3Var.n.onSupportActionModeFinished(n3Var.u);
        n3Var.u = null;
        ViewGroup viewGroup = n3Var.A;
        WeakHashMap weakHashMap = os.a;
        bs.c(viewGroup);
        n3Var.J();
    }

    public void g(em emVar, tj tjVar) {
        ko koVar = (ko) this.b;
        ts tsVarA = (ts) koVar.getOrDefault(emVar, null);
        if (tsVarA == null) {
            tsVarA = ts.a();
            koVar.put(emVar, tsVarA);
        }
        tsVarA.c = tjVar;
        tsVarA.a |= 8;
    }

    public void h() {
        int[] iArr = (int[]) this.b;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.c = null;
    }

    public void i(int i) {
        int[] iArr = (int[]) this.b;
        if (iArr == null) {
            int[] iArr2 = new int[Math.max(i, 10) + 1];
            this.b = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i >= iArr.length) {
            int length = iArr.length;
            while (length <= i) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.b = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            int[] iArr4 = (int[]) this.b;
            Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
        }
    }

    public View j(int i, int i2, int i3, int i4) {
        View viewT;
        xr xrVar = (xr) this.c;
        ol olVar = (ol) this.b;
        int iD = olVar.d();
        int iC = olVar.c();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            switch (olVar.a) {
                case 0:
                    viewT = olVar.b.t(i);
                    break;
                default:
                    viewT = olVar.b.t(i);
                    break;
            }
            int iB = olVar.b(viewT);
            int iA = olVar.a(viewT);
            xrVar.b = iD;
            xrVar.c = iC;
            xrVar.d = iB;
            xrVar.e = iA;
            if (i3 != 0) {
                xrVar.a = i3;
                if (xrVar.a()) {
                    return viewT;
                }
            }
            if (i4 != 0) {
                xrVar.a = i4;
                if (xrVar.a()) {
                    view = viewT;
                }
            }
            i += i5;
        }
        return view;
    }

    public KeyListener k(KeyListener keyListener) {
        if (keyListener instanceof NumberKeyListener) {
            return keyListener;
        }
        ((z3) ((e0) this.c).b).getClass();
        if (keyListener instanceof db) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new db(keyListener);
    }

    public void m() {
        ((SparseIntArray) this.b).clear();
    }

    public boolean n(View view) {
        xr xrVar = (xr) this.c;
        ol olVar = (ol) this.b;
        int iD = olVar.d();
        int iC = olVar.c();
        int iB = olVar.b(view);
        int iA = olVar.a(view);
        xrVar.b = iD;
        xrVar.c = iC;
        xrVar.d = iB;
        xrVar.e = iA;
        xrVar.a = 24579;
        return xrVar.a();
    }

    public void o(AttributeSet attributeSet, int i) {
        boolean z = true;
        switch (this.a) {
            case 0:
                AbsSeekBar absSeekBar = (AbsSeekBar) this.b;
                j5 j5VarQ = j5.q(absSeekBar.getContext(), attributeSet, d, i);
                Drawable drawableL = j5VarQ.l(0);
                if (drawableL != null) {
                    if (drawableL instanceof AnimationDrawable) {
                        AnimationDrawable animationDrawable = (AnimationDrawable) drawableL;
                        int numberOfFrames = animationDrawable.getNumberOfFrames();
                        AnimationDrawable animationDrawable2 = new AnimationDrawable();
                        animationDrawable2.setOneShot(animationDrawable.isOneShot());
                        for (int i2 = 0; i2 < numberOfFrames; i2++) {
                            Drawable drawableY = y(animationDrawable.getFrame(i2), true);
                            drawableY.setLevel(10000);
                            animationDrawable2.addFrame(drawableY, animationDrawable.getDuration(i2));
                        }
                        animationDrawable2.setLevel(10000);
                        drawableL = animationDrawable2;
                    }
                    absSeekBar.setIndeterminateDrawable(drawableL);
                }
                Drawable drawableL2 = j5VarQ.l(1);
                if (drawableL2 != null) {
                    absSeekBar.setProgressDrawable(y(drawableL2, false));
                }
                j5VarQ.s();
                return;
            default:
                TypedArray typedArrayObtainStyledAttributes = ((EditText) this.b).getContext().obtainStyledAttributes(attributeSet, fl.i, i, 0);
                try {
                    if (typedArrayObtainStyledAttributes.hasValue(14)) {
                        z = typedArrayObtainStyledAttributes.getBoolean(14, true);
                        break;
                    }
                    typedArrayObtainStyledAttributes.recycle();
                    x(z);
                    return;
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes.recycle();
                    throw th;
                }
        }
    }

    public void p(int i, int i2) {
        int[] iArr = (int[]) this.b;
        if (iArr == null || i >= iArr.length) {
            return;
        }
        int i3 = i + i2;
        i(i3);
        int[] iArr2 = (int[]) this.b;
        System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
        Arrays.fill((int[]) this.b, i, i3, -1);
        ArrayList arrayList = (ArrayList) this.c;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            wo woVar = (wo) ((ArrayList) this.c).get(size);
            int i4 = woVar.a;
            if (i4 >= i) {
                woVar.a = i4 + i2;
            }
        }
    }

    public void q(int i, int i2) {
        int[] iArr = (int[]) this.b;
        if (iArr == null || i >= iArr.length) {
            return;
        }
        int i3 = i + i2;
        i(i3);
        int[] iArr2 = (int[]) this.b;
        System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
        int[] iArr3 = (int[]) this.b;
        Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
        ArrayList arrayList = (ArrayList) this.c;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            wo woVar = (wo) ((ArrayList) this.c).get(size);
            int i4 = woVar.a;
            if (i4 >= i) {
                if (i4 < i3) {
                    ((ArrayList) this.c).remove(size);
                } else {
                    woVar.a = i4 - i2;
                }
            }
        }
    }

    public ab r(InputConnection inputConnection, EditorInfo editorInfo) {
        InputConnection inputConnection2;
        e0 e0Var = (e0) this.c;
        if (inputConnection == null) {
            e0Var.getClass();
            inputConnection2 = null;
        } else {
            z3 z3Var = (z3) e0Var.b;
            z3Var.getClass();
            if (!(inputConnection instanceof ab)) {
                inputConnection = new ab(editorInfo, inputConnection, (EditText) z3Var.b);
            }
            inputConnection2 = inputConnection;
        }
        return (ab) inputConnection2;
    }

    public void s(fc fcVar) {
        Handler handler = (Handler) this.c;
        e0 e0Var = (e0) this.b;
        int i = fcVar.b;
        if (i == 0) {
            handler.post(new c1(e0Var, 4, fcVar.a));
        } else {
            handler.post(new q6(e0Var, i));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x0209, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0110 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0109 A[Catch: IOException -> 0x0091, XmlPullParserException -> 0x0094, TryCatch #2 {IOException -> 0x0091, XmlPullParserException -> 0x0094, blocks: (B:20:0x0062, B:97:0x0209, B:28:0x0074, B:29:0x0082, B:31:0x0087, B:38:0x0097, B:46:0x00b1, B:41:0x00a0, B:44:0x00a9, B:47:0x00bf, B:51:0x00ce, B:53:0x00d6, B:54:0x00e0, B:63:0x0109, B:64:0x0110, B:65:0x0128, B:57:0x00e9, B:59:0x00f1, B:60:0x00ff, B:66:0x0129, B:68:0x0131, B:69:0x013f, B:72:0x0149, B:73:0x0154, B:74:0x016c, B:75:0x016d, B:78:0x0177, B:79:0x0182, B:80:0x019a, B:81:0x019b, B:83:0x01a3, B:84:0x01ac, B:87:0x01b6, B:88:0x01c0, B:89:0x01d8, B:90:0x01d9, B:93:0x01e3, B:94:0x01ed, B:95:0x0205, B:96:0x0206), top: B:105:0x0062 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void t(android.content.Context r12, android.content.res.XmlResourceParser r13) {
        /*
            Method dump skipped, instruction units count: 608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z3.t(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    public String toString() {
        switch (this.a) {
            case 9:
                String str = "[ ";
                if (((mo) this.b) != null) {
                    for (int i = 0; i < 9; i++) {
                        str = str + ((mo) this.b).h[i] + " ";
                    }
                }
                return str + "] " + ((mo) this.b);
            default:
                return super.toString();
        }
    }

    public tj u(em emVar, int i) {
        ts tsVar;
        tj tjVar;
        ko koVar = (ko) this.b;
        int iD = koVar.d(emVar);
        if (iD >= 0 && (tsVar = (ts) koVar.i(iD)) != null) {
            int i2 = tsVar.a;
            if ((i2 & i) != 0) {
                int i3 = i2 & (~i);
                tsVar.a = i3;
                if (i == 4) {
                    tjVar = tsVar.b;
                } else if (i == 8) {
                    tjVar = tsVar.c;
                } else {
                    z6.f("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    koVar.h(iD);
                    tsVar.a = 0;
                    tsVar.b = null;
                    tsVar.c = null;
                    ts.d.c(tsVar);
                }
                return tjVar;
            }
        }
        return null;
    }

    public void v(em emVar) {
        ts tsVar = (ts) ((ko) this.b).getOrDefault(emVar, null);
        if (tsVar == null) {
            return;
        }
        tsVar.a &= -2;
    }

    public void w(em emVar) {
        rh rhVar = (rh) this.c;
        if (rhVar.a) {
            rhVar.a();
        }
        int i = rhVar.d - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            if (rhVar.a) {
                rhVar.a();
            }
            Object[] objArr = rhVar.c;
            Object obj = objArr[i];
            if (emVar == obj) {
                Object obj2 = rh.e;
                if (obj != obj2) {
                    objArr[i] = obj2;
                    rhVar.a = true;
                }
            } else {
                i--;
            }
        }
        ts tsVar = (ts) ((ko) this.b).remove(emVar);
        if (tsVar != null) {
            tsVar.a = 0;
            tsVar.b = null;
            tsVar.c = null;
            ts.d.c(tsVar);
        }
    }

    public void x(boolean z) {
        kb kbVar = (kb) ((z3) ((e0) this.c).b).c;
        if (kbVar.c != z) {
            if (kbVar.b != null) {
                va vaVarA = va.a();
                jb jbVar = kbVar.b;
                vaVarA.getClass();
                hb.i(jbVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = vaVarA.a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    vaVarA.b.remove(jbVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            kbVar.c = z;
            if (z) {
                kb.a(kbVar.a, va.a().b());
            }
        }
    }

    public Drawable y(Drawable drawable, boolean z) {
        if (!(drawable instanceof LayerDrawable)) {
            if (!(drawable instanceof BitmapDrawable)) {
                return drawable;
            }
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (((Bitmap) this.c) == null) {
                this.c = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        Drawable[] drawableArr = new Drawable[numberOfLayers];
        for (int i = 0; i < numberOfLayers; i++) {
            int id = layerDrawable.getId(i);
            drawableArr[i] = y(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
        }
        LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
        for (int i2 = 0; i2 < numberOfLayers; i2++) {
            layerDrawable2.setId(i2, layerDrawable.getId(i2));
            layerDrawable2.setLayerGravity(i2, layerDrawable.getLayerGravity(i2));
            layerDrawable2.setLayerWidth(i2, layerDrawable.getLayerWidth(i2));
            layerDrawable2.setLayerHeight(i2, layerDrawable.getLayerHeight(i2));
            layerDrawable2.setLayerInsetLeft(i2, layerDrawable.getLayerInsetLeft(i2));
            layerDrawable2.setLayerInsetRight(i2, layerDrawable.getLayerInsetRight(i2));
            layerDrawable2.setLayerInsetTop(i2, layerDrawable.getLayerInsetTop(i2));
            layerDrawable2.setLayerInsetBottom(i2, layerDrawable.getLayerInsetBottom(i2));
            layerDrawable2.setLayerInsetStart(i2, layerDrawable.getLayerInsetStart(i2));
            layerDrawable2.setLayerInsetEnd(i2, layerDrawable.getLayerInsetEnd(i2));
        }
        return layerDrawable2;
    }

    public /* synthetic */ z3(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    public z3(qk qkVar) {
        this.a = 9;
        this.c = qkVar;
    }

    public z3(AbsSeekBar absSeekBar) {
        this.a = 0;
        this.b = absSeekBar;
    }

    public /* synthetic */ z3(int i, boolean z) {
        this.a = i;
    }

    public z3(ol olVar) {
        this.a = 11;
        this.b = olVar;
        xr xrVar = new xr();
        xrVar.a = 0;
        this.c = xrVar;
    }

    public z3(int i) {
        this.a = i;
        switch (i) {
            case 12:
                this.b = new ko();
                this.c = new rh();
                break;
            default:
                this.b = new SparseIntArray();
                this.c = new SparseIntArray();
                break;
        }
    }

    public z3(n3 n3Var, i1 i1Var) {
        this.a = 1;
        this.c = n3Var;
        this.b = i1Var;
    }
}
