package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import defpackage.cf;
import defpackage.dg;
import defpackage.gg;
import defpackage.hg;
import defpackage.j5;
import defpackage.ob;
import defpackage.sk;
import defpackage.tk;
import defpackage.z6;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements cf {
    @Override // defpackage.cf
    public final List a() {
        return ob.a;
    }

    @Override // defpackage.cf
    public final Object b(Context context) {
        context.getClass();
        j5 j5VarN = j5.n(context);
        j5VarN.getClass();
        if (!((HashSet) j5VarN.b).contains(ProcessLifecycleInitializer.class)) {
            z6.o("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
            return null;
        }
        if (!hg.a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            applicationContext.getClass();
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new gg());
        }
        tk tkVar = tk.i;
        tkVar.getClass();
        tkVar.e = new Handler();
        tkVar.f.e(dg.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        applicationContext2.getClass();
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new sk(tkVar));
        return tkVar;
    }
}
