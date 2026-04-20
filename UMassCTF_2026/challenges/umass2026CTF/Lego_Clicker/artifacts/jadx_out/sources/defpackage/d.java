package defpackage;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EdgeEffect;
import android.widget.TextView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static Method C;
    public static boolean D;
    public static final j5 n;
    public static j5 o;
    public static final Type[] a = new Type[0];
    public static final Object b = new Object();
    public static final float[][] c = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    public static final float[][] d = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    public static final float[] e = {95.047f, 100.0f, 108.883f};
    public static final float[][] f = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};
    public static final Object[] g = new Object[0];
    public static final int[] h = new int[0];
    public static final Object[] i = new Object[0];
    public static final j6 j = new j6();
    public static final String[] k = {"standard", "accelerate", "decelerate", "linear"};
    public static final String[] l = new String[0];
    public static final ac m = new ac(0);
    public static final String[] p = {"", "K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc"};
    public static final boolean[] q = new boolean[3];
    public static final fr r = new fr(24);
    public static final byte[] s = {112, 114, 111, 0};
    public static final byte[] t = {112, 114, 109, 0};
    public static final byte[] u = {48, 49, 53, 0};
    public static final byte[] v = {48, 49, 48, 0};
    public static final byte[] w = {48, 48, 57, 0};
    public static final byte[] x = {48, 48, 53, 0};
    public static final byte[] y = {48, 48, 49, 0};
    public static final byte[] z = {48, 48, 49, 0};
    public static final byte[] A = {48, 48, 50, 0};
    public static final Object B = new Object();
    public static final fr E = new fr(29);
    public static final rn F = new rn();
    public static final fr G = new fr(28);

    static {
        Object obj = null;
        n = new j5(obj, obj, obj);
    }

    public static Class A(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            i(rawType instanceof Class);
            return (Class) rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance((Class<?>) A(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return A(((WildcardType) type).getUpperBounds()[0]);
        }
        z6.i("Expected a Class, ParameterizedType, or GenericArrayType, but <", type, "> is of type ", type == null ? "null" : type.getClass().getName());
        return null;
    }

    public static final tn B(at atVar) {
        ArrayList arrayList = new ArrayList();
        hm.a.getClass();
        arrayList.add(new ws());
        ws[] wsVarArr = (ws[]) arrayList.toArray(new ws[0]);
        return (tn) new j5(atVar.getViewModelStore(), (ys) new e0(12, (ws[]) Arrays.copyOf(wsVarArr, wsVarArr.length)), atVar instanceof ve ? ((ve) atVar).getDefaultViewModelCreationExtras() : d9.b).i("androidx.lifecycle.internal.SavedStateHandlesVM", tn.class);
    }

    public static void C(int i2, a8 a8Var, k8 k8Var, boolean z2) {
        v7 v7Var;
        v7 v7Var2;
        boolean z3;
        v7 v7Var3;
        v7 v7Var4;
        if (k8Var.m) {
            return;
        }
        if (!(k8Var instanceof l8) && k8Var.x() && d(k8Var)) {
            l8.R(k8Var, a8Var, new j6());
        }
        v7 v7VarG = k8Var.g(2);
        v7 v7VarG2 = k8Var.g(4);
        int iC = v7VarG.c();
        int iC2 = v7VarG2.c();
        HashSet<v7> hashSet = v7VarG.a;
        if (hashSet != null && v7VarG.c) {
            for (v7 v7Var5 : hashSet) {
                k8 k8Var2 = v7Var5.d;
                int i3 = i2 + 1;
                boolean zD = d(k8Var2);
                v7 v7Var6 = k8Var2.H;
                v7 v7Var7 = k8Var2.J;
                if (k8Var2.x() && zD) {
                    z3 = true;
                    l8.R(k8Var2, a8Var, new j6());
                } else {
                    z3 = true;
                }
                boolean z4 = ((v7Var5 == v7Var6 && (v7Var4 = v7Var7.f) != null && v7Var4.c) || (v7Var5 == v7Var7 && (v7Var3 = v7Var6.f) != null && v7Var3.c)) ? z3 : false;
                int i4 = k8Var2.o0[0];
                if (i4 != 3 || zD) {
                    if (!k8Var2.x()) {
                        if (v7Var5 == v7Var6 && v7Var7.f == null) {
                            int iD = v7Var6.d() + iC;
                            k8Var2.F(iD, k8Var2.o() + iD);
                            C(i3, a8Var, k8Var2, z2);
                        } else if (v7Var5 == v7Var7 && v7Var6.f == null) {
                            int iD2 = iC - v7Var7.d();
                            k8Var2.F(iD2 - k8Var2.o(), iD2);
                            C(i3, a8Var, k8Var2, z2);
                        } else if (z4 && !k8Var2.v()) {
                            W(i3, a8Var, k8Var2, z2);
                        }
                    }
                } else if (i4 == 3 && k8Var2.v >= 0 && k8Var2.u >= 0 && (k8Var2.f0 == 8 || (k8Var2.r == 0 && k8Var2.V == 0.0f))) {
                    if (!k8Var2.v() && z4 && !k8Var2.v()) {
                        X(i3, k8Var, a8Var, k8Var2, z2);
                    }
                }
            }
        }
        if (k8Var instanceof te) {
            return;
        }
        HashSet<v7> hashSet2 = v7VarG2.a;
        if (hashSet2 != null && v7VarG2.c) {
            for (v7 v7Var8 : hashSet2) {
                k8 k8Var3 = v7Var8.d;
                int i5 = i2 + 1;
                boolean zD2 = d(k8Var3);
                v7 v7Var9 = k8Var3.H;
                v7 v7Var10 = k8Var3.J;
                if (k8Var3.x() && zD2) {
                    l8.R(k8Var3, a8Var, new j6());
                }
                boolean z5 = (v7Var8 == v7Var9 && (v7Var2 = v7Var10.f) != null && v7Var2.c) || (v7Var8 == v7Var10 && (v7Var = v7Var9.f) != null && v7Var.c);
                int i6 = k8Var3.o0[0];
                if (i6 != 3 || zD2) {
                    if (!k8Var3.x()) {
                        if (v7Var8 == v7Var9 && v7Var10.f == null) {
                            int iD3 = v7Var9.d() + iC2;
                            k8Var3.F(iD3, k8Var3.o() + iD3);
                            C(i5, a8Var, k8Var3, z2);
                        } else if (v7Var8 == v7Var10 && v7Var9.f == null) {
                            int iD4 = iC2 - v7Var10.d();
                            k8Var3.F(iD4 - k8Var3.o(), iD4);
                            C(i5, a8Var, k8Var3, z2);
                        } else if (z5 && !k8Var3.v()) {
                            W(i5, a8Var, k8Var3, z2);
                        }
                    }
                } else if (i6 == 3 && k8Var3.v >= 0 && k8Var3.u >= 0) {
                    if (k8Var3.f0 == 8 || (k8Var3.r == 0 && k8Var3.V == 0.0f)) {
                        if (!k8Var3.v() && z5 && !k8Var3.v()) {
                            X(i5, k8Var, a8Var, k8Var3, z2);
                        }
                    }
                }
            }
        }
        k8Var.m = true;
    }

    public static int D(float f2) {
        if (f2 < 1.0f) {
            return -16777216;
        }
        if (f2 > 99.0f) {
            return -1;
        }
        float f3 = (f2 + 16.0f) / 116.0f;
        float f4 = f2 > 8.0f ? f3 * f3 * f3 : f2 / 903.2963f;
        float f5 = f3 * f3 * f3;
        boolean z2 = f5 > 0.008856452f;
        float f6 = z2 ? f5 : ((f3 * 116.0f) - 16.0f) / 903.2963f;
        if (!z2) {
            f5 = ((f3 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = e;
        return j7.a(f6 * fArr[0], f4 * fArr[1], f5 * fArr[2]);
    }

    public static float E(int i2) {
        float f2 = i2 / 255.0f;
        return (f2 <= 0.04045f ? f2 / 12.92f : (float) Math.pow((f2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    public static void F(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static void G(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static float J(EdgeEffect edgeEffect, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 31) {
            return ma.c(edgeEffect, f2, f3);
        }
        la.a(edgeEffect, f2, f3);
        return f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void K(android.content.Context r5, java.lang.String r6) {
        /*
            java.lang.Object r0 = defpackage.d.b
            monitor-enter(r0)
            java.lang.String r1 = ""
            boolean r1 = r6.equals(r1)     // Catch: java.lang.Throwable -> L12
            if (r1 == 0) goto L14
            java.lang.String r6 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r5.deleteFile(r6)     // Catch: java.lang.Throwable -> L12
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L12
            return
        L12:
            r5 = move-exception
            goto L60
        L14:
            java.lang.String r1 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r2 = 0
            java.io.FileOutputStream r5 = r5.openFileOutput(r1, r2)     // Catch: java.lang.Throwable -> L12 java.io.FileNotFoundException -> L57
            org.xmlpull.v1.XmlSerializer r1 = android.util.Xml.newSerializer()     // Catch: java.lang.Throwable -> L12
            r2 = 0
            r1.setOutput(r5, r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r3 = "UTF-8"
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r1.startDocument(r3, r4)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r3 = "locales"
            r1.startTag(r2, r3)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r3 = "application_locales"
            r1.attribute(r2, r3, r6)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r6 = "locales"
            r1.endTag(r2, r6)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r1.endDocument()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            if (r5 == 0) goto L4f
        L3e:
            r5.close()     // Catch: java.lang.Throwable -> L12 java.io.IOException -> L4f
            goto L4f
        L42:
            r6 = move-exception
            goto L51
        L44:
            r6 = move-exception
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = "Storing App Locales : Failed to persist app-locales in storage "
            android.util.Log.w(r1, r2, r6)     // Catch: java.lang.Throwable -> L42
            if (r5 == 0) goto L4f
            goto L3e
        L4f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L12
            goto L5f
        L51:
            if (r5 == 0) goto L56
            r5.close()     // Catch: java.lang.Throwable -> L12 java.io.IOException -> L56
        L56:
            throw r6     // Catch: java.lang.Throwable -> L12
        L57:
            java.lang.String r5 = "AppLocalesStorageHelper"
            java.lang.String r6 = "Storing App Locales : FileNotFoundException: Cannot open file androidx.appcompat.app.AppCompatDelegate.application_locales_record_file for writing "
            android.util.Log.w(r5, r6)     // Catch: java.lang.Throwable -> L12
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L12
        L5f:
            return
        L60:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L12
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d.K(android.content.Context, java.lang.String):void");
    }

    public static int[] N(ByteArrayInputStream byteArrayInputStream, int i2) {
        int[] iArr = new int[i2];
        int iS = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            iS += (int) hb.S(byteArrayInputStream, 2);
            iArr[i3] = iS;
        }
        return iArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        r1 = r3.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0046 A[EXC_TOP_SPLITTER, PHI: r1
      0x0046: PHI (r1v2 java.lang.String) = (r1v0 java.lang.String), (r1v4 java.lang.String) binds: [B:29:0x0053, B:23:0x0044] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String O(android.content.Context r8) {
        /*
            java.lang.Object r0 = defpackage.d.b
            monitor-enter(r0)
            java.lang.String r1 = ""
            java.lang.String r2 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.io.FileInputStream r2 = r8.openFileInput(r2)     // Catch: java.lang.Throwable -> L4a java.io.FileNotFoundException -> L6a
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
            int r4 = r3.getDepth()     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
        L18:
            int r5 = r3.next()     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
            r6 = 1
            if (r5 == r6) goto L44
            r6 = 3
            if (r5 != r6) goto L2b
            int r7 = r3.getDepth()     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
            if (r7 <= r4) goto L44
            goto L2b
        L29:
            r8 = move-exception
            goto L64
        L2b:
            if (r5 == r6) goto L18
            r6 = 4
            if (r5 != r6) goto L31
            goto L18
        L31:
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
            java.lang.String r6 = "locales"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
            if (r5 == 0) goto L18
            java.lang.String r4 = "application_locales"
            r5 = 0
            java.lang.String r1 = r3.getAttributeValue(r5, r4)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L4c
        L44:
            if (r2 == 0) goto L56
        L46:
            r2.close()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L56
            goto L56
        L4a:
            r8 = move-exception
            goto L6c
        L4c:
            java.lang.String r3 = "AppLocalesStorageHelper"
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r3, r4)     // Catch: java.lang.Throwable -> L29
            if (r2 == 0) goto L56
            goto L46
        L56:
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L4a
            if (r2 != 0) goto L5d
            goto L62
        L5d:
            java.lang.String r2 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r8.deleteFile(r2)     // Catch: java.lang.Throwable -> L4a
        L62:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4a
            return r1
        L64:
            if (r2 == 0) goto L69
            r2.close()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L69
        L69:
            throw r8     // Catch: java.lang.Throwable -> L4a
        L6a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4a
            return r1
        L6c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4a
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d.O(android.content.Context):java.lang.String");
    }

    public static y9[] P(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, y9[] y9VarArr) throws IOException {
        byte[] bArr3 = z;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, A)) {
                z6.o("Unsupported meta version");
                return null;
            }
            int iS = (int) hb.S(fileInputStream, 2);
            byte[] bArrR = hb.R(fileInputStream, (int) hb.S(fileInputStream, 4), (int) hb.S(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                z6.o("Content found after the end of file");
                return null;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrR);
            try {
                y9[] y9VarArrR = R(byteArrayInputStream, bArr2, iS, y9VarArr);
                byteArrayInputStream.close();
                return y9VarArrR;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(u, bArr2)) {
            z6.o("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
            return null;
        }
        if (!Arrays.equals(bArr, bArr3)) {
            z6.o("Unsupported meta version");
            return null;
        }
        int iS2 = (int) hb.S(fileInputStream, 1);
        byte[] bArrR2 = hb.R(fileInputStream, (int) hb.S(fileInputStream, 4), (int) hb.S(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            z6.o("Content found after the end of file");
            return null;
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrR2);
        try {
            y9[] y9VarArrQ = Q(byteArrayInputStream2, iS2, y9VarArr);
            byteArrayInputStream2.close();
            return y9VarArrQ;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static y9[] Q(ByteArrayInputStream byteArrayInputStream, int i2, y9[] y9VarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new y9[0];
        }
        if (i2 != y9VarArr.length) {
            z6.o("Mismatched number of dex files found in metadata");
            return null;
        }
        String[] strArr = new String[i2];
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iS = (int) hb.S(byteArrayInputStream, 2);
            iArr[i3] = (int) hb.S(byteArrayInputStream, 2);
            strArr[i3] = new String(hb.P(byteArrayInputStream, iS), StandardCharsets.UTF_8);
        }
        for (int i4 = 0; i4 < i2; i4++) {
            y9 y9Var = y9VarArr[i4];
            if (!y9Var.b.equals(strArr[i4])) {
                z6.o("Order of dexfiles in metadata did not match baseline");
                return null;
            }
            int i5 = iArr[i4];
            y9Var.e = i5;
            y9Var.h = N(byteArrayInputStream, i5);
        }
        return y9VarArr;
    }

    public static y9[] R(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i2, y9[] y9VarArr) {
        y9 y9Var;
        if (byteArrayInputStream.available() == 0) {
            return new y9[0];
        }
        if (i2 != y9VarArr.length) {
            z6.o("Mismatched number of dex files found in metadata");
            return null;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            hb.S(byteArrayInputStream, 2);
            String str = new String(hb.P(byteArrayInputStream, (int) hb.S(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jS = hb.S(byteArrayInputStream, 4);
            int iS = (int) hb.S(byteArrayInputStream, 2);
            if (y9VarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                for (int i4 = 0; i4 < y9VarArr.length; i4++) {
                    if (y9VarArr[i4].b.equals(strSubstring)) {
                        y9Var = y9VarArr[i4];
                        break;
                    }
                }
                y9Var = null;
            } else {
                y9Var = null;
            }
            if (y9Var == null) {
                z6.o("Missing profile key: ".concat(str));
                return null;
            }
            y9Var.d = jS;
            int[] iArrN = N(byteArrayInputStream, iS);
            if (Arrays.equals(bArr, y)) {
                y9Var.e = iS;
                y9Var.h = iArrN;
            }
        }
        return y9VarArr;
    }

    public static y9[] S(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, v)) {
            z6.o("Unsupported version");
            return null;
        }
        int iS = (int) hb.S(fileInputStream, 1);
        byte[] bArrR = hb.R(fileInputStream, (int) hb.S(fileInputStream, 4), (int) hb.S(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            z6.o("Content found after the end of file");
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrR);
        try {
            y9[] y9VarArrT = T(byteArrayInputStream, str, iS);
            byteArrayInputStream.close();
            return y9VarArrT;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static y9[] T(ByteArrayInputStream byteArrayInputStream, String str, int i2) throws IOException {
        int i3 = 0;
        if (byteArrayInputStream.available() == 0) {
            return new y9[0];
        }
        y9[] y9VarArr = new y9[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            int iS = (int) hb.S(byteArrayInputStream, 2);
            int iS2 = (int) hb.S(byteArrayInputStream, 2);
            y9VarArr[i4] = new y9(str, new String(hb.P(byteArrayInputStream, iS), StandardCharsets.UTF_8), hb.S(byteArrayInputStream, 4), iS2, (int) hb.S(byteArrayInputStream, 4), (int) hb.S(byteArrayInputStream, 4), new int[iS2], new TreeMap());
        }
        int i5 = 0;
        while (i5 < i2) {
            y9 y9Var = y9VarArr[i5];
            int iAvailable = byteArrayInputStream.available();
            int i6 = y9Var.f;
            int i7 = y9Var.g;
            TreeMap treeMap = y9Var.i;
            int i8 = iAvailable - i6;
            int iS3 = i3;
            while (byteArrayInputStream.available() > i8) {
                iS3 += (int) hb.S(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iS3), 1);
                int iS4 = (int) hb.S(byteArrayInputStream, 2);
                while (iS4 > 0) {
                    hb.S(byteArrayInputStream, 2);
                    int iS5 = (int) hb.S(byteArrayInputStream, 1);
                    if (iS5 != 6 && iS5 != 7) {
                        while (iS5 > 0) {
                            hb.S(byteArrayInputStream, 1);
                            int i9 = i3;
                            int i10 = i5;
                            for (int iS6 = (int) hb.S(byteArrayInputStream, 1); iS6 > 0; iS6--) {
                                hb.S(byteArrayInputStream, 2);
                            }
                            iS5--;
                            i3 = i9;
                            i5 = i10;
                        }
                    }
                    iS4--;
                    i3 = i3;
                    i5 = i5;
                }
            }
            int i11 = i3;
            int i12 = i5;
            if (byteArrayInputStream.available() != i8) {
                z6.o("Read too much data during profile line parse");
                return null;
            }
            y9Var.h = N(byteArrayInputStream, y9Var.e);
            BitSet bitSetValueOf = BitSet.valueOf(hb.P(byteArrayInputStream, (((i7 * 2) + 7) & (-8)) / 8));
            for (int i13 = i11; i13 < i7; i13++) {
                int i14 = bitSetValueOf.get(i13) ? 2 : i11;
                if (bitSetValueOf.get(i13 + i7)) {
                    i14 |= 4;
                }
                if (i14 != 0) {
                    Integer numValueOf = (Integer) treeMap.get(Integer.valueOf(i13));
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(i11);
                    }
                    treeMap.put(Integer.valueOf(i13), Integer.valueOf(i14 | numValueOf.intValue()));
                }
            }
            i5 = i12 + 1;
            i3 = i11;
        }
        return y9VarArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x013c A[EDGE_INSN: B:85:0x013c->B:79:0x013c BREAK  A[LOOP:0: B:3:0x0002->B:88:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[LOOP:0: B:3:0x0002->B:88:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.Object, java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v17, types: [java.lang.reflect.Type[]] */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v3, types: [c] */
    /* JADX WARN: Type inference failed for: r11v4, types: [c] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Type U(java.lang.reflect.Type r9, java.lang.Class r10, java.lang.reflect.Type r11, java.util.HashMap r12) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d.U(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.HashMap):java.lang.reflect.Type");
    }

    public static void V(EditorInfo editorInfo, CharSequence charSequence, int i2, int i3) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i2);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i3);
    }

    public static void W(int i2, a8 a8Var, k8 k8Var, boolean z2) {
        float f2 = k8Var.c0;
        v7 v7Var = k8Var.H;
        int iC = v7Var.f.c();
        v7 v7Var2 = k8Var.J;
        int iC2 = v7Var2.f.c();
        int iD = v7Var.d() + iC;
        int iD2 = iC2 - v7Var2.d();
        if (iC == iC2) {
            f2 = 0.5f;
        } else {
            iC = iD;
            iC2 = iD2;
        }
        int iO = k8Var.o();
        int i3 = (iC2 - iC) - iO;
        if (iC > iC2) {
            i3 = (iC - iC2) - iO;
        }
        int i4 = ((int) (i3 > 0 ? (f2 * i3) + 0.5f : f2 * i3)) + iC;
        int i5 = i4 + iO;
        if (iC > iC2) {
            i5 = i4 - iO;
        }
        k8Var.F(i4, i5);
        C(i2 + 1, a8Var, k8Var, z2);
    }

    public static void X(int i2, k8 k8Var, a8 a8Var, k8 k8Var2, boolean z2) {
        float f2 = k8Var2.c0;
        v7 v7Var = k8Var2.H;
        int iD = v7Var.d() + v7Var.f.c();
        v7 v7Var2 = k8Var2.J;
        int iC = v7Var2.f.c() - v7Var2.d();
        if (iC >= iD) {
            int iO = k8Var2.o();
            if (k8Var2.f0 != 8) {
                int i3 = k8Var2.r;
                if (i3 == 2) {
                    iO = (int) (k8Var2.c0 * 0.5f * (k8Var instanceof l8 ? k8Var.o() : k8Var.S.o()));
                } else if (i3 == 0) {
                    iO = iC - iD;
                }
                iO = Math.max(k8Var2.u, iO);
                int i4 = k8Var2.v;
                if (i4 > 0) {
                    iO = Math.min(i4, iO);
                }
            }
            int i5 = iD + ((int) ((f2 * ((iC - iD) - iO)) + 0.5f));
            k8Var2.F(i5, iO + i5);
            C(i2 + 1, a8Var, k8Var2, z2);
        }
    }

    public static void Y(int i2, a8 a8Var, k8 k8Var) {
        float f2 = k8Var.d0;
        v7 v7Var = k8Var.I;
        int iC = v7Var.f.c();
        v7 v7Var2 = k8Var.K;
        int iC2 = v7Var2.f.c();
        int iD = v7Var.d() + iC;
        int iD2 = iC2 - v7Var2.d();
        if (iC == iC2) {
            f2 = 0.5f;
        } else {
            iC = iD;
            iC2 = iD2;
        }
        int i3 = k8Var.i();
        int i4 = (iC2 - iC) - i3;
        if (iC > iC2) {
            i4 = (iC - iC2) - i3;
        }
        int i5 = (int) (i4 > 0 ? (f2 * i4) + 0.5f : f2 * i4);
        int i6 = iC + i5;
        int i7 = i6 + i3;
        if (iC > iC2) {
            i6 = iC - i5;
            i7 = i6 - i3;
        }
        k8Var.G(i6, i7);
        e0(i2 + 1, a8Var, k8Var);
    }

    public static void Z(int i2, k8 k8Var, a8 a8Var, k8 k8Var2) {
        float f2 = k8Var2.d0;
        v7 v7Var = k8Var2.I;
        int iD = v7Var.d() + v7Var.f.c();
        v7 v7Var2 = k8Var2.K;
        int iC = v7Var2.f.c() - v7Var2.d();
        if (iC >= iD) {
            int i3 = k8Var2.i();
            if (k8Var2.f0 != 8) {
                int i4 = k8Var2.s;
                if (i4 == 2) {
                    i3 = (int) (f2 * 0.5f * (k8Var instanceof l8 ? k8Var.i() : k8Var.S.i()));
                } else if (i4 == 0) {
                    i3 = iC - iD;
                }
                i3 = Math.max(k8Var2.x, i3);
                int i5 = k8Var2.y;
                if (i5 > 0) {
                    i3 = Math.min(i5, i3);
                }
            }
            int i6 = iD + ((int) ((f2 * ((iC - iD) - i3)) + 0.5f));
            k8Var2.G(i6, i3 + i6);
            e0(i2 + 1, a8Var, k8Var2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:189:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x06c3  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x06c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:436:0x06e3 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(defpackage.l8 r40, defpackage.tg r41, java.util.ArrayList r42, int r43) {
        /*
            Method dump skipped, instruction units count: 1776
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d.a(l8, tg, java.util.ArrayList, int):void");
    }

    public static final Object[] a0(Collection collection) {
        int size = collection.size();
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArrCopyOf = new Object[size];
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                    } else if (!it.hasNext()) {
                        return Arrays.copyOf(objArrCopyOf, i3);
                    }
                    i2 = i3;
                }
            }
        }
        return g;
    }

    public static int b(int i2, int i3, int[] iArr) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return ~i5;
    }

    public static final Object[] b0(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        int size = collection.size();
        int i2 = 0;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                if (size <= objArr.length) {
                    objArrCopyOf = objArr;
                } else {
                    Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    objNewInstance.getClass();
                    objArrCopyOf = (Object[]) objNewInstance;
                }
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                    } else if (!it.hasNext()) {
                        if (objArrCopyOf != objArr) {
                            return Arrays.copyOf(objArrCopyOf, i3);
                        }
                        objArr[i3] = null;
                        return objArr;
                    }
                    i2 = i3;
                }
            } else if (objArr.length > 0) {
                objArr[0] = null;
            }
        } else if (objArr.length > 0) {
            objArr[0] = null;
            return objArr;
        }
        return objArr;
    }

    public static int c(long[] jArr, int i2, long j2) {
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = jArr[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else {
                if (j3 <= j2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    /* JADX WARN: Finally extract failed */
    public static boolean c0(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, y9[] y9VarArr) throws IOException {
        long j2;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = u;
        int i2 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = v;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrN = n(y9VarArr, bArr3);
                hb.d0(byteArrayOutputStream, y9VarArr.length, 1);
                hb.d0(byteArrayOutputStream, bArrN.length, 4);
                byte[] bArrK = hb.k(bArrN);
                hb.d0(byteArrayOutputStream, bArrK.length, 4);
                byteArrayOutputStream.write(bArrK);
                return true;
            }
            byte[] bArr4 = x;
            if (Arrays.equals(bArr, bArr4)) {
                hb.d0(byteArrayOutputStream, y9VarArr.length, 1);
                for (y9 y9Var : y9VarArr) {
                    int size = y9Var.i.size() * 4;
                    String strT = t(y9Var.a, y9Var.b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    hb.e0(byteArrayOutputStream, strT.getBytes(charset).length);
                    hb.e0(byteArrayOutputStream, y9Var.h.length);
                    hb.d0(byteArrayOutputStream, size, 4);
                    hb.d0(byteArrayOutputStream, y9Var.c, 4);
                    byteArrayOutputStream.write(strT.getBytes(charset));
                    Iterator it = y9Var.i.keySet().iterator();
                    while (it.hasNext()) {
                        hb.e0(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        hb.e0(byteArrayOutputStream, 0);
                    }
                    for (int i3 : y9Var.h) {
                        hb.e0(byteArrayOutputStream, i3);
                    }
                }
                return true;
            }
            byte[] bArr5 = w;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrN2 = n(y9VarArr, bArr5);
                hb.d0(byteArrayOutputStream, y9VarArr.length, 1);
                hb.d0(byteArrayOutputStream, bArrN2.length, 4);
                byte[] bArrK2 = hb.k(bArrN2);
                hb.d0(byteArrayOutputStream, bArrK2.length, 4);
                byteArrayOutputStream.write(bArrK2);
                return true;
            }
            byte[] bArr6 = y;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            hb.e0(byteArrayOutputStream, y9VarArr.length);
            for (y9 y9Var2 : y9VarArr) {
                String str = y9Var2.a;
                TreeMap treeMap = y9Var2.i;
                String strT2 = t(str, y9Var2.b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                hb.e0(byteArrayOutputStream, strT2.getBytes(charset2).length);
                hb.e0(byteArrayOutputStream, treeMap.size());
                hb.e0(byteArrayOutputStream, y9Var2.h.length);
                hb.d0(byteArrayOutputStream, y9Var2.c, 4);
                byteArrayOutputStream.write(strT2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    hb.e0(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i4 : y9Var2.h) {
                    hb.e0(byteArrayOutputStream, i4);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            hb.e0(byteArrayOutputStream2, y9VarArr.length);
            int i5 = 2;
            int i6 = 2;
            for (y9 y9Var3 : y9VarArr) {
                hb.d0(byteArrayOutputStream2, y9Var3.c, 4);
                hb.d0(byteArrayOutputStream2, y9Var3.d, 4);
                hb.d0(byteArrayOutputStream2, y9Var3.g, 4);
                String strT3 = t(y9Var3.a, y9Var3.b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strT3.getBytes(charset3).length;
                hb.e0(byteArrayOutputStream2, length2);
                i6 = i6 + 14 + length2;
                byteArrayOutputStream2.write(strT3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i6 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i6 + ", does not match actual size " + byteArray.length);
            }
            fu fuVar = new fu(1, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(fuVar);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i7 = 0;
            int i8 = 0;
            while (i7 < y9VarArr.length) {
                try {
                    y9 y9Var4 = y9VarArr[i7];
                    hb.e0(byteArrayOutputStream3, i7);
                    hb.e0(byteArrayOutputStream3, y9Var4.e);
                    i8 = i8 + 4 + (y9Var4.e * i5);
                    int[] iArr = y9Var4.h;
                    int length3 = iArr.length;
                    int i9 = i2;
                    while (i2 < length3) {
                        int i10 = iArr[i2];
                        hb.e0(byteArrayOutputStream3, i10 - i9);
                        i2++;
                        i5 = i5;
                        i9 = i10;
                    }
                    i7++;
                    i2 = 0;
                } catch (Throwable th) {
                }
            }
            int i11 = i5;
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i8 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i8 + ", does not match actual size " + byteArray2.length);
            }
            fu fuVar2 = new fu(3, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(fuVar2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i12 = 0;
            int i13 = 0;
            while (i12 < y9VarArr.length) {
                try {
                    y9 y9Var5 = y9VarArr[i12];
                    Iterator it3 = y9Var5.i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        h0(byteArrayOutputStream4, iIntValue, y9Var5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            i0(byteArrayOutputStream4, y9Var5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            hb.e0(byteArrayOutputStream3, i12);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i14 = i13 + 6;
                            ArrayList arrayList4 = arrayList3;
                            hb.d0(byteArrayOutputStream3, length4, 4);
                            hb.e0(byteArrayOutputStream3, iIntValue);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i13 = i14 + length4;
                            i12++;
                            arrayList3 = arrayList4;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
            if (i13 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i13 + ", does not match actual size " + byteArray5.length);
            }
            fu fuVar3 = new fu(4, byteArray5, true);
            byteArrayOutputStream3.close();
            arrayList2.add(fuVar3);
            long size2 = 12 + ((long) (arrayList2.size() * 16));
            hb.d0(byteArrayOutputStream, arrayList2.size(), 4);
            int i15 = 0;
            while (i15 < arrayList2.size()) {
                fu fuVar4 = (fu) arrayList2.get(i15);
                int i16 = fuVar4.a;
                byte[] bArr7 = fuVar4.b;
                int i17 = i11;
                if (i16 == 1) {
                    j2 = 0;
                } else if (i16 == i17) {
                    j2 = 1;
                } else if (i16 == 3) {
                    j2 = 2;
                } else if (i16 == 4) {
                    j2 = 3;
                } else {
                    if (i16 != 5) {
                        throw null;
                    }
                    j2 = 4;
                }
                hb.d0(byteArrayOutputStream, j2, 4);
                hb.d0(byteArrayOutputStream, size2, 4);
                if (fuVar4.c) {
                    long length5 = bArr7.length;
                    byte[] bArrK3 = hb.k(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrK3);
                    hb.d0(byteArrayOutputStream, bArrK3.length, 4);
                    hb.d0(byteArrayOutputStream, length5, 4);
                    length = bArrK3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    hb.d0(byteArrayOutputStream, bArr7.length, 4);
                    hb.d0(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i15++;
                arrayList5 = arrayList;
                i11 = i17;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i18 = 0; i18 < arrayList6.size(); i18++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i18));
            }
            return true;
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream2.close();
                throw th3;
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
                throw th3;
            }
        }
    }

    public static boolean d(k8 k8Var) {
        int[] iArr = k8Var.o0;
        int i2 = iArr[0];
        int i3 = iArr[1];
        k8 k8Var2 = k8Var.S;
        l8 l8Var = k8Var2 != null ? (l8) k8Var2 : null;
        if (l8Var != null) {
            int i4 = l8Var.o0[0];
        }
        if (l8Var != null) {
            int i5 = l8Var.o0[1];
        }
        boolean z2 = i2 == 1 || k8Var.y() || i2 == 2 || (i2 == 3 && k8Var.r == 0 && k8Var.V == 0.0f && k8Var.r(0)) || (i2 == 3 && k8Var.r == 1 && k8Var.s(0, k8Var.o()));
        boolean z3 = i3 == 1 || k8Var.z() || i3 == 2 || (i3 == 3 && k8Var.s == 0 && k8Var.V == 0.0f && k8Var.r(1)) || (i3 == 3 && k8Var.s == 1 && k8Var.s(1, k8Var.i()));
        return (k8Var.V > 0.0f && (z2 || z3)) || (z2 && z3);
    }

    public static String d0(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Type e(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new a(e(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new a(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static void e0(int i2, a8 a8Var, k8 k8Var) {
        boolean z2;
        v7 v7Var;
        v7 v7Var2;
        v7 v7Var3;
        v7 v7Var4;
        if (k8Var.n) {
            return;
        }
        if (!(k8Var instanceof l8) && k8Var.x() && d(k8Var)) {
            l8.R(k8Var, a8Var, new j6());
        }
        v7 v7VarG = k8Var.g(3);
        v7 v7VarG2 = k8Var.g(5);
        int iC = v7VarG.c();
        int iC2 = v7VarG2.c();
        HashSet<v7> hashSet = v7VarG.a;
        if (hashSet != null && v7VarG.c) {
            for (v7 v7Var5 : hashSet) {
                k8 k8Var2 = v7Var5.d;
                int i3 = i2 + 1;
                boolean zD = d(k8Var2);
                v7 v7Var6 = k8Var2.I;
                v7 v7Var7 = k8Var2.K;
                if (k8Var2.x() && zD) {
                    l8.R(k8Var2, a8Var, new j6());
                }
                boolean z3 = (v7Var5 == v7Var6 && (v7Var4 = v7Var7.f) != null && v7Var4.c) || (v7Var5 == v7Var7 && (v7Var3 = v7Var6.f) != null && v7Var3.c);
                int i4 = k8Var2.o0[1];
                if (i4 != 3 || zD) {
                    if (!k8Var2.x()) {
                        if (v7Var5 == v7Var6 && v7Var7.f == null) {
                            int iD = v7Var6.d() + iC;
                            k8Var2.G(iD, k8Var2.i() + iD);
                            e0(i3, a8Var, k8Var2);
                        } else if (v7Var5 == v7Var7 && v7Var6.f == null) {
                            int iD2 = iC - v7Var7.d();
                            k8Var2.G(iD2 - k8Var2.i(), iD2);
                            e0(i3, a8Var, k8Var2);
                        } else if (z3 && !k8Var2.w()) {
                            Y(i3, a8Var, k8Var2);
                        }
                    }
                } else if (i4 == 3 && k8Var2.y >= 0 && k8Var2.x >= 0 && (k8Var2.f0 == 8 || (k8Var2.s == 0 && k8Var2.V == 0.0f))) {
                    if (!k8Var2.w() && z3 && !k8Var2.w()) {
                        Z(i3, k8Var, a8Var, k8Var2);
                    }
                }
            }
        }
        boolean z4 = true;
        z4 = true;
        z4 = true;
        if (k8Var instanceof te) {
            return;
        }
        HashSet<v7> hashSet2 = v7VarG2.a;
        if (hashSet2 != null && v7VarG2.c) {
            for (v7 v7Var8 : hashSet2) {
                k8 k8Var3 = v7Var8.d;
                int i5 = i2 + 1;
                boolean zD2 = d(k8Var3);
                v7 v7Var9 = k8Var3.I;
                v7 v7Var10 = k8Var3.K;
                if (k8Var3.x() && zD2) {
                    l8.R(k8Var3, a8Var, new j6());
                }
                boolean z5 = (v7Var8 == v7Var9 && (v7Var2 = v7Var10.f) != null && v7Var2.c) || (v7Var8 == v7Var10 && (v7Var = v7Var9.f) != null && v7Var.c);
                int i6 = k8Var3.o0[1];
                if (i6 != 3 || zD2) {
                    if (!k8Var3.x()) {
                        if (v7Var8 == v7Var9 && v7Var10.f == null) {
                            int iD3 = v7Var9.d() + iC2;
                            k8Var3.G(iD3, k8Var3.i() + iD3);
                            e0(i5, a8Var, k8Var3);
                        } else if (v7Var8 == v7Var10 && v7Var9.f == null) {
                            int iD4 = iC2 - v7Var10.d();
                            k8Var3.G(iD4 - k8Var3.i(), iD4);
                            e0(i5, a8Var, k8Var3);
                        } else if (z5 && !k8Var3.w()) {
                            Y(i5, a8Var, k8Var3);
                        }
                    }
                } else if (i6 == 3 && k8Var3.y >= 0 && k8Var3.x >= 0 && (k8Var3.f0 == 8 || (k8Var3.s == 0 && k8Var3.V == 0.0f))) {
                    if (!k8Var3.w() && z5 && !k8Var3.w()) {
                        Z(i5, k8Var, a8Var, k8Var3);
                    }
                }
            }
        }
        v7 v7VarG3 = k8Var.g(6);
        if (v7VarG3.a != null && v7VarG3.c) {
            int iC3 = v7VarG3.c();
            for (v7 v7Var11 : v7VarG3.a) {
                k8 k8Var4 = v7Var11.d;
                int i7 = i2 + 1;
                boolean zD3 = d(k8Var4);
                v7 v7Var12 = k8Var4.L;
                if (k8Var4.x() && zD3) {
                    l8.R(k8Var4, a8Var, new j6());
                }
                if (k8Var4.o0[z4 ? 1 : 0] != 3 || zD3) {
                    if (!k8Var4.x()) {
                        if (v7Var11 == v7Var12) {
                            int iD5 = v7Var11.d() + iC3;
                            if (k8Var4.E) {
                                int i8 = iD5 - k8Var4.Z;
                                int i9 = k8Var4.U + i8;
                                k8Var4.Y = i8;
                                k8Var4.I.i(i8);
                                k8Var4.K.i(i9);
                                v7Var12.i(iD5);
                                z2 = z4 ? 1 : 0;
                                k8Var4.l = z2;
                            } else {
                                z2 = z4 ? 1 : 0;
                            }
                            e0(i7, a8Var, k8Var4);
                        }
                        z4 = z2;
                    }
                }
                z2 = z4 ? 1 : 0;
                z4 = z2;
            }
        }
        k8Var.n = z4;
    }

    public static void f0(ByteArrayOutputStream byteArrayOutputStream, y9 y9Var) throws IOException {
        i0(byteArrayOutputStream, y9Var);
        int i2 = y9Var.g;
        int[] iArr = y9Var.h;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = iArr[i3];
            hb.e0(byteArrayOutputStream, i5 - i4);
            i3++;
            i4 = i5;
        }
        byte[] bArr = new byte[(((i2 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : y9Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i6 = iIntValue / 8;
                bArr[i6] = (byte) (bArr[i6] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i7 = iIntValue + i2;
                int i8 = i7 / 8;
                bArr[i8] = (byte) ((1 << (i7 % 8)) | bArr[i8]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void g0(ByteArrayOutputStream byteArrayOutputStream, y9 y9Var, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        hb.e0(byteArrayOutputStream, str.getBytes(charset).length);
        hb.e0(byteArrayOutputStream, y9Var.e);
        hb.d0(byteArrayOutputStream, y9Var.f, 4);
        hb.d0(byteArrayOutputStream, y9Var.c, 4);
        hb.d0(byteArrayOutputStream, y9Var.g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void h0(ByteArrayOutputStream byteArrayOutputStream, int i2, y9 y9Var) throws IOException {
        int i3 = y9Var.g;
        byte[] bArr = new byte[(((Integer.bitCount(i2 & (-2)) * i3) + 7) & (-8)) / 8];
        for (Map.Entry entry : y9Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i4 = 0;
            for (int i5 = 1; i5 <= 4; i5 <<= 1) {
                if (i5 != 1 && (i5 & i2) != 0) {
                    if ((i5 & iIntValue2) == i5) {
                        int i6 = (i4 * i3) + iIntValue;
                        int i7 = i6 / 8;
                        bArr[i7] = (byte) ((1 << (i6 % 8)) | bArr[i7]);
                    }
                    i4++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void i(boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException();
        }
    }

    public static void i0(ByteArrayOutputStream byteArrayOutputStream, y9 y9Var) {
        int i2 = 0;
        for (Map.Entry entry : y9Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                hb.e0(byteArrayOutputStream, iIntValue - i2);
                hb.e0(byteArrayOutputStream, 0);
                i2 = iIntValue;
            }
        }
    }

    public static void j(l8 l8Var, tg tgVar, k8 k8Var) {
        k8Var.o = -1;
        v7 v7Var = k8Var.L;
        int[] iArr = k8Var.o0;
        v7 v7Var2 = k8Var.K;
        v7 v7Var3 = k8Var.I;
        v7 v7Var4 = k8Var.J;
        v7 v7Var5 = k8Var.H;
        k8Var.p = -1;
        int[] iArr2 = l8Var.o0;
        if (iArr2[0] != 2 && iArr[0] == 4) {
            int i2 = v7Var5.g;
            int iO = l8Var.o() - v7Var4.g;
            v7Var5.i = tgVar.k(v7Var5);
            v7Var4.i = tgVar.k(v7Var4);
            tgVar.d(v7Var5.i, i2);
            tgVar.d(v7Var4.i, iO);
            k8Var.o = 2;
            k8Var.X = i2;
            int i3 = iO - i2;
            k8Var.T = i3;
            int i4 = k8Var.a0;
            if (i3 < i4) {
                k8Var.T = i4;
            }
        }
        if (iArr2[1] == 2 || iArr[1] != 4) {
            return;
        }
        int i5 = v7Var3.g;
        int i6 = l8Var.i() - v7Var2.g;
        v7Var3.i = tgVar.k(v7Var3);
        v7Var2.i = tgVar.k(v7Var2);
        tgVar.d(v7Var3.i, i5);
        tgVar.d(v7Var2.i, i6);
        if (k8Var.Z > 0 || k8Var.f0 == 8) {
            mo moVarK = tgVar.k(v7Var);
            v7Var.i = moVarK;
            tgVar.d(moVarK, k8Var.Z + i5);
        }
        k8Var.p = 2;
        k8Var.Y = i5;
        int i7 = i6 - i5;
        k8Var.U = i7;
        int i8 = k8Var.b0;
        if (i7 < i8) {
            k8Var.U = i8;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01be A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02d3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0168 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x01c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0150  */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v27, types: [int] */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v38 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v44 */
    /* JADX WARN: Type inference failed for: r7v45 */
    /* JADX WARN: Type inference failed for: r7v46 */
    /* JADX WARN: Type inference failed for: r7v47 */
    /* JADX WARN: Type inference failed for: r7v48 */
    /* JADX WARN: Type inference failed for: r7v49 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v50 */
    /* JADX WARN: Type inference failed for: r7v51 */
    /* JADX WARN: Type inference failed for: r7v52 */
    /* JADX WARN: Type inference failed for: r7v53 */
    /* JADX WARN: Type inference failed for: r7v54 */
    /* JADX WARN: Type inference failed for: r7v55 */
    /* JADX WARN: Type inference failed for: r7v56 */
    /* JADX WARN: Type inference failed for: r7v57 */
    /* JADX WARN: Type inference failed for: r7v58 */
    /* JADX WARN: Type inference failed for: r7v59 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v60 */
    /* JADX WARN: Type inference failed for: r7v61 */
    /* JADX WARN: Type inference failed for: r7v62 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j0(android.content.Context r18, java.util.concurrent.Executor r19, defpackage.vk r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 741
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d.j0(android.content.Context, java.util.concurrent.Executor, vk, boolean):void");
    }

    public static void k(Type type) {
        i(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static float k0() {
        return ((float) Math.pow(0.5689655172413793d, 3.0d)) * 100.0f;
    }

    public static void l(int i2, int i3, int i4) {
        if (i2 < 0 || i3 > i4) {
            throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
        }
        if (i2 <= i3) {
            return;
        }
        throw new IllegalArgumentException("fromIndex: " + i2 + " > toIndex: " + i3);
    }

    public static cc m(Context context) {
        ProviderInfo providerInfo;
        a2 a2Var;
        ApplicationInfo applicationInfo;
        int i2 = 16;
        fr h9Var = Build.VERSION.SDK_INT >= 28 ? new h9(i2) : new fr(i2);
        PackageManager packageManager = context.getPackageManager();
        hb.i(packageManager, "Package manager required to locate emoji font provider");
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            a2Var = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArrF = h9Var.f(packageManager, str2);
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArrF) {
                    arrayList.add(signature.toByteArray());
                }
                a2Var = new a2(str, str2, "emojicompat-emoji-font", Collections.singletonList(arrayList));
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e2);
                a2Var = null;
            }
        }
        if (a2Var == null) {
            return null;
        }
        return new cc(new bc(context, a2Var));
    }

    public static byte[] n(y9[] y9VarArr, byte[] bArr) throws IOException {
        int i2 = 0;
        int length = 0;
        for (y9 y9Var : y9VarArr) {
            length += ((((y9Var.g * 2) + 7) & (-8)) / 8) + (y9Var.e * 2) + t(y9Var.a, y9Var.b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + y9Var.f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, w)) {
            int length2 = y9VarArr.length;
            while (i2 < length2) {
                y9 y9Var2 = y9VarArr[i2];
                g0(byteArrayOutputStream, y9Var2, t(y9Var2.a, y9Var2.b, bArr));
                f0(byteArrayOutputStream, y9Var2);
                i2++;
            }
        } else {
            for (y9 y9Var3 : y9VarArr) {
                g0(byteArrayOutputStream, y9Var3, t(y9Var3.a, y9Var3.b, bArr));
            }
            int length3 = y9VarArr.length;
            while (i2 < length3) {
                f0(byteArrayOutputStream, y9VarArr[i2]);
                i2++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static final qn o(lj ljVar) {
        LinkedHashMap linkedHashMap = ljVar.a;
        zn znVar = (zn) linkedHashMap.get(E);
        if (znVar == null) {
            z6.f("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
            return null;
        }
        at atVar = (at) linkedHashMap.get(F);
        if (atVar == null) {
            z6.f("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
            return null;
        }
        Bundle bundle = (Bundle) linkedHashMap.get(G);
        String str = (String) linkedHashMap.get(fr.e);
        if (str == null) {
            z6.f("CreationExtras must have a value by `VIEW_MODEL_KEY`");
            return null;
        }
        wn wnVarB = znVar.getSavedStateRegistry().b();
        sn snVar = wnVarB instanceof sn ? (sn) wnVarB : null;
        if (snVar == null) {
            z6.o("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
            return null;
        }
        tn tnVarB = B(atVar);
        qn qnVar = (qn) tnVarB.d.get(str);
        if (qnVar != null) {
            return qnVar;
        }
        Class[] clsArr = qn.f;
        snVar.b();
        Bundle bundle2 = snVar.c;
        Bundle bundle3 = bundle2 != null ? bundle2.getBundle(str) : null;
        Bundle bundle4 = snVar.c;
        if (bundle4 != null) {
            bundle4.remove(str);
        }
        Bundle bundle5 = snVar.c;
        if (bundle5 != null && bundle5.isEmpty()) {
            snVar.c = null;
        }
        qn qnVarT = hb.t(bundle3, bundle);
        tnVarB.d.put(str, qnVarT);
        return qnVarT;
    }

    public static boolean p(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z2 = true;
        for (File file2 : fileArrListFiles) {
            z2 = p(file2) && z2;
        }
        return z2;
    }

    public static final boolean q(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    public static boolean r(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return Objects.equals(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return r(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static String s(double d2) {
        if (d2 < 0.0d) {
            return "0";
        }
        if (d2 < 1000.0d) {
            return String.format(Locale.US, "%,.0f", Double.valueOf(d2));
        }
        int iLog10 = (int) (Math.log10(d2) / 3.0d);
        if (iLog10 >= 10) {
            iLog10 = 9;
        }
        double dPow = d2 / Math.pow(1000.0d, iLog10);
        String[] strArr = p;
        return dPow >= 100.0d ? String.format(Locale.US, "%.1f%s", Double.valueOf(dPow), strArr[iLog10]) : dPow >= 10.0d ? String.format(Locale.US, "%.2f%s", Double.valueOf(dPow), strArr[iLog10]) : String.format(Locale.US, "%.3f%s", Double.valueOf(dPow), strArr[iLog10]);
    }

    public static String t(String str, String str2, byte[] bArr) {
        byte[] bArr2 = y;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = x;
        String str3 = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(str3)) {
                return str2.replace(":", "!");
            }
            if (":".equals(str3)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(str3)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(str3)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append((Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!");
                sb.append(str2);
                return sb.toString();
            }
        }
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        if (r5.c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.res.ColorStateList u(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            bn r1 = new bn
            r1.<init>(r0, r8)
            java.lang.Object r2 = defpackage.en.c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = defpackage.en.b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L50
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L50
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            an r5 = (defpackage.an) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L50
            android.content.res.Configuration r6 = r5.b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4d
            if (r8 != 0) goto L3f
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L49
            goto L3f
        L3c:
            r8 = move-exception
            goto Lb8
        L3f:
            if (r8 == 0) goto L4d
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4d
        L49:
            android.content.res.ColorStateList r3 = r5.a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L4d:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L50:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L52:
            if (r3 == 0) goto L55
            return r3
        L55:
            java.lang.ThreadLocal r2 = defpackage.en.a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L67
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L67:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L76
            r3 = 31
            if (r2 > r3) goto L76
            goto L87
        L76:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = defpackage.i7.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7f
            goto L87
        L7f:
            r2 = move-exception
            java.lang.String r3 = "ResourcesCompat"
            java.lang.String r5 = "Failed to inflate ColorStateList, leaving it to the framework"
            android.util.Log.w(r3, r5, r2)
        L87:
            if (r4 == 0) goto Lb3
            java.lang.Object r2 = defpackage.en.c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = defpackage.en.b     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L9f
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L9f
            if (r3 != 0) goto La1
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L9f
            r3.<init>()     // Catch: java.lang.Throwable -> L9f
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L9f
            goto La1
        L9f:
            r8 = move-exception
            goto Lb1
        La1:
            an r0 = new an     // Catch: java.lang.Throwable -> L9f
            android.content.res.Resources r1 = r1.a     // Catch: java.lang.Throwable -> L9f
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L9f
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L9f
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            goto Lb7
        Lb1:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            throw r8
        Lb3:
            android.content.res.ColorStateList r4 = defpackage.zm.b(r0, r9, r8)
        Lb7:
            return r4
        Lb8:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d.u(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static float v(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return ma.b(edgeEffect);
        }
        return 0.0f;
    }

    public static Drawable w(Context context, int i2) {
        return ym.c().d(context, i2);
    }

    public static Set x() {
        try {
            Object objInvoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);
            if (objInvoke == null) {
                return Collections.EMPTY_SET;
            }
            Set set = (Set) objInvoke;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof int[])) {
                    return Collections.EMPTY_SET;
                }
            }
            return set;
        } catch (Throwable unused) {
            return Collections.EMPTY_SET;
        }
    }

    public static g2 y(Context context, a2 a2Var) throws PackageManager.NameNotFoundException {
        Cursor cursorQuery;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String str = (String) a2Var.b;
        String str2 = (String) a2Var.c;
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + str);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(str2)) {
            throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str2);
        }
        Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        ac acVar = m;
        Collections.sort(arrayList, acVar);
        List listQ = (List) a2Var.e;
        if (listQ == null) {
            listQ = hb.Q(resources, 0);
        }
        int i2 = 0;
        loop1: while (true) {
            cursorQuery = null;
            if (i2 >= listQ.size()) {
                providerInfoResolveContentProvider = null;
                break;
            }
            ArrayList arrayList2 = new ArrayList((Collection) listQ.get(i2));
            Collections.sort(arrayList2, acVar);
            if (arrayList.size() == arrayList2.size()) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (!Arrays.equals((byte[]) arrayList.get(i3), (byte[]) arrayList2.get(i3))) {
                        break;
                    }
                }
                break loop1;
            }
            i2++;
        }
        if (providerInfoResolveContentProvider == null) {
            return new g2(1, null);
        }
        String str3 = providerInfoResolveContentProvider.authority;
        ArrayList arrayList3 = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str3).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str3).appendPath("file").build();
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uriBuild);
        try {
            String[] strArr = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};
            String[] strArr2 = {(String) a2Var.d};
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                try {
                    cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(uriBuild, strArr, "query = ?", strArr2, null, null);
                } catch (RemoteException e2) {
                    Log.w("FontsProvider", "Unable to query the content provider", e2);
                }
            }
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                arrayList3 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    arrayList3.add(new mc(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0));
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                contentProviderClientAcquireUnstableContentProviderClient.close();
            }
            return new g2(0, (mc[]) arrayList3.toArray(new mc[0]));
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                contentProviderClientAcquireUnstableContentProviderClient.close();
            }
            throw th;
        }
    }

    public static Type z(Type type, Class cls, Class cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i2 = 0; i2 < length; i2++) {
                Class<?> cls3 = interfaces[i2];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i2];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return z(cls.getGenericInterfaces()[i2], interfaces[i2], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return z(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public abstract void H(Throwable th);

    public abstract void I(kd kdVar);

    public abstract void L(n nVar, n nVar2);

    public abstract void M(n nVar, Thread thread);

    public abstract boolean f(o oVar, k kVar);

    public abstract boolean g(o oVar, Object obj, Object obj2);

    public abstract boolean h(o oVar, n nVar, n nVar2);
}
