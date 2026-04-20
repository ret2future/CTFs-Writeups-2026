package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Trace;
import android.widget.TextView;
import androidx.activity.a;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ k1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        int i = this.a;
        Application application = null;
        int i2 = 2;
        ?? r4 = 1;
        r4 = 1;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                Activity activity = (Activity) obj2;
                if (activity.isFinishing()) {
                    return;
                }
                Handler handler = o1.g;
                Method method = o1.f;
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 28) {
                    activity.recreate();
                    return;
                }
                if (((i3 != 26 && i3 != 27) || method != null) && (o1.e != null || o1.d != null)) {
                    try {
                        Object obj3 = o1.c.get(activity);
                        if (obj3 != null && (obj = o1.b.get(activity)) != null) {
                            Application application2 = activity.getApplication();
                            n1 n1Var = new n1(activity);
                            application2.registerActivityLifecycleCallbacks(n1Var);
                            handler.post(new c1(n1Var, 1 == true ? 1 : 0, obj3));
                            if (i3 != 26 && i3 != 27) {
                                r4 = 0;
                            }
                            try {
                                if (r4 != 0) {
                                    try {
                                        Boolean bool = Boolean.FALSE;
                                        application = application2;
                                        r4 = n1Var;
                                        method.invoke(obj, obj3, null, null, 0, bool, null, null, bool, bool);
                                    } catch (Throwable th) {
                                        th = th;
                                        application = application2;
                                        ?? r42 = n1Var;
                                        handler.post(new c1(application, i2, r42));
                                        throw th;
                                    }
                                } else {
                                    application = application2;
                                    r4 = n1Var;
                                    activity.recreate();
                                }
                                handler.post(new c1(application, i2, r4));
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                activity.recreate();
                return;
            case 1:
                ((vc) obj2).invalidateMenu();
                return;
            case 2:
                a aVar = (a) obj2;
                Runnable runnable = aVar.b;
                if (runnable != null) {
                    runnable.run();
                    aVar.b = null;
                    return;
                }
                return;
            case 3:
                h2.a((h2) obj2);
                return;
            case 4:
                bc bcVar = (bc) obj2;
                synchronized (bcVar.d) {
                    try {
                        if (bcVar.h == null) {
                            return;
                        }
                        try {
                            mc mcVarB = bcVar.b();
                            int i4 = mcVarB.e;
                            if (i4 == 2) {
                                synchronized (bcVar.d) {
                                }
                            }
                            if (i4 != 0) {
                                throw new RuntimeException("fetchFonts result is not OK. (" + i4 + ")");
                            }
                            try {
                                int i5 = lq.a;
                                Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                fr frVar = bcVar.c;
                                Context context = bcVar.a;
                                frVar.getClass();
                                Typeface typefaceR = xq.a.r(context, new mc[]{mcVarB}, 0);
                                MappedByteBuffer mappedByteBufferM = hb.M(bcVar.a, mcVarB.a);
                                if (mappedByteBufferM == null || typefaceR == null) {
                                    throw new RuntimeException("Unable to open file.");
                                }
                                try {
                                    Trace.beginSection("EmojiCompat.MetadataRepo.create");
                                    kd kdVar = new kd(typefaceR, hb.O(mappedByteBufferM));
                                    Trace.endSection();
                                    Trace.endSection();
                                    synchronized (bcVar.d) {
                                        try {
                                            d dVar = bcVar.h;
                                            if (dVar != null) {
                                                dVar.I(kdVar);
                                            }
                                        } finally {
                                        }
                                        break;
                                    }
                                    bcVar.a();
                                    return;
                                } finally {
                                    int i6 = lq.a;
                                    Trace.endSection();
                                }
                            } catch (Throwable th3) {
                                throw th3;
                            }
                            break;
                        } catch (Throwable th4) {
                            synchronized (bcVar.d) {
                                try {
                                    d dVar2 = bcVar.h;
                                    if (dVar2 != null) {
                                        dVar2.H(th4);
                                    }
                                    bcVar.a();
                                    return;
                                } finally {
                                }
                            }
                        }
                    } finally {
                    }
                }
            case 5:
                ((TextView) obj2).setVisibility(8);
                return;
            default:
                tk tkVar = (tk) obj2;
                androidx.lifecycle.a aVar2 = tkVar.f;
                if (tkVar.b == 0) {
                    tkVar.c = true;
                    aVar2.e(dg.ON_PAUSE);
                }
                if (tkVar.a == 0 && tkVar.c) {
                    aVar2.e(dg.ON_STOP);
                    tkVar.d = true;
                    return;
                }
                return;
        }
    }
}
