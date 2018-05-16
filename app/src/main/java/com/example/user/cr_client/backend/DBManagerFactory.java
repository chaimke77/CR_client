package com.example.user.cr_client.backend;

/**
 * Created by User on 12/04/2018.
 */


import com.example.user.cr_client.datasource.MySQL_DBManager;

public class DBManagerFactory {
    static DB_manager manager = null;

    public static DB_manager getManager() {
        if (manager == null)
            manager = new MySQL_DBManager();
        return manager;
    }
}
