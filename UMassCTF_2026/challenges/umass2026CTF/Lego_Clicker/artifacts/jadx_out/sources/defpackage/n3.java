package defpackage;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.ContentFrameLayout;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class n3 extends z2 implements ei, LayoutInflater.Factory2 {
    public static final ko h0 = new ko();
    public static final int[] i0 = {R.attr.windowBackground};
    public static final boolean j0 = !"robolectric".equals(Build.FINGERPRINT);
    public ViewGroup A;
    public TextView B;
    public View C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public m3[] L;
    public m3 M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public Configuration R;
    public final int S;
    public int T;
    public int U;
    public boolean V;
    public i3 W;
    public i3 X;
    public boolean Y;
    public int Z;
    public boolean b0;
    public Rect c0;
    public Rect d0;
    public i5 e0;
    public OnBackInvokedDispatcher f0;
    public OnBackInvokedCallback g0;
    public final Object j;
    public final Context k;
    public Window l;
    public h3 m;
    public final p2 n;
    public o0 o;
    public fp p;
    public CharSequence q;
    public ActionBarOverlayLayout r;
    public b3 s;
    public b3 t;
    public j1 u;
    public ActionBarContextView v;
    public PopupWindow w;
    public a3 x;
    public boolean z;
    public dt y = null;
    public final a3 a0 = new a3(this, 0);

    public n3(Context context, Window window, p2 p2Var, Object obj) {
        l2 l2Var;
        this.S = -100;
        this.k = context;
        this.n = p2Var;
        this.j = obj;
        if (obj instanceof Dialog) {
            while (context != null) {
                if (!(context instanceof l2)) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    l2Var = (l2) context;
                    break;
                }
            }
            l2Var = null;
            if (l2Var != null) {
                this.S = ((n3) l2Var.getDelegate()).S;
            }
        }
        if (this.S == -100) {
            String name = this.j.getClass().getName();
            ko koVar = h0;
            Integer num = (Integer) koVar.getOrDefault(name, null);
            if (num != null) {
                this.S = num.intValue();
                koVar.remove(this.j.getClass().getName());
            }
        }
        if (window != null) {
            p(window);
        }
        q3.c();
    }

    public static oh q(Context context) {
        oh ohVar;
        oh ohVar2;
        if (Build.VERSION.SDK_INT >= 33 || (ohVar = z2.c) == null) {
            return null;
        }
        ph phVar = ohVar.a;
        oh ohVarB = e3.b(context.getApplicationContext().getResources().getConfiguration());
        if (phVar.a.isEmpty()) {
            ohVar2 = oh.b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (i < ohVarB.a.a.size() + phVar.a.size()) {
                Locale locale = i < phVar.a.size() ? phVar.a.get(i) : ohVarB.a.a.get(i - phVar.a.size());
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
                i++;
            }
            ohVar2 = new oh(new ph(nh.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        return ohVar2.a.a.isEmpty() ? ohVarB : ohVar2;
    }

    public static Configuration u(Context context, int i, oh ohVar, Configuration configuration, boolean z) {
        int i2 = i != 1 ? i != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        if (ohVar != null) {
            e3.d(configuration2, ohVar);
        }
        return configuration2;
    }

    public final m3 A(int i) {
        m3[] m3VarArr = this.L;
        if (m3VarArr == null || m3VarArr.length <= i) {
            m3[] m3VarArr2 = new m3[i + 1];
            if (m3VarArr != null) {
                System.arraycopy(m3VarArr, 0, m3VarArr2, 0, m3VarArr.length);
            }
            this.L = m3VarArr2;
            m3VarArr = m3VarArr2;
        }
        m3 m3Var = m3VarArr[i];
        if (m3Var != null) {
            return m3Var;
        }
        m3 m3Var2 = new m3();
        m3Var2.a = i;
        m3Var2.n = false;
        m3VarArr[i] = m3Var2;
        return m3Var2;
    }

    public final void B() {
        x();
        if (this.F && this.o == null) {
            Object obj = this.j;
            if (obj instanceof Activity) {
                this.o = new st((Activity) obj, this.G);
            } else if (obj instanceof Dialog) {
                this.o = new st((Dialog) obj);
            }
            o0 o0Var = this.o;
            if (o0Var != null) {
                o0Var.l(this.b0);
            }
        }
    }

    public final void C(int i) {
        this.Z = (1 << i) | this.Z;
        if (this.Y) {
            return;
        }
        View decorView = this.l.getDecorView();
        WeakHashMap weakHashMap = os.a;
        decorView.postOnAnimation(this.a0);
        this.Y = true;
    }

    public final int D(Context context, int i) {
        if (i != -100) {
            if (i != -1) {
                if (i != 0) {
                    if (i != 1 && i != 2) {
                        if (i != 3) {
                            z6.o("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                            return 0;
                        }
                        if (this.X == null) {
                            this.X = new i3(this, context);
                        }
                        return this.X.e();
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() != 0) {
                    return z(context).e();
                }
            }
            return i;
        }
        return -1;
    }

    public final boolean E() {
        boolean z = this.N;
        this.N = false;
        m3 m3VarA = A(0);
        if (!m3VarA.m) {
            j1 j1Var = this.u;
            if (j1Var != null) {
                j1Var.a();
                return true;
            }
            B();
            o0 o0Var = this.o;
            if (o0Var == null || !o0Var.b()) {
                return false;
            }
        } else if (!z) {
            t(m3VarA, true);
            return true;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0176, code lost:
    
        if (r2.f.getCount() > 0) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F(defpackage.m3 r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.F(m3, android.view.KeyEvent):void");
    }

    public final boolean G(m3 m3Var, int i, KeyEvent keyEvent) {
        gi giVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((m3Var.k || H(m3Var, keyEvent)) && (giVar = m3Var.h) != null) {
            return giVar.performShortcut(i, keyEvent, 1);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean H(defpackage.m3 r13, android.view.KeyEvent r14) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.H(m3, android.view.KeyEvent):boolean");
    }

    public final void I() {
        if (this.z) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void J() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = false;
            if (this.f0 != null && (A(0).m || this.u != null)) {
                z = true;
            }
            if (z && this.g0 == null) {
                this.g0 = g3.b(this.f0, this);
            } else {
                if (z || (onBackInvokedCallback = this.g0) == null) {
                    return;
                }
                g3.c(this.f0, onBackInvokedCallback);
                this.g0 = null;
            }
        }
    }

    @Override // defpackage.z2
    public final void a() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.k);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof n3) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // defpackage.z2
    public final void b() {
        if (this.o != null) {
            B();
            if (this.o.f()) {
                return;
            }
            C(0);
        }
    }

    @Override // defpackage.z2
    public final void d() throws IllegalAccessException {
        String strG;
        this.O = true;
        o(false, true);
        y();
        Object obj = this.j;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strG = hb.G(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
                strG = null;
            }
            if (strG != null) {
                o0 o0Var = this.o;
                if (o0Var == null) {
                    this.b0 = true;
                } else {
                    o0Var.l(true);
                }
            }
            synchronized (z2.h) {
                z2.f(this);
                z2.g.add(new WeakReference(this));
            }
        }
        this.R = new Configuration(this.k.getResources().getConfiguration());
        this.P = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // defpackage.z2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.j
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = defpackage.z2.h
            monitor-enter(r0)
            defpackage.z2.f(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r3
        L11:
            boolean r0 = r3.Y
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.l
            android.view.View r0 = r0.getDecorView()
            a3 r1 = r3.a0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.Q = r0
            int r0 = r3.S
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.j
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            ko r0 = defpackage.n3.h0
            java.lang.Object r1 = r3.j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.S
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            ko r0 = defpackage.n3.h0
            java.lang.Object r1 = r3.j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            o0 r0 = r3.o
            if (r0 == 0) goto L63
            r0.h()
        L63:
            i3 r0 = r3.W
            if (r0 == 0) goto L6a
            r0.c()
        L6a:
            i3 r3 = r3.X
            if (r3 == 0) goto L71
            r3.c()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.e():void");
    }

    @Override // defpackage.z2
    public final boolean g(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.J && i == 108) {
            return false;
        }
        if (this.F && i == 1) {
            this.F = false;
        }
        if (i == 1) {
            I();
            this.J = true;
            return true;
        }
        if (i == 2) {
            I();
            this.D = true;
            return true;
        }
        if (i == 5) {
            I();
            this.E = true;
            return true;
        }
        if (i == 10) {
            I();
            this.H = true;
            return true;
        }
        if (i == 108) {
            I();
            this.F = true;
            return true;
        }
        if (i != 109) {
            return this.l.requestFeature(i);
        }
        I();
        this.G = true;
        return true;
    }

    @Override // defpackage.z2
    public final void h(int i) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.k).inflate(i, viewGroup);
        this.m.a(this.l.getCallback());
    }

    @Override // defpackage.z2
    public final void i(View view) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.m.a(this.l.getCallback());
    }

    @Override // defpackage.z2
    public final void j(View view, ViewGroup.LayoutParams layoutParams) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.m.a(this.l.getCallback());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
    
        if (r6.j() != false) goto L20;
     */
    @Override // defpackage.ei
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(defpackage.gi r6) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.k(gi):void");
    }

    @Override // defpackage.z2
    public final void l(CharSequence charSequence) {
        this.q = charSequence;
        ActionBarOverlayLayout actionBarOverlayLayout = this.r;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setWindowTitle(charSequence);
            return;
        }
        o0 o0Var = this.o;
        if (o0Var != null) {
            o0Var.n(charSequence);
            return;
        }
        TextView textView = this.B;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01a5  */
    @Override // defpackage.z2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.j1 m(defpackage.i1 r9) {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.m(i1):j1");
    }

    @Override // defpackage.ei
    public final boolean n(gi giVar, MenuItem menuItem) {
        m3 m3Var;
        Window.Callback callback = this.l.getCallback();
        if (callback != null && !this.Q) {
            gi giVarK = giVar.k();
            m3[] m3VarArr = this.L;
            int length = m3VarArr != null ? m3VarArr.length : 0;
            int i = 0;
            while (true) {
                if (i < length) {
                    m3Var = m3VarArr[i];
                    if (m3Var != null && m3Var.h == giVarK) {
                        break;
                    }
                    i++;
                } else {
                    m3Var = null;
                    break;
                }
            }
            if (m3Var != null) {
                return callback.onMenuItemSelected(m3Var.a, menuItem);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean o(boolean r18, boolean r19) throws java.lang.IllegalAccessException {
        /*
            Method dump skipped, instruction units count: 639
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.o(boolean, boolean):boolean");
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Context a9Var;
        View b4Var;
        View view2 = null;
        if (this.e0 == null) {
            int[] iArr = fl.j;
            Context context2 = this.k;
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(iArr);
            String string = typedArrayObtainStyledAttributes.getString(116);
            typedArrayObtainStyledAttributes.recycle();
            if (string == null) {
                this.e0 = new i5();
            } else {
                try {
                    this.e0 = (i5) context2.getClassLoader().loadClass(string).getDeclaredConstructor(null).newInstance(null);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.e0 = new i5();
                }
            }
        }
        i5 i5Var = this.e0;
        int i = pr.a;
        i5Var.getClass();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, fl.x, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(4, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes2.recycle();
        a9Var = (resourceId == 0 || ((context instanceof a9) && ((a9) context).a == resourceId)) ? context : new a9(context, resourceId);
        str.getClass();
        switch (str) {
            case "RatingBar":
                b4Var = new b4(a9Var, attributeSet);
                break;
            case "CheckedTextView":
                b4Var = new r2(a9Var, attributeSet);
                break;
            case "MultiAutoCompleteTextView":
                b4Var = new x3(a9Var, attributeSet);
                break;
            case "TextView":
                b4Var = new a5(a9Var, attributeSet);
                break;
            case "ImageButton":
                b4Var = new u3(a9Var, attributeSet, com.example.LegoClicker.R.attr.imageButtonStyle);
                break;
            case "SeekBar":
                b4Var = new d4(a9Var, attributeSet);
                break;
            case "Spinner":
                b4Var = new p4(a9Var, attributeSet);
                break;
            case "RadioButton":
                b4Var = new a4(a9Var, attributeSet);
                break;
            case "ToggleButton":
                b4Var = new g5(a9Var, attributeSet);
                break;
            case "ImageView":
                b4Var = new w3(a9Var, attributeSet, 0);
                break;
            case "AutoCompleteTextView":
                b4Var = new m2(a9Var, attributeSet, com.example.LegoClicker.R.attr.autoCompleteTextViewStyle);
                break;
            case "CheckBox":
                b4Var = new q2(a9Var, attributeSet);
                break;
            case "EditText":
                b4Var = new s3(a9Var, attributeSet);
                break;
            case "Button":
                b4Var = new o2(a9Var, attributeSet);
                break;
            default:
                b4Var = null;
                break;
        }
        if (b4Var == null && context != a9Var) {
            Object[] objArr = i5Var.a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = a9Var;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i2 = 0;
                    while (true) {
                        String[] strArr = i5.g;
                        if (i2 < 3) {
                            View viewA = i5Var.a(a9Var, str, strArr[i2]);
                            if (viewA != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view2 = viewA;
                            } else {
                                i2++;
                            }
                        } else {
                            objArr[0] = null;
                            objArr[1] = null;
                        }
                    }
                } else {
                    View viewA2 = i5Var.a(a9Var, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewA2;
                }
            } catch (Exception unused) {
                objArr[0] = null;
                objArr[1] = null;
            } catch (Throwable th2) {
                objArr[0] = null;
                objArr[1] = null;
                throw th2;
            }
            b4Var = view2;
        }
        if (b4Var != null) {
            Context context3 = b4Var.getContext();
            if ((context3 instanceof ContextWrapper) && b4Var.hasOnClickListeners()) {
                TypedArray typedArrayObtainStyledAttributes3 = context3.obtainStyledAttributes(attributeSet, i5.c);
                String string2 = typedArrayObtainStyledAttributes3.getString(0);
                if (string2 != null) {
                    b4Var.setOnClickListener(new h5(b4Var, string2));
                }
                typedArrayObtainStyledAttributes3.recycle();
            }
            if (Build.VERSION.SDK_INT <= 28) {
                TypedArray typedArrayObtainStyledAttributes4 = a9Var.obtainStyledAttributes(attributeSet, i5.d);
                if (typedArrayObtainStyledAttributes4.hasValue(0)) {
                    boolean z = typedArrayObtainStyledAttributes4.getBoolean(0, false);
                    WeakHashMap weakHashMap = os.a;
                    new zr(com.example.LegoClicker.R.id.tag_accessibility_heading, Boolean.class, 0, 28, 2).d(b4Var, Boolean.valueOf(z));
                }
                typedArrayObtainStyledAttributes4.recycle();
                TypedArray typedArrayObtainStyledAttributes5 = a9Var.obtainStyledAttributes(attributeSet, i5.e);
                if (typedArrayObtainStyledAttributes5.hasValue(0)) {
                    os.i(b4Var, typedArrayObtainStyledAttributes5.getString(0));
                }
                typedArrayObtainStyledAttributes5.recycle();
                TypedArray typedArrayObtainStyledAttributes6 = a9Var.obtainStyledAttributes(attributeSet, i5.f);
                if (typedArrayObtainStyledAttributes6.hasValue(0)) {
                    boolean z2 = typedArrayObtainStyledAttributes6.getBoolean(0, false);
                    WeakHashMap weakHashMap2 = os.a;
                    new zr(com.example.LegoClicker.R.id.tag_screen_reader_focusable, Boolean.class, 0, 28, 0).d(b4Var, Boolean.valueOf(z2));
                }
                typedArrayObtainStyledAttributes6.recycle();
            }
        }
        return b4Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p(android.view.Window r8) {
        /*
            r7 = this;
            java.lang.String r0 = "AppCompat has already installed itself into the Window"
            android.view.Window r1 = r7.l
            if (r1 != 0) goto L7e
            android.view.Window$Callback r1 = r8.getCallback()
            boolean r2 = r1 instanceof defpackage.h3
            if (r2 != 0) goto L7a
            h3 r0 = new h3
            r0.<init>(r7, r1)
            r7.m = r0
            r8.setCallback(r0)
            android.content.Context r0 = r7.k
            int[] r1 = defpackage.n3.i0
            r2 = 0
            android.content.res.TypedArray r1 = r0.obtainStyledAttributes(r2, r1)
            r3 = 0
            boolean r4 = r1.hasValue(r3)
            if (r4 == 0) goto L3f
            int r3 = r1.getResourceId(r3, r3)
            if (r3 == 0) goto L3f
            q3 r4 = defpackage.q3.a()
            monitor-enter(r4)
            ym r5 = r4.a     // Catch: java.lang.Throwable -> L3c
            r6 = 1
            android.graphics.drawable.Drawable r0 = r5.e(r0, r3, r6)     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r4)
            goto L40
        L3c:
            r7 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            throw r7
        L3f:
            r0 = r2
        L40:
            if (r0 == 0) goto L45
            r8.setBackgroundDrawable(r0)
        L45:
            r1.recycle()
            r7.l = r8
            int r8 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r8 < r0) goto L79
            android.window.OnBackInvokedDispatcher r8 = r7.f0
            if (r8 != 0) goto L79
            java.lang.Object r0 = r7.j
            if (r8 == 0) goto L61
            android.window.OnBackInvokedCallback r1 = r7.g0
            if (r1 == 0) goto L61
            defpackage.g3.c(r8, r1)
            r7.g0 = r2
        L61:
            boolean r8 = r0 instanceof android.app.Activity
            if (r8 == 0) goto L74
            android.app.Activity r0 = (android.app.Activity) r0
            android.view.Window r8 = r0.getWindow()
            if (r8 == 0) goto L74
            android.window.OnBackInvokedDispatcher r8 = defpackage.g3.a(r0)
            r7.f0 = r8
            goto L76
        L74:
            r7.f0 = r2
        L76:
            r7.J()
        L79:
            return
        L7a:
            defpackage.z6.o(r0)
            return
        L7e:
            defpackage.z6.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.p(android.view.Window):void");
    }

    public final void r(int i, m3 m3Var, gi giVar) {
        if (giVar == null) {
            if (m3Var == null && i >= 0) {
                m3[] m3VarArr = this.L;
                if (i < m3VarArr.length) {
                    m3Var = m3VarArr[i];
                }
            }
            if (m3Var != null) {
                giVar = m3Var.h;
            }
        }
        if ((m3Var == null || m3Var.m) && !this.Q) {
            h3 h3Var = this.m;
            Window.Callback callback = this.l.getCallback();
            h3Var.getClass();
            try {
                h3Var.e = true;
                callback.onPanelClosed(i, giVar);
            } finally {
                h3Var.e = false;
            }
        }
    }

    public final void s(gi giVar) {
        e1 e1Var;
        if (this.K) {
            return;
        }
        this.K = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.r;
        actionBarOverlayLayout.k();
        ActionMenuView actionMenuView = ((gq) actionBarOverlayLayout.e).a.a;
        if (actionMenuView != null && (e1Var = actionMenuView.t) != null) {
            e1Var.f();
            a1 a1Var = e1Var.t;
            if (a1Var != null && a1Var.b()) {
                a1Var.i.dismiss();
            }
        }
        Window.Callback callback = this.l.getCallback();
        if (callback != null && !this.Q) {
            callback.onPanelClosed(108, giVar);
        }
        this.K = false;
    }

    public final void t(m3 m3Var, boolean z) {
        l3 l3Var;
        ActionBarOverlayLayout actionBarOverlayLayout;
        if (z && m3Var.a == 0 && (actionBarOverlayLayout = this.r) != null) {
            actionBarOverlayLayout.k();
            if (((gq) actionBarOverlayLayout.e).a.o()) {
                s(m3Var.h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.k.getSystemService("window");
        if (windowManager != null && m3Var.m && (l3Var = m3Var.e) != null) {
            windowManager.removeView(l3Var);
            if (z) {
                r(m3Var.a, m3Var, null);
            }
        }
        m3Var.k = false;
        m3Var.l = false;
        m3Var.m = false;
        m3Var.f = null;
        m3Var.n = true;
        if (this.M == m3Var) {
            this.M = null;
        }
        if (m3Var.a == 0) {
            J();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean v(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.v(android.view.KeyEvent):boolean");
    }

    public final void w(int i) {
        m3 m3VarA = A(i);
        if (m3VarA.h != null) {
            Bundle bundle = new Bundle();
            m3VarA.h.t(bundle);
            if (bundle.size() > 0) {
                m3VarA.p = bundle;
            }
            m3VarA.h.w();
            m3VarA.h.clear();
        }
        m3VarA.o = true;
        m3VarA.n = true;
        if ((i == 108 || i == 0) && this.r != null) {
            m3 m3VarA2 = A(0);
            m3VarA2.k = false;
            H(m3VarA2, null);
        }
    }

    public final void x() {
        ViewGroup viewGroup;
        if (this.z) {
            return;
        }
        Context context = this.k;
        int[] iArr = fl.j;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(117)) {
            typedArrayObtainStyledAttributes.recycle();
            z6.o("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
            return;
        }
        int i = 0;
        int i2 = 1;
        if (typedArrayObtainStyledAttributes.getBoolean(126, false)) {
            g(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(117, false)) {
            g(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(118, false)) {
            g(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(119, false)) {
            g(10);
        }
        this.I = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        y();
        this.l.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        if (this.J) {
            viewGroup = this.H ? (ViewGroup) layoutInflaterFrom.inflate(com.example.LegoClicker.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(com.example.LegoClicker.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.I) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(com.example.LegoClicker.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.G = false;
            this.F = false;
        } else if (this.F) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(com.example.LegoClicker.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new a9(context, typedValue.resourceId) : context).inflate(com.example.LegoClicker.R.layout.abc_screen_toolbar, (ViewGroup) null);
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) viewGroup.findViewById(com.example.LegoClicker.R.id.decor_content_parent);
            this.r = actionBarOverlayLayout;
            actionBarOverlayLayout.setWindowCallback(this.l.getCallback());
            if (this.G) {
                this.r.j(109);
            }
            if (this.D) {
                this.r.j(2);
            }
            if (this.E) {
                this.r.j(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.F + ", windowActionBarOverlay: " + this.G + ", android:windowIsFloating: " + this.I + ", windowActionModeOverlay: " + this.H + ", windowNoTitle: " + this.J + " }");
        }
        b3 b3Var = new b3(this, i);
        WeakHashMap weakHashMap = os.a;
        ds.u(viewGroup, b3Var);
        if (this.r == null) {
            this.B = (TextView) viewGroup.findViewById(com.example.LegoClicker.R.id.title);
        }
        boolean z = jt.a;
        try {
            Method method = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", null);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(viewGroup, null);
        } catch (IllegalAccessException e) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e2) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(com.example.LegoClicker.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.l.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.l.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new b3(this, i2));
        this.A = viewGroup;
        Object obj = this.j;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.q;
        if (!TextUtils.isEmpty(title)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.r;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setWindowTitle(title);
            } else {
                o0 o0Var = this.o;
                if (o0Var != null) {
                    o0Var.n(title);
                } else {
                    TextView textView = this.B;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.A.findViewById(R.id.content);
        View decorView = this.l.getDecorView();
        contentFrameLayout2.g.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        if (contentFrameLayout2.isLaidOut()) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(iArr);
        typedArrayObtainStyledAttributes2.getValue(124, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(125, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(122)) {
            typedArrayObtainStyledAttributes2.getValue(122, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(123)) {
            typedArrayObtainStyledAttributes2.getValue(123, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(120)) {
            typedArrayObtainStyledAttributes2.getValue(120, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(121)) {
            typedArrayObtainStyledAttributes2.getValue(121, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.z = true;
        m3 m3VarA = A(0);
        if (this.Q || m3VarA.h != null) {
            return;
        }
        C(108);
    }

    public final void y() {
        if (this.l == null) {
            Object obj = this.j;
            if (obj instanceof Activity) {
                p(((Activity) obj).getWindow());
            }
        }
        if (this.l != null) {
            return;
        }
        z6.o("We have not been given a Window");
    }

    public final k3 z(Context context) {
        if (this.W == null) {
            if (j5.f == null) {
                Context applicationContext = context.getApplicationContext();
                LocationManager locationManager = (LocationManager) applicationContext.getSystemService("location");
                j5 j5Var = new j5();
                j5Var.b = new oq();
                j5Var.c = applicationContext;
                j5Var.a = locationManager;
                j5.f = j5Var;
            }
            this.W = new i3(this, j5.f);
        }
        return this.W;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
