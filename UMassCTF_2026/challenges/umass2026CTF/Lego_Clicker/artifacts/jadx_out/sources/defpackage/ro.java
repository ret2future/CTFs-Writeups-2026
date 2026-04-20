package defpackage;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ro extends pq {
    public static final x5 c = new x5(3);
    public static final x5 d = new x5(4);
    public static final x5 e = new x5(1);
    public final /* synthetic */ int a;
    public final Object b;

    public ro(int i) {
        this.a = i;
        switch (i) {
            case 1:
                this.b = new SimpleDateFormat("hh:mm:ss a");
                break;
            case 2:
                ArrayList arrayList = new ArrayList();
                this.b = arrayList;
                Locale locale = Locale.US;
                arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
                if (!Locale.getDefault().equals(locale)) {
                    arrayList.add(DateFormat.getDateTimeInstance(2, 2));
                }
                if (kf.a >= 9) {
                    arrayList.add(new SimpleDateFormat("MMM d, yyyy h:mm:ss a", locale));
                }
                break;
            default:
                this.b = new SimpleDateFormat("MMM d, yyyy");
                break;
        }
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        Date date;
        Time time;
        Date dateB;
        switch (this.a) {
            case 0:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT = sfVar.t();
                try {
                    synchronized (this) {
                        date = ((SimpleDateFormat) this.b).parse(strT);
                        break;
                    }
                    return new java.sql.Date(date.getTime());
                } catch (ParseException e2) {
                    z6.b(strT, "' as SQL Date; at path ", sfVar.h(true), e2);
                    return null;
                }
            case 1:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT2 = sfVar.t();
                try {
                    synchronized (this) {
                        time = new Time(((SimpleDateFormat) this.b).parse(strT2).getTime());
                        break;
                    }
                    return time;
                } catch (ParseException e3) {
                    z6.b(strT2, "' as SQL Time; at path ", sfVar.h(true), e3);
                    return null;
                }
            default:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT3 = sfVar.t();
                synchronized (((ArrayList) this.b)) {
                    try {
                        ArrayList arrayList = (ArrayList) this.b;
                        int size = arrayList.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                try {
                                    dateB = ze.b(strT3, new ParsePosition(0));
                                } catch (ParseException e4) {
                                    z6.b(strT3, "' as Date; at path ", sfVar.h(true), e4);
                                    return null;
                                }
                                break;
                            } else {
                                Object obj = arrayList.get(i);
                                i++;
                                try {
                                    dateB = ((DateFormat) obj).parse(strT3);
                                } catch (ParseException unused) {
                                }
                            }
                        }
                    } finally {
                    }
                }
                return dateB;
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        String str;
        String str2;
        String str3;
        switch (this.a) {
            case 0:
                java.sql.Date date = (java.sql.Date) obj;
                if (date == null) {
                    tfVar.i();
                    return;
                }
                synchronized (this) {
                    str = ((SimpleDateFormat) this.b).format((Date) date);
                    break;
                }
                tfVar.o(str);
                return;
            case 1:
                Time time = (Time) obj;
                if (time == null) {
                    tfVar.i();
                    return;
                }
                synchronized (this) {
                    str2 = ((SimpleDateFormat) this.b).format((Date) time);
                    break;
                }
                tfVar.o(str2);
                return;
            default:
                Date date2 = (Date) obj;
                if (date2 == null) {
                    tfVar.i();
                    return;
                }
                DateFormat dateFormat = (DateFormat) ((ArrayList) this.b).get(0);
                synchronized (((ArrayList) this.b)) {
                    str3 = dateFormat.format(date2);
                    break;
                }
                tfVar.o(str3);
                return;
        }
    }
}
