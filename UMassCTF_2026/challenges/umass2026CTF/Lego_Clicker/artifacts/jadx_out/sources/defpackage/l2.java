package defpackage;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class l2 extends vc implements p2 {
    private static final String DELEGATE_TAG = "androidx:appcompat";
    private z2 mDelegate;
    private Resources mResources;

    public l2() {
        getSavedStateRegistry().c(DELEGATE_TAG, new j2(this));
        addOnContextAvailableListener(new k2(this));
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c();
        n3 n3Var = (n3) getDelegate();
        n3Var.x();
        ((ViewGroup) n3Var.A.findViewById(R.id.content)).addView(view, layoutParams);
        n3Var.m.a(n3Var.l.getCallback());
    }

    /* JADX WARN: Removed duplicated region for block: B:167:0x0231 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00aa  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void attachBaseContext(android.content.Context r10) {
        /*
            Method dump skipped, instruction units count: 586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l2.attachBaseContext(android.content.Context):void");
    }

    public final void c() {
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        decorView.setTag(com.example.LegoClicker.R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        decorView2.getClass();
        decorView2.setTag(com.example.LegoClicker.R.id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        decorView3.getClass();
        decorView3.setTag(com.example.LegoClicker.R.id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        decorView4.getClass();
        decorView4.setTag(com.example.LegoClicker.R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        o0 supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.a()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // defpackage.q7, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        o0 supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.j(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        n3 n3Var = (n3) getDelegate();
        n3Var.x();
        return (T) n3Var.l.findViewById(i);
    }

    public z2 getDelegate() {
        if (this.mDelegate == null) {
            x2 x2Var = z2.a;
            this.mDelegate = new n3(this, null, this, this);
        }
        return this.mDelegate;
    }

    public r0 getDrawerToggleDelegate() {
        ((n3) getDelegate()).getClass();
        return new fr(5);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        n3 n3Var = (n3) getDelegate();
        if (n3Var.p == null) {
            n3Var.B();
            o0 o0Var = n3Var.o;
            n3Var.p = new fp(o0Var != null ? o0Var.e() : n3Var.k);
        }
        return n3Var.p;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.mResources;
        if (resources == null) {
            int i = pr.a;
        }
        return resources == null ? super.getResources() : resources;
    }

    public o0 getSupportActionBar() {
        n3 n3Var = (n3) getDelegate();
        n3Var.B();
        return n3Var.o;
    }

    public Intent getSupportParentActivityIntent() {
        return hb.E(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().b();
    }

    @Override // androidx.activity.b, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) throws IllegalAccessException {
        super.onConfigurationChanged(configuration);
        n3 n3Var = (n3) getDelegate();
        if (n3Var.F && n3Var.z) {
            n3Var.B();
            o0 o0Var = n3Var.o;
            if (o0Var != null) {
                o0Var.g();
            }
        }
        q3 q3VarA = q3.a();
        Context context = n3Var.k;
        synchronized (q3VarA) {
            ym ymVar = q3VarA.a;
            synchronized (ymVar) {
                rh rhVar = (rh) ymVar.b.get(context);
                if (rhVar != null) {
                    int i = rhVar.d;
                    Object[] objArr = rhVar.c;
                    for (int i2 = 0; i2 < i; i2++) {
                        objArr[i2] = null;
                    }
                    rhVar.d = 0;
                    rhVar.a = false;
                }
            }
        }
        n3Var.R = new Configuration(n3Var.k.getResources().getConfiguration());
        n3Var.o(false, false);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(super.getResources().getConfiguration(), super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        onSupportContentChanged();
    }

    public void onCreateSupportNavigateUpTaskStack(jp jpVar) {
        ArrayList arrayList = jpVar.a;
        l2 l2Var = jpVar.b;
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = hb.E(this);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(l2Var.getPackageManager());
            }
            int size = arrayList.size();
            try {
                for (Intent intentF = hb.F(l2Var, component); intentF != null; intentF = hb.F(l2Var, intentF.getComponent())) {
                    arrayList.add(size, intentF);
                }
                arrayList.add(supportParentActivityIntent);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override // defpackage.vc, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().e();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // defpackage.vc, androidx.activity.b, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        o0 supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.d() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.activity.b, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((n3) getDelegate()).x();
    }

    @Override // defpackage.vc, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        n3 n3Var = (n3) getDelegate();
        n3Var.B();
        o0 o0Var = n3Var.o;
        if (o0Var != null) {
            o0Var.m(true);
        }
    }

    @Override // defpackage.vc, android.app.Activity
    public void onStart() throws IllegalAccessException {
        super.onStart();
        ((n3) getDelegate()).o(true, false);
    }

    @Override // defpackage.vc, android.app.Activity
    public void onStop() {
        super.onStop();
        n3 n3Var = (n3) getDelegate();
        n3Var.B();
        o0 o0Var = n3Var.o;
        if (o0Var != null) {
            o0Var.m(false);
        }
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (!supportShouldUpRecreateTask(supportParentActivityIntent)) {
            supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        jp jpVar = new jp(this);
        onCreateSupportNavigateUpTaskStack(jpVar);
        onPrepareSupportNavigateUpTaskStack(jpVar);
        ArrayList arrayList = jpVar.a;
        if (arrayList.isEmpty()) {
            z6.o("No intents added to TaskStackBuilder; cannot startActivities");
            return false;
        }
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        jpVar.b.startActivities(intentArr, null);
        try {
            finishAffinity();
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        getDelegate().l(charSequence);
    }

    @Override // defpackage.p2
    public j1 onWindowStartingSupportActionMode(i1 i1Var) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        o0 supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.k()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.b, android.app.Activity
    public void setContentView(int i) {
        c();
        getDelegate().h(i);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        n3 n3Var = (n3) getDelegate();
        if (n3Var.j instanceof Activity) {
            n3Var.B();
            o0 o0Var = n3Var.o;
            if (o0Var instanceof st) {
                z6.o("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
                return;
            }
            n3Var.p = null;
            if (o0Var != null) {
                o0Var.h();
            }
            n3Var.o = null;
            if (toolbar != null) {
                Object obj = n3Var.j;
                dq dqVar = new dq(toolbar, obj instanceof Activity ? ((Activity) obj).getTitle() : n3Var.q, n3Var.m);
                n3Var.o = dqVar;
                n3Var.m.b = dqVar.c;
                toolbar.setBackInvokedCallbackEnabled(true);
            } else {
                n3Var.m.b = null;
            }
            n3Var.b();
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        ((n3) getDelegate()).T = i;
    }

    public j1 startSupportActionMode(i1 i1Var) {
        return getDelegate().m(i1Var);
    }

    public void supportInvalidateOptionsMenu() {
        getDelegate().b();
    }

    public void supportNavigateUpTo(Intent intent) {
        navigateUpTo(intent);
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().g(i);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return shouldUpRecreateTask(intent);
    }

    @Override // androidx.activity.b, android.app.Activity
    public void setContentView(View view) {
        c();
        getDelegate().i(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c();
        getDelegate().j(view, layoutParams);
    }

    public void onLocalesChanged(oh ohVar) {
    }

    public void onNightModeChanged(int i) {
    }

    public void onPrepareSupportNavigateUpTaskStack(jp jpVar) {
    }

    @Override // defpackage.p2
    public void onSupportActionModeFinished(j1 j1Var) {
    }

    @Override // defpackage.p2
    public void onSupportActionModeStarted(j1 j1Var) {
    }

    @Deprecated
    public void setSupportProgress(int i) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }
}
