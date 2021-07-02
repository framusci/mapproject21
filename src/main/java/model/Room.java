package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author franc
 */
class Room {

    private int id;
    private CircularList<RoomPanel> panels;

    public Room() {
        this.panels = new CircularArrayList();
    }

    public void addPanel(RoomPanel rp) {
        panels.add(rp);
    }

    public void removePanel(int index) {
        panels.remove(index);
    }

    public RoomPanel nextPanel() {
        return panels.next();
    }

    public RoomPanel previousPanel() {
        return panels.previous();
    }

    public RoomPanel currentPanel() {
        return panels.current();
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return panels.current().getImage();
    }

    public Room getForward() {
        for (int i = 0; i < (panels.size() / 2); i++) {
            panels.next();
        }

        return panels.current().getNextRoom();
    }
}
