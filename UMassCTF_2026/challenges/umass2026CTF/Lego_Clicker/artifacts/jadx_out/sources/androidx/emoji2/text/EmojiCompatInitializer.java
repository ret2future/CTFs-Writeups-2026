package androidx.emoji2.text;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.ProcessLifecycleInitializer;
import defpackage.cc;
import defpackage.cf;
import defpackage.e0;
import defpackage.fg;
import defpackage.j5;
import defpackage.kg;
import defpackage.q6;
import defpackage.q9;
import defpackage.u7;
import defpackage.va;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements cf {
    @Override // defpackage.cf
    public final List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // defpackage.cf
    public final Object b(Context context) {
        Object objH;
        cc ccVar = new cc(new e0(context));
        ccVar.a = 1;
        if (va.k == null) {
            synchronized (va.j) {
                try {
                    if (va.k == null) {
                        va.k = new va(ccVar);
                    }
                } finally {
                }
            }
        }
        j5 j5VarN = j5.n(context);
        j5VarN.getClass();
        synchronized (j5.e) {
            try {
                objH = ((HashMap) j5VarN.a).get(ProcessLifecycleInitializer.class);
                if (objH == null) {
                    objH = j5VarN.h(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        final fg lifecycle = ((kg) objH).getLifecycle();
        lifecycle.a(new q9(this) { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // defpackage.q9
            public final void a() {
                (Build.VERSION.SDK_INT >= 28 ? u7.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new q6(), 500L);
                lifecycle.b(this);
            }
        });
        return Boolean.TRUE;
    }
}
