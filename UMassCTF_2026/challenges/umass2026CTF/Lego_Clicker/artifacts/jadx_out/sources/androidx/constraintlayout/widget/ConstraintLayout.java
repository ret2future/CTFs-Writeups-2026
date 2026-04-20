package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a8;
import defpackage.b8;
import defpackage.bl;
import defpackage.c8;
import defpackage.io;
import defpackage.j8;
import defpackage.k8;
import defpackage.l8;
import defpackage.m8;
import defpackage.se;
import defpackage.te;
import defpackage.tg;
import defpackage.x7;
import defpackage.y7;
import defpackage.z3;
import defpackage.z7;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    public static io p;
    public final SparseArray a;
    public final ArrayList b;
    public final l8 c;
    public int d;
    public int e;
    public int f;
    public int g;
    public boolean h;
    public int i;
    public j8 j;
    public z3 k;
    public int l;
    public HashMap m;
    public final SparseArray n;
    public final a8 o;

    public ConstraintLayout(Context context) {
        super(context);
        this.a = new SparseArray();
        this.b = new ArrayList(4);
        this.c = new l8();
        this.d = 0;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap();
        this.n = new SparseArray();
        this.o = new a8(this, this);
        h(null, 0, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int iMax2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static io getSharedValues() {
        if (p == null) {
            io ioVar = new io();
            new SparseIntArray();
            new HashMap();
            p = ioVar;
        }
        return p;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof z7;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.b;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                ((x7) arrayList.get(i)).getClass();
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        int i5 = Integer.parseInt(strArrSplit[2]);
                        int i6 = (int) ((i3 / 1080.0f) * width);
                        int i7 = (int) ((i4 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i6;
                        float f2 = i7;
                        float f3 = i6 + ((int) ((i5 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float f4 = i7 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f3, f2, f3, f4, paint);
                        canvas.drawLine(f3, f4, f, f4, paint);
                        canvas.drawLine(f, f4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, f4, paint);
                        canvas.drawLine(f, f4, f3, f2, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.h = true;
        super.forceLayout();
    }

    public final k8 g(View view) {
        if (view == this) {
            return this.c;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof z7) {
            return ((z7) view.getLayoutParams()).p0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof z7) {
            return ((z7) view.getLayoutParams()).p0;
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new z7(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        z7 z7Var = new z7(context, attributeSet);
        z7Var.a = -1;
        z7Var.b = -1;
        z7Var.c = -1.0f;
        z7Var.d = true;
        z7Var.e = -1;
        z7Var.f = -1;
        z7Var.g = -1;
        z7Var.h = -1;
        z7Var.i = -1;
        z7Var.j = -1;
        z7Var.k = -1;
        z7Var.l = -1;
        z7Var.m = -1;
        z7Var.n = -1;
        z7Var.o = -1;
        z7Var.p = -1;
        z7Var.q = 0;
        z7Var.r = 0.0f;
        z7Var.s = -1;
        z7Var.t = -1;
        z7Var.u = -1;
        z7Var.v = -1;
        z7Var.w = Integer.MIN_VALUE;
        z7Var.x = Integer.MIN_VALUE;
        z7Var.y = Integer.MIN_VALUE;
        z7Var.z = Integer.MIN_VALUE;
        z7Var.A = Integer.MIN_VALUE;
        z7Var.B = Integer.MIN_VALUE;
        z7Var.C = Integer.MIN_VALUE;
        z7Var.D = 0;
        z7Var.E = 0.5f;
        z7Var.F = 0.5f;
        z7Var.G = null;
        z7Var.H = -1.0f;
        z7Var.I = -1.0f;
        z7Var.J = 0;
        z7Var.K = 0;
        z7Var.L = 0;
        z7Var.M = 0;
        z7Var.N = 0;
        z7Var.O = 0;
        z7Var.P = 0;
        z7Var.Q = 0;
        z7Var.R = 1.0f;
        z7Var.S = 1.0f;
        z7Var.T = -1;
        z7Var.U = -1;
        z7Var.V = -1;
        z7Var.W = false;
        z7Var.X = false;
        z7Var.Y = null;
        z7Var.Z = 0;
        z7Var.a0 = true;
        z7Var.b0 = true;
        z7Var.c0 = false;
        z7Var.d0 = false;
        z7Var.e0 = false;
        z7Var.f0 = -1;
        z7Var.g0 = -1;
        z7Var.h0 = -1;
        z7Var.i0 = -1;
        z7Var.j0 = Integer.MIN_VALUE;
        z7Var.k0 = Integer.MIN_VALUE;
        z7Var.l0 = 0.5f;
        z7Var.p0 = new k8();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, bl.b);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            int i2 = y7.a.get(index);
            switch (i2) {
                case 1:
                    z7Var.V = typedArrayObtainStyledAttributes.getInt(index, z7Var.V);
                    break;
                case 2:
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.p);
                    z7Var.p = resourceId;
                    if (resourceId == -1) {
                        z7Var.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 3:
                    z7Var.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.q);
                    break;
                case 4:
                    float f = typedArrayObtainStyledAttributes.getFloat(index, z7Var.r) % 360.0f;
                    z7Var.r = f;
                    if (f < 0.0f) {
                        z7Var.r = (360.0f - f) % 360.0f;
                    }
                    break;
                case 5:
                    z7Var.a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, z7Var.a);
                    break;
                case 6:
                    z7Var.b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, z7Var.b);
                    break;
                case 7:
                    z7Var.c = typedArrayObtainStyledAttributes.getFloat(index, z7Var.c);
                    break;
                case 8:
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.e);
                    z7Var.e = resourceId2;
                    if (resourceId2 == -1) {
                        z7Var.e = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 9:
                    int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.f);
                    z7Var.f = resourceId3;
                    if (resourceId3 == -1) {
                        z7Var.f = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 10:
                    int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.g);
                    z7Var.g = resourceId4;
                    if (resourceId4 == -1) {
                        z7Var.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 11:
                    int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.h);
                    z7Var.h = resourceId5;
                    if (resourceId5 == -1) {
                        z7Var.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 12:
                    int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.i);
                    z7Var.i = resourceId6;
                    if (resourceId6 == -1) {
                        z7Var.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 13:
                    int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.j);
                    z7Var.j = resourceId7;
                    if (resourceId7 == -1) {
                        z7Var.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 14:
                    int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.k);
                    z7Var.k = resourceId8;
                    if (resourceId8 == -1) {
                        z7Var.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 15:
                    int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.l);
                    z7Var.l = resourceId9;
                    if (resourceId9 == -1) {
                        z7Var.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 16:
                    int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.m);
                    z7Var.m = resourceId10;
                    if (resourceId10 == -1) {
                        z7Var.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 17:
                    int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.s);
                    z7Var.s = resourceId11;
                    if (resourceId11 == -1) {
                        z7Var.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 18:
                    int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.t);
                    z7Var.t = resourceId12;
                    if (resourceId12 == -1) {
                        z7Var.t = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 19:
                    int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.u);
                    z7Var.u = resourceId13;
                    if (resourceId13 == -1) {
                        z7Var.u = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 20:
                    int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.v);
                    z7Var.v = resourceId14;
                    if (resourceId14 == -1) {
                        z7Var.v = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 21:
                    z7Var.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.w);
                    break;
                case 22:
                    z7Var.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.x);
                    break;
                case 23:
                    z7Var.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.y);
                    break;
                case 24:
                    z7Var.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.z);
                    break;
                case 25:
                    z7Var.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.A);
                    break;
                case 26:
                    z7Var.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.B);
                    break;
                case 27:
                    z7Var.W = typedArrayObtainStyledAttributes.getBoolean(index, z7Var.W);
                    break;
                case 28:
                    z7Var.X = typedArrayObtainStyledAttributes.getBoolean(index, z7Var.X);
                    break;
                case 29:
                    z7Var.E = typedArrayObtainStyledAttributes.getFloat(index, z7Var.E);
                    break;
                case 30:
                    z7Var.F = typedArrayObtainStyledAttributes.getFloat(index, z7Var.F);
                    break;
                case 31:
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    z7Var.L = i3;
                    if (i3 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                    }
                    break;
                case 32:
                    int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    z7Var.M = i4;
                    if (i4 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                    }
                    break;
                case 33:
                    try {
                        z7Var.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.N);
                    } catch (Exception unused) {
                        if (typedArrayObtainStyledAttributes.getInt(index, z7Var.N) == -2) {
                            z7Var.N = -2;
                        }
                    }
                    break;
                case 34:
                    try {
                        z7Var.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.P);
                    } catch (Exception unused2) {
                        if (typedArrayObtainStyledAttributes.getInt(index, z7Var.P) == -2) {
                            z7Var.P = -2;
                        }
                    }
                    break;
                case 35:
                    z7Var.R = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, z7Var.R));
                    z7Var.L = 2;
                    break;
                case 36:
                    try {
                        z7Var.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.O);
                    } catch (Exception unused3) {
                        if (typedArrayObtainStyledAttributes.getInt(index, z7Var.O) == -2) {
                            z7Var.O = -2;
                        }
                    }
                    break;
                case 37:
                    try {
                        z7Var.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.Q);
                    } catch (Exception unused4) {
                        if (typedArrayObtainStyledAttributes.getInt(index, z7Var.Q) == -2) {
                            z7Var.Q = -2;
                        }
                    }
                    break;
                case 38:
                    z7Var.S = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, z7Var.S));
                    z7Var.M = 2;
                    break;
                default:
                    switch (i2) {
                        case 44:
                            j8.g(z7Var, typedArrayObtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            z7Var.H = typedArrayObtainStyledAttributes.getFloat(index, z7Var.H);
                            break;
                        case 46:
                            z7Var.I = typedArrayObtainStyledAttributes.getFloat(index, z7Var.I);
                            break;
                        case 47:
                            z7Var.J = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            z7Var.K = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            z7Var.T = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, z7Var.T);
                            break;
                        case 50:
                            z7Var.U = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, z7Var.U);
                            break;
                        case 51:
                            z7Var.Y = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.n);
                            z7Var.n = resourceId15;
                            if (resourceId15 == -1) {
                                z7Var.n = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 53:
                            int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, z7Var.o);
                            z7Var.o = resourceId16;
                            if (resourceId16 == -1) {
                                z7Var.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 54:
                            z7Var.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.D);
                            break;
                        case 55:
                            z7Var.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z7Var.C);
                            break;
                        default:
                            switch (i2) {
                                case 64:
                                    j8.f(z7Var, typedArrayObtainStyledAttributes, index, 0);
                                    break;
                                case 65:
                                    j8.f(z7Var, typedArrayObtainStyledAttributes, index, 1);
                                    break;
                                case 66:
                                    z7Var.Z = typedArrayObtainStyledAttributes.getInt(index, z7Var.Z);
                                    break;
                                case 67:
                                    z7Var.d = typedArrayObtainStyledAttributes.getBoolean(index, z7Var.d);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        z7Var.a();
        return z7Var;
    }

    public int getMaxHeight() {
        return this.g;
    }

    public int getMaxWidth() {
        return this.f;
    }

    public int getMinHeight() {
        return this.e;
    }

    public int getMinWidth() {
        return this.d;
    }

    public int getOptimizationLevel() {
        return this.c.C0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        l8 l8Var = this.c;
        if (l8Var.j == null) {
            int id2 = getId();
            if (id2 != -1) {
                l8Var.j = getContext().getResources().getResourceEntryName(id2);
            } else {
                l8Var.j = "parent";
            }
        }
        if (l8Var.g0 == null) {
            l8Var.g0 = l8Var.j;
            Log.v("ConstraintLayout", " setDebugName " + l8Var.g0);
        }
        ArrayList arrayList = l8Var.p0;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            k8 k8Var = (k8) obj;
            View view = k8Var.e0;
            if (view != null) {
                if (k8Var.j == null && (id = view.getId()) != -1) {
                    k8Var.j = getContext().getResources().getResourceEntryName(id);
                }
                if (k8Var.g0 == null) {
                    k8Var.g0 = k8Var.j;
                    Log.v("ConstraintLayout", " setDebugName " + k8Var.g0);
                }
            }
        }
        l8Var.l(sb);
        return sb.toString();
    }

    public final void h(AttributeSet attributeSet, int i, int i2) {
        l8 l8Var = this.c;
        l8Var.e0 = this;
        a8 a8Var = this.o;
        l8Var.t0 = a8Var;
        l8Var.r0.f = a8Var;
        this.a.put(getId(), this);
        this.j = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, bl.b, i, i2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == 16) {
                    this.d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.d);
                } else if (index == 17) {
                    this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.e);
                } else if (index == 14) {
                    this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                } else if (index == 15) {
                    this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                } else if (index == 113) {
                    this.i = typedArrayObtainStyledAttributes.getInt(index, this.i);
                } else if (index == 56) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            i(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.k = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        j8 j8Var = new j8();
                        this.j = j8Var;
                        j8Var.d(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.j = null;
                    }
                    this.l = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        l8Var.C0 = this.i;
        tg.q = l8Var.S(512);
    }

    public final void i(int i) {
        String str;
        Context context = getContext();
        z3 z3Var = new z3(5, false);
        z3Var.b = new SparseArray();
        z3Var.c = new SparseArray();
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            b8 b8Var = null;
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                z3Var.t(context, xml);
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                b8 b8Var2 = new b8(context, xml);
                                ((SparseArray) z3Var.b).put(b8Var2.a, b8Var2);
                                b8Var = b8Var2;
                            }
                            break;
                        case 1382829617:
                            str = "StateSet";
                            name.equals(str);
                            break;
                        case 1657696882:
                            str = "layoutDescription";
                            name.equals(str);
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c8 c8Var = new c8(context, xml);
                                if (b8Var != null) {
                                    b8Var.b.add(c8Var);
                                }
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            Log.e("ConstraintLayoutStates", "Error parsing resource: " + i, e);
        } catch (XmlPullParserException e2) {
            Log.e("ConstraintLayoutStates", "Error parsing resource: " + i, e2);
        }
        this.k = z3Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:157:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x046d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(defpackage.l8 r28, int r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 1621
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.j(l8, int, int, int):void");
    }

    public final void k(k8 k8Var, z7 z7Var, SparseArray sparseArray, int i, int i2) {
        View view = (View) this.a.get(i);
        k8 k8Var2 = (k8) sparseArray.get(i);
        if (k8Var2 == null || view == null || !(view.getLayoutParams() instanceof z7)) {
            return;
        }
        z7Var.c0 = true;
        if (i2 == 6) {
            z7 z7Var2 = (z7) view.getLayoutParams();
            z7Var2.c0 = true;
            z7Var2.p0.E = true;
        }
        k8Var.g(6).a(k8Var2.g(i2), z7Var.D, z7Var.C);
        k8Var.E = true;
        k8Var.g(3).g();
        k8Var.g(5).g();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            z7 z7Var = (z7) childAt.getLayoutParams();
            k8 k8Var = z7Var.p0;
            if (childAt.getVisibility() != 8 || z7Var.d0 || z7Var.e0 || zIsInEditMode) {
                int iP = k8Var.p();
                int iQ = k8Var.q();
                childAt.layout(iP, iQ, k8Var.o() + iP, k8Var.i() + iQ);
            }
        }
        ArrayList arrayList = this.b;
        int size = arrayList.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((x7) arrayList.get(i6)).getClass();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:289:0x073f  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x076b  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0787  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x07c3  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x07e0  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x07ef  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0811  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0819  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x093c  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0942  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 2592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        k8 k8VarG = g(view);
        if ((view instanceof se) && !(k8VarG instanceof te)) {
            z7 z7Var = (z7) view.getLayoutParams();
            te teVar = new te();
            z7Var.p0 = teVar;
            z7Var.d0 = true;
            teVar.O(z7Var.V);
        }
        if (view instanceof x7) {
            x7 x7Var = (x7) view;
            x7Var.e();
            ((z7) view.getLayoutParams()).e0 = true;
            ArrayList arrayList = this.b;
            if (!arrayList.contains(x7Var)) {
                arrayList.add(x7Var);
            }
        }
        this.a.put(view.getId(), view);
        this.h = true;
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.a.remove(view.getId());
        k8 k8VarG = g(view);
        this.c.p0.remove(k8VarG);
        k8VarG.A();
        this.b.remove(view);
        this.h = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.h = true;
        super.requestLayout();
    }

    public void setConstraintSet(j8 j8Var) {
        this.j = j8Var;
    }

    @Override // android.view.View
    public void setId(int i) {
        int id = getId();
        SparseArray sparseArray = this.a;
        sparseArray.remove(id);
        super.setId(i);
        sparseArray.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.g) {
            return;
        }
        this.g = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.f) {
            return;
        }
        this.f = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.e) {
            return;
        }
        this.e = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.d) {
            return;
        }
        this.d = i;
        requestLayout();
    }

    public void setOnConstraintsChanged(m8 m8Var) {
        z3 z3Var = this.k;
        if (z3Var != null) {
            z3Var.getClass();
        }
    }

    public void setOptimizationLevel(int i) {
        this.i = i;
        l8 l8Var = this.c;
        l8Var.C0 = i;
        tg.q = l8Var.S(512);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new SparseArray();
        this.b = new ArrayList(4);
        this.c = new l8();
        this.d = 0;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap();
        this.n = new SparseArray();
        this.o = new a8(this, this);
        h(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new SparseArray();
        this.b = new ArrayList(4);
        this.c = new l8();
        this.d = 0;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap();
        this.n = new SparseArray();
        this.o = new a8(this, this);
        h(attributeSet, i, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new SparseArray();
        this.b = new ArrayList(4);
        this.c = new l8();
        this.d = 0;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap();
        this.n = new SparseArray();
        this.o = new a8(this, this);
        h(attributeSet, i, i2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        z7 z7Var = new z7(layoutParams);
        z7Var.a = -1;
        z7Var.b = -1;
        z7Var.c = -1.0f;
        z7Var.d = true;
        z7Var.e = -1;
        z7Var.f = -1;
        z7Var.g = -1;
        z7Var.h = -1;
        z7Var.i = -1;
        z7Var.j = -1;
        z7Var.k = -1;
        z7Var.l = -1;
        z7Var.m = -1;
        z7Var.n = -1;
        z7Var.o = -1;
        z7Var.p = -1;
        z7Var.q = 0;
        z7Var.r = 0.0f;
        z7Var.s = -1;
        z7Var.t = -1;
        z7Var.u = -1;
        z7Var.v = -1;
        z7Var.w = Integer.MIN_VALUE;
        z7Var.x = Integer.MIN_VALUE;
        z7Var.y = Integer.MIN_VALUE;
        z7Var.z = Integer.MIN_VALUE;
        z7Var.A = Integer.MIN_VALUE;
        z7Var.B = Integer.MIN_VALUE;
        z7Var.C = Integer.MIN_VALUE;
        z7Var.D = 0;
        z7Var.E = 0.5f;
        z7Var.F = 0.5f;
        z7Var.G = null;
        z7Var.H = -1.0f;
        z7Var.I = -1.0f;
        z7Var.J = 0;
        z7Var.K = 0;
        z7Var.L = 0;
        z7Var.M = 0;
        z7Var.N = 0;
        z7Var.O = 0;
        z7Var.P = 0;
        z7Var.Q = 0;
        z7Var.R = 1.0f;
        z7Var.S = 1.0f;
        z7Var.T = -1;
        z7Var.U = -1;
        z7Var.V = -1;
        z7Var.W = false;
        z7Var.X = false;
        z7Var.Y = null;
        z7Var.Z = 0;
        z7Var.a0 = true;
        z7Var.b0 = true;
        z7Var.c0 = false;
        z7Var.d0 = false;
        z7Var.e0 = false;
        z7Var.f0 = -1;
        z7Var.g0 = -1;
        z7Var.h0 = -1;
        z7Var.i0 = -1;
        z7Var.j0 = Integer.MIN_VALUE;
        z7Var.k0 = Integer.MIN_VALUE;
        z7Var.l0 = 0.5f;
        z7Var.p0 = new k8();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            ((ViewGroup.MarginLayoutParams) z7Var).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) z7Var).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) z7Var).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) z7Var).bottomMargin = marginLayoutParams.bottomMargin;
            z7Var.setMarginStart(marginLayoutParams.getMarginStart());
            z7Var.setMarginEnd(marginLayoutParams.getMarginEnd());
        }
        if (!(layoutParams instanceof z7)) {
            return z7Var;
        }
        z7 z7Var2 = (z7) layoutParams;
        z7Var.a = z7Var2.a;
        z7Var.b = z7Var2.b;
        z7Var.c = z7Var2.c;
        z7Var.d = z7Var2.d;
        z7Var.e = z7Var2.e;
        z7Var.f = z7Var2.f;
        z7Var.g = z7Var2.g;
        z7Var.h = z7Var2.h;
        z7Var.i = z7Var2.i;
        z7Var.j = z7Var2.j;
        z7Var.k = z7Var2.k;
        z7Var.l = z7Var2.l;
        z7Var.m = z7Var2.m;
        z7Var.n = z7Var2.n;
        z7Var.o = z7Var2.o;
        z7Var.p = z7Var2.p;
        z7Var.q = z7Var2.q;
        z7Var.r = z7Var2.r;
        z7Var.s = z7Var2.s;
        z7Var.t = z7Var2.t;
        z7Var.u = z7Var2.u;
        z7Var.v = z7Var2.v;
        z7Var.w = z7Var2.w;
        z7Var.x = z7Var2.x;
        z7Var.y = z7Var2.y;
        z7Var.z = z7Var2.z;
        z7Var.A = z7Var2.A;
        z7Var.B = z7Var2.B;
        z7Var.C = z7Var2.C;
        z7Var.D = z7Var2.D;
        z7Var.E = z7Var2.E;
        z7Var.F = z7Var2.F;
        z7Var.G = z7Var2.G;
        z7Var.H = z7Var2.H;
        z7Var.I = z7Var2.I;
        z7Var.J = z7Var2.J;
        z7Var.K = z7Var2.K;
        z7Var.W = z7Var2.W;
        z7Var.X = z7Var2.X;
        z7Var.L = z7Var2.L;
        z7Var.M = z7Var2.M;
        z7Var.N = z7Var2.N;
        z7Var.P = z7Var2.P;
        z7Var.O = z7Var2.O;
        z7Var.Q = z7Var2.Q;
        z7Var.R = z7Var2.R;
        z7Var.S = z7Var2.S;
        z7Var.T = z7Var2.T;
        z7Var.U = z7Var2.U;
        z7Var.V = z7Var2.V;
        z7Var.a0 = z7Var2.a0;
        z7Var.b0 = z7Var2.b0;
        z7Var.c0 = z7Var2.c0;
        z7Var.d0 = z7Var2.d0;
        z7Var.f0 = z7Var2.f0;
        z7Var.g0 = z7Var2.g0;
        z7Var.h0 = z7Var2.h0;
        z7Var.i0 = z7Var2.i0;
        z7Var.j0 = z7Var2.j0;
        z7Var.k0 = z7Var2.k0;
        z7Var.l0 = z7Var2.l0;
        z7Var.Y = z7Var2.Y;
        z7Var.Z = z7Var2.Z;
        z7Var.p0 = z7Var2.p0;
        return z7Var;
    }
}
