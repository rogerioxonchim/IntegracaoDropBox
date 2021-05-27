/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox.config;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Rogerio Xonchim Alves Correa
 */
public class ConfiguracaoDropBox {

    private static final Logger logger = LogManager.getLogger(ConfiguracaoDropBox.class);

    private static final String ACCESS_TOKEN = "x6zR7TtKf28AAAAAAAAAAWQIyvdZpoEcA8GqyDI0iegEo8jIo0BsYetB6GJXTdYN";

    public DbxClientV2 configuracao() {
        try {
            DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            // Get current account info
            FullAccount account = client.users().getCurrentAccount();
            System.out.println("Usuario: " + account.getName().getDisplayName());
            return client;
        } catch (DbxException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        new ConfiguracaoDropBox().configuracao();
    }
}
