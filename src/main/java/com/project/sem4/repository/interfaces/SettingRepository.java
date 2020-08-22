package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Popup;

import java.util.List;

public interface SettingRepository {
    public List<Popup> getAllPopup();
    public Boolean insertPopup(Popup popup);
    public Popup findPopupById(Integer id);
    public Boolean updatePopup(Popup popup);
    public Boolean deletePopup(Integer id);
}
