package defpackage;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class xq {
    public static final hb a;
    public static final sh b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new cr();
        } else if (i >= 28) {
            a = new br();
        } else if (i >= 26) {
            a = new ar();
        } else {
            Method method = zq.q;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                a = new zq();
            } else {
                a = new yq();
            }
        }
        b = new sh(16);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface a(android.content.Context r10, defpackage.ic r11, android.content.res.Resources r12, int r13, java.lang.String r14, int r15, int r16, defpackage.r4 r17) {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xq.a(android.content.Context, ic, android.content.res.Resources, int, java.lang.String, int, int, r4):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
