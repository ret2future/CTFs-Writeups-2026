package defpackage;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.ContentInfo;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.profileinstaller.ProfileInstallReceiver;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class e0 implements aj, ei, x4, ni, wj, t8, v8, ua, ys, vk {
    public final /* synthetic */ int a;
    public Object b;

    public e0(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.a = 13;
        if (Build.VERSION.SDK_INT >= 25) {
            this.b = new ff(uri, clipDescription, uri2);
        } else {
            this.b = new j5(uri, clipDescription, uri2);
        }
    }

    @Override // defpackage.aj
    public void a(gi giVar, boolean z) {
        if (giVar instanceof bp) {
            ((bp) giVar).z.k().c(false);
        }
        aj ajVar = ((e1) this.b).e;
        if (ajVar != null) {
            ajVar.a(giVar, z);
        }
    }

    @Override // defpackage.ys
    public vs b(Class cls, lj ljVar) {
        tn tnVar = null;
        for (ws wsVar : (ws[]) this.b) {
            wsVar.getClass();
            if (tn.class.equals(cls)) {
                tnVar = new tn();
            }
        }
        if (tnVar != null) {
            return tnVar;
        }
        z6.f("No initializer set for given class ".concat(cls.getName()));
        return null;
    }

    @Override // defpackage.t8
    public w8 build() {
        return new w8(new e0(((ContentInfo.Builder) this.b).build()));
    }

    @Override // defpackage.ua
    public void c(d dVar) {
        t7 t7Var = new t7("EmojiCompatInitializer");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), t7Var);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new wa(this, dVar, threadPoolExecutor, 0));
    }

    @Override // defpackage.v8
    public ClipData e() {
        return ((ContentInfo) this.b).getClip();
    }

    @Override // defpackage.v8
    public int f() {
        return ((ContentInfo) this.b).getFlags();
    }

    @Override // defpackage.vk
    public void g() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // defpackage.v8
    public ContentInfo h() {
        return (ContentInfo) this.b;
    }

    @Override // defpackage.vk
    public void i(int i, Object obj) {
        String str;
        switch (i) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i == 6 || i == 7 || i == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.b).setResultCode(i);
    }

    @Override // defpackage.ei
    public void k(gi giVar) {
        ei eiVar = ((ActionMenuView) this.b).v;
        if (eiVar != null) {
            eiVar.k(giVar);
        }
    }

    @Override // defpackage.t8
    public void m(Uri uri) {
        ((ContentInfo.Builder) this.b).setLinkUri(uri);
    }

    @Override // defpackage.ei
    public boolean n(gi giVar, MenuItem menuItem) {
        h1 h1Var = ((ActionMenuView) this.b).A;
        if (h1Var != null) {
            Toolbar toolbar = ((vp) h1Var).a;
            toolbar.G.b();
            zp zpVar = toolbar.I;
            if (zpVar != null ? ((bq) zpVar).a.b.onMenuItemSelected(0, menuItem) : false) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.v8
    public int o() {
        return ((ContentInfo) this.b).getSource();
    }

    @Override // defpackage.ni
    public void p(gi giVar, MenuItem menuItem) {
        ((w6) this.b).f.removeCallbacksAndMessages(giVar);
    }

    @Override // defpackage.wj
    public Object q() {
        switch (this.a) {
            case 5:
                Class cls = (Class) this.b;
                try {
                    return mr.a.a(cls);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to create instance of " + cls + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
                }
            default:
                Constructor constructor = (Constructor) this.b;
                try {
                    return constructor.newInstance(null);
                } catch (IllegalAccessException e2) {
                    hb hbVar = lm.a;
                    throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.10.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e2);
                } catch (InstantiationException e3) {
                    throw new RuntimeException("Failed to invoke constructor '" + lm.b(constructor) + "' with no args", e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException("Failed to invoke constructor '" + lm.b(constructor) + "' with no args", e4.getCause());
                }
        }
    }

    @Override // defpackage.aj
    public boolean s(gi giVar) {
        e1 e1Var = (e1) this.b;
        if (giVar == e1Var.c) {
            return false;
        }
        ((bp) giVar).A.getClass();
        aj ajVar = e1Var.e;
        if (ajVar != null) {
            return ajVar.s(giVar);
        }
        return false;
    }

    @Override // defpackage.t8
    public void setExtras(Bundle bundle) {
        ((ContentInfo.Builder) this.b).setExtras(bundle);
    }

    @Override // defpackage.ni
    public void t(gi giVar, oi oiVar) {
        w6 w6Var = (w6) this.b;
        Handler handler = w6Var.f;
        handler.removeCallbacksAndMessages(null);
        ArrayList arrayList = w6Var.h;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (giVar == ((v6) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        int i2 = i + 1;
        handler.postAtTime(new u6(this, i2 < arrayList.size() ? (v6) arrayList.get(i2) : null, oiVar, giVar), giVar, SystemClock.uptimeMillis() + 200);
    }

    public String toString() {
        switch (this.a) {
            case 8:
                return "ContentInfoCompat{" + ((ContentInfo) this.b) + "}";
            default:
                return super.toString();
        }
    }

    @Override // defpackage.t8
    public void u(int i) {
        ((ContentInfo.Builder) this.b).setFlags(i);
    }

    public /* synthetic */ e0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public e0(TextView textView) {
        this.a = 11;
        this.b = new ib(textView);
    }

    public e0(EditText editText) {
        this.a = 10;
        this.b = new z3(editText, 6);
    }

    public e0(Context context) {
        this.a = 9;
        this.b = context.getApplicationContext();
    }

    public /* synthetic */ e0() {
        this.a = 19;
    }

    public e0(ContentInfo contentInfo) {
        this.a = 8;
        contentInfo.getClass();
        this.b = s8.j(contentInfo);
    }

    public e0(ClipData clipData, int i) {
        this.a = 7;
        this.b = s8.c(clipData, i);
    }

    @Override // defpackage.x4
    public void j(int i) {
    }

    @Override // defpackage.x4
    public void l(int i) {
    }

    @Override // defpackage.x4
    public void r(int i, float f) {
    }
}
