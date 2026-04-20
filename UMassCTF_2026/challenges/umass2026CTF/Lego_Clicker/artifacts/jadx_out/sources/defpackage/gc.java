package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class gc {
    public static final sh a = new sh(16);
    public static final ThreadPoolExecutor b;
    public static final Object c;
    public static final ko d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new um());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        b = threadPoolExecutor;
        c = new Object();
        d = new ko();
    }

    public static fc a(String str, Context context, a2 a2Var, int i) {
        sh shVar = a;
        Typeface typeface = (Typeface) shVar.a(str);
        if (typeface != null) {
            return new fc(typeface);
        }
        try {
            g2 g2VarY = d.y(context, a2Var);
            mc[] mcVarArr = (mc[]) g2VarY.b;
            int i2 = g2VarY.a;
            int i3 = 1;
            if (i2 != 0) {
                i3 = i2 != 1 ? -3 : -2;
            } else if (mcVarArr != null && mcVarArr.length != 0) {
                int length = mcVarArr.length;
                i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = mcVarArr[i4].e;
                    if (i5 == 0) {
                        i4++;
                    } else if (i5 >= 0) {
                        i3 = i5;
                    }
                }
            }
            if (i3 != 0) {
                return new fc(i3);
            }
            Typeface typefaceR = xq.a.r(context, mcVarArr, i);
            if (typefaceR == null) {
                return new fc(-3);
            }
            shVar.b(str, typefaceR);
            return new fc(typefaceR);
        } catch (PackageManager.NameNotFoundException unused) {
            return new fc(-1);
        }
    }
}
