package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class us implements b9, Serializable {
    public final b9 a;
    public int b;
    public /* synthetic */ fo c;
    public final /* synthetic */ View d;

    public us(View view, fo foVar) {
        this.d = view;
        this.a = foVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // defpackage.b9
    public final void a(Object obj) {
        Object objB;
        ?? r1 = this;
        while (true) {
            us usVar = (us) r1;
            b9 b9Var = usVar.a;
            b9Var.getClass();
            try {
                objB = usVar.b(obj);
            } catch (Throwable th) {
                obj = new gn(th);
            }
            if (objB == c9.a) {
                return;
            }
            obj = objB;
            if (!(b9Var instanceof us)) {
                b9Var.a(obj);
                return;
            }
            r1 = b9Var;
        }
    }

    public final Object b(Object obj) throws Throwable {
        Object obj2;
        fr frVar = fr.c;
        int i = this.b;
        View view = this.d;
        c9 c9Var = c9.a;
        if (i == 0) {
            if (obj instanceof gn) {
                throw ((gn) obj).a;
            }
            fo foVar = this.c;
            this.c = foVar;
            this.b = 1;
            foVar.b = view;
            foVar.a = 3;
            foVar.d = this;
            return c9Var;
        }
        if (i == 1) {
            fo foVar2 = this.c;
            if (obj instanceof gn) {
                throw ((gn) obj).a;
            }
            if (view instanceof ViewGroup) {
                this.c = null;
                this.b = 2;
                foVar2.getClass();
                mq mqVar = new mq(new r5(1, (ViewGroup) view));
                if (mqVar.b.hasNext()) {
                    foVar2.c = mqVar;
                    foVar2.a = 2;
                    foVar2.d = this;
                    obj2 = c9Var;
                } else {
                    obj2 = frVar;
                }
                if (obj2 != c9Var) {
                    obj2 = frVar;
                }
                if (obj2 == c9Var) {
                    return c9Var;
                }
            }
        } else {
            if (i != 2) {
                z6.o("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            if (obj instanceof gn) {
                throw ((gn) obj).a;
            }
        }
        return frVar;
    }

    public final String c() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        StringBuilder sb = new StringBuilder("Continuation at ");
        f9 f9Var = (f9) getClass().getAnnotation(f9.class);
        Object name = null;
        str = null;
        str = null;
        str = null;
        str = null;
        str = null;
        String str = null;
        if (f9Var != null) {
            int iV = f9Var.v();
            if (iV > 1) {
                throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + iV + ". Please update the Kotlin standard library.").toString());
            }
            try {
                Field declaredField = getClass().getDeclaredField("label");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this);
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                iIntValue = (num != null ? num.intValue() : 0) - 1;
            } catch (Exception unused) {
                iIntValue = -1;
            }
            int i = iIntValue >= 0 ? f9Var.l()[iIntValue] : -1;
            j5 j5Var = d.n;
            j5 j5Var2 = d.o;
            if (j5Var2 == null) {
                try {
                    j5 j5Var3 = new j5(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
                    d.o = j5Var3;
                    j5Var2 = j5Var3;
                } catch (Exception unused2) {
                    d.o = j5Var;
                    j5Var2 = j5Var;
                }
            }
            if (j5Var2 != j5Var && (method = (Method) j5Var2.a) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = (Method) j5Var2.b) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
                Method method3 = (Method) j5Var2.c;
                Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
                if (objInvoke3 instanceof String) {
                    str = (String) objInvoke3;
                }
            }
            if (str == null) {
                strC = f9Var.c();
            } else {
                strC = str + '/' + f9Var.c();
            }
            name = new StackTraceElement(strC, f9Var.m(), f9Var.f(), i);
        }
        if (name == null) {
            name = getClass().getName();
        }
        sb.append(name);
        return sb.toString();
    }

    public final String toString() {
        if (this.a != null) {
            return c();
        }
        hm.a.getClass();
        String string = us.class.getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
