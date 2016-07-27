package haven.plugins;

import haven.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StallSellerPlugin extends Plugin{
    public void load(UI ui)
    {
        Glob glob = ui.sess.glob;
        Collection<Glob.Pagina> p = glob.paginae;
        p.add(glob.paginafor(Resource.load("paginae/add/stallseller")));
        XTendedPaginae.registerPlugin("stallseller",this);
    }
    
    public void execute(UI ui){
        ui.message("[StallSeller] Selling items to stall. This may take a while.", GameUI.MsgType.INFO);
        
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
        }
        
        List<WItem> ws = new ArrayList<WItem>();
        for (Widget wdg = UI.instance.gui.maininv.lchild; wdg != null; wdg = wdg.prev) {
            if (wdg.visible && wdg instanceof WItem) {
                ws.add(((WItem) wdg));
            }
        }
        for(WItem w : ws)
        {
            w.mousedown(Coord.z, 1);
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
            }
        }
        
        ui.message("[StallSeller] Finished selling items!", GameUI.MsgType.INFO);
    }
}
