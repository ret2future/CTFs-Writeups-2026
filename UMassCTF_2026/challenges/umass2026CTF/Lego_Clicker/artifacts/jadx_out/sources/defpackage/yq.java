package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class yq extends hb {
    public static Class o = null;
    public static Constructor p = null;
    public static Method q = null;
    public static Method r = null;
    public static boolean s = false;

    public static boolean f0(Object obj, String str, int i, boolean z) throws NoSuchMethodException {
        g0();
        try {
            return ((Boolean) q.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void g0() throws NoSuchMethodException {
        Method method;
        Class<?> cls;
        Method method2;
        if (s) {
            return;
        }
        s = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
            method = null;
            cls = null;
            method2 = null;
        }
        p = constructor;
        o = cls;
        q = method2;
        r = method;
    }

    @Override // defpackage.hb
    public Typeface q(Context context, jc jcVar, Resources resources, int i) throws NoSuchMethodException {
        g0();
        try {
            Object objNewInstance = p.newInstance(null);
            for (kc kcVar : jcVar.a) {
                File fileI = hb.I(context);
                if (fileI == null) {
                    return null;
                }
                try {
                    if (!hb.o(fileI, resources, kcVar.f)) {
                        return null;
                    }
                    if (!f0(objNewInstance, fileI.getPath(), kcVar.b, kcVar.c)) {
                        return null;
                    }
                    fileI.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    fileI.delete();
                }
            }
            g0();
            try {
                Object objNewInstance2 = Array.newInstance((Class<?>) o, 1);
                Array.set(objNewInstance2, 0, objNewInstance);
                return (Typeface) r.invoke(null, objNewInstance2);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // defpackage.hb
    public Typeface r(Context context, mc[] mcVarArr, int i) {
        Typeface typefaceCreateFromFile;
        String str;
        if (mcVarArr.length >= 1) {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(hb.w(i, mcVarArr).a, "r", null);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        try {
                            str = Os.readlink("/proc/self/fd/" + parcelFileDescriptorOpenFileDescriptor.getFd());
                        } finally {
                        }
                    } catch (ErrnoException unused) {
                    }
                    File file = OsConstants.S_ISREG(Os.stat(str).st_mode) ? new File(str) : null;
                    if (file != null && file.canRead()) {
                        Typeface typefaceCreateFromFile2 = Typeface.createFromFile(file);
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return typefaceCreateFromFile2;
                    }
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                    try {
                        File fileI = hb.I(context);
                        if (fileI == null) {
                            typefaceCreateFromFile = null;
                        } else {
                            try {
                                if (hb.p(fileI, fileInputStream)) {
                                    typefaceCreateFromFile = Typeface.createFromFile(fileI.getPath());
                                    fileI.delete();
                                }
                            } catch (RuntimeException unused2) {
                            } catch (Throwable th) {
                                fileI.delete();
                                throw th;
                            }
                            fileI.delete();
                            typefaceCreateFromFile = null;
                        }
                        fileInputStream.close();
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return typefaceCreateFromFile;
                    } finally {
                    }
                }
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return null;
                }
            } catch (IOException unused3) {
            }
        }
        return null;
    }
}
