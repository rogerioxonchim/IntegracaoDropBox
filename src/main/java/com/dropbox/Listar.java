/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import com.dropbox.config.ConfiguracaoDropBox;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

/**
 *
 * @author Rogerio Xonchim Alves Correa
 */
public class Listar {

    private ConfiguracaoDropBox config;

    public void listar() {
        try {
            config = new ConfiguracaoDropBox();
            DbxClientV2 client = config.configuracao();

            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                }
                if (!result.getHasMore()) {
                    break;
                }
                result = client.files().listFolderContinue(result.getCursor());
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new Listar().listar();
    }
}
