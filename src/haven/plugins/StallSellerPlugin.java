package haven.plugins;

import haven.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StallSellerPlugin extends Plugin {

    public void load(UI ui) {
        Glob glob = ui.sess.glob;
        Collection<Glob.Pagina> p = glob.paginae;
        p.add(glob.paginafor(Resource.load("paginae/add/stallseller")));
        XTendedPaginae.registerPlugin("stallseller", this);
    }

    public void execute(final UI ui) {
        ui.message("[StallSeller] Selling items to stall. Please wait until further notice.", GameUI.MsgType.INFO);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WItem> items = ui.gui.maininv.getSameName("", true);
                for (int i = items.size() - 1; i < items.size() && i >= 0; i--) {

                    Resource curs = ui.root.cursor;
                    if (!curs.name.contains("kreuz")) {
                        ui.message("[StallSeller] You are not selling to a stall! Aborting.", GameUI.MsgType.BAD);
                        return;
                    }

                    WItem subject = items.get(i);
                    subject.mousedown(Coord.z, 1);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {
                    }

                    items = ui.gui.maininv.getSameName("", true);
                }
                ui.message("[StallSeller] Stopped selling items.", GameUI.MsgType.INFO);
            }
        },
                 "Stall seller").start();

    }
}
