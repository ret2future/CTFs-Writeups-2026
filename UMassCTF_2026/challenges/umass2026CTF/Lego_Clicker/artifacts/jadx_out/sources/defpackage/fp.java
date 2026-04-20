package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class fp extends MenuInflater {
    public static final Class[] e;
    public static final Class[] f;
    public final Object[] a;
    public final Object[] b;
    public final Context c;
    public Object d;

    static {
        Class[] clsArr = {Context.class};
        e = clsArr;
        f = clsArr;
    }

    public fp(Context context) {
        super(context);
        this.c = context;
        Object[] objArr = {context};
        this.a = objArr;
        this.b = objArr;
    }

    public static Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public final void b(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        int i;
        XmlPullParser xmlPullParser2;
        ColorStateList colorStateList;
        int resourceId;
        ep epVar = new ep(this, menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            i = 2;
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z = false;
        boolean z2 = false;
        String str = null;
        while (!z) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            Menu menu2 = epVar.a;
            if (eventType != i) {
                if (eventType != 3) {
                    xmlPullParser2 = xmlPullParser;
                    z = z;
                } else {
                    String name2 = xmlPullParser.getName();
                    if (z2 && name2.equals(str)) {
                        xmlPullParser2 = xmlPullParser;
                        z2 = false;
                        str = null;
                    } else {
                        if (name2.equals("group")) {
                            epVar.b = 0;
                            epVar.c = 0;
                            epVar.d = 0;
                            epVar.e = 0;
                            epVar.f = true;
                            epVar.g = true;
                        } else if (name2.equals("item")) {
                            if (!epVar.h) {
                                pi piVar = epVar.z;
                                if (piVar == null || !piVar.b.hasSubMenu()) {
                                    epVar.h = true;
                                    epVar.b(menu2.add(epVar.b, epVar.i, epVar.j, epVar.k));
                                } else {
                                    epVar.h = true;
                                    epVar.b(menu2.addSubMenu(epVar.b, epVar.i, epVar.j, epVar.k).getItem());
                                }
                            }
                        } else if (name2.equals("menu")) {
                            xmlPullParser2 = xmlPullParser;
                            z = true;
                        }
                        xmlPullParser2 = xmlPullParser;
                        z = z;
                    }
                }
                eventType = xmlPullParser2.next();
                i = 2;
                z = z;
                z2 = z2;
            } else {
                if (!z2) {
                    String name3 = xmlPullParser.getName();
                    boolean zEquals = name3.equals("group");
                    Context context = this.c;
                    if (zEquals) {
                        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, fl.p);
                        epVar.b = typedArrayObtainStyledAttributes.getResourceId(1, 0);
                        epVar.c = typedArrayObtainStyledAttributes.getInt(3, 0);
                        epVar.d = typedArrayObtainStyledAttributes.getInt(4, 0);
                        epVar.e = typedArrayObtainStyledAttributes.getInt(5, 0);
                        epVar.f = typedArrayObtainStyledAttributes.getBoolean(2, true);
                        epVar.g = typedArrayObtainStyledAttributes.getBoolean(0, true);
                        typedArrayObtainStyledAttributes.recycle();
                    } else {
                        if (name3.equals("item")) {
                            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, fl.q);
                            epVar.i = typedArrayObtainStyledAttributes2.getResourceId(2, 0);
                            epVar.j = (typedArrayObtainStyledAttributes2.getInt(5, epVar.c) & (-65536)) | (typedArrayObtainStyledAttributes2.getInt(6, epVar.d) & 65535);
                            epVar.k = typedArrayObtainStyledAttributes2.getText(7);
                            epVar.l = typedArrayObtainStyledAttributes2.getText(8);
                            epVar.m = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
                            String string = typedArrayObtainStyledAttributes2.getString(9);
                            epVar.n = string == null ? (char) 0 : string.charAt(0);
                            epVar.o = typedArrayObtainStyledAttributes2.getInt(16, 4096);
                            String string2 = typedArrayObtainStyledAttributes2.getString(10);
                            epVar.p = string2 == null ? (char) 0 : string2.charAt(0);
                            epVar.q = typedArrayObtainStyledAttributes2.getInt(20, 4096);
                            if (typedArrayObtainStyledAttributes2.hasValue(11)) {
                                epVar.r = typedArrayObtainStyledAttributes2.getBoolean(11, false) ? 1 : 0;
                            } else {
                                epVar.r = epVar.e;
                            }
                            epVar.s = typedArrayObtainStyledAttributes2.getBoolean(3, false);
                            epVar.t = typedArrayObtainStyledAttributes2.getBoolean(4, epVar.f);
                            epVar.u = typedArrayObtainStyledAttributes2.getBoolean(1, epVar.g);
                            epVar.v = typedArrayObtainStyledAttributes2.getInt(21, -1);
                            epVar.y = typedArrayObtainStyledAttributes2.getString(12);
                            epVar.w = typedArrayObtainStyledAttributes2.getResourceId(13, 0);
                            epVar.x = typedArrayObtainStyledAttributes2.getString(15);
                            String string3 = typedArrayObtainStyledAttributes2.getString(14);
                            boolean z3 = string3 != null;
                            if (z3 && epVar.w == 0 && epVar.x == null) {
                                epVar.z = (pi) epVar.a(string3, f, this.b);
                            } else {
                                if (z3) {
                                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                                }
                                epVar.z = null;
                            }
                            epVar.A = typedArrayObtainStyledAttributes2.getText(17);
                            epVar.B = typedArrayObtainStyledAttributes2.getText(22);
                            if (typedArrayObtainStyledAttributes2.hasValue(19)) {
                                epVar.D = ea.b(typedArrayObtainStyledAttributes2.getInt(19, -1), epVar.D);
                            } else {
                                epVar.D = null;
                            }
                            if (typedArrayObtainStyledAttributes2.hasValue(18)) {
                                if (!typedArrayObtainStyledAttributes2.hasValue(18) || (resourceId = typedArrayObtainStyledAttributes2.getResourceId(18, 0)) == 0 || (colorStateList = d.u(context, resourceId)) == null) {
                                    colorStateList = typedArrayObtainStyledAttributes2.getColorStateList(18);
                                }
                                epVar.C = colorStateList;
                            } else {
                                epVar.C = null;
                            }
                            typedArrayObtainStyledAttributes2.recycle();
                            epVar.h = false;
                            xmlPullParser2 = xmlPullParser;
                        } else if (name3.equals("menu")) {
                            epVar.h = true;
                            SubMenu subMenuAddSubMenu = menu2.addSubMenu(epVar.b, epVar.i, epVar.j, epVar.k);
                            epVar.b(subMenuAddSubMenu.getItem());
                            xmlPullParser2 = xmlPullParser;
                            b(xmlPullParser2, attributeSet, subMenuAddSubMenu);
                        } else {
                            xmlPullParser2 = xmlPullParser;
                            str = name3;
                            z2 = true;
                        }
                        eventType = xmlPullParser2.next();
                        i = 2;
                        z = z;
                        z2 = z2;
                    }
                }
                xmlPullParser2 = xmlPullParser;
                z = z;
            }
            eventType = xmlPullParser2.next();
            i = 2;
            z = z;
            z2 = z2;
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i, Menu menu) {
        if (!(menu instanceof gi)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        boolean z = false;
        try {
            try {
                layout = this.c.getResources().getLayout(i);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(layout);
                if (menu instanceof gi) {
                    gi giVar = (gi) menu;
                    if (!giVar.p) {
                        giVar.w();
                        z = true;
                    }
                }
                b(layout, attributeSetAsAttributeSet, menu);
                if (z) {
                    ((gi) menu).v();
                }
                layout.close();
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (z) {
                ((gi) menu).v();
            }
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }
}
