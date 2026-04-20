package defpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class ar extends yq {
    public final Class t;
    public final Constructor u;
    public final Method v;
    public final Method w;
    public final Method x;
    public final Method y;
    public final Method z;

    public ar() throws NoSuchMethodException {
        Method methodL0;
        Constructor<?> constructor;
        Method methodK0;
        Method method;
        Method method2;
        Method method3;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(null);
            methodK0 = k0(cls2);
            Class cls3 = Integer.TYPE;
            method = cls2.getMethod("addFontFromBuffer", ByteBuffer.class, cls3, FontVariationAxis[].class, cls3, cls3);
            method2 = cls2.getMethod("freeze", null);
            method3 = cls2.getMethod("abortCreation", null);
            methodL0 = l0(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e.getClass().getName()), e);
            methodL0 = null;
            constructor = null;
            methodK0 = null;
            method = null;
            method2 = null;
            method3 = null;
        }
        this.t = cls;
        this.u = constructor;
        this.v = methodK0;
        this.w = method;
        this.x = method2;
        this.y = method3;
        this.z = methodL0;
    }

    public static Method k0(Class cls) {
        Class cls2 = Boolean.TYPE;
        Class cls3 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls3, cls2, cls3, cls3, cls3, FontVariationAxis[].class);
    }

    public final boolean h0(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.v.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface i0(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.t, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.z.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean j0(Object obj) {
        try {
            return ((Boolean) this.x.invoke(obj, null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Method l0(Class cls) throws NoSuchMethodException {
        Class<?> cls2 = Array.newInstance((Class<?>) cls, 1).getClass();
        Class cls3 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", cls2, cls3, cls3);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // defpackage.yq, defpackage.hb
    public final Typeface q(Context context, jc jcVar, Resources resources, int i) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        Method method = this.v;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.q(context, jcVar, resources, i);
        }
        try {
            objNewInstance = this.u.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            kc[] kcVarArr = jcVar.a;
            int length = kcVarArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    kc kcVar = kcVarArr[i2];
                    String str = kcVar.a;
                    int i3 = kcVar.e;
                    int i4 = kcVar.b;
                    boolean z = kcVar.c;
                    FontVariationAxis[] fontVariationAxisArrFromFontVariationSettings = FontVariationAxis.fromFontVariationSettings(kcVar.d);
                    ar arVar = this;
                    Context context2 = context;
                    if (arVar.h0(context2, objNewInstance, str, i3, i4, z ? 1 : 0, fontVariationAxisArrFromFontVariationSettings)) {
                        i2++;
                        this = arVar;
                        context = context2;
                    } else {
                        try {
                            arVar.y.invoke(objNewInstance, null);
                            break;
                        } catch (IllegalAccessException | InvocationTargetException unused2) {
                        }
                    }
                } else {
                    ar arVar2 = this;
                    if (arVar2.j0(objNewInstance)) {
                        return arVar2.i0(objNewInstance);
                    }
                }
            }
        }
        return null;
    }

    @Override // defpackage.yq, defpackage.hb
    public final Typeface r(Context context, mc[] mcVarArr, int i) throws IOException {
        Object objNewInstance;
        Typeface typefaceI0;
        boolean zBooleanValue;
        if (mcVarArr.length >= 1) {
            Method method = this.v;
            if (method == null) {
                Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
            }
            try {
                if (method != null) {
                    HashMap map = new HashMap();
                    for (mc mcVar : mcVarArr) {
                        if (mcVar.e == 0) {
                            Uri uri = mcVar.a;
                            if (!map.containsKey(uri)) {
                                map.put(uri, hb.M(context, uri));
                            }
                        }
                    }
                    Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
                    try {
                        objNewInstance = this.u.newInstance(null);
                    } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
                        objNewInstance = null;
                    }
                    if (objNewInstance != null) {
                        int length = mcVarArr.length;
                        int i2 = 0;
                        boolean z = false;
                        while (true) {
                            Method method2 = this.y;
                            if (i2 < length) {
                                mc mcVar2 = mcVarArr[i2];
                                ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(mcVar2.a);
                                if (byteBuffer != null) {
                                    try {
                                        zBooleanValue = ((Boolean) this.w.invoke(objNewInstance, byteBuffer, Integer.valueOf(mcVar2.b), null, Integer.valueOf(mcVar2.c), Integer.valueOf(mcVar2.d ? 1 : 0))).booleanValue();
                                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                                        zBooleanValue = false;
                                    }
                                    if (!zBooleanValue) {
                                        method2.invoke(objNewInstance, null);
                                        break;
                                    }
                                    z = true;
                                }
                                i2++;
                                z = z;
                            } else if (!z) {
                                method2.invoke(objNewInstance, null);
                            } else if (j0(objNewInstance) && (typefaceI0 = i0(objNewInstance)) != null) {
                                return Typeface.create(typefaceI0, i);
                            }
                        }
                    }
                } else {
                    mc mcVarW = hb.w(i, mcVarArr);
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(mcVarW.a, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        try {
                            Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(mcVarW.c).setItalic(mcVarW.d).build();
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return typefaceBuild;
                        } finally {
                        }
                    }
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return null;
                    }
                }
            } catch (IOException | IllegalAccessException | InvocationTargetException unused3) {
            }
        }
        return null;
    }

    @Override // defpackage.hb
    public final Typeface s(Context context, Resources resources, int i, String str, int i2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        Method method = this.v;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.s(context, resources, i, str, i2);
        }
        try {
            objNewInstance = this.u.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            if (!h0(context, objNewInstance, str, 0, -1, -1, null)) {
                try {
                    this.y.invoke(objNewInstance, null);
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                }
            } else if (j0(objNewInstance)) {
                return i0(objNewInstance);
            }
        }
        return null;
    }
}
