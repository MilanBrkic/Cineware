/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.projection;

import communcation.Communcation;
import domain.Hall;
import domain.Movie;
import domain.Projection;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.ProjectionTableModel;
import view.panel.mode.Mode;
import view.panel.projection.PanelProjectionAdd;

/**
 *
 * @author user
 */
public class ControllerProjectionAdd {

    private PanelProjectionAdd panel;
    private Mode mode;
    ArrayList<Supplier<JComponent>> componentGetters = new ArrayList<>();

    public ControllerProjectionAdd(PanelProjectionAdd panel, Mode mode) {
        this.panel = panel;
        this.mode = mode;
        fillComponents();
    }

    public void fillComponents() {
        componentGetters.add(() -> panel.getBtnEdit());
        componentGetters.add(() -> panel.getJcmbDay());
        componentGetters.add(() -> panel.getJcmbHour());
        componentGetters.add(() -> panel.getJcmbMinute());
        componentGetters.add(() -> panel.getJcmbHall());
        componentGetters.add(() -> panel.getJcmbMovie());
        componentGetters.add(() -> panel.getJcmbMonth());
        componentGetters.add(() -> panel.getTxtPrice());

    }

    public void openPanel() {
        panel.setVisible(true);
        setExitButton();
        fillForm();
        setUpForm();
        setListeners();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProjectionAdd getPanel() {
        return panel;
    }

    private void setListeners() {
        setAddListener();
        setEnableChangesListener();
        setEditListener();
    }

    private void setAddListener() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Projection projection = getProjectionFromPanel();
                    Communcation.getInstance().addProjection(projection);
                    JOptionPane.showMessageDialog(panel, "Projecation has been added", "Added", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }

        });
    }

    public Projection getProjectionFromPanel() throws Exception {
        Movie movie = (Movie) panel.getJcmbMovie().getSelectedItem();

        String hourString = (String) panel.getJcmbHour().getSelectedItem();
        if (hourString.charAt(0) == '0') {
            hourString = hourString.substring(1);
        }
        int hour = Integer.parseInt(hourString);

        String minuteString = (String) panel.getJcmbMinute().getSelectedItem();
        if (minuteString.charAt(0) == '0') {
            minuteString = minuteString.substring(1);
        }
        int minute = Integer.parseInt(minuteString);

        int month = (int) panel.getJcmbMonth().getSelectedItem();
        int day = (int) panel.getJcmbDay().getSelectedItem();

        Hall hall = (Hall) panel.getJcmbHall().getSelectedItem();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        String dateString = hour + ":" + minute + " " + day + "." + month + "." + new GregorianCalendar().get(GregorianCalendar.YEAR);
        Date startDate = sdf.parse(dateString);

        if (startDate.before(new Date())) {
            throw new Exception("You can not enter a time in past");
        }

        Date endDate = getEndDate(startDate, movie.getRuntime());

        User user = MainCoordinator.getInstance().getUser();

        BigDecimal price;
        try {
            price = new BigDecimal(panel.getTxtPrice().getText());
        } catch (NumberFormatException nfe) {
            throw new Exception("The price is not right");
        }

        Projection projection = new Projection(startDate, endDate, price, hall, movie, user);
        return projection;
    }

    private Date getEndDate(Date startDate, int runtime) {
        GregorianCalendar startGreg = new GregorianCalendar();
        startGreg.setTime(startDate);

        int year = startGreg.get(GregorianCalendar.YEAR);
        int month = startGreg.get(GregorianCalendar.MONTH);
        int day = startGreg.get(GregorianCalendar.DAY_OF_MONTH);
        int hour = startGreg.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = startGreg.get(GregorianCalendar.MINUTE);

        int movieHour = runtime / 60;
        int movieMinute = runtime % 60;

        GregorianCalendar endGreg = new GregorianCalendar(year, month, day, hour + movieHour, minute + movieMinute);
        return endGreg.getTime();
    }

    private void fillForm() {
        for (int i = 0; i <= 24; i++) {
            if (i < 10) {
                panel.getJcmbHour().addItem("0" + i);
            } else {
                panel.getJcmbHour().addItem(i + "");
            }
        }

        panel.getJcmbMinute().addItem("00");
        panel.getJcmbMinute().addItem("15");
        panel.getJcmbMinute().addItem("30");
        panel.getJcmbMinute().addItem("45");

        try {
            ArrayList<Movie> movies;
            movies = Communcation.getInstance().getAllMovies();
            for (Movie movy : movies) {
                panel.getJcmbMovie().addItem(movy);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Could not load movies", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerProjectionAdd.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ArrayList<Hall> halls = Communcation.getInstance().getAllHalls();
            for (Hall hall : halls) {
                panel.getJcmbHall().addItem(hall);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Could not load halls", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerProjectionAdd.class.getName()).log(Level.SEVERE, null, e);
        }

        fillDate();

    }

    private void fillDate() {
        GregorianCalendar greg = new GregorianCalendar();
        int day = greg.get(GregorianCalendar.DAY_OF_MONTH);
        int month = greg.get(GregorianCalendar.MONTH) + 1;

        panel.getJcmbMonth().addItem(month);
        panel.getJcmbMonth().addItem(month + 1);
        firstMonth(month, day);

        panel.getJcmbMonth().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getJcmbMonth().getSelectedIndex();
                if (index >= 0) {
                    if (index == 0) {
                        firstMonth(month, day);
                    } else {
                        secondMonth(day);
                    }
                }
            }
        });

    }

    public void firstMonth(int month, int day) {
        panel.getJcmbDay().removeAllItems();
        int limit = month == 2 ? 28 : ((month == 4 || month == 6 || month == 9 || month == 11) ? 30 : 31);

        for (int i = day; i <= limit; i++) {
            panel.getJcmbDay().addItem(i);
        }
    }

    public void secondMonth(int day) {
        panel.getJcmbDay().removeAllItems();
        for (int i = 1; i <= day; i++) {
            panel.getJcmbDay().addItem(i);
        }
    }

    public void disableAll() {
        for (Supplier<JComponent> componentGetter : componentGetters) {
            componentGetter.get().setEnabled(false);
        }
    }

    public void enableAll() {
        for (Supplier<JComponent> componentGetter : componentGetters) {
            componentGetter.get().setEnabled(true);
        }
    }

    private void setUpForm() {
        switch (mode) {
            case ADD:
                panel.getBtnAdd().setVisible(true);
                panel.getBtnEnableChanges().setVisible(false);
                panel.getBtnEdit().setVisible(false);
                break;
            case EDIT:
                panel.getBtnAdd().setVisible(false);
                panel.getBtnEnableChanges().setVisible(true);
                panel.getBtnEdit().setVisible(true);
                panel.getBtnEnableChanges().setEnabled(true);
                disableAll();
                fillProjectionDetails();
                break;
        }
    }

    private void fillProjectionDetails() {
        Projection projection = (Projection) MainCoordinator.getInstance().getParams().get(Constant.PROJECTION_DETAILS);
        Movie movie = projection.getMovie();
        panel.getJcmbMovie().setSelectedItem(movie);

        Hall hall = projection.getHall();
        panel.getJcmbHall().setSelectedIndex(hall.getId() - 1);

        BigDecimal price = projection.getPrice();
        panel.getTxtPrice().setText(price.toString());

        Date date = projection.getStartDate();

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        int month = Integer.parseInt(sdf.format(date));
        panel.getJcmbMonth().setSelectedItem(month);

        sdf = new SimpleDateFormat("dd");
        String dayStr = sdf.format(date);
        if (dayStr.charAt(0) == '0') {
            dayStr = dayStr.charAt(1) + "";
        }
        System.out.println(dayStr);
        int day = Integer.parseInt(dayStr);
        panel.getJcmbDay().setSelectedItem(day);

        sdf = new SimpleDateFormat("HH");
        String hour = sdf.format(date);
        panel.getJcmbHour().setSelectedItem(hour);

        sdf = new SimpleDateFormat("mm");
        String minute = sdf.format(date);
        panel.getJcmbMinute().setSelectedItem(minute);

    }

    private void setEnableChangesListener() {
        panel.getBtnEnableChanges().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.getBtnEnableChanges().setEnabled(false);
                enableAll();
            }
        });
    }

    private void setEditListener() {
        panel.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Projection projection  = getProjectionFromPanel();
                    int id = ((Projection)MainCoordinator.getInstance().getParams().get(Constant.PROJECTION_DETAILS)).getId();
                    projection.setId(id);
                    Communcation.getInstance().updateProjection(projection);
                    JOptionPane.showMessageDialog(panel, "Projecation has been updated", "Update", JOptionPane.INFORMATION_MESSAGE);
                    ProjectionTableModel model = (ProjectionTableModel) MainCoordinator.getInstance().getParams().get(Constant.PROJECTION_TABLE_MODEL);
                    model.refresh();
                    panel.getExitButton1().doClick();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

}
