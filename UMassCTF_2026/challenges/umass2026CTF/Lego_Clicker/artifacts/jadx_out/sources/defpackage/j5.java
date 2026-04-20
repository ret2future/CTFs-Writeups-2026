package defpackage;

import android.content.ClipDescription;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import com.example.LegoClicker.R;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class j5 implements gf {
    public static volatile j5 d;
    public static final Object e = new Object();
    public static j5 f;
    public Object a;
    public Object b;
    public Object c;

    public j5(zs zsVar, ys ysVar, e9 e9Var) {
        zsVar.getClass();
        e9Var.getClass();
        this.a = zsVar;
        this.b = ysVar;
        this.c = e9Var;
    }

    public static boolean f(Editable editable, KeyEvent keyEvent, boolean z) {
        er[] erVarArr;
        if (KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (erVarArr = (er[]) editable.getSpans(selectionStart, selectionEnd, er.class)) != null && erVarArr.length > 0) {
                for (er erVar : erVarArr) {
                    int spanStart = editable.getSpanStart(erVar);
                    int spanEnd = editable.getSpanEnd(erVar);
                    if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                        editable.delete(spanStart, spanEnd);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static j5 n(Context context) {
        if (d == null) {
            synchronized (e) {
                try {
                    if (d == null) {
                        j5 j5Var = new j5();
                        j5Var.c = context.getApplicationContext();
                        j5Var.b = new HashSet();
                        j5Var.a = new HashMap();
                        d = j5Var;
                    }
                } finally {
                }
            }
        }
        return d;
    }

    public static j5 q(Context context, AttributeSet attributeSet, int[] iArr, int i) {
        return new j5(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
    }

    @Override // defpackage.gf
    public Uri b() {
        return (Uri) this.c;
    }

    @Override // defpackage.gf
    public ClipDescription c() {
        return (ClipDescription) this.b;
    }

    @Override // defpackage.gf
    public Object d() {
        return null;
    }

    @Override // defpackage.gf
    public Uri e() {
        return (Uri) this.a;
    }

    public void g(Bundle bundle) {
        HashSet hashSet = (HashSet) this.b;
        String string = ((Context) this.c).getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                for (String str : bundle.keySet()) {
                    if (string.equals(bundle.getString(str, null))) {
                        Class<?> cls = Class.forName(str);
                        if (cf.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    h((Class) it.next(), hashSet2);
                }
            } catch (ClassNotFoundException e2) {
                throw new pc(e2);
            }
        }
    }

    public Object h(Class cls, HashSet hashSet) {
        Object objB;
        HashMap map = (HashMap) this.a;
        if (hb.K()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } finally {
                Trace.endSection();
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException("Cannot initialize " + cls.getName() + ". Cycle detected.");
        }
        if (map.containsKey(cls)) {
            objB = map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                cf cfVar = (cf) cls.getDeclaredConstructor(null).newInstance(null);
                List<Class> listA = cfVar.a();
                if (!listA.isEmpty()) {
                    for (Class cls2 : listA) {
                        if (!map.containsKey(cls2)) {
                            h(cls2, hashSet);
                        }
                    }
                }
                objB = cfVar.b((Context) this.c);
                hashSet.remove(cls);
                map.put(cls, objB);
            } catch (Throwable th) {
                throw new pc(th);
            }
        }
        return objB;
    }

    public vs i(String str, Class cls) {
        vs vsVarD;
        ys ysVar = (ys) this.b;
        zs zsVar = (zs) this.a;
        zsVar.getClass();
        LinkedHashMap linkedHashMap = zsVar.a;
        vs vsVar = (vs) linkedHashMap.get(str);
        if (!cls.isInstance(vsVar)) {
            lj ljVar = new lj((e9) this.c);
            ljVar.a.put(fr.e, str);
            try {
                vsVarD = ysVar.b(cls, ljVar);
            } catch (AbstractMethodError unused) {
                vsVarD = ysVar.d(cls);
            }
            vsVarD.getClass();
            vs vsVar2 = (vs) linkedHashMap.put(str, vsVarD);
            if (vsVar2 != null) {
                vsVar2.b();
            }
            return vsVarD;
        }
        ao aoVar = ysVar instanceof ao ? (ao) ysVar : null;
        if (aoVar != null) {
            vsVar.getClass();
            fg fgVar = aoVar.d;
            if (fgVar != null) {
                xn xnVar = aoVar.e;
                xnVar.getClass();
                hb.g(vsVar, xnVar, fgVar);
            }
        }
        vsVar.getClass();
        return vsVar;
    }

    public ColorStateList j(int i) {
        int resourceId;
        ColorStateList colorStateListU;
        TypedArray typedArray = (TypedArray) this.a;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateListU = d.u((Context) this.c, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateListU;
    }

    public Drawable k(int i) {
        int resourceId;
        TypedArray typedArray = (TypedArray) this.a;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) ? typedArray.getDrawable(i) : d.w((Context) this.c, resourceId);
    }

    public Drawable l(int i) {
        int resourceId;
        Drawable drawableE;
        if (!((TypedArray) this.a).hasValue(i) || (resourceId = ((TypedArray) this.a).getResourceId(i, 0)) == 0) {
            return null;
        }
        q3 q3VarA = q3.a();
        Context context = (Context) this.c;
        synchronized (q3VarA) {
            drawableE = q3VarA.a.e(context, resourceId, true);
        }
        return drawableE;
    }

    public Typeface m(int i, int i2, r4 r4Var) {
        r4 r4Var2;
        XmlPullParserException xmlPullParserException;
        IOException iOException;
        int resourceId = ((TypedArray) this.a).getResourceId(i, 0);
        if (resourceId != 0) {
            if (((TypedValue) this.b) == null) {
                this.b = new TypedValue();
            }
            Context context = (Context) this.c;
            TypedValue typedValue = (TypedValue) this.b;
            ThreadLocal threadLocal = en.a;
            if (!context.isRestricted()) {
                Resources resources = context.getResources();
                resources.getValue(resourceId, typedValue, true);
                CharSequence charSequence = typedValue.string;
                if (charSequence == null) {
                    throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(resourceId) + "\" (" + Integer.toHexString(resourceId) + ") is not a Font: " + typedValue);
                }
                String string = charSequence.toString();
                if (!string.startsWith("res/")) {
                    r4Var.a();
                    return null;
                }
                int i3 = typedValue.assetCookie;
                sh shVar = xq.b;
                Typeface typeface = (Typeface) shVar.a(xq.b(resources, resourceId, string, i3, i2));
                int i4 = 2;
                if (typeface != null) {
                    new Handler(Looper.getMainLooper()).post(new w2(r4Var, i4, typeface));
                    return typeface;
                }
                try {
                } catch (IOException e2) {
                    e = e2;
                    r4Var2 = r4Var;
                } catch (XmlPullParserException e3) {
                    e = e3;
                    r4Var2 = r4Var;
                }
                if (string.toLowerCase().endsWith(".xml")) {
                    ic icVarN = hb.N(resources.getXml(resourceId), resources);
                    if (icVarN == null) {
                        try {
                            Log.e("ResourcesCompat", "Failed to find font-family tag");
                            r4Var.a();
                            return null;
                        } catch (IOException e4) {
                            iOException = e4;
                            r4Var2 = r4Var;
                        } catch (XmlPullParserException e5) {
                            xmlPullParserException = e5;
                            r4Var2 = r4Var;
                            Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(string), xmlPullParserException);
                            r4Var2.a();
                            return null;
                        }
                    } else {
                        try {
                            return xq.a(context, icVarN, resources, resourceId, string, typedValue.assetCookie, i2, r4Var);
                        } catch (IOException e6) {
                            e = e6;
                            r4Var2 = r4Var;
                        } catch (XmlPullParserException e7) {
                            e = e7;
                            r4Var2 = r4Var;
                            xmlPullParserException = e;
                            Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(string), xmlPullParserException);
                            r4Var2.a();
                            return null;
                        }
                    }
                    iOException = e;
                    Log.e("ResourcesCompat", "Failed to read xml resource ".concat(string), iOException);
                } else {
                    r4Var2 = r4Var;
                    try {
                        int i5 = typedValue.assetCookie;
                        Typeface typefaceS = xq.a.s(context, resources, resourceId, string, i2);
                        if (typefaceS != null) {
                            shVar.b(xq.b(resources, resourceId, string, i5, i2), typefaceS);
                        }
                        if (typefaceS != null) {
                            new Handler(Looper.getMainLooper()).post(new w2(r4Var2, i4, typefaceS));
                        } else {
                            r4Var2.a();
                        }
                        return typefaceS;
                    } catch (IOException e8) {
                        e = e8;
                    } catch (XmlPullParserException e9) {
                        e = e9;
                        xmlPullParserException = e;
                        Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(string), xmlPullParserException);
                        r4Var2.a();
                        return null;
                    }
                }
                r4Var2.a();
                return null;
            }
        }
        return null;
    }

    public boolean o(CharSequence charSequence, int i, int i2, dr drVar) {
        if ((drVar.c & 3) == 0) {
            i9 i9Var = (i9) this.c;
            gj gjVarB = drVar.b();
            int iA = gjVarB.a(8);
            if (iA != 0) {
                ((ByteBuffer) gjVarB.d).getShort(iA + gjVarB.a);
            }
            i9Var.getClass();
            ThreadLocal threadLocal = i9.b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(charSequence.charAt(i));
                i++;
            }
            TextPaint textPaint = i9Var.a;
            String string = sb.toString();
            int i3 = ik.a;
            boolean zA = hk.a(textPaint, string);
            int i4 = drVar.c & 4;
            drVar.c = zA ? i4 | 2 : i4 | 1;
        }
        return (drVar.c & 3) == 2;
    }

    public boolean p(int i, a8 a8Var, k8 k8Var) {
        j6 j6Var = (j6) this.b;
        int[] iArr = k8Var.o0;
        int[] iArr2 = k8Var.t;
        j6Var.a = iArr[0];
        j6Var.b = iArr[1];
        j6Var.c = k8Var.o();
        j6Var.d = k8Var.i();
        j6Var.i = false;
        j6Var.j = i;
        boolean z = j6Var.a == 3;
        boolean z2 = j6Var.b == 3;
        boolean z3 = z && k8Var.V > 0.0f;
        boolean z4 = z2 && k8Var.V > 0.0f;
        if (z3 && iArr2[0] == 4) {
            j6Var.a = 1;
        }
        if (z4 && iArr2[1] == 4) {
            j6Var.b = 1;
        }
        a8Var.b(k8Var, j6Var);
        k8Var.K(j6Var.e);
        k8Var.H(j6Var.f);
        k8Var.E = j6Var.h;
        int i2 = j6Var.g;
        k8Var.Z = i2;
        k8Var.E = i2 > 0;
        j6Var.j = 0;
        return j6Var.i;
    }

    public Object r(CharSequence charSequence, int i, int i2, int i3, boolean z, eb ebVar) {
        int i4;
        char c;
        fb fbVar = new fb((ij) ((kd) this.b).c);
        int iCodePointAt = Character.codePointAt(charSequence, i);
        int i5 = 0;
        boolean zD = true;
        int iCharCount = i;
        loop0: while (true) {
            i4 = iCharCount;
            while (iCharCount < i2 && i5 < i3 && zD) {
                SparseArray sparseArray = fbVar.c.a;
                ij ijVar = sparseArray == null ? null : (ij) sparseArray.get(iCodePointAt);
                if (fbVar.a == 2) {
                    if (ijVar != null) {
                        fbVar.c = ijVar;
                        fbVar.f++;
                    } else {
                        if (iCodePointAt == 65038) {
                            fbVar.a();
                        } else if (iCodePointAt != 65039) {
                            ij ijVar2 = fbVar.c;
                            if (ijVar2.b != null) {
                                if (fbVar.f != 1) {
                                    fbVar.d = ijVar2;
                                    fbVar.a();
                                } else if (fbVar.b()) {
                                    fbVar.d = fbVar.c;
                                    fbVar.a();
                                } else {
                                    fbVar.a();
                                }
                                c = 3;
                            } else {
                                fbVar.a();
                            }
                        }
                        c = 1;
                    }
                    c = 2;
                } else if (ijVar == null) {
                    fbVar.a();
                    c = 1;
                } else {
                    fbVar.a = 2;
                    fbVar.c = ijVar;
                    fbVar.f = 1;
                    c = 2;
                }
                fbVar.e = iCodePointAt;
                if (c == 1) {
                    iCharCount = Character.charCount(Character.codePointAt(charSequence, i4)) + i4;
                    if (iCharCount < i2) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                    }
                } else if (c == 2) {
                    int iCharCount2 = Character.charCount(iCodePointAt) + iCharCount;
                    if (iCharCount2 < i2) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount2);
                    }
                    iCharCount = iCharCount2;
                } else if (c == 3) {
                    if (z || !o(charSequence, i4, iCharCount, fbVar.d.b)) {
                        zD = ebVar.d(charSequence, i4, iCharCount, fbVar.d.b);
                        i5++;
                    }
                }
            }
            break loop0;
        }
        if (fbVar.a == 2 && fbVar.c.b != null && ((fbVar.f > 1 || fbVar.b()) && i5 < i3 && zD && (z || !o(charSequence, i4, iCharCount, fbVar.c.b)))) {
            ebVar.d(charSequence, i4, iCharCount, fbVar.c.b);
        }
        return ebVar.a();
    }

    public void s() {
        ((TypedArray) this.a).recycle();
    }

    public void t(l8 l8Var, int i, int i2, int i3) {
        l8Var.getClass();
        int i4 = l8Var.a0;
        int i5 = l8Var.b0;
        l8Var.a0 = 0;
        l8Var.b0 = 0;
        l8Var.K(i2);
        l8Var.H(i3);
        if (i4 < 0) {
            l8Var.a0 = 0;
        } else {
            l8Var.a0 = i4;
        }
        if (i5 < 0) {
            l8Var.b0 = 0;
        } else {
            l8Var.b0 = i5;
        }
        l8 l8Var2 = (l8) this.c;
        l8Var2.s0 = i;
        l8Var2.Q();
    }

    public void u(l8 l8Var) {
        ArrayList arrayList = (ArrayList) this.a;
        arrayList.clear();
        int size = l8Var.p0.size();
        for (int i = 0; i < size; i++) {
            k8 k8Var = (k8) l8Var.p0.get(i);
            int[] iArr = k8Var.o0;
            if (iArr[0] == 3 || iArr[1] == 3) {
                arrayList.add(k8Var);
            }
        }
        l8Var.r0.b = true;
    }

    public /* synthetic */ j5(Object obj, Object obj2, Object obj3) {
        this.a = obj;
        this.b = obj2;
        this.c = obj3;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public j5(zs zsVar, ys ysVar) {
        this(zsVar, ysVar, (e9) d9.b);
        zsVar.getClass();
    }

    public j5(Context context, TypedArray typedArray) {
        this.c = context;
        this.a = typedArray;
    }

    @Override // defpackage.gf
    public void a() {
    }
}
