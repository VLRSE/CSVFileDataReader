package DarkThemeComponents;

import otherClasses.DarkThemeColor;

import javax.swing.*;



/**
 *
 * @author Vilrose Daquiado
 *
 */
public class TopBarMenuBar extends JMenu {

        public TopBarMenuBar() {
            setBackground(DarkThemeColor.SECONDARY_BG_COLOR);
            setForeground(DarkThemeColor.PRIMARY_TEXT_COLOR);
        }

        public TopBarMenuBar(String s) {
            super(s);
        }

        public TopBarMenuBar(Action a) {
            super(a);
        }

        public TopBarMenuBar(String s, boolean b) {
            super(s, b);
        }

        private void init(){

            
        }
}
