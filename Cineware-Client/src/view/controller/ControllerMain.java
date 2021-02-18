/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import view.util.MyClock;
import view.coordinator.MainCoordinator;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.text.WrappedPlainView;
import view.FormMain;
import view.panel.mode.Mode;
import view.util.IconSetter;

/**
 *
 * @author Milan
 */
public class ControllerMain {

    private IconSetter icon;
    private FormMain form;
    private JMenuItem menuItemLogOut;
    private MyClock clock;
    private ArrayList<Supplier<JMenuItem>> jMenuItemsAdd = new ArrayList<>();
    private ArrayList<Supplier<JMenuItem>> jMenuItemsView = new ArrayList<>();

    public ControllerMain(FormMain form) {
        this.form = form;
        icon = new IconSetter(form);
        clock = new MyClock(form.getLblTime());
        addJMenuItems();
    }

    public void addJMenuItems() {
        jMenuItemsAdd.add(() -> form.getMenuItemActorAdd());
        jMenuItemsAdd.add(() -> form.getMenuItemDirectorAdd());
        jMenuItemsAdd.add(() -> form.getMenuItemMovieAdd());
        jMenuItemsAdd.add(() -> form.getMenuItemUserAdd());
        jMenuItemsAdd.add(() -> form.getMenuItemProjectionAdd());
        jMenuItemsAdd.add(() -> form.getMenuItemProductAdd());
        jMenuItemsAdd.add(() -> form.getMenuItemInvoiceAdd());

        jMenuItemsView.add(() -> form.getMenuItemUserView());
        jMenuItemsView.add(() -> form.getMenuItemActorView());
        jMenuItemsView.add(() -> form.getMenuItemDirectorView());
        jMenuItemsView.add(() -> form.getMenuItemMovieView());
        jMenuItemsView.add(() -> form.getMenuItemHallView());
        jMenuItemsView.add(() -> form.getMenuItemProjectionView());
        jMenuItemsAdd.add(() -> form.getMenuItemProductView());
        jMenuItemsAdd.add(() -> form.getMenuItemInvoiceView());
    }

    public void setIcon() {
        icon.setIcon();
    }

    public FormMain getForm() {
        return form;
    }

    public void openForm() {
        setSizeAndLocation();
        startClock();
        setIcon();
        setMenuBar();
        setStatusBar();
        setListeners();

        form.getLblWelcomeUser().setText("Welcome: " + MainCoordinator.getInstance().getUser());
        form.setVisible(true);
    }

    public void startClock() {
        clock.start();
    }

    public void setMenuBar() {
        form.getJMenuBar().setOpaque(false);
        if (!MainCoordinator.getInstance().getUser().isAdmin()) {
            for (Supplier<JMenuItem> jmenu : jMenuItemsAdd) {
                jmenu.get().setEnabled(false);
            }
        }
        addMenuAccount();
    }

    private void addMenuAccount() {
        JMenu menu = new JMenu("Account");
        form.returnMenuBar().add(Box.createHorizontalGlue());
        form.returnMenuBar().add(menu);
        menuItemLogOut = new JMenuItem("Log-out");
        menu.add(menuItemLogOut);
    }

    public void setSizeAndLocation() {
        form.setMinimumSize(new Dimension(600, 400));
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLocationRelativeTo(null);
    }

    public void setStatusBar() {
        form.getLblStatusBarUser().setText("User: " + MainCoordinator.getInstance().getUser());
    }

    public void setIcon(IconSetter icon) {
        this.icon = icon;
    }

    private void setListeners() {
        setLogoutListener();
        setUserListeners();
        setHallListeners();
        setDirectorListeners();
        setMovieListeners();
        setActorListeners();
        setProjectionListeners();
        setProductListeners();
        setInoviceListeners();
        setWindowListener();
    }

    public void setLogoutListener() {
        menuItemLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainCoordinator.getInstance().logout();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setUserListeners() {
        form.getMenuItemUserAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(() -> form.getMenuItemUserAdd(),
                        x -> MainCoordinator.getInstance().openPanelUserAdd((Mode) x),
                        Mode.ADD);
            }
        });

        form.getMenuItemUserView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemUserView(),
                        () -> MainCoordinator.getInstance().openPanelUserView());
            }
        });
    }

    private void setHallListeners() {
        form.getMenuItemHallView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemHallView(),
                        () -> MainCoordinator.getInstance().openPanelHallView());
            }
        });
    }

    private void setDirectorListeners() {
        form.getMenuItemDirectorAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(() -> form.getMenuItemDirectorAdd(),
                        x -> MainCoordinator.getInstance().openPanelDirectorAdd((Mode) x),
                        Mode.ADD);
            }
        });
        form.getMenuItemDirectorView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemDirectorView(),
                        () -> MainCoordinator.getInstance().openPanelDirectorView());
            }
        });
    }

    private void setActorListeners() {
        form.getMenuItemActorAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(() -> form.getMenuItemActorAdd(),
                        x -> MainCoordinator.getInstance().openPanelActorAdd((Mode) x),
                        Mode.ADD);
            }
        });
        form.getMenuItemActorView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemActorView(),
                        () -> MainCoordinator.getInstance().openPanelActorView());
            }
        });
    }

    private void setMovieListeners() {
        form.getMenuItemMovieAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(() -> form.getMenuItemMovieAdd(),
                        x -> MainCoordinator.getInstance().openPanelMovieAdd((Mode) x),
                        Mode.ADD);

            }
        });

        form.getMenuItemMovieView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemMovieView(),
                        () -> MainCoordinator.getInstance().openPanelMovieView());
            }
        });
    }

    private void setProjectionListeners() {
        form.getMenuItemProjectionAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(()-> form.getMenuItemProjectionAdd(),
                        x -> MainCoordinator.getInstance().openPanelProjectionAdd((Mode)x),
                        Mode.ADD);
            }
        });
        
        
        form.getMenuItemProjectionView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemProjectionView(),
                        () -> MainCoordinator.getInstance().openPanelProjectionView());
            }
        });
    }
    
    private void setProductListeners() {
        form.getMenuItemProductAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(()-> form.getMenuItemProductAdd(),
                        x -> MainCoordinator.getInstance().openPanelProductAdd((Mode)x),
                        Mode.ADD);
            }
        });
        
        form.getMenuItemProductView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               templateForView(() -> form.getMenuItemProductView(),
                        () -> MainCoordinator.getInstance().openPanelProductView());
            }
        });
    }
    
    private void setInoviceListeners() {
        form.getMenuItemInvoiceAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForAdd(()-> form.getMenuItemInvoiceAdd(),
                        x -> MainCoordinator.getInstance().openPanelInvoiceAdd((Mode)x),
                        Mode.ADD);
            }
        });
        
        form.getMenuItemInvoiceView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateForView(() -> form.getMenuItemInvoiceView(),
                        () -> MainCoordinator.getInstance().openPanelInvoiceView());
            }
        });
    }
    

    public void templateForView(Supplier<JMenuItem> menuItemView, Runnable openPanel) {
        MainCoordinator.getInstance().removeAllPanels();
        enableAll();
        menuItemView.get().setEnabled(false);
        form.getLblWelcomeUser().setVisible(false);
        openPanel.run();
    }

    public void templateForAdd(Supplier<JMenuItem> menuItemAdd, Consumer<Mode> openPanel, Mode mode) {
        MainCoordinator.getInstance().removeAllPanels();
        enableAll();
        menuItemAdd.get().setEnabled(false);
        form.getLblWelcomeUser().setVisible(false);
        openPanel.accept(mode);
    }

    public void enableAll() {
        if (MainCoordinator.getInstance().getUser().isAdmin()) {
            for (Supplier<JMenuItem> jmenu : jMenuItemsAdd) {
                jmenu.get().setEnabled(true);
            }
        }

        for (Supplier<JMenuItem> jmenu : jMenuItemsView) {
            jmenu.get().setEnabled(true);
        }

    }

    public MyClock getClock() {
        return clock;
    }

    private void setWindowListener() {
        form.addWindowListener(new WindowListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Communcation.getInstance().logout(MainCoordinator.getInstance().getUser());
                    Communcation.getInstance().closeSocket();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }
        });
    }

    

    

}
