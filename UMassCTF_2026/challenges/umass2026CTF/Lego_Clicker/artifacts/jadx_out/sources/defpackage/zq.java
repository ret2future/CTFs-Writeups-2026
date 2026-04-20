package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zq extends hb {
    public static final Class o;
    public static final Constructor p;
    public static final Method q;
    public static final Method r;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            Class cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = null;
            method = null;
            method2 = null;
        }
        p = constructor;
        o = cls;
        q = method2;
        r = method;
    }

    public static boolean f0(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) q.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Typeface g0(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) o, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) r.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // defpackage.hb
    public final Typeface q(Context context, jc jcVar, Resources resources, int i) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        MappedByteBuffer map;
        FileInputStream fileInputStream;
        try {
            objNewInstance = p.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            for (kc kcVar : jcVar.a) {
                int i2 = kcVar.f;
                File fileI = hb.I(context);
                if (fileI != null) {
                    try {
                        if (hb.o(fileI, resources, i2)) {
                            try {
                                fileInputStream = new FileInputStream(fileI);
                            } catch (IOException unused2) {
                                map = null;
                            }
                            try {
                                FileChannel channel = fileInputStream.getChannel();
                                map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                                fileInputStream.close();
                                if (map != null && f0(objNewInstance, map, kcVar.e, kcVar.b, kcVar.c)) {
                                }
                            } finally {
                            }
                        }
                    } finally {
                        fileI.delete();
                    }
                }
                map = null;
                if (map != null) {
                }
            }
            return g0(objNewInstance);
        }
        return null;
    }

    @Override // defpackage.hb
    public final Typeface r(Context context, mc[] mcVarArr, int i) {
        Object objNewInstance;
        try {
            objNewInstance = p.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            ko koVar = new ko();
            int length = mcVarArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    mc mcVar = mcVarArr[i2];
                    Uri uri = mcVar.a;
                    ByteBuffer byteBufferM = (ByteBuffer) koVar.getOrDefault(uri, null);
                    if (byteBufferM == null) {
                        byteBufferM = hb.M(context, uri);
                        koVar.put(uri, byteBufferM);
                    }
                    if (byteBufferM == null || !f0(objNewInstance, byteBufferM, mcVar.b, mcVar.c, mcVar.d)) {
                        break;
                    }
                    i2++;
                } else {
                    Typeface typefaceG0 = g0(objNewInstance);
                    if (typefaceG0 != null) {
                        return Typeface.create(typefaceG0, i);
                    }
                }
            }
        }
        return null;
    }
}
