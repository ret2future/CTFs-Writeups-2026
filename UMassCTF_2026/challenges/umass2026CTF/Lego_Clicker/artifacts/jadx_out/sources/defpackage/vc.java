package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.b;
import androidx.lifecycle.a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class vc extends b {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    final xc mFragments;
    boolean mResumed;
    final a mFragmentLifecycleRegistry = new a(this);
    boolean mStopped = true;

    public vc() {
        final l2 l2Var = (l2) this;
        this.mFragments = new xc(new uc(l2Var));
        getSavedStateRegistry().c(LIFECYCLE_TAG, new rc(0, l2Var));
        final int i = 0;
        addOnConfigurationChangedListener(new p8() { // from class: sc
            @Override // defpackage.p8
            public final void accept(Object obj) {
                int i2 = i;
                l2 l2Var2 = l2Var;
                switch (i2) {
                    case 0:
                        l2Var2.mFragments.a();
                        break;
                    default:
                        l2Var2.mFragments.a();
                        break;
                }
            }
        });
        final int i2 = 1;
        addOnNewIntentListener(new p8() { // from class: sc
            @Override // defpackage.p8
            public final void accept(Object obj) {
                int i22 = i2;
                l2 l2Var2 = l2Var;
                switch (i22) {
                    case 0:
                        l2Var2.mFragments.a();
                        break;
                    default:
                        l2Var2.mFragments.a();
                        break;
                }
            }
        });
        addOnContextAvailableListener(new tc(l2Var, 0));
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.a.c.e.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (shouldDumpInternalState(strArr)) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print(" mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            if (getApplication() != null) {
                j5 j5Var = new j5(getViewModelStore(), kh.e);
                String canonicalName = kh.class.getCanonicalName();
                if (canonicalName == null) {
                    z6.f("Local and anonymous classes can not be ViewModels");
                    return;
                }
                po poVar = ((kh) j5Var.i("androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName), kh.class)).d;
                if (poVar.c > 0) {
                    printWriter.print(str2);
                    printWriter.println("Loaders:");
                    if (poVar.c > 0) {
                        if (poVar.b[0] != null) {
                            z6.a();
                            return;
                        }
                        printWriter.print(str2);
                        printWriter.print("  #");
                        printWriter.print(poVar.a[0]);
                        printWriter.print(": ");
                        throw null;
                    }
                }
            }
            gd gdVar = this.mFragments.a.c;
            gdVar.getClass();
            String str3 = str + "    ";
            kd kdVar = gdVar.c;
            ArrayList arrayList = (ArrayList) kdVar.a;
            HashMap map = (HashMap) kdVar.b;
            if (!map.isEmpty()) {
                printWriter.print(str);
                printWriter.println("Active Fragments:");
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        z6.a();
                        return;
                    } else {
                        printWriter.print(str);
                        printWriter.println("null");
                    }
                }
            }
            int size2 = arrayList.size();
            if (size2 > 0) {
                printWriter.print(str);
                printWriter.println("Added Fragments:");
                if (size2 > 0) {
                    if (arrayList.get(0) != null) {
                        z6.a();
                        return;
                    }
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(0);
                    printWriter.print(": ");
                    throw null;
                }
            }
            ArrayList arrayList2 = gdVar.d;
            if (arrayList2 != null && (size = arrayList2.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (int i = 0; i < size; i++) {
                    d6 d6Var = (d6) gdVar.d.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(d6Var.toString());
                    d6Var.b(str3, printWriter, true);
                }
            }
            printWriter.print(str);
            printWriter.println("Back Stack Index: " + gdVar.h.get());
            synchronized (gdVar.a) {
                try {
                    int size3 = gdVar.a.size();
                    if (size3 > 0) {
                        printWriter.print(str);
                        printWriter.println("Pending Actions:");
                        for (int i2 = 0; i2 < size3; i2++) {
                            d6 d6Var2 = (d6) gdVar.a.get(i2);
                            printWriter.print(str);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                            printWriter.println(d6Var2);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            printWriter.print(str);
            printWriter.println("FragmentManager misc state:");
            printWriter.print(str);
            printWriter.print("  mHost=");
            printWriter.println(gdVar.r);
            printWriter.print(str);
            printWriter.print("  mContainer=");
            printWriter.println(gdVar.s);
            printWriter.print(str);
            printWriter.print("  mCurState=");
            printWriter.print(gdVar.q);
            printWriter.print(" mStateSaved=");
            printWriter.print(gdVar.y);
            printWriter.print(" mStopped=");
            printWriter.print(gdVar.z);
            printWriter.print(" mDestroyed=");
            printWriter.println(gdVar.A);
        }
    }

    public fd getSupportFragmentManager() {
        return this.mFragments.a.c;
    }

    @Deprecated
    public jh getSupportLoaderManager() {
        return new lh(this, getViewModelStore());
    }

    public void markFragmentsCreated() {
        Iterator it = getSupportFragmentManager().c.h().iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                z6.a();
                return;
            }
        }
    }

    @Override // androidx.activity.b, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.a();
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.b, defpackage.q7, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.e(dg.ON_CREATE);
        gd gdVar = this.mFragments.a.c;
        gdVar.y = false;
        gdVar.z = false;
        gdVar.E.getClass();
        gdVar.c(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        gd gdVar = this.mFragments.a.c;
        boolean zIsChangingConfigurations = true;
        gdVar.A = true;
        gdVar.e(true);
        Iterator it = gdVar.b().iterator();
        if (it.hasNext()) {
            ((qo) it.next()).a();
            throw null;
        }
        kd kdVar = gdVar.c;
        uc ucVar = gdVar.r;
        if (ucVar != null) {
            zIsChangingConfigurations = ((id) kdVar.d).g;
        } else {
            l2 l2Var = ucVar.a;
            if (l2Var != null) {
                zIsChangingConfigurations = true ^ l2Var.isChangingConfigurations();
            }
        }
        if (zIsChangingConfigurations) {
            Iterator it2 = gdVar.i.values().iterator();
            while (it2.hasNext()) {
                ArrayList arrayList = ((f6) it2.next()).a;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    String str = (String) obj;
                    id idVar = (id) kdVar.d;
                    idVar.getClass();
                    if (fd.h(3)) {
                        Log.d("FragmentManager", "Clearing non-config state for saved state of Fragment " + str);
                    }
                    HashMap map = idVar.f;
                    HashMap map2 = idVar.e;
                    id idVar2 = (id) map2.get(str);
                    if (idVar2 != null) {
                        idVar2.b();
                        map2.remove(str);
                    }
                    zs zsVar = (zs) map.get(str);
                    if (zsVar != null) {
                        zsVar.a();
                        map.remove(str);
                    }
                }
            }
        }
        gdVar.c(-1);
        uc ucVar2 = gdVar.r;
        if (ucVar2 != null) {
            ucVar2.d.removeOnTrimMemoryListener(gdVar.m);
        }
        uc ucVar3 = gdVar.r;
        if (ucVar3 != null) {
            ucVar3.d.removeOnConfigurationChangedListener(gdVar.l);
        }
        uc ucVar4 = gdVar.r;
        if (ucVar4 != null) {
            ucVar4.d.removeOnMultiWindowModeChangedListener(gdVar.n);
        }
        uc ucVar5 = gdVar.r;
        if (ucVar5 != null) {
            ucVar5.d.removeOnPictureInPictureModeChangedListener(gdVar.o);
        }
        uc ucVar6 = gdVar.r;
        if (ucVar6 != null) {
            ucVar6.d.removeMenuProvider(gdVar.p);
        }
        gdVar.r = null;
        gdVar.s = null;
        if (gdVar.f != null) {
            Iterator it3 = gdVar.g.b.iterator();
            while (it3.hasNext()) {
                ((s6) it3.next()).cancel();
            }
            gdVar.f = null;
        }
        w1 w1Var = gdVar.u;
        if (w1Var != null) {
            w1Var.a();
            gdVar.v.a();
            gdVar.w.a();
        }
        this.mFragmentLifecycleRegistry.e(dg.ON_DESTROY);
    }

    @Override // androidx.activity.b, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 6) {
            gd gdVar = this.mFragments.a.c;
            if (gdVar.q >= 1) {
                Iterator it = gdVar.c.h().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (it.next() != null) {
                        z6.a();
                        break;
                    }
                }
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.a.c.c(5);
        this.mFragmentLifecycleRegistry.e(dg.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Override // androidx.activity.b, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments.a();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        this.mFragments.a();
        super.onResume();
        this.mResumed = true;
        this.mFragments.a.c.e(true);
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.e(dg.ON_RESUME);
        gd gdVar = this.mFragments.a.c;
        gdVar.y = false;
        gdVar.z = false;
        gdVar.E.getClass();
        gdVar.c(7);
    }

    @Override // android.app.Activity
    public void onStart() {
        this.mFragments.a();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            gd gdVar = this.mFragments.a.c;
            gdVar.y = false;
            gdVar.z = false;
            gdVar.E.getClass();
            gdVar.c(4);
        }
        this.mFragments.a.c.e(true);
        this.mFragmentLifecycleRegistry.e(dg.ON_START);
        gd gdVar2 = this.mFragments.a.c;
        gdVar2.y = false;
        gdVar2.z = false;
        gdVar2.E.getClass();
        gdVar2.c(5);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        gd gdVar = this.mFragments.a.c;
        gdVar.z = true;
        gdVar.E.getClass();
        gdVar.c(4);
        this.mFragmentLifecycleRegistry.e(dg.ON_STOP);
    }

    public void setEnterSharedElementCallback(ho hoVar) {
        l1.c(this, null);
    }

    public void setExitSharedElementCallback(ho hoVar) {
        l1.d(this, null);
    }

    public void startActivityFromFragment(qc qcVar, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (i != -1) {
            throw null;
        }
        startActivityForResult(intent, -1, bundle);
    }

    @Deprecated
    public void startIntentSenderFromFragment(qc qcVar, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            throw null;
        }
        startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void supportFinishAfterTransition() {
        l1.a(this);
    }

    public void supportPostponeEnterTransition() {
        l1.b(this);
    }

    public void supportStartPostponedEnterTransition() {
        l1.e(this);
    }

    public void startActivityFromFragment(qc qcVar, @SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        startActivityFromFragment(qcVar, intent, i, (Bundle) null);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Deprecated
    public void onAttachFragment(qc qcVar) {
    }

    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i) {
    }
}
