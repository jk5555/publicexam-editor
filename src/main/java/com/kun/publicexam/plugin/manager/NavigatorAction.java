package com.kun.publicexam.plugin.manager;

import com.kun.publicexam.plugin.model.Find;
import com.kun.publicexam.plugin.model.PageInfo;
import com.kun.publicexam.plugin.model.Sort;
import com.kun.publicexam.plugin.model.Tag;
import com.kun.publicexam.plugin.window.NavigatorTableData;

import javax.swing.*;

/**
 * @author shuzijun
 */
public interface NavigatorAction<T> {

    void updateUI();

    JPanel queryPanel();

    boolean selectedRow(String slug);

    void findClear();

    void findChange(String filterKey, boolean b, Tag tag);

    Find getFind();

    void sort(Sort sort);

    T getSelectedRowData();

    NavigatorTableData.PagePanel getPagePanel();

    PageInfo<T> getPageInfo();

    void loadData(String slug);

    void loadServiceData();

    void resetServiceData();

    boolean position(String slug);

    public static class Adapter<T> implements NavigatorAction {


        @Override
        public JPanel queryPanel() {
            return null;
        }

        @Override
        public boolean selectedRow(String slug) {
            return false;
        }

        @Override
        public void findClear() {

        }

        @Override
        public void updateUI() {

        }

        @Override
        public void findChange(String filterKey, boolean b, Tag tag) {

        }

        @Override
        public Find getFind() {
            return null;
        }

        @Override
        public void sort(Sort sort) {

        }

        @Override
        public T getSelectedRowData() {
            return null;
        }

        @Override
        public NavigatorTableData.PagePanel getPagePanel() {
            return null;
        }

        @Override
        public PageInfo<T> getPageInfo() {
            return null;
        }

        @Override
        public void loadData(String slug) {

        }

        @Override
        public void loadServiceData() {

        }

        @Override
        public void resetServiceData() {

        }

        @Override
        public boolean position(String slug) {
            return false;
        }
    }
}
