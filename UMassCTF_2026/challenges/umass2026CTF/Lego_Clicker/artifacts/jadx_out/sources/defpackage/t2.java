package defpackage;

import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t2 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Context b;

    public /* synthetic */ t2(Context context, int i) {
        this.a = i;
        this.b = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0094  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r10 = this;
            int r0 = r10.a
            android.content.Context r10 = r10.b
            switch(r0) {
                case 0: goto L2d;
                case 1: goto L13;
                default: goto L7;
            }
        L7:
            uk r0 = new uk
            r0.<init>()
            fr r1 = defpackage.d.r
            r2 = 0
            defpackage.d.j0(r10, r0, r1, r2)
            return
        L13:
            java.util.concurrent.ThreadPoolExecutor r3 = new java.util.concurrent.ThreadPoolExecutor
            java.util.concurrent.LinkedBlockingQueue r9 = new java.util.concurrent.LinkedBlockingQueue
            r9.<init>()
            r4 = 0
            r5 = 1
            r6 = 0
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS
            r3.<init>(r4, r5, r6, r8, r9)
            t2 r0 = new t2
            r1 = 2
            r0.<init>(r10, r1)
            r3.execute(r0)
            return
        L2d:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 33
            if (r0 < r2) goto Lac
            android.content.ComponentName r3 = new android.content.ComponentName
            java.lang.String r4 = "androidx.appcompat.app.AppLocalesMetadataHolderService"
            r3.<init>(r10, r4)
            android.content.pm.PackageManager r4 = r10.getPackageManager()
            int r4 = r4.getComponentEnabledSetting(r3)
            if (r4 == r1) goto Lac
            java.lang.String r4 = "locale"
            if (r0 < r2) goto L83
            w5 r0 = defpackage.z2.g
            java.util.Iterator r0 = r0.iterator()
        L4f:
            r2 = r0
            yh r2 = (defpackage.yh) r2
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L71
            java.lang.Object r2 = r2.next()
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2
            java.lang.Object r2 = r2.get()
            z2 r2 = (defpackage.z2) r2
            if (r2 == 0) goto L4f
            n3 r2 = (defpackage.n3) r2
            android.content.Context r2 = r2.k
            if (r2 == 0) goto L4f
            java.lang.Object r0 = r2.getSystemService(r4)
            goto L72
        L71:
            r0 = 0
        L72:
            if (r0 == 0) goto L88
            android.os.LocaleList r0 = defpackage.v2.a(r0)
            oh r2 = new oh
            ph r5 = new ph
            r5.<init>(r0)
            r2.<init>(r5)
            goto L8a
        L83:
            oh r2 = defpackage.z2.c
            if (r2 == 0) goto L88
            goto L8a
        L88:
            oh r2 = defpackage.oh.b
        L8a:
            ph r0 = r2.a
            android.os.LocaleList r0 = r0.a
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto La5
            java.lang.String r0 = defpackage.d.O(r10)
            java.lang.Object r2 = r10.getSystemService(r4)
            if (r2 == 0) goto La5
            android.os.LocaleList r0 = defpackage.u2.a(r0)
            defpackage.v2.b(r2, r0)
        La5:
            android.content.pm.PackageManager r10 = r10.getPackageManager()
            r10.setComponentEnabledSetting(r3, r1, r1)
        Lac:
            defpackage.z2.f = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t2.run():void");
    }
}
