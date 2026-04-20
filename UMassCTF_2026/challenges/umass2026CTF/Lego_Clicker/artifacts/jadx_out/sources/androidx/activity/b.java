package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.SavedStateHandleAttacher;
import com.example.LegoClicker.R;
import defpackage.ao;
import defpackage.at;
import defpackage.b6;
import defpackage.cd;
import defpackage.cj;
import defpackage.ck;
import defpackage.d;
import defpackage.d9;
import defpackage.dg;
import defpackage.e9;
import defpackage.eg;
import defpackage.fd;
import defpackage.fg;
import defpackage.fr;
import defpackage.hb;
import defpackage.ig;
import defpackage.k1;
import defpackage.kg;
import defpackage.ki;
import defpackage.kj;
import defpackage.kk;
import defpackage.l7;
import defpackage.li;
import defpackage.lj;
import defpackage.md;
import defpackage.nd;
import defpackage.o7;
import defpackage.p7;
import defpackage.p8;
import defpackage.q7;
import defpackage.r1;
import defpackage.rc;
import defpackage.s1;
import defpackage.sn;
import defpackage.tc;
import defpackage.u1;
import defpackage.vc;
import defpackage.ve;
import defpackage.x8;
import defpackage.xn;
import defpackage.yn;
import defpackage.ys;
import defpackage.z6;
import defpackage.zn;
import defpackage.zs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends q7 implements at, ve, zn {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private final androidx.activity.result.a mActivityResultRegistry;
    private int mContentLayoutId;
    private ys mDefaultFactory;
    private boolean mDispatchingOnMultiWindowModeChanged;
    private boolean mDispatchingOnPictureInPictureModeChanged;
    final md mFullyDrawnReporter;
    private final li mMenuHostHelper;
    private final AtomicInteger mNextLocalRequestCode;
    private final c mOnBackPressedDispatcher;
    private final CopyOnWriteArrayList<p8> mOnConfigurationChangedListeners;
    private final CopyOnWriteArrayList<p8> mOnMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<p8> mOnNewIntentListeners;
    private final CopyOnWriteArrayList<p8> mOnPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<p8> mOnTrimMemoryListeners;
    private final p7 mReportFullyDrawnExecutor;
    final yn mSavedStateRegistryController;
    private zs mViewModelStore;
    final x8 mContextAwareHelper = new x8();
    private final androidx.lifecycle.a mLifecycleRegistry = new androidx.lifecycle.a(this);

    /* JADX WARN: Type inference failed for: r5v0, types: [k7] */
    public b() {
        final vc vcVar = (vc) this;
        int i = 1;
        this.mMenuHostHelper = new li(new k1(i, vcVar));
        yn ynVar = new yn(this);
        this.mSavedStateRegistryController = ynVar;
        this.mOnBackPressedDispatcher = new c(new b6(i, vcVar));
        a aVar = new a(vcVar);
        this.mReportFullyDrawnExecutor = aVar;
        this.mFullyDrawnReporter = new md(aVar, new nd() { // from class: k7
            @Override // defpackage.nd
            public final Object a() {
                vcVar.reportFullyDrawn();
                return null;
            }
        });
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mActivityResultRegistry = new l7();
        this.mOnConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.mOnNewIntentListeners = new CopyOnWriteArrayList<>();
        this.mOnMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mDispatchingOnMultiWindowModeChanged = false;
        this.mDispatchingOnPictureInPictureModeChanged = false;
        if (getLifecycle() == null) {
            z6.o("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
            throw null;
        }
        getLifecycle().a(new ig() { // from class: androidx.activity.ComponentActivity$3
            @Override // defpackage.ig
            public final void d(kg kgVar, dg dgVar) {
                if (dgVar == dg.ON_STOP) {
                    Window window = vcVar.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        getLifecycle().a(new ig() { // from class: androidx.activity.ComponentActivity$4
            @Override // defpackage.ig
            public final void d(kg kgVar, dg dgVar) {
                if (dgVar == dg.ON_DESTROY) {
                    vcVar.mContextAwareHelper.b = null;
                    if (vcVar.isChangingConfigurations()) {
                        return;
                    }
                    vcVar.getViewModelStore().a();
                }
            }
        });
        getLifecycle().a(new ig() { // from class: androidx.activity.ComponentActivity$5
            @Override // defpackage.ig
            public final void d(kg kgVar, dg dgVar) {
                vc vcVar2 = vcVar;
                vcVar2.ensureViewModelStore();
                vcVar2.getLifecycle().b(this);
            }
        });
        ynVar.a();
        eg egVar = ((androidx.lifecycle.a) getLifecycle()).c;
        if (egVar != eg.b && egVar != eg.c) {
            z6.f("Failed requirement.");
            throw null;
        }
        if (getSavedStateRegistry().b() == null) {
            sn snVar = new sn(getSavedStateRegistry(), vcVar);
            getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider", snVar);
            getLifecycle().a(new SavedStateHandleAttacher(snVar));
        }
        getSavedStateRegistry().c(ACTIVITY_RESULT_TAG, new rc(i, vcVar));
        addOnContextAvailableListener(new tc(vcVar, i));
    }

    public static void a(vc vcVar) {
        Bundle bundleA = vcVar.getSavedStateRegistry().a(ACTIVITY_RESULT_TAG);
        if (bundleA != null) {
            androidx.activity.result.a aVar = ((b) vcVar).mActivityResultRegistry;
            HashMap map = aVar.c;
            HashMap map2 = aVar.b;
            Bundle bundle = aVar.h;
            ArrayList<Integer> integerArrayList = bundleA.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList == null || integerArrayList == null) {
                return;
            }
            aVar.e = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            aVar.a = (Random) bundleA.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
            bundle.putAll(bundleA.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
            for (int i = 0; i < stringArrayList.size(); i++) {
                String str = stringArrayList.get(i);
                if (map.containsKey(str)) {
                    Integer num = (Integer) map.remove(str);
                    if (!bundle.containsKey(str)) {
                        map2.remove(num);
                    }
                }
                Integer num2 = integerArrayList.get(i);
                num2.intValue();
                String str2 = stringArrayList.get(i);
                map2.put(num2, str2);
                aVar.c.put(str2, num2);
            }
        }
    }

    public static Bundle b(vc vcVar) {
        Bundle bundle = new Bundle();
        androidx.activity.result.a aVar = ((b) vcVar).mActivityResultRegistry;
        aVar.getClass();
        HashMap map = aVar.c;
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(map.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(map.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(aVar.e));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) aVar.h.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", aVar.a);
        return bundle;
    }

    public void addMenuProvider(final cj cjVar, kg kgVar) {
        final li liVar = this.mMenuHostHelper;
        liVar.b.add(cjVar);
        liVar.a.run();
        fg lifecycle = kgVar.getLifecycle();
        HashMap map = liVar.c;
        ki kiVar = (ki) map.remove(cjVar);
        if (kiVar != null) {
            kiVar.a.b(kiVar.b);
            kiVar.b = null;
        }
        map.put(cjVar, new ki(lifecycle, new ig() { // from class: ji
            @Override // defpackage.ig
            public final void d(kg kgVar2, dg dgVar) {
                dg dgVar2 = dg.ON_DESTROY;
                li liVar2 = liVar;
                if (dgVar == dgVar2) {
                    liVar2.d(cjVar);
                } else {
                    liVar2.getClass();
                }
            }
        }));
    }

    public final void addOnConfigurationChangedListener(p8 p8Var) {
        this.mOnConfigurationChangedListeners.add(p8Var);
    }

    public final void addOnContextAvailableListener(ck ckVar) {
        x8 x8Var = this.mContextAwareHelper;
        x8Var.getClass();
        ckVar.getClass();
        b bVar = x8Var.b;
        if (bVar != null) {
            ckVar.a(bVar);
        }
        x8Var.a.add(ckVar);
    }

    public final void addOnMultiWindowModeChangedListener(p8 p8Var) {
        this.mOnMultiWindowModeChangedListeners.add(p8Var);
    }

    public final void addOnNewIntentListener(p8 p8Var) {
        this.mOnNewIntentListeners.add(p8Var);
    }

    public final void addOnPictureInPictureModeChangedListener(p8 p8Var) {
        this.mOnPictureInPictureModeChangedListeners.add(p8Var);
    }

    public final void addOnTrimMemoryListener(p8 p8Var) {
        this.mOnTrimMemoryListeners.add(p8Var);
    }

    public void ensureViewModelStore() {
        if (this.mViewModelStore == null) {
            o7 o7Var = (o7) getLastNonConfigurationInstance();
            if (o7Var != null) {
                this.mViewModelStore = o7Var.b;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new zs();
            }
        }
    }

    public final androidx.activity.result.a getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    @Override // defpackage.ve
    public e9 getDefaultViewModelCreationExtras() {
        lj ljVar = new lj(d9.b);
        Application application = getApplication();
        LinkedHashMap linkedHashMap = ljVar.a;
        if (application != null) {
            linkedHashMap.put(fr.d, getApplication());
        }
        linkedHashMap.put(d.E, this);
        linkedHashMap.put(d.F, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            linkedHashMap.put(d.G, getIntent().getExtras());
        }
        return ljVar;
    }

    public ys getDefaultViewModelProviderFactory() {
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new ao(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.mDefaultFactory;
    }

    public md getFullyDrawnReporter() {
        return this.mFullyDrawnReporter;
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        o7 o7Var = (o7) getLastNonConfigurationInstance();
        if (o7Var != null) {
            return o7Var.a;
        }
        return null;
    }

    @Override // defpackage.kg
    public fg getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final c getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // defpackage.zn
    public final xn getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b;
    }

    @Override // defpackage.at
    public zs getViewModelStore() {
        if (getApplication() != null) {
            ensureViewModelStore();
            return this.mViewModelStore;
        }
        z6.o("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        return null;
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mActivityResultRegistry.a(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.b();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator<p8> it = this.mOnConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(configuration);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005c  */
    @Override // defpackage.q7, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(android.os.Bundle r3) {
        /*
            r2 = this;
            yn r0 = r2.mSavedStateRegistryController
            r0.b(r3)
            x8 r0 = r2.mContextAwareHelper
            r0.getClass()
            r0.b = r2
            java.util.concurrent.CopyOnWriteArraySet r0 = r0.a
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L22
            java.lang.Object r1 = r0.next()
            ck r1 = (defpackage.ck) r1
            r1.a(r2)
            goto L12
        L22:
            super.onCreate(r3)
            int r3 = defpackage.sm.b
            defpackage.qm.b(r2)
            int r3 = defpackage.l6.a
            int r3 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r3 >= r0) goto L5c
            r0 = 32
            if (r3 < r0) goto L6d
            java.lang.String r3 = android.os.Build.VERSION.CODENAME
            r3.getClass()
            java.lang.String r0 = "REL"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L44
            goto L6d
        L44:
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r3 = r3.toUpperCase(r0)
            r3.getClass()
            java.lang.String r1 = "Tiramisu"
            java.lang.String r0 = r1.toUpperCase(r0)
            r0.getClass()
            int r3 = r3.compareTo(r0)
            if (r3 < 0) goto L6d
        L5c:
            androidx.activity.c r3 = r2.mOnBackPressedDispatcher
            android.window.OnBackInvokedDispatcher r0 = defpackage.m7.a(r2)
            r3.getClass()
            r0.getClass()
            r3.e = r0
            r3.c()
        L6d:
            int r3 = r2.mContentLayoutId
            if (r3 == 0) goto L74
            r2.setContentView(r3)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.b.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        li liVar = this.mMenuHostHelper;
        getMenuInflater();
        liVar.a();
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            this.mMenuHostHelper.b();
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.mDispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.mDispatchingOnMultiWindowModeChanged = false;
            for (p8 p8Var : this.mOnMultiWindowModeChangedListeners) {
                configuration.getClass();
                p8Var.accept(new kj(z));
            }
        } catch (Throwable th) {
            this.mDispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness", "MissingNullability"}) Intent intent) {
        super.onNewIntent(intent);
        Iterator<p8> it = this.mOnNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        Iterator it = this.mMenuHostHelper.b.iterator();
        while (it.hasNext()) {
            fd fdVar = ((cd) ((cj) it.next())).a;
            if (fdVar.q >= 1) {
                Iterator it2 = fdVar.c.h().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        z6.a();
                        return;
                    }
                }
            }
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.mDispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.mDispatchingOnPictureInPictureModeChanged = false;
            for (p8 p8Var : this.mOnPictureInPictureModeChangedListeners) {
                configuration.getClass();
                p8Var.accept(new kk(z));
            }
        } catch (Throwable th) {
            this.mDispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        this.mMenuHostHelper.c();
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mActivityResultRegistry.a(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        o7 o7Var;
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        zs zsVar = this.mViewModelStore;
        if (zsVar == null && (o7Var = (o7) getLastNonConfigurationInstance()) != null) {
            zsVar = o7Var.b;
        }
        if (zsVar == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        o7 o7Var2 = new o7();
        o7Var2.a = objOnRetainCustomNonConfigurationInstance;
        o7Var2.b = zsVar;
        return o7Var2;
    }

    @Override // defpackage.q7, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        fg lifecycle = getLifecycle();
        if (lifecycle instanceof androidx.lifecycle.a) {
            androidx.lifecycle.a aVar = (androidx.lifecycle.a) lifecycle;
            aVar.d("setCurrentState");
            aVar.f(eg.c);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.c(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator<p8> it = this.mOnTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(i));
        }
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.b;
    }

    public final <I, O> u1 registerForActivityResult(s1 s1Var, androidx.activity.result.a aVar, r1 r1Var) {
        return aVar.b("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, s1Var, r1Var);
    }

    public void removeMenuProvider(cj cjVar) {
        this.mMenuHostHelper.d(cjVar);
    }

    public final void removeOnConfigurationChangedListener(p8 p8Var) {
        this.mOnConfigurationChangedListeners.remove(p8Var);
    }

    public final void removeOnContextAvailableListener(ck ckVar) {
        x8 x8Var = this.mContextAwareHelper;
        x8Var.getClass();
        ckVar.getClass();
        x8Var.a.remove(ckVar);
    }

    public final void removeOnMultiWindowModeChangedListener(p8 p8Var) {
        this.mOnMultiWindowModeChangedListeners.remove(p8Var);
    }

    public final void removeOnNewIntentListener(p8 p8Var) {
        this.mOnNewIntentListeners.remove(p8Var);
    }

    public final void removeOnPictureInPictureModeChangedListener(p8 p8Var) {
        this.mOnPictureInPictureModeChangedListeners.remove(p8Var);
    }

    public final void removeOnTrimMemoryListener(p8 p8Var) {
        this.mOnTrimMemoryListeners.remove(p8Var);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (hb.K()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            md mdVar = this.mFullyDrawnReporter;
            synchronized (mdVar.a) {
                try {
                    mdVar.b = true;
                    ArrayList arrayList = mdVar.c;
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList.get(i);
                        i++;
                        ((nd) obj).a();
                    }
                    mdVar.c.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.app.Activity
    public abstract void setContentView(int i);

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        decorView2.getClass();
        decorView2.setTag(R.id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        decorView3.getClass();
        decorView3.setTag(R.id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        decorView4.getClass();
        decorView4.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        decorView5.getClass();
        decorView5.setTag(R.id.report_drawn, this);
        p7 p7Var = this.mReportFullyDrawnExecutor;
        View decorView6 = getWindow().getDecorView();
        a aVar = (a) p7Var;
        if (!aVar.c) {
            aVar.c = true;
            decorView6.getViewTreeObserver().addOnDrawListener(aVar);
        }
        super.setContentView(view);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public final <I, O> u1 registerForActivityResult(s1 s1Var, r1 r1Var) {
        return registerForActivityResult(s1Var, this.mActivityResultRegistry, r1Var);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        if (this.mDispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator<p8> it = this.mOnMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new kj(z));
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        if (this.mDispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator<p8> it = this.mOnPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new kk(z));
        }
    }

    public void addMenuProvider(cj cjVar) {
        li liVar = this.mMenuHostHelper;
        liVar.b.add(cjVar);
        liVar.a.run();
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(final cj cjVar, kg kgVar, final eg egVar) {
        final li liVar = this.mMenuHostHelper;
        liVar.getClass();
        fg lifecycle = kgVar.getLifecycle();
        HashMap map = liVar.c;
        ki kiVar = (ki) map.remove(cjVar);
        if (kiVar != null) {
            kiVar.a.b(kiVar.b);
            kiVar.b = null;
        }
        map.put(cjVar, new ki(lifecycle, new ig() { // from class: ii
            @Override // defpackage.ig
            public final void d(kg kgVar2, dg dgVar) {
                li liVar2 = liVar;
                liVar2.getClass();
                Runnable runnable = liVar2.a;
                CopyOnWriteArrayList copyOnWriteArrayList = liVar2.b;
                dg.Companion.getClass();
                eg egVar2 = egVar;
                egVar2.getClass();
                int iOrdinal = egVar2.ordinal();
                dg dgVar2 = null;
                dg dgVar3 = iOrdinal != 2 ? iOrdinal != 3 ? iOrdinal != 4 ? null : dg.ON_RESUME : dg.ON_START : dg.ON_CREATE;
                cj cjVar2 = cjVar;
                if (dgVar == dgVar3) {
                    copyOnWriteArrayList.add(cjVar2);
                    runnable.run();
                    return;
                }
                dg dgVar4 = dg.ON_DESTROY;
                if (dgVar == dgVar4) {
                    liVar2.d(cjVar2);
                    return;
                }
                int iOrdinal2 = egVar2.ordinal();
                if (iOrdinal2 == 2) {
                    dgVar2 = dgVar4;
                } else if (iOrdinal2 == 3) {
                    dgVar2 = dg.ON_STOP;
                } else if (iOrdinal2 == 4) {
                    dgVar2 = dg.ON_PAUSE;
                }
                if (dgVar == dgVar2) {
                    copyOnWriteArrayList.remove(cjVar2);
                    runnable.run();
                }
            }
        }));
    }
}
